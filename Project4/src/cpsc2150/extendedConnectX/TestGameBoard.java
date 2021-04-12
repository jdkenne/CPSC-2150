package cpsc2150.extendedConnectX;

import org.junit.Assert;
import org.junit.Test;


public class TestGameBoard {
    private IGameBoard gb(int r, int c, int w) {return new GameBoard(r, c, w); }

    private String arrString(char [][] arr, int row, int col){
        StringBuilder display = new StringBuilder();

        for(int i = 0; i < col; i++){
            if(i < 10){
                display.append("| ").append(i);
            }
            else{
                display.append("|").append(i);
            }
        }

        display.append("|\n");

        for(int i = row - 1; i >= 0; i--){
            for(int j = 0; j < col; j++){
                display.append("|").append(arr[i][j]).append(" ");
            }

            display.append("|\n");
        }

        return display.toString();

    }

    @Test
    public void testConstructor_smallest(){
        char[][] arr;
        arr = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(3, 3, 3);

        Assert.assertEquals(tester.toString(), arrString(arr, 3, 3));
        Assert.assertEquals(3, tester.getNumToWin());
    }

    @Test
    public void testConstructor_biggest(){
        char[][] arr;
        arr = new char[100][100];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(100, 100, 25);

        Assert.assertEquals(tester.toString(), arrString(arr, 100, 100));
        Assert.assertEquals(25, tester.getNumToWin());
    }

    @Test
    public void testConstructor_different(){
        char[][] arr;
        arr = new char[30][20];
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 20; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(30, 20, 3);

        Assert.assertEquals(tester.toString(), arrString(arr, 30, 20));
        Assert.assertEquals(3, tester.getNumToWin());
    }

