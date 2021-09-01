package com.research;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.fileupload.FileUploadException;

//import com.fileupload.server.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		try {
//		ServletFileUpload sf = new ServletFileUpload (new DiskFileItemFactory());
//		List<FileItem> multifiles = null;
//		multifiles = sf.parseRequest(request);
//		
//		for(FileItem item: multifiles) {
//		String fi = item.getName();
//		item.write(new File("D:\\SOFTWRE\\JSPworkspace\\fileUpload\\"+ fi.substring(fi.lastIndexOf("\\"))));
//		}
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//		System.out.println("files uploaded");
		
//	FileItem fileItem = null;
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> multifiles = null;
		try {
			multifiles = sf.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(FileItem item: multifiles) {
			String fi = FilenameUtils.getName(item.getName());
		
//		try {
//			fileItem = sf.parseRequest(request).get(0);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//
//			String fileName = FilenameUtils.getName(fileItem.getName());
			String filePath = "D:\\SOFTWRE\\JSPworkspace\\fileUpload\\"
					+ fi;
			try {
			item.write(new File(filePath));
			
			request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }         		
		
			request.getRequestDispatcher("/results.jsp").forward(request, response);
			
//			catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
//			 System.out.println(fileItem.getString());

//			FileReader fr = new FileReader(filePath);
//
//			int i;
//	    	while ((i = fr.read()) != -1) {
//				System.out.print((char) i);		}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		
		
		
		}
	}
//		

//

