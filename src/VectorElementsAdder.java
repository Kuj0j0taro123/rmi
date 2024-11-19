import java.util.ArrayList;

public class VectorElementsAdder implements Runnable{
    @Override
    public void run() {

    }

    public int result(int [] vector, int start, int end){
        int result = 0;
        for (int i = start; i < end; i++){
            result += vector[i];
        }

        return result;
    }
}
