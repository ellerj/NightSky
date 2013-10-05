import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CThread extends Thread {
private Socket s;
    
    
    public CThread(String aname, Socket as) {
        super(aname);
        s = as;
    }
    
    @Override
    public void run() {
    	PrintWriter out = null;
        try {
            out = new PrintWriter(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(CThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                 new InputStreamReader(
                 s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(CThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String str = null;
        try {
            str = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(CThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(str);
        
        //treat as a small file
        Path path = Paths.get("test.txt");
        List<String> lines = null;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        lines.add("This is a line added in code by server thread.");
        
        System.out.print(lines);
        out.print(lines);
        
    }
    
}
