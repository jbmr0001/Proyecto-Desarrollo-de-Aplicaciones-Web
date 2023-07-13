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
<div class="container px-1 px-lg-1 my-1">
  <!--<section class="vh-100"></section>-->
  <section style="background-color: #eee;">
    <div class="container py-5">
      <div class="card mb-4">
        <div class="card-body">
          <h1 class="h3 mb-3 fw-normal">Listado de Productos
            <a class="btn btn-outline-primary"
               href="/productos/paginaCRUD" >Volver
            </a>
          </h1>
          <c:forEach var="producto" items="${productos}">
              <div class="row">
                <div class="col-sm-3">
                  <p class="mb-0">ID Producto</p>

                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${producto.id}</p>
                </div>
                <div class="col-sm-3">
                  <p class="mb-0">Nombre</p>

                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${producto.nombre}</p>
                </div>
                <div class="col-sm-3">
                  <p class="mb-0">Descripcion</p>

                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${producto.descripcion}</p>
                </div>
                <div class="col-sm-3">
                  <p class="mb-0">Precio</p>

                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${producto.precio}</p>
                </div>
              </div>

              <hr/>
          </c:forEach>
        </div>
      </div>
    </div>
  </section>
</div>

<%@include file="/WEB-INF/layout/footer.jspf" %>

</body>
</html>

