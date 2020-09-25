package com.acc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.acc.pojo.Person;

public class DBUtility {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person_db?serverTimezone=UTC", "root",
					"ritika123");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Person getPersonDetail(String id) throws SQLException, ClassNotFoundException {
		Person p = new Person();

		Connection conn;
		conn = getConnection();

		String sql = "select fname, lname, gender, address, city, state, country, phone, bankname, accountno, ssn from customer where id = "
				+ id;
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		ResultSet rs = stmt.getResultSet();
		System.out.println("SQL is" + sql);

		while (rs.next()) {
			String first = rs.getString("fname");
			String lname = rs.getString("lname");
			String gender = rs.getString("gender");
			String add = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String country = rs.getString("country");
			String phone = rs.getString("phone");
			String bankname = rs.getString("bankname");
			String accountno = rs.getString("accountno");
			String ssn = rs.getString("ssn");

			p.setFirstName(first);
			p.setLastName(lname);
			p.setGender(gender);
			p.setAddress(add);
			p.setCity(city);
			p.setState(state);
			p.setCountry(country);
			p.setBankName(bankname);
			p.setAccountNo(accountno);
			p.setPhone(phone);
			
			// hide the first 5 digits for ssn
			ssn = ssn.substring(5, 9);
			p.setSsn(ssn);
		}

		System.out.println(p.getFirstName());
		return p;

	}

}
