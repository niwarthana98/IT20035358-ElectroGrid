package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	
	private static final String query = "SELECT Cname,Caddress,AccNo,Cdate,UnitNo,PriceUnit,TotalAmount FROM POWER where id=?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//get the id of record
		int id = Integer.parseInt(req.getParameter("id"));
		//LOAD jdbc driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/test","root","y1o2h3a4n5@#");
				PreparedStatement ps = con.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Customer Name</td>");
			pw.println("<td><input type='text' name='cName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Customer Address</td>");
			pw.println("<td><input type='text' name='cAddress' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Customer Account</td>");
			pw.println("<td><input type='text' name='cAccNo' value='"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Date</td>");
			pw.println("<td><input type='text' name='cdate' value='"+rs.getString(4)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Unit No</td>");
			pw.println("<td><input type='text' name='unitNo' value='"+rs.getString(5)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Price Unit</td>");
			pw.println("<td><input type='text' name='priceUnit' value='"+rs.getString(6)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Total Amount</td>");
			pw.println("<td><input type='text' name='totalAmount' value='"+rs.getFloat(7)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Edit'></td>");
			pw.println("<td><input type='reset' value='cancel'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2>");
		}
		pw.println("<a href='Power-Consumption.html'>Home</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

}
