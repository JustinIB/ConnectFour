package Connect4_Game;

import java.util.*;

public class ConnectFour {
    private static int currentPiece = 1;
    private static Scanner sc;
    private static final int width = 6;
    private static final int height = 7;
    private static final int[] rowsHeight = { 5, 5, 5, 5, 5, 5, 5 };
    private static int counter;
    private static final char[][] board = new char[width][height];

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        System.out.println("Welcome to Connect Four!");
        System.out.println();
        counter = 0;
        CreateBoard();
        newBoard();
        PrintBoard();
        gameLoop();
    }

    public static void newBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public static void CreateBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public static void PrintBoard() {

        for (char[] chars : board) {
            System.out.print("|");
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(chars[c] + "|");
            }
            System.out.println();
        }
    }

    public static void gameLoop() {
        while (true) {
            System.out.println("Player: " + currentPiece);
            System.out.print("Enter your move: ");
            String move = sc.nextLine().trim();
            int c = Integer.parseInt(move) - 1;
            System.out.println();
            if (isValid(c)) {
                if (currentPiece == 1) {
                    board[rowsHeight[c]][c] = 'R';
                    rowsHeight[c]--;
                    counter++;
                    PrintBoard();
                    if (hasWon('R')) {
                        System.out.println("Player 1 has won!");
                        break;
                    } else {
                        if (counter == 42) {
                            PrintBoard();
                            System.out.println("Game is a tie!");
                            break;
                        }
                        currentPiece = 2;
                    }
                } else {
                    board[rowsHeight[c]][c] = 'Y';
                    rowsHeight[c]--;
                    counter++;
                    PrintBoard();
                    if (hasWon('Y')) {
                        System.out.println("Player 2 has won!");
                        break;
                    } else {
                        if (counter == 42) {
                            PrintBoard();
                            System.out.println("Game is a tie!");
                            break;
                        }
                        currentPiece = 1;
                    }
                }
            }
        }
    }
    public static boolean hasWon(char Piece){

        // horizontal
        for (int r = 0; r<height-3 ; r++ ){
            for (int c = 0; c<width; c++){
                if (board[c][r] == Piece && board[c][r+1] == Piece && board[c][r+2] == Piece && board[c][r+3] == Piece){
                    return true;

                }
            }
        }
        // vertical
        for (int c = 0; c<width-3 ; c++ ){
            for (int r = 0; r<height; r++){
                if (board[c][r] == Piece && board[c+1][r] == Piece && board[c+2][r] == Piece && board[c+3][r] == Piece){
                    return true;
                }
            }
        }
        // ascendingDiagonal
        for (int  c=3; c<width; c++){
            for (int r=0; r<height-3; r++){
                if (board[c][r] == Piece && board[c-1][r+1] == Piece && board[c-2][r+2] == Piece && board[c-3][r+3] == Piece)
                    return true;
            }
        }
        // descendingDiagonal
        for (int c=3; c<width; c++){
            for (int r=3; r<height; r++){
                if (board[c][r] == Piece && board[c-1][r-1] == Piece && board[c-2][r-2] == Piece && board[c-3][r-3] == Piece)
                    return true;
            }
        }
        return false;
    }

    public static boolean isValid(int c) {
        if (c >= 0 && c < 7) {
            if (rowsHeight[c] >= 0) {
                return true;
            }
        }
        System.out.println("Illegal move, try again!");
        return false;
    }
}