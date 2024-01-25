package kimngan.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
        super();
    }

	public void init(ServletConfig config) 
			throws ServletException {
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.println("<html>");
		out.println("<head><title>HelloServlet</title></head>");
		out.println("<body>");
		out.println("<h1>Hello World</h1>");
		out.println("<p>This is my first Servlet</p>");
		out.println("<p><a href='https://daihocnguyentrai.edu.vn/'>");
		out.println("Kim Ngan </a>  </p>");
		out.println("</body>");
		out.println("<html>");
	}

	
	@Override
	protected void doPost(HttpServletRequest request,
					HttpServletResponse response) throws ServletException,
		IOException {
						doGet(request, response);
					}
}
