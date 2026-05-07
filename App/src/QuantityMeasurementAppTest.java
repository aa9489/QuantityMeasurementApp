package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(
                new Quantity<>(5.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(
                new Quantity<>(9.5, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(
                new Quantity<>(-5.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2), 0.01);
    }

    @Test
    public void testDivision_CrossUnit_InchesDividedByFeet() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2), 0.01);
    }

    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> q1.divide(q2)
        );
    }

    @Test
    public void testSubtraction_Volume() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.subtract(q2);

        assertEquals(
                new Quantity<>(4.5, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    public void testDivision_Volume() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(1.0, q1.divide(q2), 0.01);
    }
}