/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreweb.model.controller;

import com.mycompany.bookstoreweb.model.bean.User;
import com.mycompany.bookstoreweb.model.dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Amethyst
 */
public class UserLoginServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String auxEmail = request.getParameter("email");
        String auxPassword = request.getParameter("password");

        UserDAO userDAO = new UserDAO();

        try {
            User user = userDAO.checkLogin(auxEmail, auxPassword);
            //Define destino padão (pagina de login)
            String destPage = "/LoginPage.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "/bstore/list";
                Logger.getLogger(UserDAO.class.getName()).log(Level.INFO,
                        "Usuario Logado: {0}",
                        user.getEmail() + " | " + user.getFullname());

            } else {

                String msgAux = "Email ou Password invalido!!!";
                request.setAttribute("message", msgAux);

                Logger.getLogger(UserDAO.class.getName()).log(Level.INFO,
                        "Erro Login: {0}",
                        auxEmail);

            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,
                    "Servlet Exception na UserLoginServlet. {0}", ex);
            throw new ServletException(ex);

        }
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
