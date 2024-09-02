package pegas.http;

public class HttpsServerRun {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8082,10);
        httpServer.run();
    }
}
