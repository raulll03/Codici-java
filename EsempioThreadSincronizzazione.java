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
            synchronized(punto){punto.sposta();}
    }
}

class Visualizza extends Thread 
{
    Punto punto;

    Visualizza(Punto punto) 
    {
        this.punto = punto;
    }

    public void run() 
    {
        while (true)
            synchronized(punto){punto.visualizza();}
    }
}

class Controlla extends Thread 
{
    Punto punto;

    Controlla(Punto punto) 
    {
        this.punto = punto;
    }

    public void run() 
    {
        while (true)
            synchronized(punto){punto.controlla();}
    }
}

class EsempioThreadSincronizzazione 
{ 
    public static void main(String [] s) 
    {
        Punto punto; 
        punto = new Punto(); 
        Sposta t1 = new Sposta (punto); 
        Visualizza t2 = new Visualizza (punto); 
        Controlla t3 = new Controlla (punto);

        t1.start(); 
    //  t2.start(); 
        t3.start();
    } 
}