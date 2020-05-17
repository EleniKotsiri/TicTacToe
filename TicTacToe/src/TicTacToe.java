import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    // *****************************************************************************
    // main method starts here
    public static void main(String args[]) {
        // create tic tac toe gameboard
        char[][] gameBoard = {  {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '} };
        printGameBoard(gameBoard);

        // start playing
        String result = "";
        while(result.length() <= 0) {
            // user fills gameBoard
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9)");
            int playerPos = scan.nextInt();
            // check if position is taken
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken! Enter correct position");
                playerPos = scan.nextInt();
            }
            System.out.println(playerPos);

            placePiece(playerPos, gameBoard, "player");

            result = checkWinner();

            // cpu fills gameBoard
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            // check if position is taken
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(cpuPos, gameBoard, "cpu");

            printGameBoard(gameBoard);

            // check if someone won. If someone did, exit
            result = checkWinner();

        }
        System.out.println(result);
    }
    // *****************************************************************************

    // printGameBoard method to print gameBoard
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

    }
    // *****************************************************************************

    // placePiece method to put a piece in gameBoard starts here
    public static void placePiece(int position, char[][] gameBoard, String user) {
        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default: break;
        }
    }
    // *******************************************************************************

    // checkWinner method to check if someone won starts here
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(botRow);
        winning.add(midRow);
        winning.add(leftCol);
        winning.add(rightCol);
        winning.add(midCol);
        winning.add(cross1);
        winning.add(cross2);

        // check if player fits in any List to win
        for(List l: winning){
            if(playerPositions.containsAll(l))
                return "Congratulations, you won!";
            else if(cpuPositions.containsAll(l))
                return "CPU wins! Sorry :(";
            else if(playerPositions.size() + cpuPositions.size() == 9)
                return "CAT!";
        }

        return "";
    }
    // *****************************************************************************


}
