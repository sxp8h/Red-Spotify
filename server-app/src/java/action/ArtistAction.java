/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.Artist;
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

public class ArtistAction implements Action{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("ACTION");
        String[] actionArray = action.split("\\.");
        switch(actionArray[1]){
            case "DELETE":
                return delete(request, response);
            case "CREATE":
                return create(request, response);
            case "MODIFY":
                return modify(request, response);
            case "FIND_ALL":
                return findAll(request, response);
            case "FIND_FILTER":
                return findFilter(request, response);
        }
        return null;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))
        int idUser = -1;

        Artist findAll = new Artist();
        ArrayList<Artist> lstArtist = null;
        
        try {
            lstArtist = findAll.read();
        }catch(Exception e){
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        jsonResponse = findAll.toArrayJson(lstArtist);
        
        return jsonResponse;

    }    
    private String findFilter(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))

        Artist findAll = new Artist();
        ArrayList<Artist> lstArtist = null;
        String name = (request.getParameter("NAME") == null || request.getParameter("NAME").equals("")) ? null : request.getParameter("NAME");
        String nationality = (request.getParameter("NATIONALITY") == null || request.getParameter("NATIONALITY").equals("")) ? "<EMPTY>" : request.getParameter("NATIONALITY");
        nationality = nationality.toUpperCase();
        try {
            lstArtist = findAll.read(name, nationality);
        }catch(Exception e){
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        jsonResponse = findAll.toArrayJson(lstArtist);
        
        return jsonResponse;

    }    

    private String delete(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))
        int idUser = Integer.parseInt(request.getParameter("ID_ARTIST"));
        Artist aux = new Artist();
        int ret = aux.delete(idUser);
        return aux.toArrayJson(aux.read());
    }
    
    private String create(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        // Recuperar los parámetros desde la página web
        // Objeto + Método (request.getParameter("))
        int idUser = -1;
        Artist aux = new Artist();
        String nationality = request.getParameter("NATIONALITY").toUpperCase();
        aux.setName((String)request.getParameter("NAME"));
        aux.setUrl((String)request.getParameter("URL"));
        aux.setNationality(nationality.toUpperCase());
        int ret = aux.create(aux);
        return "[{ "+ ret +" }]";
    }
    private String modify(HttpServletRequest request, HttpServletResponse response){
        String jsonResponse = "";
        Artist aux = new Artist();
        int parse = Integer.parseInt(request.getParameter("ID_ARTIST"));
        aux.setIdArtist(parse);
        String nationality = request.getParameter("NATIONALITY").toUpperCase();
        aux.setName((String)request.getParameter("NAME"));
        aux.setUrl((String)request.getParameter("URL"));
        aux.setNationality(nationality.toUpperCase());
        int ret = aux.update(aux);
        return "[{ "+ ret +" }]";
    }
}
