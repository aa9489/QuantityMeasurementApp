public class UC6 {

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

        public Quantity convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Invalid target unit");
            }

            double valueInInches = value * unit.getConversionFactor();

            double convertedValue =
                    valueInInches / targetUnit.getConversionFactor();

            return new Quantity(convertedValue, targetUnit);
        }

        public Quantity add(Quantity other) {

            if (other == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }

            double firstValue =
                    this.value * this.unit.getConversionFactor();

            double secondValue =
                    other.value * other.unit.getConversionFactor();

            double total = firstValue + secondValue;

            double result =
                    total / this.unit.getConversionFactor();

            return new Quantity(result, this.unit);
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

            double firstValue =
                    first.value * first.unit.getConversionFactor();

            double secondValue =
                    second.value * second.unit.getConversionFactor();

            double total = firstValue + secondValue;

            double result =
                    total / targetUnit.getConversionFactor();

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
                    this.value * this.unit.getConversionFactor();

            double secondValue =
                    other.value * other.unit.getConversionFactor();

            return Double.compare(firstValue, secondValue) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);

        System.out.println("Input: add(" + q1 + ", " + q2 + ")");
        System.out.println("Output: " + q1.add(q2));

        Quantity q3 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q4 = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println("Input: add(" + q3 + ", " + q4 + ")");
        System.out.println("Output: " + q3.add(q4));

        Quantity q5 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity q6 = new Quantity(1.0, LengthUnit.FEET);

        System.out.println("Input: add(" + q5 + ", " + q6 + ")");
        System.out.println("Output: " + q5.add(q6));

        Quantity q7 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q8 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Input: add(" + q7 + ", " + q8 + ")");
        System.out.println("Output: " + q7.add(q8));

        Quantity q9 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity q10 = new Quantity(1.0, LengthUnit.YARDS);

        System.out.println("Input: add(" + q9 + ", " + q10 + ")");
        System.out.println("Output: " + q9.add(q10));

        Quantity q11 = new Quantity(2.54, LengthUnit.CENTIMETERS);
        Quantity q12 = new Quantity(1.0, LengthUnit.INCHES);

        System.out.println("Input: add(" + q11 + ", " + q12 + ")");
        System.out.println("Output: " + q11.add(q12));

        Quantity q13 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q14 = new Quantity(0.0, LengthUnit.INCHES);

        System.out.println("Input: add(" + q13 + ", " + q14 + ")");
        System.out.println("Output: " + q13.add(q14));

        Quantity q15 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q16 = new Quantity(-2.0, LengthUnit.FEET);

        System.out.println("Input: add(" + q15 + ", " + q16 + ")");
        System.out.println("Output: " + q15.add(q16));
    }
}