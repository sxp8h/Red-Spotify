package com.sxp8h;

import action.ArtistAction;
import action.UserAction;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String format = (String) request.getParameter("FORMAT");
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.
                split("\\.");
        String output = "";
        format = request.getParameter("FORMAT");
        switch (arrayAction[0]) {
            case "USERS":
                output = new UserAction().execute(request, response);
                break;
            case "ARTISTS":
                output = new ArtistAction().execute(request, response);
        }
        if (format == null) {
            format = "";
        }
        if (format.equals("JSP")) {
            response.setContentType("text/html;charset=UTF-8");
            request.
                    getRequestDispatcher(output)
                    .forward(request, response);
        } else if (format.equals("JSON")) {
            response.setContentType("text/plain;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(output);
            }
        }
        
        System.out.println("##########################################################");
        System.out.println("#  ::::::::  :::    ::: :::::::::   ::::::::  :::    ::: #");
        System.out.println("# :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+: #");
        System.out.println("# +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+ #");
        System.out.println("# +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++ #");
        System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
        System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
        System.out.println("#  ########  ###    ### ###         ########  ###    ### #");
        System.out.println("##########################################################");
    
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
