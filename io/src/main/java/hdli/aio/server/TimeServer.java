package hdli.aio.server;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        try{if (args != null & args.length > 0) {
            port = Integer.valueOf(args[0]);
        }   }catch(NumberFormatException e) {
            e.printStackTrace();
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }

}
