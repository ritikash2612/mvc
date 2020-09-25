package com.rs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acc.dao.PersonDAO;
import com.acc.db.DBUtility;
import com.acc.pojo.Person;

/**
 * Servlet implementation class PersonalInfo
 */

@WebServlet("/PersonalInfo")
public class PersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("fname");
		String midName = request.getParameter("mname");
		String lastName = request.getParameter("lname");
		String gender = request.getParameter("gender");

		PersonDAO personDao = new PersonDAO();

		String errors = "";
		if (firstName == null || firstName.trim().length() == 0) {
			errors += "Please provide First Name";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			errors += "<br/>Please provide Last Name";
		}
		if (midName == null || midName.trim().length() == 0) {
			errors += "<br/>Please provide Middle Name";
		}

		System.out.println("errors: " + errors);

		if (errors == null || errors.length() == 0) {
			HttpSession session = request.getSession();

			long key = -1L;
			try {
				key = personDao.insertPersonDetail(firstName, midName, lastName, gender);

			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("id", key);

			RequestDispatcher rd = request.getRequestDispatcher("/contactinfo.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("errors", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
