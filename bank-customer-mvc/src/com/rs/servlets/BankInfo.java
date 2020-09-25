package com.rs.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

@WebServlet("/BankInfo")
public class BankInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getParameter("pid");

		System.out.println("the id fetched is " + id);

		String bank = request.getParameter("bank");
		String account = request.getParameter("account");
		String ssn = request.getParameter("ssn");

		System.out.println("Id on address page/hidden " + request.getParameter("pid"));
		String errors = "";

		if (account == null || account.trim().length() == 0 || account.trim().length() < 10) {
			errors += "<br/>Please provide account number";
		}
		if (ssn == null || ssn.trim().length() == 0 || ssn.trim().length() < 9) {
			errors += "<br/>Please provide a valid SSN";
		}

		System.out.println("errors: " + errors);

		if (errors == null || errors.length() == 0) {
			HttpSession session = request.getSession();

			session.setAttribute("id", id);
			Person p;
			PersonDAO personDao = new PersonDAO();
			personDao.saveAddressDetails(id, bank, account, ssn);

			try {
				p = personDao.getPersonDetail(id);
				System.out.println("person details fetched " + p.getFirstName());
				session.setAttribute("person", p);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("jsp/customerbankinfo.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("errors", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/error.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
