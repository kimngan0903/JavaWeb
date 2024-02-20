package PhamThiKimNgan.servlet;


import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;



@WebServlet("/productCreate")
public class ProductCreateServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	public ProductCreateServlet() {
		super();
	}
	// Hiển thị trang thêm mới sản phẩm. 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
		dispatcher.forward(request, response);
	}
	// Khi người dùng nhấn vào nút ghi (submit).
	// Phương thức doPost sẽ được gọi. @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}
}
