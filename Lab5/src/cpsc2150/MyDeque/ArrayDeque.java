package cpsc2150.MyDeque;

import java.util.ArrayList;

public class ArrayDeque <T> extends AbsDeque <T> {

    /**
     * @Invariants 0 <= myLength <= MAX_LENGTH
     * @Correspondences dqSize = myLength
     */

    //where the data is stored.
    //myQ[0] is the front of the deque
    private T [] myQ;

    //tracks how many items in the deque
    //also used to find the end of the deque
    private int myLength;

    //complete the class

    // Adds x to the end of the deque
    public void enqueue (T x){
        myQ[myLength] = x;
        myLength = myLength + 1;
    }

    public T dequeue(){
        T popVal = myQ[0];
        myLength = myLength - 1;

        for(int i = 0; i < myLength; i++){
            myQ[i] = myQ[i+1];
        }

        return popVal;
    }

    public void inject (T x) {

        for(int i = myLength; i != 0; i--){

            myQ[i] = myQ[i-1];

        }

        myQ[0] = x;
        myLength = myLength + 1;

    }

    public <T> removeLast(){
        T popVal = myQ[myLength-1];

        myQ[myLength-1]= null;
        myLength = myLength - 1;

        return popVal;
    }

    public int length(){
        return myLength;
    }

    public void clear(){
        for(int i = 0; i < myLength; i++){
            myQ[i] = null;
        }
        myLength = 0;
    }

    /**
     * @post myLength = 0
     */
    public ArrayDeque(){
        myLength = 0;
        myQ = (T[])new Object[MAX_LENGTH];
    }

}
