package io.javabrains.reactiveworkshop.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Auodumbar
 */
public class FluxSinkExample {

    public static void main(String[] args) throws IOException {
        //Flux.create() uses sink.onRequest() to control how many items to emit when requested.
        Flux<String> flux = Flux.create(sink -> {

            AtomicLong counter = new AtomicLong(0);

            sink.onRequest(nRequest -> {
                for (int i = 0; i < nRequest; i++) {
                    long value = counter.incrementAndGet();
                    if (value > 10) { //Once 10 items are emitted, the sink.complete() ends the stream.
                        sink.complete();
                        return;
                    }
                    sink.next("Item " + value);
                }

            });

        });

        flux.subscribe(new Mysubscriber());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class Mysubscriber implements Subscriber<String> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        System.out.println("Requested");
        //"I am ready to receive up to 3 items.
        //You can send between 0 to 3 items whenever you are ready."
        s.request(3);//// Request 3 items initially

    }

    @Override
    public void onNext(String s) {
        System.out.println("Received: " + s);
        try {
            Thread.sleep(2000); // Simulate processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscription.request(2); // Request 2 more items after each onNext
        //This gives you manual control over backpressure.
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
