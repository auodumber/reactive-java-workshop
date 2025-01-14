package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise2 {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        ReactiveSources.intNumbersFlux().subscribe(e -> System.out.print(e));

        // Print all users in the ReactiveSources.userFlux stream
        ReactiveSources.userFlux().subscribe(e -> System.out.print(e));

        System.out.println("Press a key to end");
        System.in.read();

    }

}
