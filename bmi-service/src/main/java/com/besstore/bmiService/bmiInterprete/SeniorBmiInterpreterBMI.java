package com.besstore.bmiService.bmiInterprete;

import com.besstore.bmiService.constatnts.BMICategory;

/**
 * AdultBmiInterpreterBMI is an implementation of the InterpreterBMI interface
 * that interprets BMI values for seniors according to standard BMI categories.
 * It categorizes the BMI into four categories: Underweight, Normal weight,
 * Overweight, and Obese.
 */
public class SeniorBmiInterpreterBMI implements InterpreterBMI {
    @Override
    public BMICategory interpret(double bmi) {
        if (bmi < 22.0) return BMICategory.UNDERWEIGHT;
        if (bmi < 27.0) return BMICategory.NORMAL_WEIGHT;
        if (bmi < 30.0) return BMICategory.OVERWEIGHT;
        return BMICategory.OBESE;
    }
}
