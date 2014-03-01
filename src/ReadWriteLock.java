/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
public class ReadWriteLock {
	private int wr = 0; 										 //Number of writer requests that are waiting for lock
	public int r= 0;                                             //Number of readers
	private int w= 0;                                            //Number of writers
	                                         
	public synchronized void lockRead() throws InterruptedException
	{
		while(w>0 || wr>0 )
		{
			wait();                                               //waiting to acquire read lock
			}
	       r++;                                                   //Readers are incremented once lock is acquired
	  }
	 public synchronized void unlockRead() 
	  {
	    r--;                                                      //Readers are decremented
	    notifyAll();                                              //Lock is released and all other threads waiting for it are notified
	  }

	  public synchronized void lockWrite() throws InterruptedException
	  {
		wr++;                                                    //Write requests are increased(to implement fairness)
	    while(r > 0 || w > 0)
	    {
			wait();                                              //Waiting to acquire write lock
	    }
	    wr--;                                                    //Lock acquired
	    w++;
	  }
	  public synchronized void unlockWrite() 
	  {
	    w--;                                                    //Lock released and all the other threads are notified
	    notifyAll();
	  }
}

