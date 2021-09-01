<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "java.io.File" %>
<%  
File directory = new File("E:\\Researchwork_2021\\JSPworkspace\\fileUpload");
File[] fList = directory.listFiles();
for(File file : fList)
    {
	out.print(file.getName());%>
    <br />
<%    }
	%>
//	
//	<c:forEach items="${files}" var="file">
//    <c:out value="${file.name}" /> <br/>
// </c:forEach> 
<a href = "fileupload.jsp"> Back  </a> 
</body>
</html>