import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        ArrayList<Integer> numbersInFile = new ArrayList<Integer>();
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            BufferedReader br = new BufferedReader(new FileReader("src/text.txt"));
            String line = br.readLine();
            while(line != null) {
                String[] tokens = line.split(" ");
                numbersInFile.add(Integer.parseInt(tokens[0]));
                line = br.readLine();
            }
            for (int num : numbersInFile) {
                service.sendMessage(num);
            }

            String serviceName = "RMI Service";

            System.out.println("Starting " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(serviceName, service);
            System.out.println("Start " + serviceName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}