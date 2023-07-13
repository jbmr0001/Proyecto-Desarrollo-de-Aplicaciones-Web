<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>TiendaDAW</title>

    <link rel="stylesheet" href="<c:url value="../../css/styles.css"/>">
    <!--<link href="css/styles.css" rel="stylesheet"/>-->

</head>

<body>
<%@include file="/WEB-INF/layout/nav.jspf" %>
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                                       src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..."/></div>
            <div class="col-md-6">
                <div class="small mb-1">Id: ${producto.id}</div>
                <h1 class="display-5 fw-bolder">${producto.nombre}</h1>

                <p class="lead">${producto.descripcion}</p>
                <div class="fs-5 mb-5">

                    <span>${producto.precio} Euros</span>
                </div>
                <div class="d-flex">

                            <c:set var="idProducto" value="${ctrlProducto.producto.id}"/>

                            <a class="btn btn-outline-dark flex-shrink-0"
                                            href="/productos/paginaCRUD"  >Comprar
                            </a>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/layout/footer.jspf" %>

</body>
</html>
