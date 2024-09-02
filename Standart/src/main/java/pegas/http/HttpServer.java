package pegas.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final ExecutorService executorService;
    private final int port;
    public HttpServer(int port,int i){
        this.executorService = Executors.newFixedThreadPool(i);
        this.port = port;
    }
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                var socket = serverSocket.accept();
                executorService.submit(()->processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try(socket; var dis = new DataInputStream(socket.getInputStream());
        var dos = new DataOutputStream(socket.getOutputStream())){
            System.out.println(new String(dis.readNBytes(1)));
            byte[] body = "<html><body><h1>HI</h1></body></html>".getBytes();
            dos.write("""
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes());
            dos.write(System.lineSeparator().getBytes());
            System.out.println(Arrays.toString(body));
            dos.write(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
