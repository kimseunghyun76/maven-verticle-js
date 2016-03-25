package io.vertx.example.core.ha;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;

import java.lang.management.ManagementFactory;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-03-25
 * Time : 오전 11:09
 * To change this template use File | Settings | File and Code Templates.
 */
public class Server extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Launcher.main(new String[] { "run", Server.class.getName(), "-ha"});
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            final String name = ManagementFactory.getRuntimeMXBean().getName();
            req.response().end("Happily served by " + name);
        }).listen(8080);
    }
}