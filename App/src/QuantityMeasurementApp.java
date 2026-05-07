package com.bridgelabz.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // =========================
        // LENGTH
        // =========================

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCH);

        demonstrateAddition(feet, inches, LengthUnit.FEET);
        demonstrateSubtraction(feet, inches, LengthUnit.FEET);
        demonstrateDivision(feet, inches);

        // =========================
        // WEIGHT
        // =========================

        Quantity<WeightUnit> kg =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);
        demonstrateSubtraction(kg, gram, WeightUnit.KILOGRAM);
        demonstrateDivision(kg, gram);

        // =========================
        // VOLUME
        // =========================

        Quantity<VolumeUnit> litre =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateAddition(litre, milli, VolumeUnit.LITRE);
        demonstrateSubtraction(litre, milli, VolumeUnit.LITRE);
        demonstrateDivision(litre, milli);
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println("Addition:");
        System.out.println(q1 + " + " + q2 + " = " + result);
        System.out.println();
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        System.out.println("Subtraction:");
        System.out.println(q1 + " - " + q2 + " = " + result);
        System.out.println();
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1,
            Quantity<U> q2
    ) {

        double result = q1.divide(q2);

        System.out.println("Division:");
        System.out.println(q1 + " / " + q2 + " = " + result);
        System.out.println();
    }
}