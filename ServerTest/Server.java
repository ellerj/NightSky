import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

private static ArrayList<CThread> Threads = new ArrayList<>();
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(9876);
        Socket s;
        
        int count = 0;
        
        while (count < 3) {
            s = ss.accept();
            
            Threads.add(new CThread("ChatT", s));
            Threads.get(count).start();
            
            count++;
        }
        
        for (int i=0; i<Threads.size(); ++i) {
            Threads.get(i).join();
        }
        
        ss.close();
    }
    
}
