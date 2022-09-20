package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class FindByIdEmployeeServlet
 */
@WebServlet("/FindByIdEmployeeServlet")
public class FindByIdEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindByIdEmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "UpdateEmployee";
		String message = "<h1>Error while " + source + "</h1>";
		Employee newemp = new Employee();
		int result = 0;
		try {

			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setEmp_id(empId);
//-------------------
		Employee getEmployee = EmployeeDao.getEmployeeById(empId);
		} catch (Exception err) {
			message += " Error in Inserting the input </p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
		}
	    request.setAttribute("result","getEmployee");
		RequestDispatcher rd = request.getRequestDispatcher("/addemp.jsp");
		rd.forward(request, response);
	}
	

}
