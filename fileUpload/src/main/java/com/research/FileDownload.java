package com.research;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class fileDownload
 */
public class FileDownload extends HttpServlet {	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
String filename = request.getParameter("sel");

HttpSession session = request.getSession();
session.setAttribute("thefilename", filename);
out.println("File to be dowloaded : " + session.getAttribute("thefilename"));

String filename1 = (String)session.getAttribute("thefilename");   
String filepath = "D:\\SOFTWRE\\JSPworkspace\\fileUpload\\";   
response.setContentType("APPLICATION/OCTET-STREAM");   
response.setHeader("Content-Disposition","attachment; filename=\"" + filename1 + "\"");   
  
FileInputStream fileInputStream = new FileInputStream(filepath + filename1);  
            
int i;   
while ((i=fileInputStream.read()) != -1) {  
out.write(i);   
}   
fileInputStream.close();   
out.close();   
}  
  

}


