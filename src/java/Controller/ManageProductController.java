/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.OrderDAO;
import Model.ProductDAO;
import data.OrderDTO;
import data.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thien
 */
public class ManageProductController extends HttpServlet {

    private static final String ERROR = "listproductacc.jsp";
    private static final String SUCCESS = "listproductacc.jsp";

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
            CheckLogin log = new CheckLogin();
            boolean checkUserLog = log.checkAD(request, response);
            if (!checkUserLog) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;

            }
            String outStock = request.getParameter("outStock");
            String key = request.getParameter("search");
            if (key == null || key.equals("null")) {
                key = "";
            }

            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.listProductIdSearch(key);
            if (outStock != null) {
                list = dao.listProductOutStock(Integer.parseInt(outStock));
            }
            if (list.size() != 0) {
                request.setAttribute("List", list);

            } else {
                request.setAttribute("Message", "No product show");
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchController: " + ", " + e.toString());
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
