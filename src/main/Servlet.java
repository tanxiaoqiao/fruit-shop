package main;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Manager;
import manager.MenuDemo;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	static Manager manager=Manager.getInstance();
	
	
	
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;Charset=utf-8");
		response.setCharacterEncoding("utf-8");			
		PrintWriter out = response.getWriter();
		//验证登录
		int choose= Integer.parseInt(request.getParameter("choose"));
		MenuDemo menuDemo=new MenuDemo();
		try {
			menuDemo.menu(choose,request, response,out);
			System.out.println("3333333333333333");
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
	
	}

	

	
}
