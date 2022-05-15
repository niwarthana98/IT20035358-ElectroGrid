package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	
	private static final String query = "update POWER set Cname=?,Caddress=?,AccNo=?,Cdate=?,UnitNo=?,PriceUnit=?,TotalAmount=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
				PrintWriter pw = resp.getWriter();
				//set content type
				resp.setContentType("text/html");
				//get the id of record
				int id = Integer.parseInt(req.getParameter("id"));
				//get the edit data we want to edit
				String cName = req.getParameter("cName");
				String cAddress = req.getParameter("cAddress");
				String cAccNo = req.getParameter("cAccNo");
				String cdate = req.getParameter("cdate");
				String unitNo = req.getParameter("unitNo");
				String priceUnit = req.getParameter("priceUnit");
				float totalAmount = Float.parseFloat(req.getParameter("totalAmount"));
				//LOAD jdbc driver
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				}catch(ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				//generate the connection
				try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/test","root","y1o2h3a4n5@#");
						PreparedStatement ps = con.prepareStatement(query);){
					ps.setString(1, cName);
					ps.setString(2, cAddress);
					ps.setString(3, cAccNo);
					ps.setString(4, cdate);
					ps.setString(5, unitNo);
					ps.setString(6, priceUnit);
					ps.setFloat(7, totalAmount);
					ps.setInt(8, id);
					int count = ps.executeUpdate();
					if(count==1) {
						pw.println("<h2>Record is Edited Successfully</h2>");
					}else {
						pw.println("<h2>Record is not Edited Successfully</h2>");
					}
				}catch(SQLException se) {
					se.printStackTrace();
					pw.println("<h1>"+se.getMessage()+"</h2>");
				}catch(Exception e) {
					e.printStackTrace();
					pw.println("<h1>"+e.getMessage()+"</h2>");
				}
				pw.println("<a href='Power-Consumption.html'>Home</a>");
				pw.println("<br>");
				pw.println("<a href='powerList'>Power List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
}
