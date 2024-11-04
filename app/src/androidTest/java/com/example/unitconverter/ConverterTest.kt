package com.example.unitconverter

import org.junit.Test
import org.junit.Assert.assertEquals

class ConverterTest {

    /**
     * Test Celsius to Fahrenheit conversion.
     */
    @Test
    fun testCelsiusToFahrenheit() {
        // 0°C should be 32°F
        assertEquals(32.0, Converter.convert(0.0, "Celsius", "Fahrenheit"), 0.001)
    }

    /**
     * Test Fahrenheit to Celsius conversion.
     */
    @Test
    fun testFahrenheitToCelsius() {
        // 32°F should be 0°C
        assertEquals(0.0, Converter.convert(32.0, "Fahrenheit", "Celsius"), 0.001)
    }

    /**
     * Test Meters to Feet conversion.
     */
    @Test
    fun testMetersToFeet() {
        // 1 meter should be approximately 3.28084 feet
        assertEquals(3.28084, Converter.convert(1.0, "Meters", "Feet"), 0.001)
    }

    /**
     * Test Feet to Meters conversion.
     */
    @Test
    fun testFeetToMeters() {
        // 3.28084 feet should be approximately 1 meter
        assertEquals(1.0, Converter.convert(3.28084, "Feet", "Meters"), 0.001)
    }

    /**
     * Test Kilograms to Pounds conversion.
     */
    @Test
    fun testKilogramsToPounds() {
        // 1 kilogram should be approximately 2.20462 pounds
        assertEquals(2.20462, Converter.convert(1.0, "Kilograms", "Pounds"), 0.001)
    }

    /**
     * Test Pounds to Kilograms conversion.
     */
    @Test
    fun testPoundsToKilograms() {
        // 2.20462 pounds should be approximately 1 kilogram
        assertEquals(1.0, Converter.convert(2.20462, "Pounds", "Kilograms"), 0.001)
    }

    /**
     * Test for unsupported conversion case.
     */
    @Test(expected = IllegalArgumentException::class)
    fun testUnsupportedConversion() {
        // Attempting an unsupported conversion should throw an error
        Converter.convert(1.0, "Liters", "Gallons")
    }
}