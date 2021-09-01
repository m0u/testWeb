<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="algorithm.CellBiClust" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
String filename = (String)session.getAttribute("thefilename");
String delimeter = request.getParameter("delimiter");
int minrow = Integer.parseInt(request.getParameter("minrow"));
int mincol = Integer.parseInt(request.getParameter("mincol"));
String outputpath = request.getParameter("outputpath");
String outputfolder = request.getParameter("outputfolder"); 
float rulethreshold = Float.parseFloat(request.getParameter("rulethreshold"));
float biclustersimilarity = Float.parseFloat(request.getParameter("biclustersimilarity"));
String rule = request.getParameter("s1");
CellBiClust ob=new CellBiClust("E:\\Researchwork_2021\\JSPworkspace\\fileUpload"+filename,delimeter,minrow,mincol);
ob.runAlgorithm(outputpath,outputfolder,rule,rulethreshold,biclustersimilarity);
%>


<br>
<h2 align = "center"> Bicluster result </h2>


</body>
</html>