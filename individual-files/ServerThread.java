import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread {
  public static void main(String args[]) throws Exception {
    int PORT = 5555;      // Open port 5555

    //open socket to listen
    ServerSocket server = new ServerSocket(PORT);
    Socket client = null;

    while (true) {
      System.out.println("Waiting for client...");

      // open client socket to accept connection
      client = server.accept();

      System.out.println(client.getInetAddress()+" contacted ");
      System.out.println("Creating thread to serve request");

      SThread student = new SThread(client);
      student.start();
    }
  }
}

public class SThread extends Thread {
    Socket client; 

    public SThread(Socket x) {  
        client = x;  
    }

    public void run() {
        // create object to send information to client
        PrintWriter out = new  PrintWriter(client.getOutputStream(),true);

        out.println("Student name: ");//send text to client;
    }
}

