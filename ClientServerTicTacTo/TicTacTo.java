package ClientServerTicTacTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Tic Tac To project to practice basic networking
 * skills recently learned in class. 
 */
public abstract class TicTacTo {
    /**
     * {@link ServerSocket}, only used for Server side of connection
     * to create the socket.
     */
    private ServerSocket serverSocket;
    /**
     * {@link Socket} for the conection between client and server.
     */
    private Socket socket;
    /**
     * {@link BufferedReader} to read input from opponent.
     */
    private BufferedReader reader;
    /**
     * {@link PrintWriter} to send output to opponent.
     */
    private PrintWriter writer;
    /**
     * The current board.
     */
    private String[][] board;
    /**
     * {@link Scanner} to take user input for where they want to place a piece on the board.
     */
    private Scanner userScanner;
    /**
     * "X" or "O", whichever the user is. 
     */
    private String team;
    /**
     * "X" or "O", opposite of the user. 
     */
    private String opponent;

    /**
     * Constructor for client side.
     * @param host host ip for connection, in this game localhost.
     * @param port port number for connection, in this game 1738.
     * @param team "X" or "O".
     * @throws IOException Takes care of all IOExceptions.
     */
    public TicTacTo(String host, int port,String team) throws IOException{
        
        this.socket = new Socket(host,port);
        this.serverSocket = null;

        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        this.reader = new BufferedReader(ir);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }
        this.userScanner = new Scanner(System.in);
        this.team = team;
        if(this.team.equals("X")){
            this.opponent = "O";
        } else {
            this.opponent= "X";
        }
    }
    /**
     * Constructor for server side.
     * @param port port for connection, in this game 1738.
     * @param team "X" or "O".
     * @throws IOException Takes care of all IOExceptions.
     */
    public TicTacTo(int port,String team) throws IOException{
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        this.reader = new BufferedReader(ir);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        //creates and fills blank board
        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }

        this.userScanner = new Scanner(System.in);
        this.team = team;
        if(this.team.equals("X")){
            this.opponent = "O";
        } else {
            this.opponent= "X";
        }
    }
    /**
     * Prompts user for a spot to pick on the board (1-9).
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 
     * Then validates if the entered choice is between 1 and 9, aswell as 
     * if the spot is empty. 
     */
    public void myTurn(){
        System.out.println("Pick a spot (1-9)");

        //validates user choice
        int userChoice = -1;
        while(userChoice == -1){
            userChoice = userScanner.nextInt();
            userChoice--;
            if(userChoice < 0 || userChoice > 8){
                System.out.println("Pick a number 1-9");
                userChoice = -1;
            } else if(!board[((int)userChoice/3)][userChoice % 3].equals("-")){
                System.out.println("Spot already taken");
                userChoice = -1;
            }
        }
        //updates board
        board[((int)userChoice/3)][userChoice % 3] = team;
        //sends choice to other user
        send(userChoice);
    }

    /**
     * Receives opponents selection, and updates the board.
     * @throws IOException Takes care of all IOExceptions.
     */
    public void oppTurn() throws IOException{
        int oppChoice = receive();
        board[((int)oppChoice/3)][oppChoice % 3] = opponent;
    }

    /**
     * Checks the board to see if a user has won.
     * @return true if a user has obtained 3 in a row, false otherwise.
     * This method is called after every turn, so whomever is calling this method
     * will know who won the game based off of the previous turn. 
     */
    public boolean checkWin(){
        for(int i = 0;i<3;i++){
            if(!board[i][0].equals("-") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])){
                return true;
            }else if(!board[0][i].equals("-") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])){
                return true;
            }
        }
        if(!board[0][0].equals("-") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])){
            return true;
        } else if(!board[0][2].equals("-") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])){
            return true;
        }
        return false;
    }
    /**
     * Check's for a cat game (draw)
     * @return true if the game is a cat game, false if not.
     */
    public boolean checkCatGame(){
        int count = 0;
        for(String[] row : board){
            for(String spot : row){
                if(!spot.equals("-")){
                    count++;
                }
            }
        }
        if(count == 9){
            return true;
        }
        return false;
    }

    /**
     * Sends a chosen spot to the other player. 
     * @param spot Already validated spot that the user has chosen. 
     */
    private void send(int spot){
        writer.println(spot);
        writer.flush();
    }

    /**
     * Receive opponent's choice of spot.
     * @return returns opponent's choice. 
     * @throws IOException Takes care of all IOExceptions.
     */
    private int receive() throws IOException{
        return Integer.parseInt(reader.readLine());
    }

    /**
     * Prints the board in a 3x3 manner.
     */
    public void printBoard(){
        for(String[] line : board){
            for(String spot : line){
                System.out.print(spot + " ");
            }
            System.out.println();
        }
    }

    /**
     * Closes all open sockets, scanners, readers, and writers.
     * @throws IOException Takes care of all IOExceptions.
     */
    public void close() throws IOException{
        if(serverSocket!=null){
            serverSocket.close();
        }
        socket.close();
        writer.close();
        reader.close();
        userScanner.close();
    }

    /**
     * Creates while loop to play the game, implemented differently based
     * on server/client, client plays first, server plays second
     * @throws IOException Takes care of all IOExceptions
     */
    public abstract void playGame() throws IOException;
}
