/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bumperballs;


import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CClientes {
    
      int IdCliente;
    String nombres;
    String apellidos;
    String telefono;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 
 
   
public void InsertarCliente(JTextField paramNombres, JTextField paramApellidos, JTextField paramTelefono){
    
    setNombres(paramNombres.getText());
    setApellidos(paramApellidos.getText());
    setTelefono(paramTelefono.getText());
    
    
    CConexion objetoCConexion = new CConexion();
    
    
    String consulta = "insert into clientes (nombres, apellidos, telefono) values (?,?,?);" ;
    
    try {
        
        CallableStatement cs = objetoCConexion.estableceConexion().prepareCall(consulta);
        
        cs.setString(1, getNombres());
        cs.setString(2, getApellidos());
        cs.setString(3, getTelefono());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
        
        
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "Cliente NO se registro correctamente. Error: " +e.toString());
        
    }  
    
}
    
    public void MostrarClientes(JTable paramTablaTotalClientes){

            CConexion objetoCConexion = new CConexion(); 
            
            DefaultTableModel modelo = new DefaultTableModel();
            
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            paramTablaTotalClientes.setRowSorter(OrdenarTabla);
            
            String sql ="select * from clientes;";
            
            modelo.addColumn("IdCliente");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Telefono");
            
            
            
            paramTablaTotalClientes.setModel(modelo);
            
            String[] datos = new String[4];
            Statement st;
            
            
            try {
            
             st = objetoCConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
            
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                
                modelo.addRow(datos);
            
            }
            
            paramTablaTotalClientes.setModel(modelo);
                
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros. Error"+e.toString());
            
            
        }
      
     
          
        
 }   
 
    
    public void SeleccionarClientes(JTable paramTablaTotalClientes, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramTelefono){ 
      
      try {
          int fila = paramTablaTotalClientes.getSelectedRow();
          
          if (fila>=0){
              
          paramId.setText ((paramTablaTotalClientes.getValueAt(fila, 0).toString()));
          paramNombres.setText ((paramTablaTotalClientes.getValueAt(fila, 1).toString()));
          paramApellidos.setText ((paramTablaTotalClientes.getValueAt(fila, 2).toString()));
          paramTelefono.setText ((paramTablaTotalClientes.getValueAt(fila, 3).toString()));
      
          }
          else{
          
          JOptionPane.showMessageDialog(null,"Fila no seleccionada.");
          
          }
      } catch (Exception e) {
          
          JOptionPane.showMessageDialog(null,"Error de selección. Error:" +e.toString());
      }
  } 
    
    
    public void ModificarClientes(JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramTelefono) {
    
       setIdCliente(Integer.parseInt(paramId.getText()));
      setNombres(paramNombres.getText());
      setApellidos(paramApellidos.getText());
     setTelefono(paramTelefono.getText());
     
     
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE clientes SET clientes.nombres = ?, clientes.apellidos = ?, clientes.telefono = ? WHERE clientes.IdCliente=?;";
        
        try {
            
         CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
         
         cs.setString(1, getNombres());
         cs.setString(2, getApellidos());
         cs.setString(3, getTelefono());
        cs.setInt(4, getIdCliente());
            
         
         
         cs.execute();
         
         JOptionPane.showMessageDialog(null, "Modificación exitosa");
         
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Modificación NO exitosa Error:"+e.toString());
            
        }
     
    }
    
    public void EliminarClientes(JTextField paramCodigo){
    
    setIdCliente (Integer.parseInt(paramCodigo.getText()));
    
    
     CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Clientes WHERE clientes.IdCliente = ?;";
        
        try {
            
           CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
           cs.setInt(1, getIdCliente());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se elimino correctamente el registro.");
            
        } catch (Exception e) {
            
            
           JOptionPane.showMessageDialog(null, "NO se elimino correctamente el registro.Error:"+e.toString()); 
            
        }
    }
}
