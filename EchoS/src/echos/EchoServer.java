/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echos;


 
import conexion.conexionDB;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
         
      try {
      ServerSocket server = new ServerSocket(5566);

      while (true) {
        Socket client = server.accept();
        miThread thread = new miThread(client);
        thread.start();
       
      }
    }
    catch (Exception e) {
      System.err.println("Exception caught:" + e);
    }
  }
}