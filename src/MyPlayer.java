import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

// kjhvfcdxsetdcfvtgbyhnuimko,ovftcdr

//kjhgvfcrtyhj
public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public int a;
    public int b;
    public int c;

    ArrayList<Board> losingBoards = new ArrayList<>();
    ArrayList<Board> winningBoards = new ArrayList<>();

    public MyPlayer() {
        columns = new int[10];
        Square();
        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */


    }


    public void Square() {

        losingBoards.add(new Board(1,0,0, new Point(0,0)));

        for (a = 1; a < 4; a++) {
            for (b = 0; b <= a; b++) {
                for (c = 0; c <= b; c++) {
                    System.out.println("board: " + a + " " + b + " " + c);
                    printResultBoards(a, b, c);
                }
            }
        }
    }

    public boolean findLosers(int one, int two, int three) {
        boolean containsLoser = false;
        for (int y = 0; y < losingBoards.size(); y++){
            if(losingBoards.get(y).boardNums[0]==one && losingBoards.get(y).boardNums[1]==two && losingBoards.get(y).boardNums[2]==three){
                containsLoser = true;
            }
        }
        return containsLoser;

    } // comment


    public void printResultBoards(int A, int B, int C) {

        boolean hasLoser = false;
        Point goodMove = new Point(0,0);

        for (int c2 = C - 1; c2 >= 0; c2--) { // third col

            System.out.println(A + " " + B + " " + c2 + " move to make: " + c2 + "," + 2);
            if (findLosers(A,B,c2) == true) {
                hasLoser = true;
                goodMove = new Point(c2, 2);
            }

    // put hasLoser in each thingy with new thingy
        }


        for (int b2 = B - 1; b2 >= 0; b2--) { // second col

            if (b2 < C) {
                System.out.println(A + " " + b2 + " " + b2 + " move to make: " + b2 + "," + 1);
                if (findLosers(A,b2,b2) == true) {
                    hasLoser = true;
                    goodMove = new Point(b2, 1);

                }
            } else {
                System.out.println(A + " " + b2 + " " + C + " move to make: " + b2 + "," + 1);
                if (findLosers(A,b2,C) == true) {
                    hasLoser = true;
                    goodMove = new Point(b2, 1);

                }
            }


        }

        for (int a2 = A - 1; a2 >= 1; a2--) { // first col


            if (a2 < B && a2 < C) {
                System.out.println(a2 + " " + a2 + " " + a2 + " move to make: " + a2 + "," + 0);
                if (findLosers(a2,a2,a2) == true) {
                    hasLoser = true;
                    goodMove = new Point(a2, 0);

                }
            }
            else if (a2 < B) {
                System.out.println(a2 + " " + a2 + " " + C + " move to make: " + a2 + "," + 0);
                if (findLosers(a2,a2,C) == true) {
                    hasLoser = true;
                    goodMove = new Point(a2, 0);

                }
            }else {
                System.out.println(a2 + " " + B + " " + C + " move to make: " + a2 + "," + 0);
                if (findLosers(a2,B,C) == true) {
                    hasLoser = true;
                    goodMove = new Point(a2, 0);

                }
            }



        }
        // if we found a loser, add ABC to winning boards
        if (hasLoser == true) {
            winningBoards.add(new Board( A,B,C, goodMove ));
        } else {
            losingBoards.add(new Board(A,B,C, goodMove));
        }

    }


    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        columns = checkChips(gameBoard);
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */
        Point myMove = new Point(row, column);

        for (int x = 0; x< winningBoards.size();x++){
            System.out.println(winningBoards.get(x).boardNums[0] + "" + winningBoards.get(x).boardNums[1] + "" + winningBoards.get(x).boardNums[2] + " vs " + columns[0] +  columns[1] + columns[2]);
            if (winningBoards.get(x).boardNums[0] == columns[0] && winningBoards.get(x).boardNums[1] == columns[1] && winningBoards.get(x).boardNums[2] == columns[2]) {
                System.out.println("found the board!");
                myMove = winningBoards.get(x).move;
            }
        }
        System.out.println(myMove);

        return myMove;
    }

    public int[]checkChips(Chip[][] gameBoard) {
        int[] cols = new int[10];

        for (int c = 0; c < 10; c++){
            for (int r = 0; r < 10; r++){
                if (gameBoard[r][c].isAlive == true){
                    cols[c] = cols[c] + 1;
                }
            }
        }

        return cols;
    }


}
