public class UC4 {

    enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toInches(double value) {
            return value * conversionFactor;
        }
    }

    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
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

            double firstValue = unit.toInches(value);
            double secondValue = other.unit.toInches(other.value);

            return Double.compare(firstValue, secondValue) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Input: " + q1 + " and " + q2);
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        Quantity q3 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q4 = new Quantity(36.0, LengthUnit.INCHES);

        System.out.println("Input: " + q3 + " and " + q4);
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");

        Quantity q5 = new Quantity(2.0, LengthUnit.YARDS);
        Quantity q6 = new Quantity(2.0, LengthUnit.YARDS);

        System.out.println("Input: " + q5 + " and " + q6);
        System.out.println("Output: Equal (" + q5.equals(q6) + ")");

        Quantity q7 = new Quantity(2.0, LengthUnit.CENTIMETERS);
        Quantity q8 = new Quantity(2.0, LengthUnit.CENTIMETERS);

        System.out.println("Input: " + q7 + " and " + q8);
        System.out.println("Output: Equal (" + q7.equals(q8) + ")");

        Quantity q9 = new Quantity(1.0, LengthUnit.CENTIMETERS);
        Quantity q10 = new Quantity(0.393701, LengthUnit.INCHES);

        System.out.println("Input: " + q9 + " and " + q10);
        System.out.println("Output: Equal (" + q9.equals(q10) + ")");
    }
}