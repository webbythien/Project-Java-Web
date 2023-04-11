/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CartDAO;
import Model.OrderDAO;
import Model.ProductDAO;
import Model.UserDAO;
import data.CartDTO;
import data.OrderDTO;
import data.ProductDTO;
import data.UserDTO;
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
public class BuyController extends HttpServlet {

    private static final String ERROR = "Order.jsp";
    private static final String SUCCESS = "Order.jsp";

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
            String fullname = request.getParameter("fname");
            String country = request.getParameter("country");
            String street = request.getParameter("street");
            String street2 = request.getParameter("street2");
            String town = request.getParameter("town");
            String phone = request.getParameter("phone");
            String status = request.getParameter("st");
//            String search = request.getParameter("search");
//            if (search == null) {
//                search = "null";
//            }

            if (status == null) {
                status = "4";
            }
            OrderDAO odao = new OrderDAO();
            ProductDAO dao = new ProductDAO();
            List<OrderDTO> list = null;
            List<OrderDTO> list1 = null;
            List<OrderDTO> list2 = null;
            List<OrderDTO> list3 = null;

            int st = Integer.parseInt(status);

            String userID = (String) session.getAttribute("LOGIN_USER_ID");
            String details = fullname + ", " + phone + ", " + street2 + ", " + street + ", " + town + ", " + country;
            if (userID == null) {
                userID = "null";
            }
            if (!userID.equals("null")) {
                if (details != null) {
                    if (!details.contains("null")) {
                        odao.insertOrder(Integer.parseInt(userID), details);
                    }
                }
                List<ProductDTO> listCart = dao.listProductId();
                request.setAttribute("ListCart", listCart);

                switch (st) {
                    case 0:
                        list = odao.list(Integer.parseInt(userID), 0);
                        break;
                    case 1:
                        list1 = odao.list(Integer.parseInt(userID), 1);
                        break;
                    case 2:
                        list2 = odao.list(Integer.parseInt(userID), 2);
                        break;
                    case 3:
                        list3 = odao.list(Integer.parseInt(userID), 3);
                        break;
                    default:
                        list1 = odao.list(Integer.parseInt(userID), 1);
                        list2 = odao.list(Integer.parseInt(userID), 2);
                        list3 = odao.list(Integer.parseInt(userID), 3);
                        break;
                }

                request.setAttribute("List0", list);
                request.setAttribute("List1", list1);
                request.setAttribute("List2", list2);
                request.setAttribute("List3", list3);

                url = SUCCESS;
            } else {
                url = "login";
            }

        } catch (Exception e) {
            log("Error at SearchController: " + ", " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the  +", "+ sign on the left to edit the code.">
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
