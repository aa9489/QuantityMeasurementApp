public class UC5 {

    enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Invalid unit");
            }

            this.value = value;
            this.unit = unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue = convert(value, unit, targetUnit);
            return new QuantityLength(convertedValue, targetUnit);
        }

        public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (sourceUnit == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid unit");
            }

            double valueInInches = value * sourceUnit.getConversionFactor();

            return valueInInches / targetUnit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            double firstValue = convert(value, unit, LengthUnit.INCHES);
            double secondValue = convert(other.value, other.unit, LengthUnit.INCHES);

            return Double.compare(firstValue, secondValue) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit fromUnit,
                                                   LengthUnit toUnit) {

        double result = QuantityLength.convert(value, fromUnit, toUnit);

        System.out.println("Input: convert(" + value + ", " + fromUnit + ", " + toUnit + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength length,
                                                   LengthUnit targetUnit) {

        QuantityLength converted = length.convertTo(targetUnit);

        System.out.println("Input: " + length);
        System.out.println("Converted To: " + converted);
    }

    public static void demonstrateLengthEquality(QuantityLength first,
                                                 QuantityLength second) {

        System.out.println(first + " and " + second + " Equal: " + first.equals(second));
    }

    public static void demonstrateLengthComparison(double value1,
                                                   LengthUnit unit1,
                                                   double value2,
                                                   LengthUnit unit2) {

        QuantityLength first = new QuantityLength(value1, unit1);
        QuantityLength second = new QuantityLength(value2, unit2);

        demonstrateLengthEquality(first, second);
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);

        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);

        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        QuantityLength length = new QuantityLength(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(length, LengthUnit.FEET);

        demonstrateLengthComparison(1.0, LengthUnit.YARDS,
                3.0, LengthUnit.FEET);
    }
}