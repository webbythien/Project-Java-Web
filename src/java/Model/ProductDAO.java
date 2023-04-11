/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.ProductDTO;
import Utils.DBUtils;
import data.CartDTO;
import data.UserDTO;
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
public class ProductDAO {

    public ProductDTO item(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "SElect * from tblProduct \n"
                    + "where id = ? \n";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                pro = new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin"));
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

        return pro;
    }

    public List<ProductDTO> listPagingAccount(int page) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> tempList = new ArrayList<>();
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "SElect * from tblProduct \n"
                    + "order by id \n"
                    + "OFFSET ? rows fetch next 12 rows only; ";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, (page - 1) * 12);
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin")));
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

    public int getTotalPage() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select count(*) as total from tblProduct";
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
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

    public int getTotalPageSearch(String keyword) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select count(*) as total from tblProduct where name like ? or color like ? or category like ? or origin like ? ";
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            stm.setString(4, "%" + keyword + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
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

    public boolean delete(int id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Delete from [tblProduct] where id = ?";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate() > 0;
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
        return check;
    }

    public int Update(ProductDTO product) throws SQLException {
        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblProduct SET "
                    + " name = ? , [description] = ? ,[color] = ? ,[price] = ?, [image_link] = ? ,  [quantity] =? , [weight] = ? , [category] = ? , [origin] = ? "
                    + " WHERE id = ? ;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setString(3, product.getColor());
            stm.setFloat(4, product.getPrice());
            stm.setString(5, product.getImaga_link());
            stm.setInt(6, product.getQuantity());
            stm.setFloat(7, product.getWeight());
            stm.setString(8, product.getCategory());
            stm.setString(9, product.getOrigin());
            stm.setInt(10, product.getId());
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
        return value;
    }

    public int insert(ProductDTO product) throws SQLException {
        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO [dbo].[tblProduct]\n"
                    + "           ([name]\n"
                    + "           ,[description]\n"
                    + "           ,[color]\n"
                    + "           ,[price]\n"
                    + "           ,[image_link]\n"
                    + "           ,[quantity]\n"
                    + "           ,[weight]\n"
                    + "           ,[category]\n"
                    + "           ,[origin])\n"
                    + "     VALUES "
                    + "(  ? , ? , ? , ?, ? , ?,  ? ,  ? ,?) ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setString(3, product.getColor());
            stm.setFloat(4, product.getPrice());
            stm.setString(5, product.getImaga_link());
            stm.setInt(6, product.getQuantity());
            stm.setFloat(7, product.getWeight());
            stm.setString(8, product.getCategory());
            stm.setString(9, product.getOrigin());
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
        return value;
    }

    public List<ProductDTO> listSearchProduct(int page, String keyword) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> tempList = new ArrayList<>();
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "SElect * from tblProduct where name like ? or color like ? or category like ? or origin like ? \n"
                    + "order by id \n"
                    + "OFFSET ? rows fetch next 12 rows only; ";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            stm.setString(4, "%" + keyword + "%");
            stm.setInt(5, (page - 1) * 12);
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin")));
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

    public List<ProductDTO> listProductId() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> tempList = new ArrayList<>();
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "Select * from tblProduct ";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin")));
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

     public List<ProductDTO> listProductIdSearch(String keyword) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> tempList = new ArrayList<>();
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "Select * from tblProduct where name like ? or color like ? or category like ? or origin like ? ";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            stm.setString(4, "%" + keyword + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin")));
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
    
    public List<ProductDTO> listProductOutStock(int quantity) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> tempList = new ArrayList<>();
        ProductDTO pro = null;
        String sql = "";
        try {
            sql = "Select * from tblProduct where quantity = ? ";

            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, quantity);
 
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ProductDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("color"), rs.getFloat("price"), rs.getString("image_link"), rs.getInt("quantity"),
                        rs.getFloat("weight"), rs.getString("category"), rs.getString("origin")));
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

    public static void main(String[] args) throws SQLException {
        ProductDAO dao = new ProductDAO();
////        System.out.println(pro.getTotalPage());
//        List<ProductDTO> list = pro.listPagingAccount(1);
//        for (ProductDTO productDTO : list) {
//            System.out.println(productDTO.toString());
//        }

//        double so = 13.5;
//        int thu = 42;
//        int tron = (int) Math.ceil(so);
//        System.out.println(tron);
//        double in = thu;
//        System.out.println(in);
//        ProductDTO pro = dao.item(3);
        System.out.println(dao.listProductId());
    }
}
