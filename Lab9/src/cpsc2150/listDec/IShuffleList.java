package cpsc2150.listDec;

import java.util.*;

public interface IShuffleList<T> extends List<T> {

    /*
    *
    * @pre the list has values in it
    * @post values in the list are randomly swapped around
     */
    default void shuffle(int swaps) {
        Random rand = new Random();
        for(int i = 0; i < swaps; i++) {
            swap(rand.nextInt(this.size()), rand.nextInt(this.size()));
        }
    }


    /*
    *
    * @pre the list has at least 2 values in it
    * @post #i == j, #j == i
     */
    default void swap(int i, int j){
        T temp = this.get(i);
        T temp2 = this.get(j);
        this.set(j, temp);
        this.set(i, temp2);
    }


}
