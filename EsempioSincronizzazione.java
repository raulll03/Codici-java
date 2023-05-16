class ContoCorrente 
{ 
    private double c = 0;
    public void incrementa() { c = c + 1;} 
    public void decrementa() { c = c - 1;} 
    public double saldo() { return c;}
}

class Paperone extends Thread 
{
    ContoCorrente conto;

    Paperone(ContoCorrente conto) 
    {
        this.conto = conto;
    }

    public void run() 
    { 
        for (int i=0; i < 20000000; i++) // 20 000 000 
            conto.incrementa();
    }
}

class Paperino extends Thread 
{
    ContoCorrente conto;

    Paperino(ContoCorrente conto) 
    {
        this.conto = conto;
    }

    public void run() 
    { 
        for (int i=0; i < 20000000; i++) // 20 000 000 
            conto.decrementa();
    }
}

public class EsempioSincronizzazione 
{
    public static void main(String aa[]) 
    {
        ContoCorrente c = new ContoCorrente();
        Paperone paperone = new Paperone(c);
        Paperino paperino = new Paperino(c);
        
        paperone.start();
        try 
        {
            paperone.join();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        paperino.start();
        try 
        {
            paperino.join();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.out.println(c.saldo());
    }
}