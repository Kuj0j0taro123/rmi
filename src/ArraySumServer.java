import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ArraySumServer {
    public static void main(String[] args) {
        final int PORT = 12345;
        System.out.println("Server is running and listening on " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    int size = in.readInt();

                    int[] array = new int[size];
                    for (int i = 0; i < size; i++) {
                        array[i] = in.readInt();
                    }

                    int result = computeSum(array);

                    out.writeInt(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int computeSum(int[] array) {
        final int numThreads = 3;
        int[] partialSums = new int[numThreads];
        int range = array.length / numThreads;

        // create and start threads
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int start = i * range;
            final int end = (i == numThreads - 1) ? array.length : start + range;
            final int index = i;
            threads[i] = new Thread(() -> {
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                partialSums[index] = sum;
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                // wait for threads to finish
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // return the total sum
        return Arrays.stream(partialSums).sum();
    }
}
