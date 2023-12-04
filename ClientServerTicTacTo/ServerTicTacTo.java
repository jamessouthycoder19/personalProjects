package ClientServerTicTacTo;

import java.io.IOException;

public class ServerTicTacTo extends TicTacTo{
    /**
     * Stays false untill the game is over, then is changed to true
     * so that the while loop in playGame() ends. 
     */
    private boolean gameOver = false;

    /**
     * Constructor to create server side of tic tac to game.
     * @param port port number for connection, in this game 1738.
     * @throws IOException Takes care of all IOExceptions
     */
    public ServerTicTacTo(int port) throws IOException{
        super(port,"X");
    }

    /**
     * Uses a while loop to play the game, exits once one of the two players
     * has won or a draw occurs. 
     */
    public void playGame() throws IOException{
        /**
         * Prints a board shown below for user ease of use.
         * 1 2 3
         * 4 5 6
         * 7 8 9
         */
        for(int i = 0;i<3;i++){
            for(int j = 1;j<4;j++){
                System.out.print(i*3+j + " ");
            }
            System.out.println();
        }
        System.out.println();
        //Prints the board.
        printBoard();
        //While loop that plays the game.
        while(!gameOver){
            System.out.println();
            System.out.println("Opponent's move:");
            oppTurn();
            printBoard();
            System.out.println();
            if(checkWin()){
                System.out.println("You have lost :(");
                break;
            }
            if(checkCatGame()){
                System.out.println("Cat game :(");
                break;
            }
            myTurn();
            System.out.println();
            printBoard();
            if(checkWin()){
                System.out.println();
                gameOver = true;
                System.out.println("You have Won!");
            }
        }
    }

    public static void main(String[] args) throws IOException{
        ServerTicTacTo server = new ServerTicTacTo(1738);
        server.playGame();
    }
    
}
