package com.research;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * Servlet implementation class inputfile
 */
public class inputfile extends HttpServlet {
	File directory = new File("D:\\SOFTWRE\\JSPworkspace\\fileUpload\\");

    //get all the files from a directory
    File[] fList = directory.listFiles();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

}
