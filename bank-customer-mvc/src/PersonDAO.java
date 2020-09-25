

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.acc.db.DBUtility;
import com.acc.pojo.Person;

public class PersonDAO {

	public Person getPersonDetail(String id) throws SQLException, ClassNotFoundException {
		Person p = new Person();

		Connection conn;
		conn = DBUtility.getConnection();

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

	public void saveAddressDetails(String id, String bank, String account, String ssn) {
		Connection conn;
		try {
			conn = DBUtility.getConnection();
			String sql = "update customer set bankname = ?, accountno =?, ssn =? where id = " + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bank);
			pstmt.setString(2, account);
			pstmt.setString(3, ssn);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public long insertPersonDetail(String fn, String mn, String ln, String gender) {
		Connection conn;
		long key = -1L;
		try {
			conn = DBUtility.getConnection();
			String sql = "INSERT INTO customer(fname, mname, lname, gender) VALUES(?,?,?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, fn);
			pstmt.setString(2, mn);
			pstmt.setString(3, ln);
			pstmt.setString(4, gender);

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getLong(1);
			}
			System.out.println("key is: " + key);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;

	}

	public void updateAddressInfo(String address, String city, String state, String country, String phone, String id) {
		try {
			Connection conn = DBUtility.getConnection();
			String sql = "update customer set address = ?, city =?, state =?, country = ?, phone = ? where id = " + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address);
			pstmt.setString(2, city);
			pstmt.setString(3, state);
			pstmt.setString(4, country);
			pstmt.setString(5, phone);

			pstmt.executeUpdate();
			System.out.println("SQL is" + sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