    @Test
    public void testCheckIfFree_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);
        Assert.assertTrue(tester.checkIfFree(2));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckIfFree_one_space(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);
        tester.placeToken('X', 4);
        arr[0][4] = 'X';
        Assert.assertTrue(tester.checkIfFree(4));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckIfFree_full(){
        char[][] arr;
        arr = new char[5][5];
        IGameBoard tester = gb(5, 5, 3);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(j == 2){
                    arr[i][2] = 'X';
                    tester.placeToken('X', 2);
                }
                else {
                    arr[i][j] = ' ';
                }
            }
        }

        Assert.assertFalse(tester.checkIfFree(2));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));

    }

    @Test
    public void testCheckHorizontalWin_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertFalse(tester.checkHorizWin(new BoardPosition(2, 2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));

    }

    @Test
    public void testCheckHorizontalWin_just_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i == 0 && j < 3)
                    arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 3; i++){
            tester.placeToken('X', i);
        }

        Assert.assertTrue(tester.checkHorizWin(new BoardPosition(0,2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckHorizontalWin_more_than_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i == 0 && j < 4)
                    arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            tester.placeToken('X', i);
        }

        Assert.assertTrue(tester.checkHorizWin(new BoardPosition(0,3), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckHorizontalWin_just_not_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i == 0 && j < 4)
                    arr[i][j] = 'X';
            }
        }

        arr[0][2] = 'O';

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            if(i == 2)
                tester.placeToken('O', i);
            else
                tester.placeToken('X', i);

        }

        Assert.assertFalse(tester.checkHorizWin(new BoardPosition(0, 3), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckVerticalWin_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertFalse(tester.checkVertWin(new BoardPosition(2, 2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));

    }

    @Test
    public void testCheckVerticalWin_just_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i < 3 && j == 2)
                    arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 3; i++){
            tester.placeToken('X', 2);
        }

        Assert.assertTrue(tester.checkVertWin(new BoardPosition(2,2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckVerticalWin_more_than_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i < 4 && j == 2)
                    arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            tester.placeToken('X', 2);
        }

        Assert.assertTrue(tester.checkVertWin(new BoardPosition(3,2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckVerticalWin_just_not_enough(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
                if(i < 4 && j == 2)
                    arr[i][j] = 'X';
            }
        }

        arr[2][2] = 'O';

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            if(i == 2)
                tester.placeToken('O', 2);
            else
                tester.placeToken('X', 2);

        }

        Assert.assertFalse(tester.checkVertWin(new BoardPosition(3, 2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckDiagonalWin_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertFalse(tester.checkDiagWin(new BoardPosition(0, 0), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckDiagonalWin_left_just_enough(){
        char[][] arr;
        arr = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[1][0] = 'O';
        arr[2][2] = 'X';

        IGameBoard tester = gb(4,4,3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);
        tester.placeToken('O',2);
        tester.placeToken('X', 2);
        tester.placeToken('O', 0);
        tester.placeToken('X', 2);

        Assert.assertTrue(tester.checkDiagWin(new BoardPosition(2, 2), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));

    }

    @Test
    public void testCheckDiagonalWin_left_more_than_enough() {
        char[][] arr;
        arr = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[1][0] = 'O';
        arr[2][2] = 'X';
        arr[0][3] = 'X';
        arr[1][3] = 'O';
        arr[2][3] = 'X';
        arr[3][3] = 'X';

        IGameBoard tester = gb(4, 4, 3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);
        tester.placeToken('O', 2);
        tester.placeToken('X', 2);
        tester.placeToken('O', 0);
        tester.placeToken('X', 2);
        tester.placeToken('X',3);
        tester.placeToken('O', 3);
        tester.placeToken('X', 3);
        tester.placeToken('X', 3);

        Assert.assertTrue(tester.checkDiagWin(new BoardPosition(3, 3), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));
    }

    @Test
    public void testCheckDiagonalWin_left_just_not_enough() {
        char[][] arr;
        arr = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[1][0] = 'O';
        arr[2][2] = 'O';
        arr[0][3] = 'X';
        arr[1][3] = 'O';
        arr[2][3] = 'X';
        arr[3][3] = 'O';

        IGameBoard tester = gb(4, 4, 3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);
        tester.placeToken('O', 2);
        tester.placeToken('X', 2);
        tester.placeToken('O', 0);
        tester.placeToken('O', 2);
        tester.placeToken('X',3);
        tester.placeToken('O', 3);
        tester.placeToken('X', 3);
        tester.placeToken('O', 3);

        Assert.assertFalse(tester.checkDiagWin(new BoardPosition(2, 3), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));
    }

    @Test
    public void testCheckDiagonalWin_right_just_enough(){
        char[][] arr;
        arr = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][3] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'O';
        arr[2][1] = 'X';

        IGameBoard tester = gb(4,4,3);

        tester.placeToken('X', 3);
        tester.placeToken('O', 2);
        tester.placeToken('X', 2);
        tester.placeToken('O',1);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);

        Assert.assertTrue(tester.checkDiagWin(new BoardPosition(2,1), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));

    }

    @Test
    public void testCheckDiagonalWin_right_more_than_enough(){
        char[][] arr;
        arr = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][3] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'O';
        arr[2][1] = 'X';
        arr[0][0] = 'X';
        arr[1][0] = 'O';
        arr[2][0] = 'X';
        arr[3][0] = 'X';

        IGameBoard tester = gb(4,4,3);

        tester.placeToken('X', 3);
        tester.placeToken('O', 2);
        tester.placeToken('X', 2);
        tester.placeToken('O',1);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);
        tester.placeToken('X', 0);
        tester.placeToken('O', 0);
        tester.placeToken('X', 0);
        tester.placeToken('X', 0);

        Assert.assertTrue(tester.checkDiagWin(new BoardPosition(3,0), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));

    }

    @Test
    public void testCheckDiagonalWin_right_just_not_enough(){
        char[][] arr;
        arr = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][3] = 'X';
        arr[0][2] = 'O';
        arr[1][2] = 'X';
        arr[0][1] = 'O';
        arr[1][1] = 'O';
        arr[2][1] = 'X';
        arr[0][0] = 'X';
        arr[1][0] = 'O';
        arr[2][0] = 'X';
        arr[3][0] = 'O';

        IGameBoard tester = gb(4,4,3);

        tester.placeToken('X', 3);
        tester.placeToken('O', 2);
        tester.placeToken('X', 2);
        tester.placeToken('O',1);
        tester.placeToken('O', 1);
        tester.placeToken('X', 1);
        tester.placeToken('X', 0);
        tester.placeToken('O', 0);
        tester.placeToken('X', 0);
        tester.placeToken('O', 0);

        Assert.assertFalse(tester.checkDiagWin(new BoardPosition(2, 0), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 4, 4));
    }

    @Test
    public void testCheckTie_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertFalse(tester.checkTie());
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckTie_full(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        int rows = 0;
        while(rows < 5){
            for(int i = 0; i < 5; i++){
                tester.placeToken('X', rows);
            }
            rows++;
        }

        Assert.assertTrue(tester.checkTie());
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckTie_almost_full(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = 'X';
                if(j == 4)
                    arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5,5,3);
        int rows = 0;
        while(rows < 4){
            for(int i = 0; i < 5; i++){
                tester.placeToken('X', rows);
            }
            rows++;
        }

        Assert.assertFalse(tester.checkTie());
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testCheckTie_all_but_one(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = 'X';
            }
        }

        arr[4][4] = ' ';

        IGameBoard tester = gb(5,5,3);
        int rows = 0;
        while(rows < 4){
            for(int i = 0; i < 5; i++){
                tester.placeToken('X', rows);
            }
            rows++;
        }

        tester.placeToken('X',4);
        tester.placeToken('X',4);
        tester.placeToken('X',4);
        tester.placeToken('X',4);

        Assert.assertFalse(tester.checkTie());
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testWhatsAtPos_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertEquals(' ', tester.whatsAtPos(new BoardPosition(0, 0)));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testWhatAtPos_player_x(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X',0);

        Assert.assertEquals('X', tester.whatsAtPos(new BoardPosition(0, 0)));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testWhatsAtPos_player_e(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'E';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('E',0);

        Assert.assertEquals('E', tester.whatsAtPos(new BoardPosition(0, 0)));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testWhatsAtPos_player_nearby(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X',0);

        Assert.assertEquals(' ', tester.whatsAtPos(new BoardPosition(1, 0)));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testWhatsAtPos_two_players() {
        char[][] arr;
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);

        Assert.assertEquals('O', tester.whatsAtPos(new BoardPosition(0, 1)));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testIsPlayerAtPos_empty(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        IGameBoard tester = gb(5, 5, 3);

        Assert.assertFalse(tester.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testIsPlayerAtPos_player_x(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X',0);

        Assert.assertTrue(tester.isPlayerAtPos(new BoardPosition(0,0), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testIsPlayerAtPos_player_e(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'E';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('E',0);

        Assert.assertTrue(tester.isPlayerAtPos(new BoardPosition(0,0), 'E'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testIsPlayerAtPos_empty_space_nearby(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X',0);

        Assert.assertFalse(tester.isPlayerAtPos(new BoardPosition(0, 1), 'X'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testIsPlayerAtPos_two_players(){
        char[][] arr;
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);

        Assert.assertTrue(tester.isPlayerAtPos(new BoardPosition(0, 1), 'O'));
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testPlaceToken_bottom_left(){
        char[][] arr;
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';

        IGameBoard tester = gb(5,5,3);
        tester.placeToken('X',0);
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testPlaceToken_bottom_right(){
        char[][] arr;
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][4] = 'X';

        IGameBoard tester = gb(5,5,3);
        tester.placeToken('X',4);
        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testPlaceToken_fill_board(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] = 'X';
            }
        }

        IGameBoard tester = gb(5,5,3);
        int rows = 0;
        while(rows < 5){
            for(int i = 0; i < 5; i++){
                tester.placeToken('X', rows);
            }
            rows++;
        }

        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testPlaceToken_different_characters(){
        char[][] arr;
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ' ';
            }
        }

        arr[0][0] = 'X';
        arr[0][1] = 'O';

        IGameBoard tester = gb(5, 5, 3);

        tester.placeToken('X', 0);
        tester.placeToken('O', 1);

        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

    @Test
    public void testPlaceToken_fill_different_characters(){
        char[][] arr;
        arr = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][0] = 'S';
                arr[i][1] = 'H';
                arr[i][2] = 'R';
                arr[i][3] = 'E';
                arr[i][4] = 'K';
            }
        }

        IGameBoard tester = gb(5,5,3);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tester.placeToken('S', 0);
                tester.placeToken('H',1);
                tester.placeToken('R', 2);
                tester.placeToken('E', 3);
                tester.placeToken('K',4);
            }
        }

        Assert.assertEquals(tester.toString(), arrString(arr, 5, 5));
    }

}

