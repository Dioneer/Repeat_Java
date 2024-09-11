<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>title</title>
</head>
<body>
    <h1>List of flights:</h1>
    <ul>
       Size: ${requestScope.flights.size()}
       Size: ${requestScope.flights.get(0).getDescription()}
    </ul>
</body>
</html>