package com.damasroyale.controlador.rest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;
import com.damasroyale.modelo.utils.Damas;

@Path("/Rest")
public class ServidorRest {
	
	@Context
	HttpServletRequest request;

	@EJB
	PartidaEJB partidaEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;
	
	@EJB
	PuntuacionEJB puntuacionEJB;

	private HashMap<Integer, Damas> listaPartidas = new HashMap<Integer, Damas>();

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida actualizarInformacion(@PathParam("idPartida") Integer idPartida) {
		
		System.out.println("ME HA LLAMAO");

		return partidaEJB.getPartidaByID(idPartida);

	}
	
	@GET
	@Path("/getUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("idUsuario") Integer idUsuario) {
		
		System.out.println("ME HA LLAMAO 2");

		return usuarioEJB.getUsuarioByID(idUsuario);

	}
	
	@GET
	@Path("/getPuntuacionUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getPuntuacionUsuario(@PathParam("idUsuario") Integer idUsuario) {
		
		System.out.println("ME HA LLAMAO 3");
		
		ArrayList<Resultado> resultados = partidaEJB.getAllResultadoByIdUsuario(idUsuario);

		return puntuacionEJB.getPuntuacion(idUsuario, resultados);

	}

	@GET
	@Path("/createPartida/{idPartida}")
	public void createPartida(@PathParam("idPartida") Integer idPartida) {

		Damas damas = new Damas();

		listaPartidas.put(idPartida, damas);

	}

//	@GET
//	@Path("/makeMovimiento/{idPartida}/{idJugador}/{filaInicial}/{filaFinal}/{columnaInicial}/{columnaFinal}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Movimiento makeMovimiento(@PathParam("idPartida") Integer idPartida,
//			@PathParam("idJugador") Integer idJugador, @PathParam("filaInicial") int filaInicial,
//			@PathParam("filaFinal") int filaFinal, @PathParam("columnaInicial") int columnaInicial,
//			@PathParam("columnaFinal") int columnaFinal) {
//
//		Damas damas = listaPartidas.get(idPartida);
//
//		Movimiento movimiento = damas.mover(idPartida, idJugador, filaInicial, filaFinal, columnaInicial, columnaFinal);
//
//		return movimiento;
//	}
}
