package kimngan.servlet;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/UserAccount")
public class UserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserAccount() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter(); //Writer to client
		//Lấy dữ liệu khi submit form từ UserAccount
		String phone = request.getParameter("phone");
		out.println("<html><head><meta charset='UTF-8'><title>InputForm</title></head>");
		out.println("<body style='text-align: center'>");
		out.println("<h1>KẾT QUẢ TÌM KIẾM</h1>");
		out.println("<form action='SearchAccount' method='post'>");
		out.println("<p>Input phone: ");
		out.println("<input type=text name='phone value=' " + phone + " '/>");
		out.println("<p> <input type='submit' value='Search' />");
		out.println("<</form>");
		try {
			String sql = "Select * from USER_ACCOUNT ";
			if(phone!=null && phone.length()!=0)
				sql += "WHERE CusPhone like '% " + phone + "%'";
			Connection conn = ConnectionUtils.getMSSQLConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			out.println("<h2> Danh sách </h2>");
			out.println("<table border");
			

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
