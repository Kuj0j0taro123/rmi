import java.io.*;
import java.net.*;

public class ArraySumClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 12345; // make sure the port is the same on the server

        int[] array = new int[30000];
        for (int i =0; i < 30000; i++){
            array[i] = i;
        }

        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            long startTime = System.currentTimeMillis();

            out.writeInt(array.length);

            for (int value : array) {
                out.writeInt(value);
            }

            int result = in.readInt();

            long endTime = System.currentTimeMillis();

            long responseTime = endTime - startTime;

            System.out.println("Received result from server: " + result);
            System.out.println("Response time: " + responseTime + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
