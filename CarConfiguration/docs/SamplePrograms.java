//Multithreaded Programs
//----------------------
//Hello Threads
class ThreadX extends Thread {
  
  public void run() {
    try {
      while(true) {
        Thread.sleep(2000);
        System.out.println("Hello");
      }
    }
    catch(InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}

public class MainClass {

  public static void main(String args[]) {
    
    ThreadX tx = new ThreadX();
    tx.start();
  }
}

//Many threads of the same class and each has its own name.
public class simpleThread extends Thread
{
	private int countDown = 5;
	private int threadNumber;
	private static int threadCount = 0;
	
	public simpleThread()
	{
		threadNumber = ++threadCount;
		System.out.println("Making "+ threadNumber);
	}

	public void run()
	{
		while(true)
		{
			System.out.println("Thread "+ threadNumber + " ( "+countDown + " ) ");
			if(--countDown == 0)
				return;
		}
	}

	public static void main (String[] args)
	{
		for(int I = 0; I < 5; I++)
			new simpleThread().start();
		System.out.println("All threads started");
	}
}

//Using the start method with runnable interface
class caller implements Runnable {
  String msg;


  public caller(String s) {
    msg = s;
    new Thread(this).start();
  }

  public void run() {
    System.out.println("run");
  }
}

public class MainClass {
  public static void main(String args[]) {
    new caller("Hello");
  }
}

// Using stop with a sentinel since stop itself is deprecated now.
class MyThread implements Runnable {
  public int click = 0;

  private Thread t;

  private boolean running = true;

  public MyThread(int p) {
    t = new Thread(this);
    t.setPriority(p);
  }

  public void run() {
    while (running) {
      click++;
    }
  }

  public void stop() {
    running = false;
  }

  public void start() {
    t.start();
  }
}

public class MainClass {
  public static void main(String args[]) {
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    MyThread hi = new MyThread(Thread.NORM_PRIORITY + 2);
    MyThread lo = new MyThread(Thread.NORM_PRIORITY - 2);
    lo.start();
    hi.start();

    try {
      Thread.sleep(10000);
    } catch (Exception e) {
    }

    lo.stop();
    hi.stop();
    System.out.println(lo.click + " vs. " + hi.click);
  }
}


class SyncEx1
{
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.err.println("java SyncEx1 <loops per thread>");
			System.exit(-1);
		}
		int n = Integer.parseInt(args[0]); //iterations per thread
		MyThread plus = new MyThread(+1, n, 1); //plus increments
		MyThread minus = new MyThread(-1, n, 2); //minus decrements
		plus.start();
		System.out.println("+ started()");
		minus.start();
		System.out.println("- started()");
		try
		{
			plus.join();
			System.out.println("+ waiting to join()");
			minus.join();	
			System.out.println("- waiting to join()");
		} catch (InterruptedException e) { }
		System.out.println(n + " iterations each: "+ MyThread.shared);
	}
}

class MyThread extends Thread
{
	MyThread(int type, int howMany, int threadNumber)
	{
		this.threadNumber = threadNumber;
		t = type;
		n = howMany;
	}
	public void run()
	{
		for(int i = 0; i < n; i++)
		{
			if(t > 0)
				{ 
				  shared = shared + 1; 
				try {  
					sleep(1000); 
				  } catch (InterruptedException e) { }

				}
			else
				{ 
				  shared = shared - 1; 
				  try {  
					sleep(3000); 
				  } catch (InterruptedException e) { }
				}
		}
		System.out.println("Thread: " + threadNumber + " is done." );

	}

	static int shared; //shared by all MyThread objects
	private int n;
	private int t;
	private int threadNumber;
}



//Using Runnable - enqueue/dequeue from list
//Using Runnable - enqueue/dequeue from list
class Queue {
  int n;

  synchronized int get() {
  	System.out.println("Got: " + n);
	try {  
		Thread.sleep(3000); 
     	} catch (InterruptedException e) { }	
	return n;
  }

  synchronized void put(int n) {
  	this.n = n;
	System.out.println("Put: " + n);
	try {  
		Thread.sleep(3000); 
     	} catch (InterruptedException e) { }	
  }

}

class Consumer implements Runnable {
  Queue q;

  Consumer(Queue q) {
  this.q = q;
  new Thread(this, "Consumer").start();
  }

  public void run() {
    while(true) {
	q.get();      
   }
  }

}

class Producer implements Runnable {
  Queue q;

  Producer(Queue q) {
  this.q = q;
  new Thread(this, "Producer").start();
  }

  public void run() {
   int i = 0;
   while(true) {
      i = i + 1;
      q.put(i);
   }
  }

}

public class MainClass {
  public static void main(String args[]) {
  Queue q = new Queue();
  new Consumer(q);
  new Producer(q);
  }
}

//Am I running currently?
public class MainClass implements Runnable {
  MainClass() {
    Thread ct = Thread.currentThread();
    Thread t = new Thread(this, "Demo Thread");
    System.out.println("currentThread: " + ct);
    System.out.println("Thread created: " + t);
    t.start();

  }

  public void run() {
    for (int i = 5; i > 0; i--) {
      System.out.println("" + i);
    }
    System.out.println("exiting child thread");
  }

  public static void main(String args[]) {
    new MainClass();
  }
}
           
