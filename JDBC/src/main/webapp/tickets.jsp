<%@ page isELIgnored="false" %>
<%@ page  import="Pegas.service.TicketService"%>
<%@ page  import="Pegas.DTO.TicketDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
</head>
<body>
    <h1>List of Tickets for flights:</h1>
    <ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
    <li>${ticket.seatNo()}</li>
    </c:forEach>
    </ul>
</body>
</html>