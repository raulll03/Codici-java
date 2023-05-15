import java.io.*;
import java.net.*;

public class TriviaServer 
{
    static String[] questions;
    static String[] answers;

    public static void main(String[] args) 
    { 
        final int PORTNUM = 1234; 
        ServerSocket serverSocket = null;

        if (!initQnA()) 
        { 
            System.err.println("Error: couldn't initialize questions and answers"); 
            return;
        }
        
        try 
        { 
            serverSocket = new ServerSocket(PORTNUM); 
            System.out.println("TriviaServer up and running...");
        } catch (IOException e) 
        { 
            System.err.println("Exception: couldn't create ServerSocket"); 
            System.exit(1);
        }

        try 
        { 
            while (true) 
            { 
                //in una applicazione reale il server accetterebbe un numero 
                //limitato di connessioni in contemporanea
                Socket incoming = serverSocket.accept(); 
                Thread t = new TriviaServerThread(incoming, questions, answers); //quanta memoria occupa ogni Thread?
                t.start(); 
            }
        } catch (IOException e) 
        { 
            System.err.println("Exception: couldn't connect to client socket");
        } 
    }

    private static boolean initQnA() 
    { 
        boolean isQ = true;
        int index = 0;
        BufferedReader inStream = null;  
        int numQuestions;
    
        try 
        { 
            inStream = new BufferedReader(new FileReader("QnA.txt")); 
            String riga; 
            riga = inStream.readLine(); 
            numQuestions = Integer.parseInt(riga); 
            numQuestions /= 2; 
            TriviaServer.questions = new String[numQuestions]; 
            answers = new String[numQuestions]; 
            while ((riga = inStream.readLine()) != null) 
            { 
                if (isQ) 
                { 
                    questions[index] = riga; 
                    isQ = false;
                } 
                else 
                { 
                    answers[index] = riga; 
                    isQ = true; 
                    index++; //aggiorno dopo che ho inserito il secondo elemento della coppia 
                } 
            }
        } catch (FileNotFoundException e) 
        { 
            System.err.println("Exception: couldn't find the fortune file"); 
            return false;
        } catch (IOException e) 
        { 
            System.err.println("Exception: I/O error trying to read questions"); 
            return false;
        }
        finally
        { 
            try 
            {
                inStream.close();
            } catch (IOException e) 
            { 
                System.err.println("Exception: I/O error trying to close file"); 
                return false;
            } 
        } 
        return true; 
    } 
}