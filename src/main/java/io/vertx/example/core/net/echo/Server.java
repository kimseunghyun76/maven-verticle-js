package io.vertx.example.core.net.echo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.streams.Pump;
import io.vertx.example.util.Runner;

public class Server extends AbstractVerticle {
    //Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(Server.class);
    }

/*
    @Override
    public void start() throws Exception {
        //Add SSL Option
        */
/*NetServerOptions options = new NetServerOptions()
                .setSsl(true).setKeyStoreOptions(new JksOptions().setPath("server-keystore.jks").setPassword("wibble"));*//*


        vertx.createNetServer().connectHandler(sock -> {
            // Create a pump
            Pump.pump(sock, sock).start();
        }).listen(1234);
        System.out.println("Echo server is now listening");
    }
*/

    @Override
    public void start() throws Exception {
        HttpServer server =
                vertx.createHttpServer(new HttpServerOptions().setSsl(true).setKeyStoreOptions(
                        new JksOptions().setPath("server-keystore.jks").setPassword("wibble")
                ));
        server.requestHandler(req -> {
            req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
        }).listen(4443);

        /*
        vertx.createHttpServer().requestHandler(req -> {
            req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
        }).listen(8080);*/
    }
}
