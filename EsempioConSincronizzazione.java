class ContoCorrenteCS 
{ 
    private double c = 0;
    synchronized public void incrementa() { c = c + 1;} 
    synchronized public void decrementa() { c = c - 1;} 
    public double saldo() { return c;}
}

class PaperoneCS extends Thread 
{
    ContoCorrenteCS conto;

    PaperoneCS(ContoCorrenteCS conto) 
    {
        this.conto = conto;
    }

    public void run() 
    {
        for (int i = 0; i < 20000000; i++) // 20 000 000
            conto.incrementa();
    }
}

class PaperinoCS extends Thread 
{
    ContoCorrenteCS conto;

    PaperinoCS(ContoCorrenteCS conto) 
    {
        this.conto = conto;
    }

    public void run() 
    {
        for (int i = 0; i < 20000000; i++) // 20 000 000
            conto.decrementa();
    }
}

public class EsempioConSincronizzazione 
{
    public static void main(String aa[]) 
    {
        ContoCorrenteCS c = new ContoCorrenteCS();
        PaperoneCS paperone = new PaperoneCS(c);
        PaperinoCS paperino = new PaperinoCS(c);
        paperone.start();
        paperino.start();
        try 
        {
            paperone.join();
            paperino.join();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.out.println(c.saldo());
    }
}