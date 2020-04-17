package com.damasroyale.controlador;

import java.io.IOException; 
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Ficha")
public class Ficha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String page = request.getParameter("page");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || id.equals("")) {

				response.sendRedirect("Jugar");

			} else {
				
				RequestDispatcher rs;


				if (page == null) {

					rs = getServletContext().getRequestDispatcher("/Informacion");

					request.setAttribute("page", "info");

					rs.forward(request, response);

				} else {

					if (page.equals("info")) {

						rs = getServletContext().getRequestDispatcher("/Informacion");

						request.setAttribute("page", "info");

						rs.forward(request, response);

					} else if (page.equals("stats")) {

						rs = getServletContext().getRequestDispatcher("/Estadisticas");

						request.setAttribute("page", "info");

						rs.forward(request, response);

					} else if (page.equals("history")) {

						rs = getServletContext().getRequestDispatcher("/Historial");

						request.setAttribute("page", "history");

						rs.forward(request, response);

					} else {

						rs = getServletContext().getRequestDispatcher("/Informacion");

						request.setAttribute("page", "info");

						rs.forward(request, response);
					}
				}
			}
		}
	}

}
