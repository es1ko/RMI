import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;

    ArrayList<Integer> NumberOfEvenNumbers = new ArrayList<Integer>();
    static long startTime = 0, endTime = 0;
    boolean isProcessStarted = false;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public Integer getMessage() throws RemoteException {
        if(!isProcessStarted) {
            startTime = System.nanoTime();
        }
        isProcessStarted = true;
        return this.queue.poll();
    }

    @Override
    public void sendMessage(int num) throws RemoteException {
        this.queue.add(num);
    }

    @Override
    public void storeCalculatedMessage(int num) throws RemoteException {
        System.out.println("Queue: " + queue);
        NumberOfEvenNumbers.add(num);
        Output(NumberOfEvenNumbers);
    }

    public static void Output(ArrayList<Integer> listOfNumbers) {
        int c = 0;
        for(int numbers : listOfNumbers){
            c = c+ numbers;
        }
        System.out.println("Sum: " + c);
        endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime));

    }
}