package conexionjdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ingredientes {

    public static void main(String[] args) {
        
        /** referenciar el usuario y password con el cual me conectaré a la base de datos */
        String usuario = "root"; 
        String password = "Azabache1#";
        String url= "jdbc:mysql://localhost:3306/recetario_luzdelicias";
        
        /** establecer conexión */
        
        Connection conexion; 
        Statement statement; /** permite ejecutar sentencias sql a través de la conexión establecida */
        ResultSet rs; /** sirve como objeto que tiene la capacidad de recibir la respuesta desde la base de datos, como el reflejo de una tabla */
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");         
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        /** establecer la conexion*/
        try {            
            /** metodos */
            conexion = DriverManager.getConnection(url,usuario,password);
            statement = conexion.createStatement();           
            
            /**INSERTAR información a la base de datos, se debe cambiar el id cada vez que se corra el codigo para insertar un nuevo dato por ser llave primaria*/
            
            statement.executeUpdate("INSERT INTO INGREDIENTES(id_ingrediente, nombre_ingrediente, cantidad, unidad_de_medida) VALUES ('26', 'pierna de pato', '2', 'kilo')");
            rs = statement.executeQuery("SELECT * FROM INGREDIENTES");             
            System.out.println( "elemento añadido"); /** mensaje */                
            
            rs.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** CONSULTAR */        
        
        try {            
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recetario_luzdelicias","root","Azabache1#");
            PreparedStatement sentencia = cn.prepareStatement("SELECT * FROM INGREDIENTES WHERE ID_INGREDIENTE = ?");
            sentencia.setString(1, "18");
            ResultSet crs =sentencia.executeQuery();
            crs.next();
            do {
                System.out.println(
                        crs.getInt("id_ingrediente") + " " + crs.getString("nombre_ingrediente") + " " + crs.getInt("cantidad") + " " + crs.getString("unidad_de_medida"));
            } while(crs.next());            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** EDITAR */
        
        try {            
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3306/recetario_luzdelicias","root","Azabache1#");
            PreparedStatement sentenciaEditar = cne.prepareStatement("UPDATE INGREDIENTES SET NOMBRE_INGREDIENTE =? WHERE ID_INGREDIENTE = ?");
            sentenciaEditar.setString(1, "jugo de manzana");
            sentenciaEditar.setString(2, "10");
            int edt = sentenciaEditar.executeUpdate();
            System.out.println("Elemento editado: " + edt);                     
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** ELIMINAR */
        
        try {            
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3306/recetario_luzdelicias","root","Azabache1#");
            PreparedStatement sentenciaEliminar = cne.prepareStatement("DELETE FROM INGREDIENTES WHERE ID_INGREDIENTE = ?");
            sentenciaEliminar.setString(1, "2");
            int elm = sentenciaEliminar.executeUpdate();
            
            System.out.println("Elemento eliminado: " + elm);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** LISTAR */
        
        try {            
            Connection cnl = DriverManager.getConnection("jdbc:mysql://localhost:3306/recetario_luzdelicias","root","Azabache1#");
            PreparedStatement sentenciaListar = cnl.prepareStatement("SELECT * FROM INGREDIENTES");            
            ResultSet lrs =sentenciaListar.executeQuery();
            lrs.next();
            do {
                /**System.out.println( "ID" + "NOMBRE" + "CANTIDAD" + "UNIDAD");*/
                System.out.println(
                        lrs.getInt("id_ingrediente") + " " + lrs.getString("nombre_ingrediente") + " " + lrs.getInt("cantidad") + " " + lrs.getString("unidad_de_medida"));
            } while(lrs.next());            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingredientes.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
                        
    }
    
}
