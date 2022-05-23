/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreweb.model.dao;

import com.mycompany.bookstoreweb.model.bean.User;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amethyst
 */
public class UserDAO {


    private static final String SQL_INSERT = "INSERT INTO user(email, "
            + "password, fullname)"
            + "VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user " + "WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE user SET email = ?,"
            + "password = ?, fullname = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";
    private static final String SQL_VERIFY = "SELECT * FROM user WHERE email = ? AND password = ?";

    /**
     * Insere um usuario na base de dados User
     * @param u
     */
    public void create(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }

    }
    /**
     * Retorna todos os registros da tabela user.
     * @return 
     */
    public List<User> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        List<User> listaUsers = null;

        try {
            //Prepara a string de SELECT e executa a query
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            //Carrega os dados de ResultSet rs, converte em Book e
            // adiciona na lista de retorno.
            listaUsers = new ArrayList<>();

            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                listaUsers.add(u);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsers;
    }

    /**
     * Retorna um produto de tabela produto.
     * @param id Identificação de User
     * @return
     */
    public User getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
            }

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return u;
    }

    /**
     * Atualiza um regidtro na tabela produto.
     *
     * @param u User a ser atualizado.
     */
    public void update(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setString(4, u.getFullname());

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);

        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }

    }

    /**
     * Exclui um user com base de ID fornecido
     *
     * @param id IDentficação do produto.
     */
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Delete: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);

        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Checa o login 
     * 
     * @param email
     * @param password
     * @return 
     */
    public User checkLogin(String email, String password) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {
            stmt = conn.prepareStatement(SQL_VERIFY);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
            }

        } catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return u;
    }

}

