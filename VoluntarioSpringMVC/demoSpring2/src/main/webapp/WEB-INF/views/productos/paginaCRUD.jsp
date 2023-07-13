<?xml version="1.0" encoding="UTF-8"?>
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
    <div class="container px-1 px-lg-1 my-1">
        <!--<section class="vh-100"></section>-->
        <section style="background-color: #eee;">

            <div class="container py-5">

                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                                     alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;"></img>
                                <h5 class="my-3"></h5>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Correo</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"></p>
                                    </div>

                                </div>
                                <hr></hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Contraseña</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"></p>
                                    </div>
                                </div>

                                <hr></hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Nombre</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"></p>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card mb-4 mb-md-0">
                                    <div class="card-body">
                                        <h3>Gestionar de Productos</h3>
                                        <hr></hr>
                                        <div class="row">
                                            <div>
                                                <a class="btn btn-primary" href="../productos/listado">Mostrar Productos</a>
                                            </div>
                                        </div>
                                        <hr></hr>
                                        <div class="row">
                                            <div>
                                                <a class="btn btn-outline-primary" href="../productos/crea">Insertar Producto</a>
                                            </div>
                                        </div>
                                        <hr></hr>
                                        <div class="row">
                                            <div>
                                                <a class="btn btn-outline-primary" href="../productos/modifica">Modificar Producto</a>
                                            </div>
                                        </div>
                                        <hr></hr>
                                        <div class="row">
                                            <div>
                                                <a class="btn btn-outline-danger" href="../productos/borra">Borrar Producto</a>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </div>
</section>

<%@include file="/WEB-INF/layout/footer.jspf" %>

</body>
</html>
