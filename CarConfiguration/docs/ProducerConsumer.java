class goofy {
	public static void main(String [] a211)
	{
		coffee a1 = new coffee();	
		cgoofy a3 = new cgoofy(a1);	
		a3.start();
		try {
			Thread.sleep(2000);
		} catch (Exception e) { }
		pgoofy a2 = new pgoofy(a1);	
		a2.start();
	}
}

class pgoofy extends Thread {
	coffee a1;
	pgoofy(coffee e)
	{
		a1 = e;
	}
	public void run()
	{
		a1.put(10);
		System.out.println(a1.contents);
	}
}

class cgoofy extends Thread {
	coffee a1;
	cgoofy(coffee e)
	{
		a1 = e;
	}
	public void run()
	{
		System.out.println("consumer" + a1.get());
	}
}

class coffee {
boolean available=false; 
int contents;

//indicating there nothing to get.
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