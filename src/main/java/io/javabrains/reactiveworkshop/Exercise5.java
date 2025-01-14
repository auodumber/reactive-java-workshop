package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFluxWithException().subscribe(
                e -> System.out.println(e),
                err -> System.err.println("Error Occurred " + err.getMessage()),
                () -> System.out.println("Flux Events Completed")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {


    /**
     * Backpressure
     * The subscriber in this case is not ready to consume all the data at once.
     * It initially requests 2 elements
     */
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        request(2); //Initially it request for 2 elements
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("value from hookOnNext " + value.toString());
        request(2); //We have request more elements.
    }
}