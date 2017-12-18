package model.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MotorSQL {
        // ATRIBUTOS
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static final String URL = 
            "jdbc:oracle:thin:system/system@localhost:1521:orcl";
    private static final String CONTROLADOR = "oracle.jdbc.OracleDriver";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // MÉTODOS
    public void connect(){
        try {
            // Indico el controlador necesario en función de la base de datos
            Class.forName(CONTROLADOR);
            // Objeto que me devuelve una conexión a MySql
                // jdbc:mysql://localhost:3306/concesionario
                con = DriverManager.getConnection(URL);
                // Un objeto que me permite hablar con la base de datos
                st = con.createStatement();
        }catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    public Connection getCon() {
        return con;
    }
    
    public void disconnect(){
        try {
            // ResultSet
            if(rs!=null){ rs.close();}
            // Statement
           if(st!=null){ st.close();}
            // Connection
            if(con!=null){ con.close();}
        } catch (Exception ex) {
            System.out.println("Error al cerrar la conexión. ");
        }
    }
    public int execute(String sql){ 
        //INSERT INTO CLIENTE(USER,PASS) VALUES ('LUIS','1234')
        int resp = 0 ;
        try {
            resp = st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println("Error al modificar los datos. ");
        }
        return resp;
    }
    public ResultSet executeQuery(String sql){ // SELECT * FROM CLIENTE
       try {
            rs = st.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println("Error al consultar los datos. ");
        }
return rs;
   }
   

}
