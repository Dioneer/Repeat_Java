package pegas.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) {
        try(var serverSocket = new ServerSocket(8081); var socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            var dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)){
            String request = dis.readUTF();
            while (!request.equals("exit")){
                System.out.println("Client: "+request);
                dos.writeUTF(scanner.nextLine());
                request = dis.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
