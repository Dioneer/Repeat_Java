package pegas.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try(var socket = new Socket("localhost", 8081);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            var dos = new DataOutputStream(socket.getOutputStream()); Scanner scanner = new Scanner(System.in)){
            while (true){
                dos.writeUTF(scanner.nextLine());
                System.out.println("Server: "+dis.readUTF());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
