package com.example.alumnos.controller;

import com.example.alumnos.model.dao.AlumnoDao;
import com.example.alumnos.model.dao.GrupoDao;
import com.example.alumnos.model.dao.impl.AlumnoDaoImpl;
import com.example.alumnos.model.dao.impl.GrupoDaoImpl;
import com.example.alumnos.model.entity.Alumno;
import com.example.alumnos.service.AlumnoService;
import com.example.alumnos.service.GrupoService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "AltaAlumnoController", value = "/alumno/alta")
public class AltaAlumnoController extends HttpServlet {

    private AlumnoDao alumnoDao;
    private GrupoDao grupoDao;
    private AlumnoService alumnoService;

    @Override
    public void init() throws ServletException {
        alumnoDao = new AlumnoDaoImpl();
        grupoDao = new GrupoDaoImpl();
        alumnoService = AlumnoService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Alumno alumno = alumnoService.inicializaAlumno();
        request.setAttribute("alumno", alumno);
        request.setAttribute("alumnos", alumnoDao.listAll());
        request.setAttribute("grupos", grupoDao.listAll());
        request.setAttribute("readonly", false);
        request.setAttribute("showSubmit", true);

        request.getRequestDispatcher("/sections/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Alumno alumno = alumnoService.requestToClass(request);
        Map<String,String> errorsItems = alumnoService.errors(request);
        if (errorsItems.isEmpty()) {
            if (alumnoDao.add(alumno)!=null) {
                String mensaje = "El alumno " + alumno.getNombre() + ", " + alumno.getApellido1() + alumno.getApellido2()
                        +  " ha sido dado de alta.";
                request.setAttribute("alertSuccess", mensaje);
                alumno = alumnoService.inicializaAlumno();

            } else {
                String mensaje = "El alumno " + alumno.getNombre() + ", " + alumno.getApellido1() + alumno.getApellido2()
                        +  " no ha sido dado de alta. Ya existe.";
                request.setAttribute("alertDanger", mensaje);
            }
        } else {
            request.setAttribute("alertDanger", "Los datos no son correctos, no se pudo guardar el alumno");
        }
        request.setAttribute("errorsItems", errorsItems);
        request.setAttribute("alumno", alumno);
        request.setAttribute("grupos", grupoDao.listAll());
        request.setAttribute("articulos", alumnoDao.listAllFillGrupo());
        request.setAttribute("readonly", false);
        request.setAttribute("showSubmit", true);
        request.getRequestDispatcher("/sections/add.jsp").forward(request, response);

    }
}
