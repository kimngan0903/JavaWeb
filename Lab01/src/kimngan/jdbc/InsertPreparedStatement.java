package kimngan.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertPreparedStatement {
public static void main(String[] args)
throws ClassNotFoundException, SQLException {
// Tạo kết nối
Connection connection =

ConnectionUtils.getMySQLConnection();

// Tạo truy vấn thêm
String sql = "INSERT INTO product

(ProID,ProName,ProQty,ProPrice) ";

sql += " VALUES(?,?,?,?)";
PreparedStatement pstm =

connection.prepareStatement(sql);

// gán giá trị cho các biến
pstm.setInt(1, 3);
pstm.setString(2,"HP Pavilion dv4t - BTO");
pstm.setInt(3, 50);
pstm.setFloat(4, 1500);
// Thực thi câu lệnh truy vấn
int rowCount = pstm.executeUpdate();
System.out.println("Đã thêm mới 1 bản ghi: " +

rowCount);
}
}