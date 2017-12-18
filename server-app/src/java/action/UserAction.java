/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.User;
import interfaces.Action;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
//  ::::::::  :::    ::: :::::::::   ::::::::  :::    :::
// :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+:
// +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+
// +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++
//        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+
// #+#    #+# #+#    #+# #+#        #+#    #+# #+#    #+#
//  ########  ###    ### ###         ########  ###    ###
//
//          --------Created by SXP8H--------

public class UserAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("ACTION");
        String[] actionArray = action.split("\\.");
        switch(actionArray[1]){
            case "LOGIN":
                return login(request, response);
        }
        return null;
    }
    
    private String login(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))
        int idUser = -1;
        String user = (String)request.getParameter("USER");
        String pass = (String)request.getParameter("PASSWORD");

        User userLoging = new User();
        userLoging.setEmail(user);
        userLoging.setPassword(pass);

        ArrayList<User> lstUsers = null;

        try {
            lstUsers = userLoging.read();
            for(int i=0;i<lstUsers.size();i++) {
                System.out.println(lstUsers.get(i).toString());
                if(lstUsers.get(i).getEmail().equals(user) && lstUsers.get(i).getPassword().equals(pass)){
                    idUser = lstUsers.get(i).getIdUser();
                }
            }
            lstUsers.clear();
            lstUsers = userLoging.read(idUser);

            for(int i=0;i<lstUsers.size();i++) {
                System.out.println(lstUsers.get(i).toString());
                if(lstUsers.get(i).getEmail().equals(user) && lstUsers.get(i).getPassword().equals(pass)){
                    idUser = lstUsers.get(i).getIdUser();
                }
            }
            userLoging.setIdUser(idUser);
            jsonResponse = userLoging.toArrayJson(lstUsers);
        } catch (Exception ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Configuro datos que voy a enviar al
        // Cliente Web
        //request.getParameter
        //request.setAttribute("LISTA_CLIENTES", lstClientes);
        //request.setAttribute("MENSAJE", "Usuario Correcto");
        //String pagDestino = "/index.jsp";
        return jsonResponse;
    }
}
