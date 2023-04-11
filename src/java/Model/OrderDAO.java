/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.DBUtils;
import data.CartDTO;
import data.OrderDTO;
import data.ProductDTO;
import data.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thien
 */
public class OrderDAO {

    public Long insertOrder(int idUser, String details) throws SQLException {
        int value = 0;
        ArrayList<CartDTO> list;
        list = new ArrayList<CartDTO>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        int id = getID() + 1;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            conn = DBUtils.getConnection();
            stm = conn.createStatement();

            conn.setAutoCommit(false);

            CartDAO cdao = new CartDAO();
            list = (ArrayList<CartDTO>) cdao.list(idUser);
            int i = list.size();
            if (list.size() != 0) {
                stm.executeUpdate("Insert into tblOrder (id,date, customer_id, status) values (" + id + ",'" + date + "'," + idUser + ",1)");
                for (CartDTO cartDTO : list) {
                    stm.executeUpdate("Insert into tblOrderDetails (order_id,quantity, product_id,totalPrice , details) values (" + id + "," + cartDTO.getQuantity() + "," + cartDTO.getProductID() + "," + (cartDTO.getP_price() * cartDTO.getQuantity()) + " ,'" + details + "')");
                    stm.executeUpdate("Update tblProduct set quantity = quantity - " + cartDTO.getQuantity() + " where id = " + cartDTO.getProductID());
                    cdao.delete(cartDTO);
                }
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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

    public int getID() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select top 1 * from tblOrder order by id desc";
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
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
        return 0;
    }

    public int sold(int idp) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS count\n"
                + "FROM tblOrder o\n"
                + "JOIN tblOrderDetails od ON o.id = od.order_id\n"
                + "WHERE od.product_id = ?";
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, idp);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
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
        return 0;
    }

    public List<OrderDTO> load(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> tempList = new ArrayList<>();
        String sql = "";
        try {
            sql = "SELECT o.id, o.customer_id, o.date, o.status, od.quantity, od.product_id, od.totalPrice, od.Details\n"
                    + "                    FROM tblOrder o\n"
                    + "                    JOIN tblOrderDetails od ON o.id = od.order_id WHERE  order_id = ?";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new OrderDTO(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("date"), rs.getInt("status"), rs.getInt("quantity"), rs.getInt("product_id"), rs.getFloat("totalPrice"), rs.getString("Details")));
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

        return tempList;
    }

    public boolean cancelOrder(int customerID, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            List<OrderDTO> list = load(orderID);
            conn = DBUtils.getConnection();
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            for (OrderDTO orderDTO : list) {
                stm.executeUpdate("UPDATE tblProduct\n"
                        + "SET quantity = quantity + (\n"
                        + "    SELECT SUM(quantity)\n"
                        + "    FROM tblOrderDetails\n"
                        + "    WHERE order_id = " + orderID + " and product_id = " + orderDTO.getProduct_id() + "\n"
                        + ") where id = " + orderDTO.getProduct_id());
            }
            check = stm.executeUpdate("Update tblOrder set status = 0  where status = 1 and  id = " + orderID + " and customer_id = " + customerID) > 0;

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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
        return check;
    }
//reOrder cho 1 ngày nếu >1 xóa đẩy product về quantity cũ 

    public boolean reOrder(int customerID, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            List<OrderDTO> list = load(orderID);
            conn = DBUtils.getConnection();
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            for (OrderDTO orderDTO : list) {
                stm.executeUpdate("UPDATE tblProduct\n"
                        + "SET quantity = quantity - (\n"
                        + "    SELECT SUM(quantity)\n"
                        + "    FROM tblOrderDetails\n"
                        + "    WHERE order_id = " + orderID + " and product_id = " + orderDTO.getProduct_id() + "\n"
                        + ") where id = " + orderDTO.getProduct_id());
            }
            check = stm.executeUpdate("Update tblOrder set status = 1  where status = 0 and  id = " + orderID + " and customer_id = " + customerID) > 0;

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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
        return check;
    }

    public List<OrderDTO> listSearchOrder(int customerID, String keyword) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> tempList = new ArrayList<>();
        String sql = "";
        try {
            sql = "SELECT o.id, o.customer_id, o.date, o.status, od.quantity, od.product_id, od.totalPrice, od.Details\n"
                    + "FROM tblOrder o\n"
                    + "JOIN tblOrderDetails od ON o.id = od.order_id where o.customer_id = ? and  o.date = ? order by o.date desc , o.id desc ;";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, customerID);
            stm.setString(2, keyword);
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new OrderDTO(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("date"), rs.getInt("status"), rs.getInt("quantity"), rs.getInt("product_id"), rs.getFloat("totalPrice"), rs.getString("Details")));
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

        return tempList;
    }

    public List<OrderDTO> list(int customerID, int status) throws SQLException {
        ArrayList<OrderDTO> list;
        list = new ArrayList<OrderDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT o.id, o.customer_id, o.date, o.status, od.quantity, od.product_id, od.totalPrice, od.Details\n"
                    + "FROM tblOrder o\n"
                    + "JOIN tblOrderDetails od ON o.id = od.order_id where o.customer_id = ? and status = ? order by o.date desc , o.id desc ;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, customerID);
            stm.setInt(2, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDTO(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("date"), rs.getInt("status"), rs.getInt("quantity"), rs.getInt("product_id"), rs.getFloat("totalPrice"), rs.getString("Details")));
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

    public boolean confirmOrder(int customerID, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            check = stm.executeUpdate("Update tblOrder set status = 2  where status = 1 and  id = " + orderID + " and customer_id = " + customerID) > 0;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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
        return check;
    }

    public boolean completeOrder(int customerID, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            check = stm.executeUpdate("Update tblOrder set status = 3  where status = 2 and  id = " + orderID + " and customer_id = " + customerID) > 0;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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
        return check;
    }

    public List<OrderDTO> listOrderForAdmin(int status) throws SQLException {
        ArrayList<OrderDTO> list;
        list = new ArrayList<OrderDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT o.id, o.customer_id, o.date, o.status, od.quantity, od.product_id, od.totalPrice, od.Details\n"
                    + "FROM tblOrder o\n"
                    + "JOIN tblOrderDetails od ON o.id = od.order_id where status = ? order by o.date desc , o.id desc ;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDTO(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("date"), rs.getInt("status"), rs.getInt("quantity"), rs.getInt("product_id"), rs.getFloat("totalPrice"), rs.getString("Details")));
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

    //insert
    //insertOrderdetail
    //update 0 = cancel 2=confirm 1=processing 3=completed
    //listSearch(int cus, int status )
    //getlistOrderbyID by id
    //update product
    // update product quantity
    //transaction
    public static void main(String[] args) throws SQLException {
        OrderDAO dao = new OrderDAO();
//            public OrderDTO(int id, int user_id, String date, int status, int quantity, int product_id, float totalPrice, String details) {
//    public OrderDTO(int id, int quantity, int product_id, float totalPrice, String details) {
        ArrayList<OrderDTO> print = null;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        print = (ArrayList<OrderDTO>) dao.load(2);
        for (OrderDTO orderDTO : print) {
            System.out.println(orderDTO.getProduct_id());
        }

    }
}
