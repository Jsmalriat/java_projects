/*Author: Jonah Malriat
* Date: 10/12/2020
* Course: CIS 2168
* Project: Using a static sudoku puzzle, solves using recursion.*/

public class sudoku {

    public static boolean solve(int[][] board, int row, int col) {
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    for (int i = 1; i < 10; i++) {
                        if (valid(board, row, col, i)) {
                            board[row][col] = i;
                            if (solve(board, row, col)) {
                                return true;
                            }
                            else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
        

    public static boolean valid(int[][] board, int row, int col, int value) {
        if (sameRow(board, row, col, value) || sameCol(board, row, col, value) || sameBox(board, row, col, value)) {
            return false;
        }
        else {
            return true;
        }
    }    

    public static boolean sameRow(int[][] board, int row, int col, int value) {
        if (col == 0) {
            for (int i = col + 1; i < board[row].length; i++) {
                if (value == (board[row][i])) {
                    return true;
                }
            }
        }
        else if (col== 8) {
            for (int i = col - 1; i >= 0; i--) {
                if (value == (board[row][i])) {
                    return true;
                }
            }
        }
        else {
            for (int i = col + 1; i < board.length; i++) {
                if (value == (board[row][i])) {
                    return true;
                }
            }
            for (int i = col - 1; i >= 0; i--) {
                if (value == (board[row][i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean sameCol (int[][] board, int row, int col, int value) {
        if (row == 0) {
            for (int i = row + 1; i < board.length; i++) {
                if (value == (board[i][col])) {
                    return true;
                }
            }
        }
        else if (row == 8) {
            for (int i = row - 1; i >= 0; i--) {
                if (value == (board[i][col])) {
                    return true;
                }
            }
        }
        else {
            for (int i = row + 1; i < board.length; i++) {
                if (value == (board[i][col])) {
                    return true;
                }
            }
            for (int i = row - 1; i >= 0; i--) {
                if (value == (board[i][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean sameBox (int[][] board, int row, int col, int value) {
        if ((row >= 0) && (row <= 2)) {
            if ((col >= 0) && (col <= 2)) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else if ((col >= 3) && (col <= 5)) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
        }
        else if ((row >= 3) && (row <= 5)) {
            if ((col >= 0) && (col <= 2)) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else if ((col >= 3) && (col <= 5)) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
        }
        else {
            if ((col >= 0) && (col <= 2)) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else if ((col >= 3) && (col <= 5)) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
            else {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if (value == (board[i][j])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int[][] createSudoku () {
        int[][] board = new int[][] {
            {0, 0, 3, 0, 2, 0, 6, 0, 0},
            {9, 0, 0, 3, 0, 5, 0, 0, 1},
            {0, 0, 1, 8, 0, 6, 4, 0, 0},
            {0, 0, 8, 1, 0, 2, 9, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 6, 7, 0, 8, 2, 0, 0},
            {0, 0, 2, 6, 0, 9, 5, 0, 0},
            {8, 0, 0, 2, 0, 3, 0, 0, 9},
            {0, 0, 5, 0, 1, 0, 3, 0, 0}
        };
        return board;
    }

    public static void printSudoku(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(" |");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("  ------------------------------------");
        }
    }

    public static void main(String[] args) {
        int[][] board = createSudoku();
        printSudoku(board);
        System.out.println();
        int row = 0;
        int col = 0;
        solve(board, row, col);
        printSudoku(board);
    }
}