//how many threads are running?
//Why did I crash?
//Who is running This time really show me
public class MainClass {
  public static void main(String args[]) {
    Thread t = Thread.currentThread();
    t.setName("My Thread");
    t.setPriority(1);
    System.out.println("current thread: " + t);
    int active = Thread.activeCount();
    System.out.println("currently active threads: " + active);
    Thread all[] = new Thread[active];
    Thread.enumerate(all);
    for (int i = 0; i < active; i++) {
      System.out.println(i + ": " + all[i]);
    }
    Thread.dumpStack();
  }
}


//We are a team - we work together
class MyThread implements Runnable {
  String name; // name of thread

  Thread t;

  MyThread(String threadname) {
    name = threadname;
    t = new Thread(this, name);
    System.out.println("New thread: " + t);
    t.start();
  }

  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println(name + ": " + i);
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      System.out.println(name + " interrupted.");
    }
    System.out.println(name + " exiting.");
  }
}

public class MainClass {
  public static void main(String args[]) {
    MyThread ob1 = new MyThread("One");
    MyThread ob2 = new MyThread("Two");
    MyThread ob3 = new MyThread("Three");

    System.out.println("Thread One is alive: " + ob1.t.isAlive());
    System.out.println("Thread Two is alive: " + ob2.t.isAlive());
    System.out.println("Thread Three is alive: " + ob3.t.isAlive());

    try {
      System.out.println("Waiting for threads to finish.");
      ob1.t.join();
      ob2.t.join();
      ob3.t.join();
    } catch (InterruptedException e) {
      System.out.println("Main thread Interrupted");
    }

    System.out.println("Thread One is alive: " + ob1.t.isAlive());
    System.out.println("Thread Two is alive: " + ob2.t.isAlive());
    System.out.println("Thread Three is alive: " + ob3.t.isAlive());

    System.out.println("Main thread exiting.");
  }
}

//May I run in the background? 
//What is volatile mean anyway?
import static java.util.concurrent.TimeUnit.SECONDS;

public class MainClass extends Thread {
  // This field is volatile because two different threads may access it
  volatile boolean keepRunning = true;

  public MainClass() {
    setDaemon(true);
  }

  public void run() {
    while (keepRunning) {
      long now = System.currentTimeMillis();
      System.out.printf("%tr%n", now);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        return;
      }
    }
  }
  public void pleaseStop() {
    keepRunning = false;
  }

  public static void main(String[] args) {
    MainClass thread = new MainClass();
    thread.start();
    try {
      SECONDS.sleep(10);
    } catch (InterruptedException ignore) {
    }
    thread.pleaseStop();
  }
}

public class MainClass {
  public static void main(String args[]) {

    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }
    System.out.println("]");
  }
}

//Deadlocks
class DeadlockEx {
   public static void main( String[ ] args ) {
      new MyThread( "foo" ).start();
      new MyThread( "bar" ).start();
   }
}
class MyThread extends Thread {
   public MyThread( String name ) { super( name ); }
   public void run() {
      while ( true )
         if ( getName().equalsIgnoreCase( "foo" ) )
           fooM();
         else
           barM();
   }
   private synchronized void fooM() {
      System.out.println( getName() + " entering fooM." );
      while ( getName().equalsIgnoreCase( "bar" ) )
        try {
           wait(); // wait indefinitely
        } catch( InterruptedException e ) { }
      barM();  // invoke other synchronized method
      System.out.println( getName() + " exiting fooM." );
      notify(); // awaken the other thread, if it's waiting
   }
   private synchronized void barM() {
      System.out.println( getName() + " entering barM." );
      while ( getName().equalsIgnoreCase( "foo" ) )
        try {
           wait(); // wait indefinitely
        } catch( InterruptedException e ) { }
      fooM();  // invoke other synchronized method
      System.out.println( getName() + " exiting barM." );
      notify(); // awaken the other thread, if it's waiting
   }
}

//Usage of wait() and notify()
//************************************************************************************************
//USAGE OF wait() and notify() or notifyall()
class coffee {
//not waiting on each other
boolean available=false; //indicating there nothing to get.

	public synchronized int get() {    // won't work!
	    if (available == true) {
        	available = false;
        	return contents;
    		}
     	}
	public synchronized void put(int value) {    // won't work!
	    if (available == false) {
        		available = true;
		        contents = value;
    		}
	}
}


class coffee {
boolean available=false; //indicating there nothing to get.
//waiting on each other.
public synchronized int get() {
    while (available == false) {
        try {
            // wait for Producer to put value
            wait();
        } catch (InterruptedException e) {
        }
    }
    available = false;
    // notify Producer that value has been retrieved
    notifyAll();
    return contents;
}
public synchronized void put(int value) {
    while (available == true) {
        try {
            // wait for Consumer to get value
            wait();
        } catch (InterruptedException e) {
        }
    }
    contents = value;
    available = true;
    // notify Consumer that value has been set
    notifyAll();
 } 
}

//Importance of Synchronized
//************************************************************************************************
class Goofy extends Thread {
    static String[] msg = { "Example","of","how","messy","Java","is","without","synchronization" };
                            
    public static void main(String[] args) {
        Goofy t1 = new Goofy("t1: ");
        Goofy t2 = new Goofy("t2: ");
        
        t1.start();
        t2.start();
        
        boolean t1IsAlive = true;
        boolean t2IsAlive = true;
        
        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }
            
            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);
    }                            
                                
    public Goofy(String id) {
        super(id);
    }
    
    void randomWait() {
        try {
            Thread.currentThread().sleep((long)(3000*Math.random()));
        } catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }    

    
    public void run() {
        synchronized(System.out) {
            for( int i=0; i<msg.length; i++ ) {
                randomWait();
                System.out.println(getName() + msg[i]);
            }    
        }
    }
}   

   


