package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class AddEmployee
 */
@WebServlet("/EmployeeA")
public class EmployeeA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeA() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();

		List<Employee> allEmployees = EmployeeDao.getAllEmployee();
		Iterator<Employee> empIterator = allEmployees.iterator();
//		out.print("<table border=\"1\"><tr><th>Emp ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th><th>Hire Date</th><th>Job ID</th><th>Salary</th></tr>");
		while (empIterator.hasNext()) {
			Employee result = empIterator.next();
//			out.print("<tr><td>" + result.getEmp_id() + "</td><td>" + result.getFirst_name() + "</td><td>"
//					+ result.getLast_name() + "</td><td>" + result.getEmail() + "</td><td>" + result.getHire_date()
//					+ "</td><td>" + result.getJob_id() + "</td><td>" + result.getSalary() + "</td></tr>");
//		}
//		out.print("</table>");
		out.println("<hr/>");// in emp

        out.println("<p>"+result.getEmp_id() + "," + result.getFirst_name() + "," + result.getLast_name() + ","
                + result.getEmail() + "," + result.getHire_date() + "," + result.getJob_id() + ","
                + result.getSalary()+"</p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("<h1>Post Called </h1>");
//		here we can redirect to jsp
		PrintWriter out = response.getWriter();
		String submitValue = request.getParameter("submit");
		if (submitValue.equals("UPDATE")) {
			doPut(request, response);
		} else if (submitValue.equals("DELETE")) {
			doDelete(request, response);
		} else if (submitValue.equals("ADD")) {

			String source = "AddNewEmployee";
			String message = "<h1>Error while " + source + "</h1>";
			Employee newemp = null;
			int result = 0;
			int empId = 0;
			String testname = null;
			try {
				newemp = new Employee();
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParse(id);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return; // it terminate the code execution beyond this point
				}
				empId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(empId);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setEmp_id(empId);
//--------------------------------
				String fname = request.getParameter("fname");
				testname = fname;
				try {
					Validator.checkStringOnly(testname);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee Firt Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checklengthOfString(fname);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee First Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setFirst_name(fname);
//-----------------------------------
				String lname = request.getParameter("lname");
				testname = fname;
				try {
					Validator.checkStringOnly(testname);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee Last Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.checklengthOfString(lname);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee Last Name input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setLast_name(lname);
//----------------------------------			
				String email = request.getParameter("email");
				try {
					Validator.checkMail(email);
				} catch (InvalidInputDataException err) {
					message += " Error in E-mail input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setEmail(email);
//--------------------------------------			
				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String emp_HireDate = request.getParameter("hdate");
				// Date hire_date=hire_dateFormate.parse(emp_HireDate);

				try {
					Validator.checkDateFormat(emp_HireDate);
				} catch (InvalidInputDataException err) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				Date newDate = null;
				try {
					newDate = hire_dateFormate.parse(emp_HireDate);
				} catch (ParseException err) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				try {
					Validator.CheckNofutureDate(newDate);
				} catch (InvalidInputDataException err) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}

				newemp.setHire_date(newDate);
//----------------------------------------
				String jobId = request.getParameter("jobid");
				try {
					Validator.checkjob(jobId);
				} catch (InvalidInputDataException err) {
					message += " Error in Job id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setJob_id(jobId);
//---------------------------------------			
				String sal = request.getParameter("salary");
				try {
					Validator.checkStringForParseFloat(sal);
				} catch (InvalidInputDataException err) {
					message += " Error in Job id input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				float salParse = Float.parseFloat(sal);
				try {
					Validator.checkSalLimit(salParse);
				} catch (InvalidInputDataException err) {
					message += " Error in Salry input </p>";
					String errorPage = ExceptionManager.HandleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setSalary(salParse);
//----------------------------------------------			
				result = EmployeeDao.insertEmployee(newemp);
			} catch (Exception err) {
				message += " Error in Inserting the input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
			}
			out.println("<div> Add New Employee: " + result + "</div>");
			// + new Employee()); -> from the servlet send only
			// object are illegal.
		} else {
			out.print("<h1> SELECT VALID CHOICE </h1>");
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
//--------------------------------
			String fname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(fname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee First Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checklengthOfString(fname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee First Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setFirst_name(fname);
//-----------------------------------
			String lname = request.getParameter("lname");
			try {
				Validator.checkStringOnly(lname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee Last Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checklengthOfString(lname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee Last Name input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setLast_name(lname);
//----------------------------------			
			String email = request.getParameter("email");
			try {
				Validator.checkMail(email);
			} catch (InvalidInputDataException err) {
				message += " Error in E-mail input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setEmail(email);
//--------------------------------------			
			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("hdate");
			// Date hire_date=hire_dateFormate.parse(emp_HireDate);

			try {
				Validator.checkDateFormat(emp_HireDate);
			} catch (InvalidInputDataException err) {
				message += " Error in Hire Date input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			Date newDate = null;
			try {
				newDate = hire_dateFormate.parse(emp_HireDate);
			} catch (ParseException err) {
				message += " Error in Hire Date input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.CheckNofutureDate(newDate);
			} catch (InvalidInputDataException err) {
				message += " Error in Hire Date input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}

			newemp.setHire_date(newDate);
//----------------------------------------
			String jobId = request.getParameter("jobid");
			try {
				Validator.checkjob(jobId);
			} catch (InvalidInputDataException err) {
				message += " Error in Job id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setJob_id(jobId);
//---------------------------------------			
			String sal = request.getParameter("salary");
			try {
				Validator.checkStringForParseFloat(sal);
			} catch (InvalidInputDataException err) {
				message += " Error in Job id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			float salParse = Float.parseFloat(sal);
			try {
				Validator.checkSalLimit(salParse);
			} catch (InvalidInputDataException err) {
				message += " Error in Salry input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setSalary(salParse);
//----------------------------------------------			
			result = EmployeeDao.updateEmployee(newemp);
		} catch (Exception err) {
			message += " Error in Updating the input </p>";
			String errorPage = ExceptionManager.HandleException(err, source, message);
			out.print(errorPage);
		}
		out.println("<div> Update Employee: " + result + "</div>");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String source = "AddNewEmployee";
		String message = "<h1>Error while " + source + "</h1>";
		int result = 0;
		String id = null;
		try {
			id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee id input </p>";
				String errorPage = ExceptionManager.HandleException(err, source, message);
				out.print(errorPage);
				return; // it terminate the code execution beyond this point
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
			result = EmployeeDao.deleteEmployee(empId);
		} catch (Exception e) {
			message += " Error in Deleting the input </p>";
			String errorPage = ExceptionManager.HandleException(e, source, message);
			out.print(errorPage);
		}
		out.print("<div> Deleted Employee: " + result + "</div>");
	}


}
