public class UC7 {

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

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private static double toBaseUnit(Quantity quantity) {
            return quantity.value * quantity.unit.getConversionFactor();
        }

        private static Quantity createResult(double baseValue,
                                             LengthUnit targetUnit) {

            double convertedValue =
                    baseValue / targetUnit.getConversionFactor();

            return new Quantity(convertedValue, targetUnit);
        }

        public Quantity add(Quantity other) {

            if (other == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }

            double total =
                    toBaseUnit(this) + toBaseUnit(other);

            return createResult(total, this.unit);
        }

        public Quantity add(Quantity other,
                            LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Invalid target unit");
            }

            double total =
                    toBaseUnit(this) + toBaseUnit(other);

            return createResult(total, targetUnit);
        }

        public static Quantity add(Quantity first,
                                   Quantity second,
                                   LengthUnit targetUnit) {

            if (first == null || second == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Invalid target unit");
            }

            double total =
                    toBaseUnit(first) + toBaseUnit(second);

            return createResult(total, targetUnit);
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

            return Double.compare(
                    toBaseUnit(this),
                    toBaseUnit(other)
            ) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + q1 + ", " + q2 + ", FEET)"
        );
        System.out.println(
                "Output: " + q1.add(q2, LengthUnit.FEET)
        );

        System.out.println(
                "Input: add(" + q1 + ", " + q2 + ", INCHES)"
        );
        System.out.println(
                "Output: " + q1.add(q2, LengthUnit.INCHES)
        );

        System.out.println(
                "Input: add(" + q1 + ", " + q2 + ", YARDS)"
        );
        System.out.println(
                "Output: " + q1.add(q2, LengthUnit.YARDS)
        );

        Quantity q3 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q4 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println(
                "Input: add(" + q3 + ", " + q4 + ", YARDS)"
        );
        System.out.println(
                "Output: " + q3.add(q4, LengthUnit.YARDS)
        );

        Quantity q5 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity q6 = new Quantity(1.0, LengthUnit.YARDS);

        System.out.println(
                "Input: add(" + q5 + ", " + q6 + ", FEET)"
        );
        System.out.println(
                "Output: " + q5.add(q6, LengthUnit.FEET)
        );

        Quantity q7 = new Quantity(2.54, LengthUnit.CENTIMETERS);
        Quantity q8 = new Quantity(1.0, LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + q7 + ", " + q8 + ", CENTIMETERS)"
        );
        System.out.println(
                "Output: " + q7.add(q8, LengthUnit.CENTIMETERS)
        );

        Quantity q9 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q10 = new Quantity(0.0, LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + q9 + ", " + q10 + ", YARDS)"
        );
        System.out.println(
                "Output: " + q9.add(q10, LengthUnit.YARDS)
        );

        Quantity q11 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q12 = new Quantity(-2.0, LengthUnit.FEET);

        System.out.println(
                "Input: add(" + q11 + ", " + q12 + ", INCHES)"
        );
        System.out.println(
                "Output: " + q11.add(q12, LengthUnit.INCHES)
        );
    }
}