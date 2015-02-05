package com.orangebyte256.server.utils;

/**
 * Created by dmitry on 2/3/2015.
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class SocketProcessor implements Runnable {

    private Socket s;
    private InputStream is;
//    private OutputStream os;

    SocketProcessor(Socket s){
        this.s = s;
        try
        {
            this.is = s.getInputStream();
        }
        catch(Throwable e)
        {
            System.out.print("error,trust me");
        }
//        this.os = s.getOutputStream();
    }

    public void run()
    {
        try
        {
/*            readInputHeaders();
            writeResponse("<html><body><h1>Hello from Habrahabr</h1></body></html>");
            */
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true)
            {
                String s = br.readLine();
                if(s == null || s.trim().length() == 0)
                {
                    break;
                }
                System.out.print(s);
            }
        } catch (Throwable t) {
                /*do nothing*/
        } finally {
            try {
                s.close();
            } catch (Throwable t) {
                    /*do nothing*/
            }
        }
        System.err.println("Client processing finished");
    }

/*    private void writeResponse(String s) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }

    private void readInputHeaders() throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0) {
                break;
            }
        }
    }
    */
}

public class Server
{
    public Server(int port)
    {
        try
        {
            ServerSocket ss = new ServerSocket(port);
            while (true)
            {
                Socket s = ss.accept();
                System.err.println("Client accepted");
                new Thread(new SocketProcessor(s)).start();
            }
        }
        catch (Throwable e)
        {
            d;
            System.err.println("Client accepted");
        }
    }
}

