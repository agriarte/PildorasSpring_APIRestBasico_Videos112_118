<%-- 
    Document   : index.jsp
    Created on : 23-mar-2024, 19:53:22
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Indice de Proyecto Rest</title>
    </head>
    <body>
        <h1 style="text-align: center">Aplicaci�n Spring Rest</h1>
        <hr>
        <a href="${pageContext.request.contextPath}/prueba/saludo">Pulsa aqu� para saludo prueba</a><br>
        <a href="${pageContext.request.contextPath}/api/empleados">Obtener Empleados</a>
    </body>
</html>
