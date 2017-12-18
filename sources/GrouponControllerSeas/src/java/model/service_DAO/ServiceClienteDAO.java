package model.service_DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Cliente;
import model.dao.DAO;
import model.utils.MotorSQL;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class ServiceClienteDAO implements DAO<Cliente, Integer> {

    private MotorSQL miConexion;

    public ServiceClienteDAO(MotorSQL miConexion) {
        this.miConexion = miConexion;
    }
private static final String SQL_FIND_ALL = 
        "SELECT * FROM USUARIO WHERE ";

@Override
public ArrayList<Cliente> findAll(Cliente cliente) throws Exception {
        this.miConexion.connect();
        String cabecera = SQL_FIND_ALL;
        String cuerpo = "";
        String sql = "";
        if (cliente.getEmail() != null) {
            if (cuerpo.equals("")) {
                cuerpo += "EMAIL LIKE '%" + 
                        cliente.getEmail() + "%'";
            } else {
                cuerpo += "AND EMAIL LIKE '%" + cliente.
                        getEmail() + "%'";
            }
        }
        if (cliente.getPassword()!= null) {
            if (cuerpo.equals("")) {
                cuerpo += "PASSWORD LIKE '%" + 
                        cliente.getPassword() + "%'";
            } else {
                cuerpo += "AND PASSWORD LIKE '%" + cliente.getPassword()
                        + "%'";
            }
        }
        sql = cabecera + cuerpo;
        ResultSet rs = this.miConexion.executeQuery(sql);
        ArrayList<Cliente> tabla = new ArrayList();
        while (rs.next()) {
            Cliente cliente2 = new Cliente();
            cliente2.setIdUser(rs.getInt(1));
            cliente2.setEmail(rs.getString(2));
            cliente2.setPassword(rs.getString(3));
            tabla.add(cliente2);
        }
        this.miConexion.disconnect();
        return tabla;

    }
public ArrayList<Cliente> findAllPl(Cliente cliente) throws Exception {
        this.miConexion.connect();
        Connection con = this.miConexion.getCon();
        String cabecera = SQL_FIND_ALL;
        String cuerpo = "";
        String sql = "";
        CallableStatement cs;
        ResultSet rs;
        
        cs = con.prepareCall("{CALL usuarios.existsUsuario(?, ?, ?)}");
        
        /*cs.setString(1, cliente.getEmail());
        cs.setString(2, cliente.getPassword());*/
        cs.setString(1, cliente.getEmail());
        cs.setString(2, cliente.getPassword());
        cs.registerOutParameter(3, OracleTypes.CURSOR);
        
        cs.execute();
        
        rs = (ResultSet)cs.getObject(3);

        
        ArrayList<Cliente> tabla = new ArrayList();
        while (rs.next()) {
            Cliente cliente2 = new Cliente();
            cliente2.setIdUser(rs.getInt(1));
            cliente2.setEmail(rs.getString(2));
            cliente2.setPassword(rs.getString(3));
            tabla.add(cliente2);
        }
        this.miConexion.disconnect();
        return tabla;

    }

    
    


}
