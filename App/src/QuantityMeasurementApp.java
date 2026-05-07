public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2 +
                " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        System.out.println(quantity +
                " converted to " +
                targetUnit + " -> " +
                quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        System.out.println(q1 + " + " + q2 +
                " -> " +
                q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

        // LENGTH OPERATIONS

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(
                length1,
                LengthUnit.INCHES);

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET);

        // WEIGHT OPERATIONS

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM);

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM);

        // CROSS CATEGORY CHECK

        System.out.println(
                length1.equals(weight1));

        System.out.println(
                "\n===== VOLUME OPERATIONS =====");

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0,
                        VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        demonstrateEquality(volume1, volume2);

        demonstrateConversion(
                volume1,
                VolumeUnit.MILLILITRE);

        demonstrateConversion(
                volume3,
                VolumeUnit.LITRE);

        demonstrateAddition(
                volume1,
                volume2,
                VolumeUnit.LITRE);

        demonstrateAddition(
                volume1,
                volume3,
                VolumeUnit.MILLILITRE);

        System.out.println(
                "\n===== CROSS CATEGORY CHECK =====");

        System.out.println(
                volume1.equals(
                        new Quantity<>(
                                1.0,
                                LengthUnit.FEET)));


    }
}