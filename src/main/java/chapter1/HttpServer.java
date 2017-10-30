package chapter1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * created by xdCao on 2017/10/30
 */

public class HttpServer {

    public static final String WEB_ROOT="E:\\projects\\HowTomcatWork\\webroot";

    private static final String SHUTDOWN="/shutdown";

    private boolean isShutdown=false;

    public static void main(String[] args) {
        HttpServer httpServer=new HttpServer();
        httpServer.await();
    }

    private void await() {

        ServerSocket serverSocket=null;
        int port=8080;
        try {
            serverSocket=new ServerSocket(port);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        while (!isShutdown){
            Socket socket=null;
            InputStream inputStream=null;
            OutputStream outputStream=null;
            try {
                socket=serverSocket.accept();
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
                Request request=new Request(inputStream);
                request.parse();
                Response response=new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();
                socket.close();
                isShutdown=request.getUri().equals(SHUTDOWN);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }


    }


}
