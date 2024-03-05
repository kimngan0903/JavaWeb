package PhamThiKimNgan.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PhamThiKimNgan.entity.Department;

public class DepartmentUtils {
	 // Đọc danh sách sản phẩm
    public static List<Department> queryDepartment(Connection conn) throws SQLException {
        String sql = "Select *  from Department a ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
            int DEPT_ID = rs.getInt("DEPT_ID");
            String DEPT_NAME = rs.getString("DEPT_NAME");
            String DEPT_NO = rs.getString("DEPT_NO");
            String LOCATION = rs.getString("LOCATION");
            Department department = new Department();
            department.setDeptId(DEPT_ID);
            department.setDeptName(DEPT_NAME);
            department.setDeptNo(DEPT_NO);
            department.setLocation(LOCATION);
            list.add(department);
        }
        return list;
    }

    // Tìm kiếm theo mã sản phẩm (code)
    public static Department findDepartment(Connection conn, String code) throws SQLException {
        String sql = "Select *  from Department a where a.DEPT_ID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int DEPT_ID = rs.getInt("DEPT_ID");
            String DEPT_NAME = rs.getString("DEPT_NAME");
            String DEPT_NO = rs.getString("DEPT_NO");
            String LOCATION = rs.getString("LOCATION");
            Department department = new Department(DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION);
            return department;
        }
        return null;
    }
    // Tìm kiếm theo mã sản phẩm (name)
    public static  List<Department> findDepartmentByName(Connection conn, String name) throws SQLException {
        String sql = "Select *  from Department a where a.DEPT_NAME like  '%" + name + "%'";
        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
            int DEPT_ID = rs.getInt("DEPT_ID");
            String DEPT_NAME = rs.getString("DEPT_NAME");
            String DEPT_NO = rs.getString("DEPT_NO");
            String LOCATION = rs.getString("LOCATION");
            Department department = new Department(DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION);
            list.add(department);
            return list;
        }
        return null;
    }
    // Thêm mới sản phẩm
    public static void insertProduct(Connection conn, Department department) throws SQLException {
        String sql = "Insert into DEPARTMENT(DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION) values (?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, department.getDeptId());
        pstm.setString(2, department.getDeptName());
        pstm.setString(3, department.getDeptNo());
        pstm.setString(4, department.getLocation());
       
        pstm.executeUpdate();
    }

}
