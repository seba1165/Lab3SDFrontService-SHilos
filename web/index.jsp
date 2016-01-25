<%-- 
    Document   : index
    Created on : 24-01-2016, 20:05:06
    Author     : Seba
--%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
        %>
        <h1>Web Search Engine</h1>
        <form action = "FrontService" method="POST">
        <input type ="text" name="query"/>
        <input type ="submit" value="Buscar"/>
        <input type ="reset" value="Limpiar"/>
    </body>
</html>
