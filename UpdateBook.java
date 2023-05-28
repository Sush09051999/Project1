package text;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatebook")
public class UpdateBook extends HttpServlet{
	Connection con=null;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=sql@123");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		} catch (SQLException e) {
			
		e.printStackTrace();
		}
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    int id =Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));
		String author=req.getParameter("author");
		
		
		PreparedStatement pstmt=null;
		
		String query="update book_data set book_name=?,book_price=?,author=? where book_id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setString(3, author);
			pstmt.setInt(4, id);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" RECORD UPDATED SUCCESSFULLY</h1>");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
