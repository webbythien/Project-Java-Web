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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thien
 */
public class EditProductController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String LIST = "listproductacc.jsp";
    private static final String EDIT = "editproduct.jsp";

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
            ProductDTO product = null;
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession(false);
            String proId = request.getParameter("proId");
            if (proId == null || proId.equals("null")) {
                proId = "0";
            }
            String task = request.getParameter("task");
            if (task.equals("Delete")) {
                boolean check = dao.delete(Integer.parseInt(proId));
                if (check) {
                    url = LIST;
                    request.setAttribute("Message", "DELETE SUCCESSFULL !");
                } else {
                    url = ERROR;
                }
            } else if (task.equals("Change")) {

                if (proId != null) {
                    product = dao.item(Integer.parseInt(proId));
                    url = EDIT;
                }
                request.setAttribute("object", product);
                request.setAttribute("task", "Update");
            } else if (task.equals("Update")) {
                String name = request.getParameter("name");
                String color = request.getParameter("color");
                float price = Float.parseFloat(request.getParameter("price"));
                String imaga_link = request.getParameter("url");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                float weight = Float.parseFloat(request.getParameter("weight"));
                String category = request.getParameter("category");
                String origin = request.getParameter("origin");
                String description = request.getParameter("description");

                product = new ProductDTO(Integer.parseInt(proId), name, description, color, price, imaga_link, quantity, weight, category, origin);
                int check = dao.Update(product);
                if (check > 0) {
                    url = LIST;
                    request.setAttribute("Message", "Update Product Successful !");
                } else {
                    url = ERROR;
                }

            } else if (task.equals("Create")) {
                url = EDIT;
                request.setAttribute("task", "Insert");
            } else if (task.equals("Insert")) {
                String name = request.getParameter("name");
                String color = request.getParameter("color");
                float price = Float.parseFloat(request.getParameter("price"));
                String imaga_link = request.getParameter("url");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                float weight = Float.parseFloat(request.getParameter("weight"));
                String category = request.getParameter("category");
                String origin = request.getParameter("origin");
                String description = request.getParameter("description");

                product = new ProductDTO(name, description, color, price, imaga_link, quantity, weight, category, origin);
                int check = dao.insert(product);
                if (check > 0) {
                    url = LIST;
                    request.setAttribute("Message", "Add Product Successful !");
                } else {
                    url = ERROR;
                }

            }

        } catch (Exception e) {
            log("Error at LogoutController " + e.toString());
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
