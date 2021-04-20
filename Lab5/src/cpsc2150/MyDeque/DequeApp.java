//Xavier James and Josh Kennerly

package cpsc2150.MyDeque;

import java.util.Scanner;

public class DequeApp {

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
        System.out.println("Which implementation would you like to use?");
        System.out.println("\"A\" for Array");
        System.out.println("\"B\" for List");

        Scanner scan = new Scanner (System.in);

        String input = scan.nextLine();
        Character charInput;
        int intInput;

        while(!input.equals("A") && !input.equals("B")){
            System.out.println("You selected " + input);
            System.out.println("Please select one of the two options");
            System.out.println("\"A\" for Array");
            System.out.println("\"B\" for List");
            input = scan.nextLine();
        }

        if(input.equals("A")){
            q = new ArrayDeque();
        }else{
            q = new ListDeque();
        }

        System.out.println("\nYou selected \"" + input + "\"\n");
        mainMenu();
        input = scan.nextLine();

        while(!input.equals("12")){
            switch(input){
                case "1": //add at the end
                    System.out.println("What would you like to add?");
                    input = scan.nextLine();
                    System.out.println("You selected \"" + input + "\"");

                    while(input.length() > 1){
                        System.out.println("Please insert one Character");
                        input = scan.nextLine();
                    }

                    q.enqueue(input.charAt(0));
                    break;

                case "2": //add at the front
                    System.out.println("What would you like to add?");
                    input = scan.nextLine();
                    System.out.println("You selected \"" + input + "\"");

                    while(input.length() > 1){
                        System.out.println("Please insert one Character");
                        input = scan.nextLine();
                    }

                    q.inject(input.charAt(0));
                    break;

                case "3": //remove from front
                    charInput = q.remove(1);

                    System.out.println(charInput + " has been removed from the queue");
                    break;

                case "4": //remove from end
                    charInput = q.removeLast();

                    System.out.println(charInput + " has been removed from the queue");
                    break;

                case "5": //peek at front of queue
                    charInput = q.peek();

                    System.out.println(charInput + " was located at the front of the queue");
                    break;

                case "6": //peek at end of queue
                    charInput = q.endDeque();

                    System.out.println(charInput + " was located at the end of the queue");
                    break;

                case "7": //Insert at position
                    System.out.println("What would you like to add?");
                    input = scan.nextLine();
                    System.out.println("You selected \"" + input + "\"");

                    while(input.length() > 1){
                        System.out.println("Please insert one Character");
                        input = scan.nextLine();
                    }

                    charInput = input.charAt(0);

                    System.out.println("Where would you like to place it?");
                    intInput = scan.nextInt();
                    System.out.println("You selected \"" + intInput + "\"");

                    while(intInput < 1 || intInput > q.length()){
                        System.out.println("Not valid position!");
                        intInput = scan.nextInt();
                    }
                    //Skips newline character
                    input = scan.nextLine();
                    q.insert(charInput, intInput);

                    break;

                case "8": //remove at position
                    System.out.println("What position would you like to get");
                    intInput = scan.nextInt();

                    while(intInput < 0 || intInput > q.length()){
                        System.out.println("Not valid position!");
                        intInput = scan.nextInt();
                    }
                    //Skips newline character
                    input = scan.nextLine();
                    charInput = q.remove(intInput);

                    System.out.println(charInput + " has been removed from the list");
                    break;

                case "9": //peek at position
                    System.out.println("What position would you like to get");
                    intInput = scan.nextInt();

                    while(intInput < 0 || intInput > q.length()){
                        System.out.println("Not valid position!");
                        intInput = scan.nextInt();
                    }
                    //Skips newline character
                    input = scan.nextLine();
                    charInput = q.get(intInput);

                    System.out.println(charInput + " was at position");
                    break;

                case "10": //return the length
                    System.out.println("The length of the queue is: " + q.length());
                    break;

                case "11": //clear the queue
                    q.clear();
                    System.out.println("Queue has been clear.");
                    break;

                default:
                    System.out.println("Please choose a number on the menu");
                    break;
            }
            System.out.println("Current string is : " + q.toString() + "\n");
            mainMenu();
            input = scan.nextLine();
        }
        System.out.println("Thank you for using the application, goodbye");

    }
}
