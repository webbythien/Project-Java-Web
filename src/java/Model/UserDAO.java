/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.UserDTO;
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
public class UserDAO {

    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                sql = "SELECT *  from tblCustomer"
                        + " where email=?"
                        + " and password=? and status = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String userId = rs.getString("id");
                    String role = rs.getString("role");
                    user = new UserDTO(userId, name, email, "*****", role);
                }
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

        return user;
    }

    public boolean creatUser(String name, String email, String password) throws SQLException {
        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = " INSERT INTO tblCustomer (email, password, name, role,status) VALUES (?,?,?,'US',1)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setString(3, name);
            int value = stm.executeUpdate();
            result = value > 0 ? true : false;
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
        return result;
    }

    public UserDTO load(int id) throws SQLException {
        List<UserDTO> listCopy = getListUser("", true);
        for (UserDTO studentDTO : listCopy) {
            if (studentDTO.getUserID().equals(Integer.toString(id))) {
                return studentDTO;
            }
        }
        return null;
    }

    public int updateUser(UserDTO user) throws SQLException {

        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblCustomer SET "
                    + " name = ? , role = ?  WHERE id = ?;";
            stm = conn.prepareStatement(sql);
            stm.setString(2, user.getRole());
            stm.setInt(3, Integer.parseInt(user.getUserID()));
            stm.setString(1, user.getName());
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
    
    public int updateUserInfo(UserDTO user) throws SQLException {

        int value = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblCustomer SET "
                    + " name = ? , password = ? WHERE id = ?;";
            stm = conn.prepareStatement(sql);
            stm.setInt(3, Integer.parseInt(user.getUserID()));
            stm.setString(2, user.getPassword() );
            stm.setString(1, user.getName());
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

    public boolean delete(String userID) throws SQLException {
        boolean value = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "Update tblCustomer set "
                    + " status = 0 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(userID));
            value = stm.executeUpdate() > 0;
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

    public boolean restore(String userID) throws SQLException {
        boolean value = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "Update tblCustomer set "
                    + " status = 1 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(userID));
            value = stm.executeUpdate() > 0;
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

    public List<UserDTO> getListUser(String search, boolean status) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "from tblCustomer"
                        + " Where (name LIKE ? or email like ? ) and status = ? and role = ?";// nhớ có dấu cách
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + search + "%");
                stm.setBoolean(3, status);
                stm.setString(4,"US");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("id");
                    String fullname = rs.getString("name");
                    String email = rs.getString("email");
                    String password = "***";
                    String role = rs.getString("role");
                    UserDTO searchUser = new UserDTO(userID, fullname, email, password, role);
                    list.add(searchUser);
                }
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

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        dao.creatUser("tan","tanduong123@example.com","12345");
    }
}
