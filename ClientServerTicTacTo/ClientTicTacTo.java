package ClientServerTicTacTo;

import java.io.IOException;

public class ClientTicTacTo extends TicTacTo{
    private boolean gameOver = false;

    public ClientTicTacTo(String host,int port) throws IOException{
        super(host,port,"O");
    }

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
