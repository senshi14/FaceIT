package com.group3.faceit.servelet.registration;
import services.*;
import services.registration.RegistrationServices;
import model.registration.RegistrationModel;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.faceit.dao.regisration.RegistrationDAO;

@WebServlet({"/Registration"})
public class RegistrationServelet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("Test");
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String year = req.getParameter("year");
		String gender = req.getParameter("gender");
		RegistrationModel regData = new RegistrationModel();
		regData.setFname(fname);
		regData.setLname(lname);
		regData.setUsername(email);
		regData.setPassword(password);
		regData.setBirthdate(month + "/" + day + "/" + year);
		regData.setGender(gender);
		
		RegistrationServices regServ = new RegistrationServices();
		regServ.registerAccount(regData);
		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		
		
		
	}
}
