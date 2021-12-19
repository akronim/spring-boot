package com.example.lombok.intro;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserIntegrationTest {

    @Test
    public void givenAnnotatedUser_thenHasEmptyConstructor() {
        /* User user = */ new User();
    }

    @Test
    public void givenAnnotatedUser_thenHasGettersAndSetters() {
        User user = new User("testnickname", "Test", "JUnit", "123456");

        assertEquals("testnickname", user.getNickname());
        assertEquals("Test", user.getFirstName());
        assertEquals("JUnit", user.getLastName());
        assertEquals("123456", user.getPhoneNr());

        user.setNickname("testnickname2");
        user.setFirstName("Test2");
        user.setLastName("JUnit2");
        user.setPhoneNr("654321");

        assertEquals("testnickname2", user.getNickname());
        assertEquals("Test2", user.getFirstName());
        assertEquals("JUnit2", user.getLastName());
        assertEquals("654321", user.getPhoneNr());
    }

    @Test
    public void givenAnnotatedUser_thenHasProtectedSetId() throws NoSuchMethodException {
        Method setIdMethod = User.class.getDeclaredMethod("setId", Long.class);
        int modifiers = setIdMethod.getModifiers();
        assertTrue(Modifier.isProtected(modifiers));
    }

    @Test
    public void givenAnnotatedUser_thenImplementsHasContactInformation() {
        User user = new User("testnickname3", "Test3", "JUnit3", "987654");
        assertTrue(user instanceof HasContactInformation);

        assertEquals("Test3", user.getFirstName());
        assertEquals("JUnit3", user.getLastName());
        assertEquals("987654", user.getPhoneNr());
        assertEquals("Test3 JUnit3", user.getFullName());

        user.setFirstName("Test4");
        user.setLastName("JUnit4");
        user.setPhoneNr("456789");

        assertEquals("Test4", user.getFirstName());
        assertEquals("JUnit4", user.getLastName());
        assertEquals("456789", user.getPhoneNr());
        assertEquals("Test4 JUnit4", user.getFullName());
    }

    @Test
    public void givenAnnotatedUser_whenHasEvents_thenToStringDumpsNoEvents() {
        User user = new User("testnickname", "Test", "JUnit", "123456");
        List<UserEvent> events = Arrays.asList(new UserEvent(user), new UserEvent(user));
        user.setEvents(events);
        assertFalse(user.toString().contains("events"));
    }

}
