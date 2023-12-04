package ClientServerTicTacTo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicTacTo {
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    private String[][] board;

    public TicTacTo(String host, int port) throws IOException{
        this.socket = new Socket(host,port);
        this.serverSocket = null;
        InputStream in = socket.getInputStream();
        this.scanner = new Scanner(in);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }
    }
    public TicTacTo(int port) throws IOException{
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        this.scanner = new Scanner(in);

        OutputStream out = socket.getOutputStream();
        this.writer = new PrintWriter(out);

        this.board = new String[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = "-";
            }
        }
    }

    private void send(int spot){
        writer.println(spot);
        writer.flush();
    }

    private int receive(){
        return scanner.nextInt();
    }

    public void printBoard(){
        for(String[] line : board){
            for(String spot : line){
                System.out.print(spot + " ");
            }
            System.out.println();
        }
    }

    public void close() throws IOException{
        if(serverSocket!=null){
            serverSocket.close();
        }
        socket.close();
        scanner.close();
        writer.close();
    }
}
