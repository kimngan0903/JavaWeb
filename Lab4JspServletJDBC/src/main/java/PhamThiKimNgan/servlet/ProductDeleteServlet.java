package PhamThiKimNgan.servlet;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import PhamThiKimNgan.conn.ConnectionUtils;
import PhamThiKimNgan.utils.ProductUtils;


@WebServlet("/productDeletep")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductDeleteServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String errorString=null;
    		String code = (String)request.getParameter("code");
    		Connection conn=null;
    		try {
    			conn=ConnectionUtils.getMSSQLConnection();
    			ProductUtils.deleteProduct (conn, code);
    		}catch(Exception e) {
    			e.printStackTrace();
    			errorString=e.getMessage();
    		}
    		if(errorString!=null) {
    			request.setAttribute("errorString", errorString);
    			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productDeleteError.jsp");
    		dispatcher.forward(request, response);
    		}else {
    			response.sendRedirect(request.getContextPath()+"/productList");
    		}
    }
    		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    		{
    		doGet(request, response);
    		}
    }
