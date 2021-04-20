package cpsc2150.MyDeque;

import java.util.*;

public class ListDeque <T> extends AbsDeque <T> {

    /**
     * @Invariants 0 <= myLength <= MAX_LENGTH
     * @Correspondences dqSize = myLength
     */

    //this time store the deque in a list
    //myQ.get(0) is the front of the deque
    private List<T> myQ;

    // complete the class
    //Adds x to the end of the deque
    public void enqueue(T x){
        myQ.add(x);
    }

    //removes and returns the Character at the front of the deque
    public T dequeue(){
        T firstChar;
        firstChar = myQ.get(0);

        myQ.remove(0);

        return firstChar;
    }

    //Adds x to the front of the deque
    public void inject (T x){
        myQ.add(0,x);
    }

    // @precondition myQ length > 0;
    public <T> removeLast(){
        T lastChar;

        lastChar = myQ.get(myQ.size()-1);

        myQ.remove(myQ.size()-1);

        return lastChar;
    }

    //returns the number of Characters in the deque
    public int length(){
        return myQ.size();
    }

    //clears the entire deque
    public void clear(){
        for(int i = 0; i < myQ.size(); i++){
            myQ.remove(i);
            i--;
        }
    }

    /**
     * @post myQ is a new instance of ArrayList
     */
    public ListDeque(){
        myQ = new ArrayList<T>();
    }


}
