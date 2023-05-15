//client che interroga un server web facendosi mandare la pagina index 
//funziona correttamente se il server usa il protocollo HTTP e non HTTPS

import java.io.*; 
import java.net.*; 

public class SocketTest2 
{
    public static void main(String[] args) 
    {
        try 
        {
            String nome; 
            int porta;
            
            if (args.length ==0) 
                nome = "www.unipg.it";
            else nome = args[0];

            if (args.length <=1) 
                porta = 80;
            else porta = Integer.parseInt(args[1]); 
            
            Socket s = new Socket(nome, porta);
            BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true); 
            
            out.println("GET / HTTP/1.1"); 
            out.println("Accept: text/html "); 
            out.println("Host: "+nome); 
            out.println("");
            
            boolean more = true;
            while (more) 
            { 
                String line = in.readLine(); 
                
                if (line == null) 
                    more = false; 
                else System.out.println(line);
            }
        } catch (IOException e) { System.out.println("Error" + e);}
    } 
}