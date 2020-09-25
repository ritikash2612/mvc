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

@WebServlet("/ContactInfo")
public class ContactInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonDAO personDao = new PersonDAO();
		String id = (String) request.getParameter("pid");

		System.out.println("the id fetched is " + id);
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String phone = request.getParameter("phone");

		System.out.println("Id on address page/hidden " + request.getParameter("pid"));
		String errors = "";
		if (address == null || address.trim().length() == 0) {
			errors += "Please provide your address";
		}
		if (city == null || city.trim().length() == 0) {
			errors += "<br/>Please provide city";
		}
		if (state == null || state.trim().length() == 0) {
			errors += "<br/>Please provide State";
		}
		if (phone == null || phone.trim().length() == 0 || phone.trim().length() < 10) {
			errors += "<br/>Please provide contact number";
		}

		System.out.println("errors: " + errors);

		if (errors == null || errors.length() == 0) {
			HttpSession session = request.getSession();
			personDao.updateAddressInfo(address, city, state, country, phone, id);
//			try {
//				Connection conn = DBUtility.getConnection();
//				String sql = "update customer set address = ?, city =?, state =?, country = ?, phone = ? where id = "
//						+ id;
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, address);
//				pstmt.setString(2, city);
//				pstmt.setString(3, state);
//				pstmt.setString(4, country);
//				pstmt.setString(5, phone);
//
//				pstmt.executeUpdate();
//				System.out.println("SQL is" + sql);
//
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			session.setAttribute("id", id);

			RequestDispatcher rd = request.getRequestDispatcher("jsp/bankinfo.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("errors", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/error.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
