import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Initialize the Tic Tac Toe board
        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};

        // Print the initial empty board
        printBoard(board);

        // Display game welcome message and menu for choosing opponent
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Choose your opponent:");
        System.out.println("1. Play against the computer");
        System.out.println("2. Play against another player");

        // Read user's choice for opponent
        int choice = scanner.nextInt();

        // Branch based on user's choice
        if (choice == 1) {
            playAgainstComputer(board, scanner); // Play against the computer
        } else if (choice == 2) {
            playMultiplayer(board, scanner); // Play against another player
        } else {
            System.out.println("Invalid choice. Exiting the game."); // Invalid choice
        }

        // Close scanner to release resources
        scanner.close();
    }

    // Method to play against the computer
    private static void playAgainstComputer(char[][] board, Scanner scanner) {
        while (true) {
            playerTurn(board, scanner); // Player's turn
            if (isGameFinished(board)) { // Check if game is finished
                break;
            }
            printBoard(board); // Print the board after player's move

            computerTurn(board); // Computer's turn
            if (isGameFinished(board)) { // Check if game is finished
                break;
            }
            printBoard(board); // Print the board after computer's move
        }
    }

    // Method to play multiplayer mode
    private static void playMultiplayer(char[][] board, Scanner scanner) {
        while (true) {
            playerTurn(board, scanner, 'X'); // Player X's turn
            if (isGameFinished(board)) { // Check if game is finished
                break;
            }
            printBoard(board); // Print the board after player X's move

            playerTurn(board, scanner, 'O'); // Player O's turn
            if (isGameFinished(board)) { // Check if game is finished
                break;
            }
            printBoard(board); // Print the board after player O's move
        }
    }

    // Method to check if the game is finished
    private static boolean isGameFinished(char[][] board) {
        // Check if any player has won
        if (hasContestantWon(board, 'X')) {
            printBoard(board);
            System.out.println("Player X wins!");
            return true;
        }
        if (hasContestantWon(board, 'O')) {
            printBoard(board);
            System.out.println("Player O wins!");
            return true;
        }
        // Check if the game ended in a tie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("The game ended in a tie!");
        return true;
    }

    // Method to check if a contestant has won
    private static boolean hasContestantWon(char[][] board, char symbol) {
        // Check all possible winning combinations
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    // Method for the computer's turn
    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            // Generate a random move for the computer
            computerMove = rand.nextInt(9) + 1;
            if (isValidMove(board, Integer.toString(computerMove))) {
                break;
            }
        }
        System.out.println("Computer chose " + computerMove);
        placeMove(board, Integer.toString(computerMove), 'O'); // Place the computer's move on the board
    }

    // Method to validate if a move is valid
    private static boolean isValidMove(char[][] board, String position) {
        // Check if the position is empty on the board
        switch (position) {
            case "1":
                return (board[0][0] == ' ');
            case "2":
                return (board[0][1] == ' ');
            case "3":
                return (board[0][2] == ' ');
            case "4":
                return (board[1][0] == ' ');
            case "5":
                return (board[1][1] == ' ');
            case "6":
                return (board[1][2] == ' ');
            case "7":
                return (board[2][0] == ' ');
            case "8":
                return (board[2][1] == ' ');
            case "9":
                return (board[2][2] == ' ');
            default:
                return false;
        }
    }

    // Method for player's turn
    private static void playerTurn(char[][] board, Scanner scanner) {
        String userInput;
        while (true) {
            System.out.println("Where would you like to play? (1-9)");
            userInput = scanner.next();
            if (isValidMove(board, userInput)) {
                break;
            } else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeMove(board, userInput, 'X'); // Place the player's move on the board
    }

    // Method for player's turn in multiplayer mode
    private static void playerTurn(char[][] board, Scanner scanner, char symbol) {
        String userInput;
        while (true) {
            System.out.println("Player " + symbol + ", where would you like to play? (1-9)");
            userInput = scanner.next();
            if (isValidMove(board, userInput)) {
                break;
            } else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeMove(board, userInput, symbol); // Place the player's move on the board
    }

    // Method to place a move on the board
    private static void placeMove(char[][] board, String position, char symbol) {
        // Update the board with the player's move
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                System.out.println(":(");
        }
    }

    // Method to print the Tic Tac Toe board
    private static void printBoard(char[][] board) {
        // Print the board with current moves
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

}
