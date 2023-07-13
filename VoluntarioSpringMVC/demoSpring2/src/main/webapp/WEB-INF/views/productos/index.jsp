<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>TiendaDAW</title>

    <link rel="stylesheet" href="<c:url value="css/styles.css"/>">
    <!--<link href="css/styles.css" rel="stylesheet"/>-->

</head>

<body>
<%@include file="/WEB-INF/layout/nav.jspf" %>
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach var="producto" items="${productos}">
                <div class="col mb-5">
                    <%@ include file="/WEB-INF/layout/tarjeta_producto.jspf" %>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/layout/footer.jspf" %>

</body>
</html>
