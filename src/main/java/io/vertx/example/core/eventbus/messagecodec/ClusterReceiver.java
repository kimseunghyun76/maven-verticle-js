package io.vertx.example.core.eventbus.messagecodec;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.example.core.eventbus.messagecodec.util.CustomMessage;
import io.vertx.example.core.eventbus.messagecodec.util.CustomMessageCodec;
import io.vertx.example.util.Runner;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-03-24
 * Time : 오후 2:57
 * To change this template use File | Settings | File and Code Templates.
 */
public class ClusterReceiver extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runClusteredExample(ClusterReceiver.class);
    }

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        EventBus eventBus = getVertx().eventBus();

        //Register codec for custom message
        eventBus.registerDefaultCodec(CustomMessage.class, new CustomMessageCodec());

        //Receive message
        eventBus.consumer("cluster-message-receiver", message ->{
            CustomMessage customMessage = (CustomMessage) message.body();

            System.out.println("Custom Message received : " + customMessage.getSummary());

            //Replying is sam as publishing
            CustomMessage replyMessage = new CustomMessage(200, "a00000002", "Message sent from cluster receiver!");
            message.reply(replyMessage);
        });
    }
}
