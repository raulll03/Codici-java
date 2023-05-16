Ecco come implementare InputStream utilizzando la classe FileInputStream.

Supponiamo di avere un file chiamato input.txt con il seguente contenuto.

This is a line of text inside the file.

Proviamo a leggere questo file utilizzando FileInputStream (una sottoclasse di InputStream).


import java.io.FileInputStream;
import java.io.InputStream;

class EsempioInputStream 
{
    public static void main(String[] args) 
    {
        byte[] array = new byte[100];

        try
        {
            InputStream input = new FileInputStream("input.txt");

            System.out.println("Available bytes in the file: " + input.available());

            // Read byte from the input stream
            input.read(array);
            System.out.println("Data read from the file: ");

            // Convert byte array into string
            String data = new String(array);
            System.out.println(data);

            // Close the input stream
            input.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}

Nell'esempio precedente, abbiamo creato un flusso di input utilizzando la classe FileInputStream. Il flusso di input è collegato al file input.txt.

InputStream input = new FileInputStream("input.txt");

Per leggere i dati dal file input.txt, abbiamo implementato questi due metodi.

input.read(array);  // to read data from the input stream
input.close();     // to close the input stream

