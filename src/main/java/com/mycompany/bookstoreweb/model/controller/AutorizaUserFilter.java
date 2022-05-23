/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreweb.model.controller;

import com.mycompany.bookstoreweb.model.bean.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amethyst
 */
@WebFilter(filterName = "AutorizaUserFilter", urlPatterns = {"/bstore/*"})
public class AutorizaUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                "AutorizarUserFilter Iniciado!!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //Carrega a session caso exista.
        HttpSession session = httpRequest.getSession(false);
        boolean isUsuarioLogado = (session != null && session.getAttribute("user") != null);

        if (isUsuarioLogado) {
            //Tudo ok! Usuario com session autorizado e segue requisição.
            User userLogado = (User) session.getAttribute("user");
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                    "Usuario autenticado: {0}", userLogado.getEmail());

            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                    "Carrega proximo Filtro ou Servlet - chain.doFilter()");

            chain.doFilter(request, response);
        } else {
            //ops... usuario não autenticado: Redirecionar para página de login
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                    "Usuario não autenticado");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
            dispatcher.forward(request, response);
        }

        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                "*** Pos-Filtro ***");
    }

    @Override
    public void destroy() {
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
                "AutorizarUserFilter Destruído!!!");
    }

}
