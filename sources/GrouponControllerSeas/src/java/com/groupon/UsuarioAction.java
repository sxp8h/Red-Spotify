package com.groupon;

import interfaces.Action;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.Cliente;
import model.service_DAO.ServiceClienteDAO;
import model.utils.MotorSQL;

public class UsuarioAction implements Action{

    @Override
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) {
        // ACTION=CLIENTE.ADD
        // ACTION=CLIENTE.DELETE
        // ACTION=CLIENTE.UPDATE
        // ACTION=CLIENTE.LISTAR
        // ...
        String pagDestino = "";
                                // PHP $_POST[ACTION]
        String action = (String) request.
                getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        //[USUARIO][LOGIN]
        if(arrayAction[1].equals("LOGIN")){
            pagDestino = login(request, response);
        }
        return pagDestino;
    }
    private String login(
            HttpServletRequest request, 
            HttpServletResponse response){
    // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))
        String user = (String)
                request.getParameter("USER");
        String pass = (String)
                request.getParameter("PASSWORD");
        
        Cliente miCliente = new Cliente();
            miCliente.setEmail(user);
            miCliente.setPassword(pass);
        
        ServiceClienteDAO dao = new ServiceClienteDAO(new MotorSQL());
        
        ArrayList<Cliente> lstClientes
                = null;
        
        try {
            lstClientes = 
                    dao.findAllPl(miCliente);
        } catch (Exception ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Configuro datos que voy a enviar al
        // Cliente Web
        //request.getParameter
        //request.setAttribute("LISTA_CLIENTES", lstClientes);
        //request.setAttribute("MENSAJE", "Usuario Correcto");
        //String pagDestino = "/index.jsp";
        return Cliente.toArrayJSon(lstClientes);
    }
    
}
