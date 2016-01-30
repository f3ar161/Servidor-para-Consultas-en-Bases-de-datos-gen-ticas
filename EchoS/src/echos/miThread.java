/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echos;

import conexion.conexionDB;
import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class miThread extends Thread{
    Socket client;
     miThread (Socket client) {
        this.client = client;
     }
    public void run(){
     long threadId = Thread.currentThread().getId();
     Connection miConexion;
     miConexion= conexionDB.GetConnection();
     String CHROM="",POS="",ID="", REF="",ALT="", QAL="", FILTER="",INFO="",FORMAT="",SAMPLE1="",SAMPLE2="",SAMPLE3="",RESP="";
        try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
      //writer.println("");
      
      while (true) {
        
        String line = reader.readLine();
        if (line.trim().equals("desconectar")) {
          writer.println("Desconectado");
          break;
        }
         System.out.println(" "+threadId +": "+line);
         if(miConexion!=null)
        {
            //JOptionPane.showMessageDialog(null,threadId + "Procesando consulta");
            System.out.println("Cliente: "+threadId + ", Procesando consulta" );
                StringTokenizer lineaTrozeada = new StringTokenizer(line, " ");
                try {
                while (lineaTrozeada.hasMoreTokens()){
                    CHROM = lineaTrozeada.nextToken();
                    POS = lineaTrozeada.nextToken();
                    ID = lineaTrozeada.nextToken();
                    REF = lineaTrozeada.nextToken();
                    ALT = lineaTrozeada.nextToken();
                    QAL = lineaTrozeada.nextToken();
                    FILTER = lineaTrozeada.nextToken();
                    INFO = lineaTrozeada.nextToken();
                    FORMAT = lineaTrozeada.nextToken();
                    SAMPLE1 = lineaTrozeada.nextToken();
                    SAMPLE2 = lineaTrozeada.nextToken();
                    SAMPLE3 = lineaTrozeada.nextToken();
                }
                
               
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Consulta finalizada");
                }
                   ResultSet resultados = conexionDB.consultar("SELECT NAME_DNA FROM genes.dna where ID='"+ID+"' and ALT='"+ALT+"'");        
                if (resultados != null) {
                try {
                    while (resultados.next()) {
                        RESP= resultados.getString("NAME_DNA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writer.println("[Resultado de consulta: ] " + RESP +" #CHROM: "+ CHROM);
        }
       
        
        
        
      }
    }
    catch (Exception e) {
      System.err.println("cliente desconectado");
    }
    finally {
      try { client.close(); }
      catch (Exception e ){ ; }
    }
        
        
  
    }
    
}
