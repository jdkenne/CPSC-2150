package cpsc2150.MyDeque;

import java.util.ArrayList;

/** A deque containing characters.
 * A deque is a data structure a double-ended queue that allows you to insert and remove from both ends.
 * This deque is bounded by MAX_LENGTH
 * @Initialization ensures that you have an empty deque
 * @define dqSize = deque size
 * @constraints 0 <= dqSize <= MAX_LENGTH
 **/

public interface IDeque <T> {
    public static final int MAX_LENGTH = 100;

    /**
     *
     * @pre dqSize < MAX_LENGTH
     * @param x is the character being enqueued
     * @post dqSize = #dqSize + 1
     */
    //Adds x to the end of the deque
    public void enqueue(T x);


    /**
     * @pre 0 < dqSize <= MAX_LENGTH
     * @post dqSize = #dqSize - 1
     * @return variable at the front of queue
     */
    //removes and returns the Character at the front of the deque
    public T dequeue();

    /**
     * @pre 0 <= dqSize < MAX_LENGTH
     * @post dqSize = #dqSize + 1
     * @param x is the character being put into the front of the deque
     */
    //Adds x to the front of the deque
    public void inject(T x);

    /**
     * @pre 0 < dqSize <= MAX_LENGTH
     * @post dqSize = #dqSize - 1
     * @return  variable at the end of queue
     */
    //removes and returns the Character at the end of the deque
    public T removeLast();

    /**
     * @return dqSize
     */
    //returns the number of Characters in the deque
    public int length();

    /**
     * @post #dqSize  = 0
     */
    //clears the entire deque
    public void clear();

    /**
     * @pos the character returned is the character you are trying to peek at
     * @return the character your trying to see in the queue
     * @post the character is still in the deque
     */
    default public T peek(){
        T x;

        x = this.dequeue();
        this.inject(x);

        return x;
    }

    /**
     * @pos the character returned is the character at the end of the deque
     * @return the character at the end of the deque
     * @post the character is still in the deque
     */
    default public T endDeque(){
        T x;

        x = this.removeLast();
        this.enqueue(x);

        return x;
    }

    /**
     * @pre the deque isn't full
     * @param c the character you are trying to input into the deque
     * @param pos the position at which you are inputting
     * @post the character inserted is inside the deque at position #pos-1
     */
    default public void insert(T c, int pos){
        T x;
        if(pos == this.length()){
            this.enqueue(c);
        } else if(pos-1 == 0){
            this.inject(c);
        }else{
            for(int i = 0; i < pos - 1; i++){
                 x = this.dequeue();
                 this.enqueue(x);
            }
            this.enqueue(c);

            for (int i = 0; i < pos; i++){
                x = this.dequeue();
                this.enqueue(x);
            }
        }
    }

    /**
     * @pre the deque isn't empty
     * @param pos the position in the deque
     * @return the character at position pos in the deque
     * @post the character at position pos is no longer in the deque
     */
    default public T remove(int pos){
        T x = null;
        T popVal;

        if(pos == this.length()){
            x = this.dequeue();
            return x;

        } else{
            for(int i = 0; i <= this.length(); i++){
                if(i == pos-1){
                    x = this.dequeue();
                }else{
                    popVal = this.dequeue();
                    this.enqueue(popVal);
                }
            }
            return x;
        }

    }

    /**
     * @pre the deque isn't empty
     * @param pos the position in the deque
     * @return the character at position pos in the deque
     * @post the character at position pos is still in the deque
     */
    default public T get(int pos){
        T x = null;
        T popVal;

        for(int i = 0; i <= this.length()-1; i++){
            if(i == pos-1){
                x = this.dequeue();
                this.enqueue(x);
            }else{
                popVal = this.dequeue();
                this.enqueue(popVal);
            }
        }

        return x;

    }

}
