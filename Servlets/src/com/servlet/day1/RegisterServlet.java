package com.servlet.day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request , HttpServletResponse response){
		
		System.out.println("I am In doGet Method");
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		String id=request.getParameter("id");
		
		String name=request.getParameter("name");
		
		String password=request.getParameter("password");
		
		String address=request.getParameter("address");
		
		String zipcode=request.getParameter("zipcode");
		
		System.out.println(id+"  "+name+"   "+password+"   "+address+"   "+zipcode);
		
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema", "root", "Princy@45");
			
			String sql = "insert into first_tables values(?,?,?,?,?)";

			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, Integer.parseInt(id));
			
			statement.setString(2, name);
			
			statement.setString(3, address);
			
			statement.setString(4, zipcode);
			
			statement.setString(5, password);
			
			statement.executeUpdate();
			
			//while(rs.next()){
				//System.out.println("the team is-->" +rs.getInt(1)+"     "+rs.getString(2)+"  "+rs.getString(3)+"   "+rs.getInt(4));
				
		//	}
			
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		
			PrintWriter out=response.getWriter();
			
			out.println("<html>");
			out.println("Saved Successfully");
			out.println("</html>");
			
			
			
		
		}
	}

}
