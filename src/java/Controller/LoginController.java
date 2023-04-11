/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CartDAO;
import Model.UserDAO;
import data.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thien
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String HOME = "index.jsp";

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
        String url = ERROR;
        int i = 0;
        try {
            HttpSession session = request.getSession();
            String emailID = request.getParameter("email");
            String password = request.getParameter("password");
            String fPath = request.getParameter("currPath");

            UserDAO dao = new UserDAO();
            CartDAO daoC = new CartDAO();
            UserDTO user = dao.checkLogin(emailID, password);
            session.setAttribute("LOGIN_USER_EMAIL", emailID);
            if (user != null) {
                if (fPath == null) {
                    fPath = "null";
                }
                if (fPath.equals("null")) {
                    url = HOME;
                } else {
                    url = fPath;
                }
                session.setAttribute("USER", user);
                i = daoC.cartSize(Integer.parseInt(user.getUserID()));
                session.setAttribute("LOGIN_USER_ID", user.getUserID());
                session.setAttribute("LOGIN_USER", user.getName());
            } else {
                session.setAttribute("ERROR_MESSAGE", "Incorrect USER ID or PASSWORD");
            }
            request.setAttribute("NUM_CART", i);

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
