/*Author: Jonah Malriat
* Date: 10/12/2020
* Course: CIS 2168
* Project: Creates a chess board, then solves the 8 queens problem using recursion.*/

public class chess {

    public static boolean solve(int[][] board, int pos) {
        if (pos == 8) {
            return true;
        }
        for (int i = 0; i < board[pos].length; i++) {
            int choice = i;
            board[pos][i] = 1;
            if (valid(board, choice, pos)) {
                if (solve(board, pos + 1) == true) {
                    return true;
                }
            }
            board[pos][i] = 0;
        }
        return false; // backtrack
    } 

    public static boolean valid(int[][] board, int choice, int pos) {
        if (pos == 0) {
            return true;
        }
        else {
            int diagCheck = 1;
            for (int i = (pos - 1); i >= 0; i--) {
                if ((board[pos][choice] == 1) && (board[i][choice] == 1)) {
                    return false;
                }
                if (choice + diagCheck < board[i].length) {
                    if ((board[pos][choice] == 1) && (board[i][choice + diagCheck] == 1)) {
                        return false;
                    }
                }
                if (choice - diagCheck >= 0) {
                    if ((board[pos][choice] == 1) && (board[i][choice - diagCheck] == 1)) {
                        return false;
                    }
                }
                diagCheck++;
            }
            return true;
        }
    }

    public static void createChess (int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int columns = 8;
        int rows = 8;
        int[][] board = new int[rows][columns];
        createChess(board);
        System.out.println();
        int pos = 0;
        solve(board, pos);
        createChess(board);
    }
}