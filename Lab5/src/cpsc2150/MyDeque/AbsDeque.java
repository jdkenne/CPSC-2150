package cpsc2150.MyDeque;

public abstract class AbsDeque implements IDeque {
    /**
     * @post to get a string that represents what is in the deque
     * @returns a String containing the characters inside the deque
     */
    @Override
    public <T> toString(){
        T x = "<";
        int length = length();
        T grabChar;

        if(length != 0) {
            grabChar = dequeue();
            x = x + grabChar;
            enqueue(grabChar);
        }

        if(length > 1) {
            for (int i = 1; i < length; i++) {
                x = x + ',';
                grabChar = dequeue();
                x = x + grabChar;
                enqueue(grabChar);
            }
        }

        x = x + '>';

        return x;

    }

}
