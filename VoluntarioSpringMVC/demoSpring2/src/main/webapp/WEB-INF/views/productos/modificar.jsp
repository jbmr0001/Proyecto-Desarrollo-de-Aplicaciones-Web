<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>TiendaDAW</title>

    <link rel="stylesheet" href="<c:url value="../css/styles.css"/>">
    <!--<link href="css/styles.css" rel="stylesheet"/>-->

</head>

<body>
<%@include file="/WEB-INF/layout/nav.jspf" %>
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <section>
            <main class="form-signin w-100 m-auto">

                <h1 class="h3 mb-3 fw-normal">Modificar Producto.
                    <a class="btn btn-outline-primary"
                       href="../productos/paginaCRUD" >Volver
                    </a>
                </h1>
                <p class="text-danger">${error}</p>

                <form:form method="POST" modelAttribute="producto" action="modifica">
                    <form:errors path="*" cssClass="text-danger" element="div"/>
                    <div class="form-group">
                        <label>Id:</label>
                        <form:input path="id" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Nombre:</label>
                        <form:input path="nombre" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Descripcion:</label>
                        <form:input path="descripcion" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Precio</label>
                        <form:input path="precio" class="form-control"/>
                    </div>
                    <input type="submit" name="enviar" value="Modificar Producto" class="w-100 btn btn-lg btn btn-success">
                </form:form>
            </main>
        </section>
    </div>
</section>

<%@include file="/WEB-INF/layout/footer.jspf" %>

</body>
</html>