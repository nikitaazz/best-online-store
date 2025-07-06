package com.besstore.bmiService.bmiInterprete;

import com.besstore.bmiService.constatnts.BMICategory;

/**
 * AdultBmiInterpreterBMI is an implementation of the InterpreterBMI interface
 * that interprets BMI values for teen girls according to standard BMI categories.
 * It categorizes the BMI into four categories: Underweight, Normal weight,
 * Overweight, and Obese.
 */
public class TeenGirlBmiInterpreterBMI implements InterpreterBMI {
    @Override
    public BMICategory interpret(double bmi) {
        if (bmi < 17) return BMICategory.UNDERWEIGHT;
        if (bmi < 22.5) return BMICategory.NORMAL_WEIGHT;
        if (bmi < 24.5) return BMICategory.OVERWEIGHT;
        return BMICategory.OBESE;
    }
}
