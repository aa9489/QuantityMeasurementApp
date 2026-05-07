public class UC9 {

    public static void main(String[] args) {

        // Equality Examples

        QuantityWeight weight1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        System.out.println(
                "1 kg equals 1000 g : "
                        + weight1.equals(weight2)
        );

        // Conversion Examples

        QuantityWeight poundWeight =
                new QuantityWeight(2.0,
                        WeightUnit.POUND);

        QuantityWeight converted =
                poundWeight.convertTo(
                        WeightUnit.KILOGRAM);

        System.out.println(
                "2 pounds in kilograms : "
                        + converted
        );

        // Addition Example (default unit)

        QuantityWeight sum1 =
                weight1.add(weight2);

        System.out.println(
                "1 kg + 1000 g : "
                        + sum1
        );

        // Addition Example (explicit target unit)

        QuantityWeight sum2 =
                weight1.add(
                        weight2,
                        WeightUnit.GRAM);

        System.out.println(
                "1 kg + 1000 g in grams : "
                        + sum2
        );

        // Pound + Kilogram

        QuantityWeight w3 =
                new QuantityWeight(1.0,
                        WeightUnit.POUND);

        QuantityWeight w4 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight sum3 =
                w3.add(w4,
                        WeightUnit.POUND);

        System.out.println(
                "1 pound + 1 kilogram in pounds : "
                        + sum3
        );
    }
}