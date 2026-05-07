public class UC8 {

    public static void main(String[] args) {

        QuantityLength length1 =
                new QuantityLength(1.0,
                        LengthUnit.FEET);

        QuantityLength length2 =
                new QuantityLength(12.0,
                        LengthUnit.INCHES);

        // Equality

        System.out.println(
                "1 foot equals 12 inches : "
                        + length1.equals(length2)
        );

        // Conversion

        QuantityLength converted =
                length1.convertTo(
                        LengthUnit.INCHES);

        System.out.println(
                "1 foot in inches : "
                        + converted
        );

        // Addition

        QuantityLength sum =
                length1.add(length2);

        System.out.println(
                "1 foot + 12 inches : "
                        + sum
        );

        // Addition with target unit

        QuantityLength sum2 =
                length1.add(
                        length2,
                        LengthUnit.YARDS);

        System.out.println(
                "1 foot + 12 inches in yards : "
                        + sum2
        );
    }
}