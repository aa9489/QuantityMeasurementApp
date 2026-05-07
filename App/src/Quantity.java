package com.bridgelabz.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundToTwoDecimals(unit.convertToBaseUnit(value)));
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(
                roundToTwoDecimals(convertedValue),
                targetUnit
        );
    }

    // =========================
    // UC11 ADDITION
    // =========================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit
        );
    }

    // =========================
    // UC12 SUBTRACTION
    // =========================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit
        );
    }

    // =========================
    // UC12 DIVISION
    // =========================

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // =========================
    // UC13 REFACTOR
    // =========================

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException("Cannot divide by zero");
            }

            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
    ) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (other.unit == null) {
            throw new IllegalArgumentException("Other unit cannot be null");
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException(
                    "Cannot perform arithmetic on different measurement categories"
            );
        }

        if (Double.isNaN(this.value) || Double.isInfinite(this.value)
                || Double.isNaN(other.value) || Double.isInfinite(other.value)) {

            throw new IllegalArgumentException(
                    "Values must be finite numbers"
            );
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBaseValue =
                this.unit.convertToBaseUnit(this.value);

        double otherBaseValue =
                other.unit.convertToBaseUnit(other.value);

        return operation.compute(thisBaseValue, otherBaseValue);
    }

    private double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}