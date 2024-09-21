/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bumperballs;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;




public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "";
    String bd = "bumper_balls";
    String ip = "localhost";
    String puerto = "3306";
    
    
    String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
   
    
    public Connection estableceConexion (){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,contrasena);
            //JOptionPane.showMessageDialog(null, "Se conecto a la base de datos BUMPER BALLS");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "NO se conecto a la base de datos, error:" +e.toString());
            
        }
        
        return conectar;
        
    }
}