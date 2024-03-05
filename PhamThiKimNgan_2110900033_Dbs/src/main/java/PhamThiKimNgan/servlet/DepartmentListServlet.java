package PhamThiKimNgan.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import PhamThiKimNgan.dao.DepartmentUtils;
import PhamThiKimNgan.entity.Department;
import PhamThiKimNgan.utils.ConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
* Servlet implementation class DepartmentListServlet
*/
@WebServlet("/departmentList")
public class DepartmentListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public DepartmentListServlet() {
       super();
       // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    * response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // TODO Auto-generated method stub
//        response.getWriter().append("Served at: ").append(request.getContextPath());
       Connection conn = null;
       String errorString = null;
       List<Department> list = null;
//        String deptName = (String) request.getParameter("deptName");
       try {
           conn = ConnectionUtils.getMSSQLConnection();

           try {
               list = DepartmentUtils.queryDepartment(conn);
//                if(deptName !=null) {
//                    list = DepartmentUtils.findDepartmentByName(conn, deptName);
//                }
           } catch (SQLException e) {
               e.printStackTrace();
               errorString = e.getMessage();
           }
           // Store info in request attribute, before forward to views
           request.setAttribute("errorString", errorString);
           request.setAttribute("department", list);
//            request.setAttribute("deptName", deptName);

           // Forward to /WEB-INF/views/productListView.jsp
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/departmentList.jsp");
           dispatcher.forward(request, response);
       } catch (SQLException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
           errorString = e1.getMessage();
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/departmentList.jsp");
           request.setAttribute("errorString", errorString);
           dispatcher.forward(request, response);
       } catch (ClassNotFoundException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    * response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // TODO Auto-generated method stub

       request.setCharacterEncoding("UTF-8");
       String errorString = null;
       // Lấy dữ liệu trên form
       String deptName = (String) request.getParameter("departmentName");
//        response.sendRedirect(request.getContextPath() + "/DepartmentListServlet");
       String sql = "Select * from Department a where a.DEPT_NAME like '%" + deptName + "%'";
       request.setAttribute("deptName", deptName);
       List<Department> list = null;
       Connection conn = null;
       try {
           conn = ConnectionUtils.getMSSQLConnection();
           if(deptName !=null) {
               list = DepartmentUtils.findDepartmentByName(conn, deptName);
           }else {
               list = DepartmentUtils.queryDepartment(conn);
           }
       } catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       
       request.setAttribute("errorString", errorString);
       request.setAttribute("department", list);
       request.setAttribute("deptName", deptName);
       RequestDispatcher dispatcher = request.getServletContext()
.getRequestDispatcher("/WEB-INF/views/departmentList.jsp");
dispatcher.forward(request, response);

//        doGet(request, response);
   }

}
