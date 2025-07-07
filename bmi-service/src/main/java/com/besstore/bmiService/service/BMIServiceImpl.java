package com.besstore.bmiService.service;

import com.besstore.bmiService.bmiInterprete.BmiInterpreterFactory;
import com.besstore.bmiService.bmiInterprete.InterpreterBMI;
import com.besstore.bmiService.constatnts.BMICategory;
import com.besstore.bmiService.dto.BmiRequest;
import com.besstore.bmiService.dto.BmiResponse;
import com.besstore.bmiService.model.Item;
import com.besstore.bmiService.utils.BmiCalculator;
import com.besstore.bmiService.utils.CaloriesBMI;
import com.besstore.bmiService.utils.HealthRiskCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * BMIServiceImpl here all computations are called.
 * It calculates the Body Mass Index (BMI) based on the provided request,
 * interprets the BMI category, and provides health recommendations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BMIServiceImpl implements BMIService {

    /**
     * The Groq is used to get recommendations based on the BMI category.
     */
    private final OpenAiChatModel chatModel;

    /**
     * Calculates the Body Mass Index (BMI) based on the provided request.
     *
     * @param bmiRequest the request containing weight, age, gender and height
     * @return a response containing the calculated BMI, category, health risk, recommendations, our products
     */
    @Override
    public BmiResponse calculateBmi(BmiRequest bmiRequest) {

        double bmi = BmiCalculator.calculate(bmiRequest.weightKg(), bmiRequest.heightCm());

        InterpreterBMI interpreter = BmiInterpreterFactory
                .getInterpreter(bmiRequest.gender(), bmiRequest.age());


        BMICategory bmiCategory = interpreter.interpret(bmi);
        log.info("BMI category: {}", bmiCategory);
        List<String> recommendations = getRecommendations(bmiCategory);
        log.info("Ai recommendations done: {}", recommendations);

        String healthRisk = HealthRiskCalculator.calculateHealthRisk(bmiCategory);
        log.info("Health conditions: {}", healthRisk);

        int calories = CaloriesBMI.getCaloriesByBMI(bmiCategory);

//        List<Item> recItems =

        return new BmiResponse(bmi, bmiCategory, healthRisk, recommendations,null);
    }


    /**
     * Gets food recommendations by AI based on the BMI category.
     *
     * @param category the BMI category
     * @return a list of recommended foods
     */
    private List<String> getRecommendations(BMICategory category) {
        String prompt = switch (category) {
            case UNDERWEIGHT -> "Suggest 5 high-calorie, healthy foods for a person who is underweight.(Short response)";
            case NORMAL_WEIGHT -> "Suggest 5 balanced, healthy foods for someone with normal BMI.(Short response)";
            case OVERWEIGHT -> "Suggest 5 light and healthy foods for someone who is overweight.(Short response)";
            case OBESE -> "Suggest 5 vegetables or low-calorie foods for someone who is obese.(Short response)";
        };

        String result = chatModel.call(prompt);

        return parseList(result);
    }

    /**
     * Parses the AI response into a list of recommendations.
     * "1. ....
     *  2. ...."
     * to
     * {"...","..."}
     *
     * @param response the AI response string
     * @return a list of recommendations
     */
    private List<String> parseList(String response) {
        return Arrays.stream(response.split("\n"))
                .map(line -> line.replaceAll("^\\d+[.)]\\s*", "").trim())
                .filter(line -> !line.isBlank())
                .toList();
    }
}