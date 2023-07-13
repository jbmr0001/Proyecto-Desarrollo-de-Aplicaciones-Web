package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    //...
    private final Map<String,String> usuarios = new HashMap<>();
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errores = new HashMap<>(); //map para errores en vista
//recuperar parámetros
        String correo = request.getParameter("correo");
        String contraseña1 = request.getParameter("contr1");
        String contraseña2 = request.getParameter("contr2");
//validamos isbn
        if (! (correo.length() >= 4 && correo.length() <= 255) ) {
            errores.put("correo", "Debe indicarse un correo válido [4-255] caracteres)");
        }
        if (! correo.matches(".*@.*")) {
            errores.put("correo", "Formato de correo no válido (....@....");
        }
        if (!contraseña1.equals(contraseña2)) {
            errores.put("correo", "La contraseña ha de ser la misma en los dos campos");
        }

        if ( errores.size() > 0 ) { //Hay errores, informar al usuario
            request.setAttribute("errores", errores); //pasar datos a contexto request
            request.setAttribute("correo", correo);
            request.setAttribute("contr", contraseña1);
//Volver a mostrar formulario con información y mensajes de error
            request.getRequestDispatcher("/registrarse.xhtml")
                    .forward(request, response);
        } else {

            request.setAttribute("correo", correo); //pasar datos a contexto request
            request.setAttribute("contr", contraseña1);
            request.getRequestDispatcher("/paginaUsuario.xhtml").forward(request,response); //delegar generación de respuesta a JSP
// Datos correctos, procesar adecuadamente, e.g. reenviamos el usuario
        }
    }
}
