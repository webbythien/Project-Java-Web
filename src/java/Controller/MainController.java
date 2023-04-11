/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CartDAO;
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
public class MainController extends HttpServlet {

    private static final String ADD_ITEM = "addtocart";
    private static final String ERROR = "index.jsp";
    private static final String LOGIN = "login";
    private static final String SEARCH = "search";
    private static final String LOGOUT = "logout";
    private static final String SHOP = "product";
    private static final String ITEM = "item";
    private static final String Remove = "remove";
    private static final String CREATE = "create";
    private static final String BUY = "buy";
    private static final String SEARCHACC = "searchacc";
    private static final String UPDATEACC = "updateacc";
    private static final String DELETEACC = "deleteacc";
    private static final String RESTOREACC = "restoreuser";
    private static final String MOD = "updatecart";
    private static final String CHECKOUT = "checkout";
    private static final String CANCEL = "cancel";
    private static final String REORDER = "reorder";
    private static final String ADMINORDER = "manageorder";
    private static final String ADMINPRODUCT = "manageproduct";
    private static final String CONFIRMUPDATE = "confirmupdate";
    private static final String COMPLETEUPDATE = "completeupdate";
    private static final String EDITPRODUCT = "editproduct";
    private static final String INFOUSER = "info";

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
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            String page = request.getParameter("page");
            String category = request.getParameter("category");
            String id = request.getParameter("id");

            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("InforUser".equals(action)) {
                url = INFOUSER;
            } else if ("Search".equals(action) ) {
                url = SEARCH;
            } else if ("CheckOut".equals(action))  {
                url = CHECKOUT;
            } else if ("Confirm".equals(action) ) {
                url = CONFIRMUPDATE;
            } else if ("Edit".equals(action) ) {
                url = EDITPRODUCT;
            } else if ("Complete".equals(action) ) {
                url = COMPLETEUPDATE;
            } else if ("Item".equals(action) || id != null) {
                url = ITEM;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Modify".equals(action)) {
                url = MOD;
            } else if ("Order".equals(action)) {
                url = BUY;
            } else if ("ManageProduct".equals(action)) {
                url = ADMINPRODUCT;
            } else if ("ManageOrder".equals(action)) {
                url = ADMINORDER;
            } else if ("Shop".equals(action) || category != null || page != null) {
                url = SHOP;
            } else if ("RestoreAcc".equals(action)) {
                url = RESTOREACC;
            } else if ("Delete".equals(action)) {
                url = DELETEACC;
            } else if ("Reorder".equals(action)) {
                url = REORDER;
            } else if ("Cancel".equals(action)) {
                url = CANCEL;
            } else if ("Update".equals(action)) {
                url = UPDATEACC;
            } else if ("SearchAcc".equals(action)) {
                url = SEARCHACC;
            } else if ("removeCart".equals(action)) {
                url = Remove;
            } else if ("AddCart".equals(action)) {
                url = ADD_ITEM;
            } else if ("Register".equals(action)) {
                url = CREATE;
            } else {
                session.setAttribute("ERROR_MESSAGE", "Function is not available");
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
