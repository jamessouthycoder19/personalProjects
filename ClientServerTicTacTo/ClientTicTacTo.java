package ClientServerTicTacTo;

import java.io.IOException;

public class ClientTicTacTo extends TicTacTo{
    /**
     * Stays false untill the game is over, then is changed to true
     * so that the while loop in playGame() ends. 
     */
    private boolean gameOver = false;

    /**
     * Constructor to create client side of tic tac to game.
     * @param host host name for connection, in this game localhost.
     * @param port port number for connection, in this game 1738.
     * @throws IOException Takes care of all IOExceptions
     */
    public ClientTicTacTo(String host,int port) throws IOException{
        super(host,port,"O");
    }

    /**
     * Uses a while loop to play the game, exits once one of the two players
     * has won or a draw occurs. 
     */
    public void playGame() throws IOException{
        printBoard();
        while(!gameOver){
            System.out.println();
            myTurn();
            System.out.println();
            printBoard();
            System.out.println();
            if(checkWin()){
                gameOver = true;
                System.out.println("You have Won!");
                break;
            }
            System.out.println("Opponent's turn:");
            oppTurn();
            printBoard();
            if(checkWin()){
                gameOver = true;
                System.out.println("You have lost :(");
            }
        }
    }

    public static void main(String[] args) throws IOException{
        ClientTicTacTo client = new ClientTicTacTo("localhost", 1738);
        client.playGame();
    }
}
