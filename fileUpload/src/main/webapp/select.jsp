<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<% String filename = request.getParameter("sel");
session.setAttribute("thefilename", filename);
out.println("Input file is : " + session.getAttribute("thefilename"));
%>
<body bgcolor="violet">
<form action = "compute_bicluster.jsp" >
<h3> Please specify the parameters for biclustering </h3>
<pre> 
<% String value = (String)session.getAttribute("thefilename");%>
Input file : <input type = text size =50 name= file value= "<%=value%>">
Delimiter : <input type = text size =50 name= delimiter value= "Enter ',' for a CSV file" >
Minimum number of rows : <input type = text size =50 name= minrow value="Enter the minimum number of rows for cluster" >
Minimum number of columns : <input type = text size =50 name= mincol value="Enter the minimum number of columns for cluster" >       
Output path : <input type = text size =50 name= outputpath value="Enter the path where the generated output will be stored" >    
Output folder name : <input type = text size =50 name= outputfolder value="Enter the name of the folder for output files ">   
Rule generation : <select name="s1">
<option value="true">true</option>
<option value="false">false</option>   
</select>
Minimum threshold for predicting from the rule : <input type = text size =50 name= rulethreshold value= "Enter a value between 0 to 1" >
Minimum threshold for comparing two biclusters for prediction : <input type = text size =50 name= biclustersimilarity value="Enter a value between 0 to 1" >
                                                    
<input type=submit name=submit value="submit" >
 
</form>                                       
                                                                
</pre>
<hr>
</body>                                                                                                                                                                                                                                                                                                                                                                                  </body>
</html>