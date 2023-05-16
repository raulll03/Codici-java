Ecco come implementare InputStream utilizzando la classe FileInputStream.

Supponiamo di avere un file chiamato input.txt con il seguente contenuto.

This is a line of text inside the file.

Proviamo a leggere questo file utilizzando FileInputStream (una sottoclasse di InputStream).


In particolar modo, in quest'esempio abbiamo creato un flusso di input utilizzando la classe FileInputStream. Il flusso di input è collegato al file input.txt.

InputStream input = new FileInputStream("input.txt");

Per leggere i dati dal file input.txt, abbiamo implementato questi due metodi.

input.read(array);  // to read data from the input stream
input.close();     // to close the input stream

