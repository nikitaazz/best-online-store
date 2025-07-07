package com.besstore.bmiService.utils;

import com.besstore.bmiService.constatnts.BMICategory;
import lombok.experimental.UtilityClass;


@UtilityClass
public class CaloriesBMI {
    public int getCaloriesByBMI(BMICategory bmiCategory) {
        return switch (bmiCategory) {
            case UNDERWEIGHT -> 2500;
            case NORMAL_WEIGHT -> 2000;
            case OVERWEIGHT -> 1800;
            case OBESE -> 1500;
        };
    }
}
