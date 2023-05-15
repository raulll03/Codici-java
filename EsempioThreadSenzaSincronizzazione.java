class Punto 
{
    int x = -1000000000, y = -1000000000;

    void sposta() 
    {
        x++;
        y++;
    }

    void visualizza() 
    {
        System.out.println("x=" + x + " y=" + y);
    }

    void controlla() 
    {
        if (x - y != 0)
            System.out.println("x=" + x + " y=" + y);
    }
}

class Sposta extends Thread 
{
    Punto punto;

    Sposta(Punto punto) 
    {
        this.punto = punto;
    }

    public void run() 
    {
        while (true)
            punto.sposta();
    }
}

class Disegna extends Thread 
{
    Punto punto;

    Disegna(Punto punto) 
    {
        this.punto = punto;
    }

    public void run() 
    {
        while (true)
            punto.visualizza();
    }
}

class Verifica extends Thread 
{
    Punto punto;

    Verifica(Punto punto) 
    {
        this.punto = punto;
    }

    public void run() 
    {
        while (true)
            punto.controlla();
    }
}

class EsempioThreadSenzaSincronizzazione 
{ 
    public static void main(String [] s) 
    {
        Punto punto; 
        punto = new Punto(); 
        Sposta t1=new Sposta (punto); 
        Disegna t2=new Disegna (punto); 
        Verifica t3=new Verifica (punto);

        t1.start(); 
    //  t2.start(); 
        t3.start();
    } 
}