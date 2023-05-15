/*@version 1.10 1997-06-27 @author Cay Horstmann */

import java.io.*;
import java.net.*;

public class EchoClient 
{
    private static final int PORTNUM = 8189;

    public static void main(String[] args) 
    { 
        Socket socket = null; 
        BufferedReader in = null;
        PrintWriter out = null;
        String address;
        
        // Check the command-line args for the host address 
        if (args.length != 1) 
        {
            System.out.println("Usage: java EchoClient <address>"); 
            return;
        }
        else address = args[0];

        // Initialize the socket and streams 
        try 
        { 
            socket = new Socket(address, PORTNUM); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            out = new PrintWriter(socket.getOutputStream());
        }catch(IOException e)
        {
            System.err.println("Exception: couldn't create stream socket"); 
            System.exit(1);
        }
        
        // Process user input and server responses 
        try 
        { 
            StringBuffer str = new StringBuffer(128); 
            String inStr; 
            int c;
            
            while((inStr = in.readLine()) != null)
            { // usa caratteri UNICODE 
                System.out.println(inStr); 
                while ((c = System.in.read()) != '\n') 
                    str.append((char)c); 
                System.out.println("Client: " + str); 
                
                out.print(str.toString()); // usa caratteri UNICODE
                out.flush();
                
                if ((str.toString()).trim().equals("BYE"))
                    break;
                
                str.setLength(0);
            }
            
            // Cleanup 
            out.close();
            in.close(); 
            socket.close();
        } catch(IOException e)
        {
            System.err.println("Exception: I/O error trying to talk to server");
        }
    }
}