/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CartDAO;
import data.CartDTO;
import Model.ProductDAO;
import Model.UserDAO;
import data.ProductDTO;
import data.UserDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "cart.jsp";
    private static final String NONID = "login.jsp";

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
            ProductDAO dao = new ProductDAO();
            String quantityTemp = request.getParameter("quantity");
            String idTemp = request.getParameter("idItem");
            String uIDTEMP = request.getParameter("idUser");
            UserDAO daoU = new UserDAO();
            String loginUser = (String) session.getAttribute("LOGIN_USER_ID");
            if (loginUser == null) {
                if (uIDTEMP == null || uIDTEMP.equals("null") || uIDTEMP.equals("")) {
                    url = NONID;
                    String path = request.getRequestURI();
                    String lastPath = path.substring(path.lastIndexOf('/') + 1);
                    request.setAttribute("currPath", lastPath);
                    url = NONID;
                    return;
                }
            }
            if (uIDTEMP == null) {
                uIDTEMP = loginUser;
            }
            UserDTO user = daoU.load(Integer.parseInt(uIDTEMP));
            if (user == null) {
                url = "index.jsp";
                request.setAttribute("ERROR_AD", "Sorry ADMIN account cannot buy product");
            } else {

                float subtotal = 0;

                if (idTemp == null) {
                    idTemp = "0";
                }

                if (quantityTemp == null) {
                    quantityTemp = "0";
                }
                int idU = Integer.parseInt(uIDTEMP);
                int id = Integer.parseInt(idTemp);
                int quantity = Integer.parseInt(quantityTemp);
                CartDAO cartD = new CartDAO();
                if (idU != 0 && id != 0) {
                    ProductDTO pro = dao.item(id);
                    CartDTO dtoCart = new CartDTO(idU, pro.getId(), quantity);
                    cartD.add(dtoCart);
                }
                List<CartDTO> list = cartD.list(idU);

                if (list.size() != 0) {

                    for (CartDTO cart : list) {

                        float sub = cart.getP_price() * cart.getQuantity();
                        subtotal = subtotal + sub;
                    }
                    request.setAttribute("subtotal", subtotal);
//                request.setAttribute("PRO_DAO", dao);
//                request.setAttribute("LIST_CART", list);
                    request.setAttribute("DATA", list);
                    url = SUCCESS;

                }

            }

        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {

            request.getRequestDispatcher(url).forward(request, response);
        }

    }

//    public String htmlCart(int idUser, int product_id, String name, String imageLink, String description, float price, int quantityCard, int quantityPro) {
//
//        String productTemp = "<tr class=\"text-center\">\n"
//                + "<td class=\"product-remove\">\n" + "<a href=\"main?action=removeCart&idUser=" + idUser + "&proID=" + product_id + "\">\n" + "<span class=\"ion-ios-close\">\n" + "</span>\n" + "</a>\n" + "</td>\n"
//                + "<td class=\"image-prod\">\n" + "<div class=\"img\" style=\"background-image:url(" + imageLink + ");\">\n" + "</div>\n" + "</td>\n"
//                + "<td class=\"product-name\">\n"
//                + "<h3>\n" + name + "</h3>\n"
//                + "<p>\n" + description + "</p>\n"
//                + "</td>\n"
//                + "<td class=\"price\">\n$" + price + "</td>\n"
//                + "<td class=\"quantity\">\n"
//                + "<div class=\"input-group mb-3\">\n"
//                + "<input type=\"number\" name=\"quantity\" class=\"quantity form-control input-number\" value=\"" + quantityCard + "\" min=\"1\" max=\"" + quantityPro + "\">\n"
//                + "</div>\n"
//                + "</td>\n"
//                + "<td class=\"total\">\n$" + quantityCard * price + "</td>\n"
//                + "</tr>\n";
//        return productTemp;
//    }
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
