package kimngan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kimngan.beans.Product;
import kimngan.conn.ConnectionUtils;
import kimngan.utils.ProductUtils;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ProductListServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		String errorString = null;
		List<Product> list = null;
		try {
		conn = ConnectionUtils.getMSSQLConnection();
			try {
				list = ProductUtils.queryProduct(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
	}
	//Store info in request attribute, before forward to views
			request.setAttribute("errorString", errorString);
			request.setAttribute("productList", list);
	//Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
			errorString = e1.getMessage();
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
			request.setAttribute("errorString", errorString);
			dispatcher.forward(request, response);
		}
	}
	// Khi người dùng nhấn vào nút ghi (submit).
	// Phương thức doPost sẽ được gọi.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("UTF-8");
				String errorString = null;
			//Lấy dữ liệu trên form
				String code = (String) request.getParameter("code");
				String name = (String) request.getParameter("name");
				String priceStr = (String) request.getParameter("price");
				float price = 0;
				try {
					price = Float.parseFloat(priceStr);
				} catch (Exception e) {
				 errorString=e.getMessage();
				}
				Product product = new Product (code, name, price); 
				// Kiểm tra code ít nhất 1 ký tự -zA-Z_0-9]
				String regex = "\\w+";
				if(code == null || !code.matches(regex)){
				 errorString = "Product Code invalid!";
				}
				if(errorString != null) {
					request.setAttribute("errorString", errorString);
					 request.setAttribute("product", product);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Connection conn = null;
				try {
				conn = ConnectionUtils.getMSSQLConnection();
				ProductUtils.insertProduct(conn, product);
				response.sendRedirect(request.getContextPath() + "/productList");
				} catch (Exception e) {
					e.printStackTrace();
					errorString = e.getMessage();
					request.setAttribute("errorString", errorString);
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
							dispatcher.forward(request, response);
					}
					}
}
