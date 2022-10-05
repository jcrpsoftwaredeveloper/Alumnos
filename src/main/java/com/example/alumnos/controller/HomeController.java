package com.example.alumnos.controller;

import com.example.alumnos.model.dao.AlumnoDao;
import com.example.alumnos.model.dao.impl.AlumnoDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeController", value = "")
public class HomeController extends HttpServlet {
    private AlumnoDao alumnoDao;

    public void init() {
        alumnoDao = new AlumnoDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
