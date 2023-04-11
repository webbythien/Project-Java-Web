/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserDAO;
import data.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thien
 */
public class InforUserController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String INFO = "info.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String id = (String) session.getAttribute("LOGIN_USER_ID");
        String url = ERROR;
        String task = request.getParameter("task");

        if (id == null || id.equals("null")) {
            id = "0";
        }
        try {
            CheckLogin log = new CheckLogin();
            boolean checkUserLog = log.checkUser(request, response);
            if (!checkUserLog) {
                request.getRequestDispatcher("login").forward(request, response);
                return;

            }

            UserDAO dao = new UserDAO();
            if (action.equals("InforUser") && task == null) {
                UserDTO user = dao.load(Integer.parseInt(id));
                request.setAttribute("object", user);
                url = INFO;
                session.setAttribute("Message", "Update Successfull");

            } else if (task.equals("Update")) {
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                UserDTO user = dao.load(Integer.parseInt(id));
                user.setName(name);
                user.setPassword(password);
                int value = dao.updateUserInfo(user);
                if (value > 0) {
                    url = INFO;
                    session.setAttribute("Message", "Update Successfull");
                    request.setAttribute("object", user);

                } else {
                    url = ERROR;
                }
            }

        } catch (Exception e) {
            log("Erroe at LoginServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InforUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InforUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
