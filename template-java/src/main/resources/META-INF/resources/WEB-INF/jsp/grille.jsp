<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%
    String result="<table>";

            for(int i=0; i<8; i++) {
                result += "<tr>";

                for (int j = 0; j < 8; j++) {
                    result += "<td";

                    if (request.getAttribute("Grille").getGrille()[i][j] == -1)
                        result += " class=\"white\"";
                    else if (request.getAttribute("Grille").getGrille()[i][j] == 1)
                        result += " class=\"black\"";
                    result += "></td>";
                }
                result += "</tr>";
            }

            result += "</table>";
%>

${result}


</body>
</html>