enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}

public class UC8 {

    static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Invalid unit");
            }

            this.value = value;
            this.unit = unit;
        }

        public Quantity convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Invalid target unit");
            }

            double baseValue = unit.convertToBaseUnit(value);

            double convertedValue =
                    targetUnit.convertFromBaseUnit(baseValue);

            return new Quantity(convertedValue, targetUnit);
        }

        public Quantity add(Quantity other,
                            LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Invalid target unit");
            }

            double firstValue =
                    this.unit.convertToBaseUnit(this.value);

            double secondValue =
                    other.unit.convertToBaseUnit(other.value);

            double total = firstValue + secondValue;

            double result =
                    targetUnit.convertFromBaseUnit(total);

            return new Quantity(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Quantity other = (Quantity) obj;

            double firstValue =
                    this.unit.convertToBaseUnit(this.value);

            double secondValue =
                    other.unit.convertToBaseUnit(other.value);

            return Double.compare(firstValue, secondValue) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);

        System.out.println(
                "Input: " + q1 + ".convertTo(INCHES)"
        );
        System.out.println(
                "Output: " + q1.convertTo(LengthUnit.INCHES)
        );

        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println(
                "Input: " + q1 + ".add(" + q2 + ", FEET)"
        );
        System.out.println(
                "Output: " + q1.add(q2, LengthUnit.FEET)
        );

        Quantity q3 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity q4 = new Quantity(1.0, LengthUnit.YARDS);

        System.out.println(
                "Input: " + q3 + ".equals(" + q4 + ")"
        );
        System.out.println(
                "Output: " + q3.equals(q4)
        );

        Quantity q5 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q6 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println(
                "Input: " + q5 + ".add(" + q6 + ", YARDS)"
        );
        System.out.println(
                "Output: " + q5.add(q6, LengthUnit.YARDS)
        );

        Quantity q7 = new Quantity(2.54, LengthUnit.CENTIMETERS);

        System.out.println(
                "Input: " + q7 + ".convertTo(INCHES)"
        );
        System.out.println(
                "Output: " + q7.convertTo(LengthUnit.INCHES)
        );

        Quantity q8 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q9 = new Quantity(0.0, LengthUnit.INCHES);

        System.out.println(
                "Input: " + q8 + ".add(" + q9 + ", FEET)"
        );
        System.out.println(
                "Output: " + q8.add(q9, LengthUnit.FEET)
        );

        System.out.println(
                "Input: LengthUnit.FEET.convertToBaseUnit(12.0)"
        );
        System.out.println(
                "Output: " +
                        LengthUnit.FEET.convertToBaseUnit(12.0)
        );

        System.out.println(
                "Input: LengthUnit.INCHES.convertToBaseUnit(12.0)"
        );
        System.out.println(
                "Output: " +
                        LengthUnit.INCHES.convertToBaseUnit(12.0)
        );
    }
}