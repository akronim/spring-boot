package com.example.lombok.getter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GetterBooleanUnitTest {

    @Test
    public void whenBasicBooleanField_thenMethodNamePrefixedWithIsFollowedByFieldName() {
        GetterBooleanPrimitive lombokExamples = new GetterBooleanPrimitive();
        assertFalse(lombokExamples.isRunning());
    }

    @Test
    public void whenBooleanFieldPrefixedWithIs_thenMethodNameIsSameAsFieldName() {
        GetterBooleanSameAccessor lombokExamples = new GetterBooleanSameAccessor();
        assertTrue(lombokExamples.isRunning());
    }

    @Test
    public void whenTwoBooleanFieldsCauseNamingConflict_thenLombokMapsToFirstDeclaredField() {
        GetterBooleanPrimitiveSameAccessor  lombokExamples = new GetterBooleanPrimitiveSameAccessor();
        assertTrue(lombokExamples.isRunning() == lombokExamples.running);
        assertFalse(lombokExamples.isRunning() == lombokExamples.isRunning);
    }

    @Test
    public void whenFieldOfBooleanType_thenLombokPrefixesMethodWithGetInsteadOfIs() {
        GetterBooleanType lombokExamples = new GetterBooleanType();
        assertTrue(lombokExamples.getRunning());
    }
}
