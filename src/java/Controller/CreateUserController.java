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
public class CreateUserController extends HttpServlet {

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
        String url = ERROR;
        response.setContentType("text/html;charset=UTF-8");
        int i = 0;
        try {
            CheckLogin log = new CheckLogin();
            boolean checkUserLog = log.checkAD(request, response);
            if (!checkUserLog) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            String emailID = request.getParameter("userEmail");
            String password = request.getParameter("userPassword");
            String name = request.getParameter("userName");
            CartDAO daoC = new CartDAO();

            UserDAO dao = new UserDAO();
            boolean check = dao.creatUser(name, emailID, password);
            if (check) {
                UserDTO user = dao.checkLogin(emailID, password);
                url = HOME;
                session.setAttribute("USER", user);
                i = daoC.cartSize(Integer.parseInt(user.getUserID()));
                session.setAttribute("LOGIN_USER_ID", user.getUserID());
                session.setAttribute("LOGIN_USER", user.getName());
            } else {
                session.setAttribute("ERROR_MESSAGE2", "Email has been existed");
            }
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
