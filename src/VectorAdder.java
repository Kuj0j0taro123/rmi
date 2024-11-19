import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VectorAdder {
    private Executor threadPoolExecutor;


    public VectorAdder(){
        threadPoolExecutor =  Executors.newFixedThreadPool(3);
    }

    public int addVector(int [] vector){
        int thirdOfVector = (int) Math.floor(vector.length / 3);
        int low = 0;
        int mid = thirdOfVector;
        int high = thirdOfVector * 2;

        //Future<Integer> lowerResult = threadPoolExecutor.
        Future<Integer> middleResult;
        Future<Integer> upperResult;

        return 0;
    }



}
