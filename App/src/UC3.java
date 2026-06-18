public class UC3 {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
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

            QuantityLength other = (QuantityLength) obj;

            double firstValue = unit.toFeet(value);
            double secondValue = other.unit.toFeet(other.value);

            return Double.compare(firstValue, secondValue) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
        }
    }

    public static void main(String[] args) {
        QuantityLength value1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength value2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Input: " + value1 + " and " + value2);
        System.out.println("Output: Equal (" + value1.equals(value2) + ")");

        QuantityLength value3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength value4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Input: " + value3 + " and " + value4);
        System.out.println("Output: Equal (" + value3.equals(value4) + ")");
    }
}