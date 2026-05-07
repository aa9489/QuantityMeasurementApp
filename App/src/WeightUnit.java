public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert current unit to base unit (Kilogram)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert base unit (Kilogram) to current unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}