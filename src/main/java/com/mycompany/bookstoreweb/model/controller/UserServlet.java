/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreweb.model.controller;

import com.mycompany.bookstoreweb.model.bean.User;
import com.mycompany.bookstoreweb.model.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amethyst
 */
public class UserServlet extends HttpServlet{
    
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

        String action = request.getPathInfo();
        Logger.getLogger(UserServlet.class.getName()).log(Level.INFO, "Path solicitado: {0}", action);

        try {
            switch (action) {
                case "/new":
                    showNewUserForm(request, response);
                    break;

                case "/insert":
                    insertUserAction(request, response);
                    break;

                case "/edit":
                    showEditUserForm(request, response);
                    break;

                case "/update":
                    updateUserAction(request, response);

                case "/delete":
                    deleteUserAction(request, response);
                    break;

                case "/list":
                default:
                    listUser(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    /**
     * Exibe a lista completa de livros
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        UserDAO userDAO = new UserDAO();
        List<User> listaUser = userDAO.getResults();

        Logger.getLogger(UserDAO.class.getName()).log(Level.INFO,
                "Total de registros: {0}", listaUser.size());

        request.setAttribute("listaUsers", listaUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserList.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserForm.jsp");
        dispatcher.forward(request, response);

    }

    private void insertUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        UserDAO userDAO = new UserDAO();
        User novoUser = new User();
        novoUser.setEmail(request.getParameter("formEmail"));
        novoUser.setPassword(request.getParameter("formPassword"));
        novoUser.setFullname(request.getParameter("formFullname"));

        userDAO.create(novoUser);
        response.sendRedirect("list");
    }

    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User atualizaUser = userDAO.getResultById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserForm.jsp");
        request.setAttribute("user", atualizaUser);
        dispatcher.forward(request, response);

    }

    private void updateUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        UserDAO userDAO = new UserDAO();
        User userAtualizado = new User();

        userAtualizado.setId(Integer.parseInt(request.getParameter("formId")));
        userAtualizado.setEmail(request.getParameter("formEmail"));
        userAtualizado.setPassword(request.getParameter("formPassword"));
        userAtualizado.setFullname(request.getParameter("formFullname"));

        userDAO.update(userAtualizado);
        response.sendRedirect("list");
    }

    private void deleteUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO userDAO = new UserDAO();
        userDAO.delete(id);
        response.sendRedirect("list");
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

