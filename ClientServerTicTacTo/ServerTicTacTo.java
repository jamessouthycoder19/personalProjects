package ClientServerTicTacTo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTicTacTo {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter writer;
    private String[][] board;

    public ServerTicTacTo(String host,int port) throws IOException{
        this.serverSocket = new ServerSocket(port);
        this.clientSocket = serverSocket.accept();

        InputStream in = clientSocket.getInputStream();
        this.scanner = new Scanner(in);

        OutputStream out = clientSocket.getOutputStream();
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

    public void close() throws IOException{
        serverSocket.close();
        clientSocket.close();
        scanner.close();
        writer.close();
    }
    public static void main(String[] args) throws IOException{
        
    }
    
}
