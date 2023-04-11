/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.ReviewDTO;
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
public class ReviewDAO {

    public List<ReviewDTO> listReview(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ReviewDTO> tempList = new ArrayList<>();
        ReviewDTO pro = null;
        String sql = "";
        try {
            sql = "SELECT * FROM tblReview where product_id = ? \n";
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                tempList.add(new ReviewDTO(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("rating"), rs.getString("comment")));
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

    public int rating(int id) {
        double result = 0;
        List<ReviewDTO> tempList = new ArrayList<>();
        int count = 0;
        try {
            tempList = listReview(id);
            for (ReviewDTO reviewDTO : tempList) {
                count++;
                result = result + reviewDTO.getRating();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return (int) Math.floor(result / count);
        }
    }      
        
      

    public int Stock(int id) {
        int result = 0;
        List<ReviewDTO> tempList = new ArrayList<>();
        try {
            tempList = listReview(id);
            result = tempList.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result ;
        }
        
    }

    public static void main(String[] args) {
        ReviewDAO dao = new ReviewDAO();
        
        int count = dao.Stock(3);
        System.out.println(count);
    }
}
