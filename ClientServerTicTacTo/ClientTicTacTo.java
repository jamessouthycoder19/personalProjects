package ClientServerTicTacTo;

import java.io.IOException;

public class ClientTicTacTo extends TicTacTo{

    public ClientTicTacTo(String host,int port) throws IOException{
        super(host,port);
    }

    public static void main(String[] args) throws IOException{
        ClientTicTacTo client = new ClientTicTacTo("localhost", 1738);
        client.printBoard();
        client.close();
    }
}
