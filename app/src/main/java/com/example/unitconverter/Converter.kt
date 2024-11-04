package com.example.unitconverter

object Converter {

    /**
     * Converts a given value from one unit to another.
     *
     * @param value The numerical value to convert.
     * @param fromUnit The unit to convert from .
     * @param toUnit The unit to convert to.
     * @return The converted value as a Double.
     */
    fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit to toUnit) {
            // Temperature conversions
            "Celsius" to "Fahrenheit" -> (value * 9 / 5) + 32
            "Fahrenheit" to "Celsius" -> (value - 32) * 5 / 9

            // Length conversions
            "Meters" to "Feet" -> value * 3.28084
            "Feet" to "Meters" -> value / 3.28084

            // Weight conversions
            "Kilograms" to "Pounds" -> value * 2.20462
            "Pounds" to "Kilograms" -> value / 2.20462

            // If conversion is unsupported, throw an error
            else -> throw IllegalArgumentException("Unsupported conversion from $fromUnit to $toUnit")
        }
    }
}
