package com.example.misc;

public class MockAndSpyTest {

}

/*
 * @MockBean //or Mockito's @Mock
 * - it mocks the object and all its methods with do nothing and their result
 * value will be null,
 * - use for example: when(...) methods to create mocked method behaviour
 * - use when you want to completely get rid of the object's normal behaviour
 * - caution: unmocked methods won't work -> can result in a RuntimeException
 * during tests (@SpyBean is a remedy here apart from wider mocking)
 * 
 * @SpyBean //or Mockito's @Spy
 * - an object will behave like an @Autowired object
 * - all its methods will actually works, but we can define some custom behavior
 * for its methods
 * - use doReturn(...) / doNothing(...) to add custom (mocked) method behaviour
 * - use if you want to provide your mock behaviour but not dismiss entirely its
 * normal behaviour
 */

// source: https://javapointers.com/tutorial/difference-between-spy-and-mock-in