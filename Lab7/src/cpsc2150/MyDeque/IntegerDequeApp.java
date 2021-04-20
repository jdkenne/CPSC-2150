//Xavier James and Josh Kennerly
package cpsc2150.MyDeque;

import java.util.Scanner;

public class IntegerDequeApp{

    /**
     * @post the menu is printing out to the terminal
     */
    public static void mainMenu(){
        System.out.println("What would you like to do?\n");
        System.out.println("1. Add to the end of the deque");
        System.out.println("2. Add to the front of the deque");
        System.out.println("3. Remove from the front of the deque");
        System.out.println("4. Remove from the end of the deque");
        System.out.println("5. Peek at the front of the deque");
        System.out.println("6. Peek at the end of the deque");
        System.out.println("7. Insert into a position in the deque");
        System.out.println("8. Remove from any position in the deque");
        System.out.println("9. Peek at value in any position in the deque");
        System.out.println("10. Returns the length of the deque");
        System.out.println("11. Clears the deque");
        System.out.println("12. Exit");
    }

    public static void main(String [] argc){
        IDeque q;

        /*
        You will add in code here to ask the user whether
        they want an array implementation or a list implementation.
        Then use their answer to initialize q appropriately
        */
        System.out.println("Enter 1 for array implementation or 2 for List implementation");

        Scanner read = new Scanner(System.in);

        int intInput;
        intInput = read.nextInt();

        while(!(intInput ==1) && !(intInput == 2)){
            System.out.println("You selected " + intInput);
            System.out.println("Please select one of the two options");
            System.out.println("\"1\" for Array");
            System.out.println("\"2\" for List");
            intInput = read.nextInt();
        }

        if(intInput == 1){
            q = new ArrayDeque <Integer>();
        }
        else {
            q = new ListDeque <Integer>();
        }

        System.out.println("You selected \"" + intInput + "\"\n");
        mainMenu();
        intInput = read.nextInt();

        while (intInput != 12) {
            int addChar;
            int p = -1;
            switch (intInput) {
                case 1:
                    if(q.length() < IDeque.MAX_LENGTH){
                        System.out.println("Which character to enque to the end of the Deque?");
                        addChar = read.nextInt();//check the precondition
                        q.enqueue(addChar);
                    }else{
                        System.out.println("Your List is full!");
                        System.out.println("Please decrease your length size in order to use.");
                    }
                    break;

                case 2:
                    if(q.length() < IDeque.MAX_LENGTH){
                        System.out.println("What character to inject to the front of the Deque?");
                        addChar = read.nextInt();
                        q.inject(addChar);
                    }else{
                        System.out.println("Your List is full!");
                        System.out.println("Please decrease your length size in order to use.");
                    }
                    break;

                case 3:
                    if(q.length() > 0) {
                        System.out.println("Character at the front: "+ q.dequeue() );
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }
                    break;

                case 4:
                    if(q.length()>0) {
                        System.out.println("Character at the end: "+ q.removeLast() );
                        // q.removeLast();
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }
                    break;

                case 5:
                    if(q.length()>0) {
                        System.out.println("Peek: " + q.peek());

                    }
                    else{
                        System.out.println("Deque is empty!");
                    }
                    break;

                case 6:
                    if(q.length() > 0) {
                        System.out.println("End of Deque: " + q.endOfDeque());
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }
                    break;

                case 7:
                    if(q.length() == IDeque.MAX_LENGTH){
                        System.out.println("Your List is full!");
                        System.out.println("Please decrease your length size in order to use.");
                    }else {
                        System.out.println("What number to insert to the Deque?");
                        intInput = read.nextInt();
                        while (p == -1) {
                            System.out.println("What position do you want to insert from the Deque?");
                            p = read.nextInt();

                            if(p < 0 || p > q.length()) {
                                p = -1;
                                System.out.println("Not a valid position in the Deque: ");
                            }
                        }
                        q.insert(intInput, p);
                        break;
                    }
                    break;

                case 8:
                    if(q.length() == 0){
                        System.out.println("Deque is empty");
                    }else {
                        while (p == -1) {
                            System.out.println("What position to remove from the Deque?");
                            p = read.nextInt();

                            if (p <= 0 || p > q.length()) {
                                p = -1;
                                System.out.println("Not a valid position in the Deque! ");
                            }
                        }
                        System.out.println(q.remove(p) + " is at position " + p + " in the Deque");
                    }
                    break;

                case 9:
                    if(q.length() == 0){
                        System.out.println("Deque is empty");
                    }else {

                        while (p == -1) {

                            System.out.println("What position to get from the Deque?");
                            p = read.nextInt();

                            if (p <= 0 || p > q.length()) {
                                p = -1;
                                System.out.println("Not a valid position in the Deque! ");
                            }
                        }

                        System.out.println(q.get(p) + " is at position " + p + " in the Deque");
                    }
                    break;

                case 10:
                    System.out.println("Length of Deque: " + q.length());
                    break;

                case 11:
                    if(q.length() == 0){
                        System.out.println("Queue is already empty");
                    }else {
                        System.out.println("Deque is now empty!");
                        q.clear();
                    }
                    break;

                default:
                    System.out.println("Please choose a number on the menu");
                    break;
            }
            System.out.println("Current string is : " + q.toString() + "\n");
            mainMenu();
            intInput = read.nextInt();
        }
        System.out.println("Thank you for using the application, goodbye");

    }

}
