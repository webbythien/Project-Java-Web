/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProductDAO;
import data.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class ProductPageController extends HttpServlet {

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
        String index = request.getParameter("page");
        String category = request.getParameter("category");

        ProductDAO dao = new ProductDAO();
        double count = dao.getTotalPage();
        int endPage = (int) Math.ceil(count / 12);
        HttpSession session = request.getSession();

        if (category == null) {
            category = "";
        }

        if (index == null) {
            index = "1";
        }
        int page = Integer.parseInt(index);
        List<ProductDTO> listPro = dao.listPagingAccount(page);
        List<ProductDTO> listTemp = null;
        if (category != "") {
            listPro.clear();
            listTemp = dao.listSearchProduct(page, "");
            for (ProductDTO productDTO : listTemp) {
                if (productDTO.getCategory().equalsIgnoreCase(category)) {
                    listPro.add(productDTO);
                }
            }
            if (listPro.size() != 0) {
                request.setAttribute("LIST_PRO", listPro);
            } else {
                request.setAttribute("ERROR_MESS", "SORRY NOT FOUND CATEGORY IS " + category);
            }
        } else {
            listPro = dao.listPagingAccount(page);
            request.setAttribute("LIST_PRO", listPro);
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
            Logger.getLogger(ProductPageController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductPageController.class.getName()).log(Level.SEVERE, null, ex);
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
