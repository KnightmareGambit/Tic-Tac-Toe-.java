package Question04;

import java.util.Scanner;

public class Main {
    public static final int BOARD_SIZE = 4;
    public static final char EMPTY = '-';
    public static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        Scanner input = new Scanner(System.in);
        boolean player1Turn = true;
        boolean gameOver = false;

        while (!gameOver) {
            char symbol = player1Turn ? 'X' : 'O';
            int row, col;

            do {
                System.out.print("Player " + (player1Turn ? "Amir (X)" : "Ali (O)") + ", enter row (1-" + BOARD_SIZE + ") and column (1-" + BOARD_SIZE + "): ");
                row = input.nextInt() - 1;
                col = input.nextInt() - 1;
            } while (!isValidMove(row, col));

            board[row][col] = symbol;
            printBoard();

            if (isWinner(symbol)) {
                System.out.println("Player " + (player1Turn ? "Amir (X)" : "Ali (O)") + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                System.out.println("It's a tie!");
                gameOver = true;
            } else {
                player1Turn = !player1Turn;
            }
        }

        input.close();
    }

    public static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("Invalid move! Row and column must be between 1 and " + BOARD_SIZE + ".");
            return false;
        }
        if (board[row][col] != EMPTY) {
            System.out.println("Invalid move! Cell is already occupied.");
            return false;
        }
        return true;
    }

    public static boolean isWinner(char symbol) {

        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != symbol) {
                    rowWin = false;
                }
                if (board[j][i] != symbol) {
                    colWin = false;
                }
            }
            if (rowWin || colWin) {
                return true;
            }
        }

        boolean diagonal1Win = true;
        boolean diagonal2Win = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] != symbol) {
                diagonal1Win = false;
            }
            if (board[i][BOARD_SIZE - 1 - i] != symbol) {
                diagonal2Win = false;
            }
        }
        return diagonal1Win || diagonal2Win;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
