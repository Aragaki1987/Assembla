package lab1;


/*************************************************************************
 *  Compilation:  javac Goofy.java
 *  Execution:    java Goofy N
 *  
 *  Sort N random real numbers between 0 and 1 using an algorithm
 *  of Jim Huggins.
 *
 *************************************************************************/


public class Goofy {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        sort(a, lo, hi-1);     // first n-1 items 
        if (less(a[hi], a[hi-1])) { 
            exch(a, hi-1, hi);
            sort(a, lo, hi-1);  // first n-1 items 
        }
    }

   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


 /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }



    // test client
    public static void main(String[] args) {

        // generate array of N random reals between 0 and 1
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.random();
        }
        
        // sort the array
        sort(a);

        // display results
        for (int i = 0; i < N; i++) {
            System.out.println(a[i]);
        }
        System.out.println("isSorted = " + isSorted(a));
    }

}