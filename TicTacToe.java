import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe
 */
public class TicTacToe {
    static HashMap<Integer, Position> positionMap = new HashMap<>();    
    
    public static void main(String[] args) {

        positionMap.put(1, new Position(0, 0));
        positionMap.put(2, new Position(0, 1));
        positionMap.put(3, new Position(0, 2));
        positionMap.put(4, new Position(1, 0));
        positionMap.put(5, new Position(1, 1));
        positionMap.put(6, new Position(1, 2));
        positionMap.put(7, new Position(2, 0));
        positionMap.put(8, new Position(2, 1));
        positionMap.put(9, new Position(2, 2));

        char[][] board = {{' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '}};

        boolean keepGoing = true;
        Random randomMove = new Random();

        while (true) {
            printBoard(board);
            int move;
            while (true) {
                move = promptForInput();
                Position position = positionMap.get(move);
                if (checkMove(board, position) == true) {
                    board[position.getRow()][position.getCol()] = 'X';
                    if (checkWin(board, 'X') == true) {
                        keepGoing = false;
                    }
                    break;
                } else {
                    System.out.println("Invalid move.");
                }
            }
            if (keepGoing == false) {
                printBoard(board);
                System.out.println("You've won!");
                break;
            }
            System.out.println("Computer's turn...");
            while (true) {
                move = randomMove.nextInt(8) + 1;
                Position position = positionMap.get(move);
                if (checkMove(board, position) == true) {
                    board[position.getRow()][position.getCol()] = 'O';
                    if (checkWin(board, 'O') == true) {
                        keepGoing = false;
                    }
                    break;
                } 
            }
            if (keepGoing == false) {
                printBoard(board);
                System.out.println("You've Lost!");
                break;
            }
        }
        
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int row = 0; row < board.length; row++) {
            String rowPrint = "| ";
            for (int col = 0; col < board[row].length; col++) {
                rowPrint += board[row][col] + " | ";
            }
            System.out.println(rowPrint);
            if (row != board.length - 1 ) {
                System.out.println("|---|---|---|");
            }
        }
        System.out.println("-------------");
    }

    public static int promptForInput() {
        String digits = "123456789";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter move. (1-9)");
            String input = scanner.nextLine();
            if (digits.indexOf(input) != -1 && input.length() == 1) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Please enter a valid move.");
            }

        }
    }

    public static boolean checkMove(char[][] board, Position position) {
        if (board[position.getRow()][position.getCol()] == ' ') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        // check rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
            if (board[0][row] == player && board[0][row] == board[1][row] && board[1][row] == board[2][row]) {
                return true;
            }
        }
        if (board[0][0] == player && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] == player && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    
    public static void processMove() {

    }
}

