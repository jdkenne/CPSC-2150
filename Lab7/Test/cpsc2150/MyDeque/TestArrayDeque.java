package cpsc2150.MyDeque;

import org.junit.Test;

public class TestArrayDeque {
    private IDeque<Character> MakeADeque(){
        return new ArrayDeque();
    }

    @Test
    public void testEnqueueEmpty() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testEnqueueMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testEnqueueFull() {
        IDeque q = MakeADeque();
        String test = "<";
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test += "a, ";
        }
        test = test.substring(0, test.length()-2);
        test += ">";

        assert(q.toString().equals(test));
    }

    @Test
    public void testDequeueEmpty() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals("<>"));
    }

    @Test
    public void testDequeueMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals("<b>"));
    }

    @Test
    public void testDequeueFull() {
        IDeque q = MakeADeque();
        String test = "<";
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
            test += "a, ";
        }
        test = test.substring(0, test.length() - 2);
        test += ">";

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals(test));
    }

    @Test
    public void testInjectEmpty() {
        IDeque q = MakeADeque();
        q.inject('a');

        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testInjectMiddle() {
        IDeque q = MakeADeque();
        q.inject('a');
        q.inject('b');

        assert(q.toString().equals("<b, a>"));
    }

    @Test
    public void testInjectFull() {
        IDeque q = MakeADeque();
        String test = "<";
        for(int i = 0; i < 100; i++){
            q.inject('a');
            test += "a, ";
        }
        test = test.substring(0, test.length()-2);
        test += ">";

        assert(q.toString().equals(test));
    }

    @Test
    public void testRemoveLastEmpty() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        assert(q.removeLast().equals('a'));
        assert(q.toString().equals("<>"));
    }

    @Test
    public void testRemoveLastMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.removeLast().equals('b'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testRemoveLastFull() {
        IDeque q = MakeADeque();
        String test = "<";
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
            test += "a, ";
        }

        test = test.substring(0, test.length() - 2);
        test += ">";

        assert(q.removeLast().equals('a'));
        assert(q.toString().equals(test));
    }

    @Test
    public void testClearLastEmpty() {
        IDeque q = MakeADeque();
        q.clear();

        assert(q.toString().equals("<>"));
    }

    @Test
    public void testClearMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.clear();

        assert(q.toString().equals("<>"));
    }

    @Test
    public void testClearFull() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
        }
        q.clear();


        assert(q.toString().equals("<>"));
    }

    @Test
    public void testPeekSizeOne() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        assert(q.peek().equals('a'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testPeekSizeTwo() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.peek().equals('a'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testPeekMaxSize() {
        IDeque q = MakeADeque();
        String test = "<";
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test += "a, ";
        }
        test = test.substring(0, test.length()-2);
        test += ">";

        assert(q.peek().equals('a'));
        assert(q.toString().equals(test));
    }

    @Test
    public void testEndOfDequeSizeOne() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        assert(q.endOfDeque().equals('a'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testEndOfDequeSizeTwo() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.endOfDeque().equals('b'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testEndOfDequeSize() {
        IDeque q = MakeADeque();
        String test = "<";
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test += "a, ";
        }
        test = test.substring(0, test.length()-2);
        test += ">";

        assert(q.endOfDeque().equals('a'));
        assert(q.toString().equals(test));
    }

    @Test
    public void testInsertStart() {
        IDeque q = MakeADeque();
        q.enqueue('a');

        q.insert('1', 1);

        assert(q.toString().equals("<1, a>"));
    }

    @Test
    public void testInsertMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        q.insert('1', 2);

        assert(q.toString().equals("<a, 1, b>"));
    }

    @Test
    public void testInsertEnd() {
        IDeque q = MakeADeque();
        String test = "<";

        for(int i = 0; i < 99; i++){
            q.enqueue('a');
            test += "a, ";
        }
        test = test.substring(0, test.length()-2);
        test += ", 1>";

        q.insert('1', 100);

        assert(q.toString().equals(test));
    }

    @Test
    public void testRemoveStart() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.remove(1).equals('a'));
        assert(q.toString().equals("<b>"));
    }

    @Test
    public void testRemoveMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.remove(2).equals('b'));
        assert(q.toString().equals("<a, c>"));
    }

    @Test
    public void testRemoveEnd() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.remove(3).equals('c'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testGetStart() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.get(1).equals('a'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testGetMiddle() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.get(2).equals('b'));
        assert(q.toString().equals("<a, b, c>"));
    }

    @Test
    public void testGetEnd() {
        IDeque q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.get(3).equals('c'));
        assert(q.toString().equals("<a, b, c>"));
    }
}

