@Test
public void testAddition_DelegatesCorrectly() {

    Quantity<LengthUnit> feet =
            new Quantity<>(1.0, LengthUnit.FEET);

    Quantity<LengthUnit> inch =
            new Quantity<>(12.0, LengthUnit.INCH);

    Quantity<LengthUnit> result = feet.add(inch);

    assertEquals(
            new Quantity<>(2.0, LengthUnit.FEET),
            result
    );
}

@Test
public void testSubtraction_DelegatesCorrectly() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> inch =
            new Quantity<>(6.0, LengthUnit.INCH);

    Quantity<LengthUnit> result =
            feet.subtract(inch);

    assertEquals(
            new Quantity<>(9.5, LengthUnit.FEET),
            result
    );
}

@Test
public void testDivision_DelegatesCorrectly() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> feet2 =
            new Quantity<>(2.0, LengthUnit.FEET);

    double result = feet.divide(feet2);

    assertEquals(5.0, result, 0.01);
}

@Test
public void testDivision_ByZero_ShouldThrowException() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> zero =
            new Quantity<>(0.0, LengthUnit.FEET);

    assertThrows(
            ArithmeticException.class,
            () -> feet.divide(zero)
    );
}

@Test
public void testCrossCategory_Addition_ShouldThrowException() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<WeightUnit> kg =
            new Quantity<>(5.0, WeightUnit.KILOGRAM);

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.add((Quantity) kg)
    );
}

@Test
public void testCrossCategory_Subtraction_ShouldThrowException() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<WeightUnit> kg =
            new Quantity<>(5.0, WeightUnit.KILOGRAM);

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.subtract((Quantity) kg)
    );
}

@Test
public void testCrossCategory_Division_ShouldThrowException() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<WeightUnit> kg =
            new Quantity<>(5.0, WeightUnit.KILOGRAM);

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.divide((Quantity) kg)
    );
}

@Test
public void testAddition_Immutability() {

    Quantity<VolumeUnit> litre =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> ml =
            new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    litre.add(ml);

    assertEquals(
            new Quantity<>(1.0, VolumeUnit.LITRE),
            litre
    );
}

@Test
public void testSubtraction_Immutability() {

    Quantity<VolumeUnit> litre =
            new Quantity<>(5.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> ml =
            new Quantity<>(500.0, VolumeUnit.MILLILITRE);

    litre.subtract(ml);

    assertEquals(
            new Quantity<>(5.0, VolumeUnit.LITRE),
            litre
    );
}

@Test
public void testDivision_Immutability() {

    Quantity<WeightUnit> kg =
            new Quantity<>(10.0, WeightUnit.KILOGRAM);

    Quantity<WeightUnit> gram =
            new Quantity<>(5000.0, WeightUnit.GRAM);

    kg.divide(gram);

    assertEquals(
            new Quantity<>(10.0, WeightUnit.KILOGRAM),
            kg
    );
}