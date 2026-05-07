package org.example;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println("Equality:");
        System.out.println(q1 + " == " + q2 + " : " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        System.out.println("Conversion:");
        System.out.println(quantity + " -> "
                + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println("Addition:");
        System.out.println(q1 + " + " + q2 + " = " + result);
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        System.out.println("Subtraction:");
        System.out.println(q1 + " - " + q2 + " = " + result);
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1,
            Quantity<U> q2) {

        double result = q1.divide(q2);

        System.out.println("Division:");
        System.out.println(q1 + " / " + q2 + " = " + result);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(length1, LengthUnit.INCHES);

        demonstrateAddition(length1, length2, LengthUnit.FEET);

        demonstrateSubtraction(length1, length2, LengthUnit.FEET);

        demonstrateDivision(length1, length2);

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM);

        demonstrateSubtraction(weight1, weight2, WeightUnit.KILOGRAM);

        demonstrateDivision(weight1, weight2);

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateAddition(volume1, volume2, VolumeUnit.LITRE);

        demonstrateSubtraction(volume1, volume2, VolumeUnit.LITRE);

        demonstrateDivision(volume1, volume2);
    }
}