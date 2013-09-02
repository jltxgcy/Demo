public class ThreadDemo2
{  
    public static void main(String[] args)   
    {  
        Resource rs = new Resource(100);  
 
        for (int i=0;i<10 ;i++ )  
        {  
            new Seller(i,rs).start();  
        }  
    }  
}  
/**  
 *  资源类  
 *  定义了票的总数，和同步了的售票方法  
 */ 
class Resource  
{  
    int ticketNum = 50;  
    boolean flag = false ; // 定义票是否卖完  
 
    public Resource(){}  
    public Resource(int num)  
    {  
        this.ticketNum = num;  
    }  
 
    public synchronized void sellTicket(Seller s)  
    {  
        if (ticketNum > 0)  
        {  
            System.out.println("第" + s.num + "售票点卖出了第" + ticketNum + "张票……");  
            ticketNum--;  
        }  
        else   
        {  
            flag = true;  
        }  
    }  
}  
/**  
 *  售票点类  
 *    
 */ 
class Seller extends Thread 
{  
    int num;  
    Resource rs;  
 
    public Seller(int num,Resource rs)  
    {  
        this.num = num;  
        this.rs = rs;  
    }  
 
    public final void run()  
    {  
        while (!rs.flag)  
        {  
            /**  
             *  调用资源类的同步方法  
             */   
            rs.sellTicket(this);   
 
            try 
            {  
                Thread.sleep(100);  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
        }  
    }  
} 
