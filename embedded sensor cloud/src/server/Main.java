package server;

//import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
    	HttpServer myServer = new HttpServer();
 
    	myServer.listenAndProcess();    // Server starts and waits for clients to accept
    }
}