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
public class FichaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String tab = request.getParameter("tab");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || id.equals("")) {

				response.sendRedirect("Jugar");

			} else {

				RequestDispatcher rs;

				if (tab == null || tab.equals("")) {

					response.sendRedirect("Ficha?id=" + id + "&tab=info");

				} else if (tab.equals("info")) {

					rs = getServletContext().getRequestDispatcher("/Informacion");

					request.setAttribute("tab", "info");

					rs.forward(request, response);

				} else if (tab.equals("stats")) {

					rs = getServletContext().getRequestDispatcher("/Estadistica");

					request.setAttribute("tab", "info");

					rs.forward(request, response);

				} else if (tab.equals("history")) {

					rs = getServletContext().getRequestDispatcher("/Historial");

					request.setAttribute("tab", "history");

					rs.forward(request, response);

				} else {

					response.sendRedirect("Ficha?id=" + id + "&tab=info");

				}
			}
		}
	}
}
