package io.vertx.example.core.verticle.asyncstart;

import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-03-25
 * Time : 오전 10:18
 * To change this template use File | Settings | File and Code Templates.
 */
public class DeployExample extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(DeployExample.class);
    }

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        System.out.println("Main verticle has started, let's deploy some others...");

        //Deploy another instance and want for it to start
        vertx.deployVerticle("io.vertx.example.core.verticle.asyncstart.OtherVerticle", res->{
            if(res.succeeded()){
                String deploymentID = res.result();

                System.out.println("Other verticle deployed ok, deploymentId = " + deploymentID);

                vertx.undeploy(deploymentID, res2->{
                    if(res2.succeeded()){
                        System.out.println("Undeployed ok!");
                    }else{
                        res2.cause().printStackTrace();
                    }
                });
            }
        });
    }
}
