package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(e -> System.out.println(e));

        // Get the value from the Mono into an integer variable
        Integer monoInteger = ReactiveSources.intNumberMono().block();
        System.out.println(monoInteger);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
