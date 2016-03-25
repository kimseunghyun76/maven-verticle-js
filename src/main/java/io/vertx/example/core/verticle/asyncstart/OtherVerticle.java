package io.vertx.example.core.verticle.asyncstart;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-03-25
 * Time : 오전 10:19
 * To change this template use File | Settings | File and Code Templates.
 */
public class OtherVerticle extends AbstractVerticle{
    /**
     * Start the verticle.<p>
     * This is called by Vert.x when the verticle instance is deployed. Don't call it yourself.<p>
     * If your verticle does things in it's startup which take some time then you can override this method
     * and call the startFuture some time later when start up is complete.
     *
     * @param startFuture a future which should be called when verticle start-up is complete.
     * @throws Exception
     */
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.println("In OtherVerticle.start (async)");

        //This veticle takes some time to start (maybe it has to deploy other verticles or whatever)
        //So we override the async version of start(), then we can mark the verticle as started some time later
        //when all the show startup is done, without blocking the actual start method.

        //we simulate this long startup time by setting a timer
        vertx.setTimer(2000, tid ->{
            //Now everything is started, we can tell Vert.x this verticle is started when it will call the deploy handler
            // of the caller that originally deployed it.

            System.out.println("Startup tasks are now complete, OtherVerticle is now started");

            startFuture.complete();
        });

    }

    /**
     * Stop the verticle.<p>
     * This is called by Vert.x when the verticle instance is un-deployed. Don't call it yourself.<p>
     * If your verticle does things in it's shut-down which take some time then you can override this method
     * and call the stopFuture some time later when clean-up is complete.
     *
     * @param stopFuture a future which should be called when verticle clean-up is complete.
     * @throws Exception
     */
    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
       //If you have slow cleanup tasks to perform, you can similarly override the async stop method

        vertx.setTimer(2000, tid->{
            System.out.println("Cleanup tasks are now complete, OtherVerticle is now stopped!");

            stopFuture.complete();
        });
    }
}
