package io.vertx.example.core.verticle.deploy;

import io.vertx.core.AbstractVerticle;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-03-24
 * Time : 오후 4:24
 * To change this template use File | Settings | File and Code Templates.
 */
public class OtherVerticle extends AbstractVerticle {

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        //The start method will be called when the verticle is deployed
        System.out.println("In OtherVerticle.start");

        System.out.println("Config is " + config());
    }

    /**
     * If your verticle has simple synchronous clean-up tasks to complete then override this method and put your clean-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        // You can optionally override the stop method too, if you have some clean-up to do
        System.out.println("In OtherVerticle.stop");
    }
}
