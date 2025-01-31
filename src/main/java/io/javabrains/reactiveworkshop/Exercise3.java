package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.List;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        List<Integer> integerList = ReactiveSources.intNumbersFlux().toStream().toList();
        System.out.println("integerList is " + integerList);
        System.out.println("integerList size " + integerList.size());

        System.out.println("Press a key to end");
        System.in.read();
    }

}
