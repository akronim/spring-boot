package main;

public class Main {
  public static void main(String[] args) {
    Greeting greeting1 = new Greeting("Good morning!");

    HelloWorld helloWorld = new HelloWorld(greeting1);
    helloWorld.greet();

    Greeting greeting2 = new Greeting("Good evening!");
    helloWorld.setGreeting(greeting2);
    helloWorld.greet();
  }
}
