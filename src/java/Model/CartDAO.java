/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.CartDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thien
 */
public class CartDAO {
    
    public List<CartDTO> list(int customerID) throws SQLException {
        ArrayList<CartDTO> list;
        list = new ArrayList<CartDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT c.customer_id, p.id AS product_id, p.name AS product_name, p.image_link AS product_image_link,\n"
                    + "       p.description AS product_description, p.price AS product_price, c.quantity AS cart_quantity,\n"
                    + "       p.quantity AS product_quantity\n"
                    + "FROM [dbo].[tblCart_Item] c\n"
                    + "JOIN [dbo].[tblProduct] p ON c.product_id = p.id where customer_id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, customerID);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CartDTO(customerID, rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("cart_quantity"), rs.getString("product_image_link"), rs.getString("product_description"), rs.getFloat("product_price"), rs.getInt("product_quantity")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int cartSize(int id) throws SQLException {
        return list(id).size();
    }

    public Long add(CartDTO newCart) throws SQLException {
        int value = 0;
        ArrayList<CartDTO> list;
        list = new ArrayList<CartDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblCart_Item (product_id, customer_id, quantity) VALUES"
                    + " (? , ? , ?); ";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, newCart.getProductID());
            stm.setInt(2, newCart.getCustomerID());
            stm.setInt(3, newCart.getQuantity());
            value = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return Long.valueOf(value);
    }
    
    public void delete(CartDTO oldCart) throws SQLException {
        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM tblCart_Item "
                    + " where product_id = ? AND customer_id = ?  ";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, oldCart.getProductID());
            stm.setInt(2, oldCart.getCustomerID());
            value = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void Update(CartDTO newCart) throws SQLException {
        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblCart_Item SET "
                    + " quantity = ? WHERE product_id = ? AND customer_id = ?;";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, newCart.getProductID());
            stm.setInt(3, newCart.getCustomerID());
            stm.setInt(1, newCart.getQuantity());
            value = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        CartDAO dao = new CartDAO();
//        CartDTO newCart = new CartDTO(9, 3, 5);
//        System.out.println(dao.list(4));
////        Long rs = dao.add(newCart);
////        if (rs == 0) {
////            System.out.println("0");
////        } else {
////            System.out.println("1");
////        }
//        dao.Update(newCart);
        for (CartDTO cart : dao.list(6)) {
            System.out.println(cart);
        }
        
    }
}
