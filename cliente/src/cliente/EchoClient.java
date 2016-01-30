/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {

        String hostName = "192.168.80.144";
        int portNumber = 5566;
 
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput="74 4673 rs6319 G A 78 . NS=3;DP=12;AF=0.4;DB;H2 GT:GQ:DP 0|0:48:1:52,56 1|0:48:8:51,53 1/1:43:9";
            System.out.println("[escriba 'desconectar' para desconectarse]");
            int i=0;
            //while ((userInput = stdIn.readLine()) != null) {
            while ((i<=10000)) {
                out.println(userInput);
                
                i++;
            }
            System.out.println("echo: " + in.readLine());
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}