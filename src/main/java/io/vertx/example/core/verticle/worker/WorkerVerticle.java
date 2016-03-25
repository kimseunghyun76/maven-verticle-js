package io.vertx.example.core.verticle.worker;


import io.vertx.core.AbstractVerticle;

public class WorkerVerticle extends AbstractVerticle {
    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        System.out.println("[worker] Starting in " + Thread.currentThread().getName());

        vertx.eventBus().consumer("sample.data", message->{
            System.out.println("[Worker] Consuming data in " + Thread.currentThread().getName());
            String body = (String) message.body();
            message.reply(body.toUpperCase());
        });
    }
}
