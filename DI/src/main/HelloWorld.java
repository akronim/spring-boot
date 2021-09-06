package main;

public class HelloWorld {
    Greeting greeting;

    public HelloWorld(Greeting greeting) {
        super();
        this.greeting = greeting;
    }

    public void greet() {
        System.out.println(greeting.greetMsg());
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }
}
