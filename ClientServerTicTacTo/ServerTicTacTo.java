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
        printBoard();
        while(!gameOver){
            System.out.println();
            System.out.println("Opponent's move:");
            oppTurn();
            printBoard();
            System.out.println();
            if(checkWin()){
                gameOver = true;
                System.out.println("You have lost :(");
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
