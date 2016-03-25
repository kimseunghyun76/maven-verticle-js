package io.vertx.example.core.net.echo;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.example.util.Runner;

public class Client  extends AbstractVerticle{
    //Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(Client.class);
    }
/*

    @Override
    public void start() throws Exception {
        //Add SSL Option
        NetClientOptions options = new NetClientOptions().setSsl(true).setTrustAll(true);

        vertx.createNetClient().connect(1234,"localhost",res ->{
            if(res.succeeded()){
                NetSocket socket = res.result();
                socket.handler(buffer-> {
                    System.out.println("Net client receiving : " + buffer.toString("UTF-8"));
                });
                //Now send some data
                for(int i = 0 ; i < 10 ; i++){
                    String str = "hello " + i +"\n";
                    System.out.println("Net client sending: " + str);
                    socket.write(str);
                }
            }else{
                System.out.println("Failed to connect" + res.cause());
            }
        });


    }

*/

    @Override
    public void start() throws Exception {
        // Note! in real-life you wouldn't often set trust all to true as it could leave you open to man in the middle attacks.

        vertx.createHttpClient(new HttpClientOptions().setSsl(true).setTrustAll(true)).getNow(4443, "localhost", "/", resp -> {
            System.out.println("Got response " + resp.statusCode());
            resp.bodyHandler(body -> System.out.println("Got data " + body.toString("ISO-8859-1")));
        });
/*        vertx.createHttpClient().getNow(8080, "localhost", "/", resp -> {
            System.out.println("Got response " + resp.statusCode());
            resp.bodyHandler(body -> {
                System.out.println("Got data " + body.toString("ISO-8859-1"));
            });
        });*/
    }
}
