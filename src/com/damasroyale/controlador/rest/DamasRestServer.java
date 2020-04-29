package com.damasroyale.controlador.rest;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.damasroyale.modelo.ejb.ListaPartidasEJB;
import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Damas;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@Path("/Rest")
public class DamasRestServer {

	@Context
	HttpServletRequest request;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	ListaPartidasEJB<Damas> listaPartidasEJB;

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

	}

	@GET
	@Path("/getUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("idUsuario") String idUsuario) {

		Usuario usuario = usuarioEJB.getUsuarioByID(Integer.valueOf(idUsuario));

		usuario.setContrasenya(null);
		usuario.setEmail(null);
		usuario.setRegistro(null);

		return usuario;

	}

	@GET
	@Path("/getPuntuacionUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getPuntuacionUsuario(@PathParam("idUsuario") String idUsuario) {

		ArrayList<Resultado> resultados = partidaEJB.getAllResultadoByIdUsuario(Integer.valueOf(idUsuario));

		return puntuacionEJB.getPuntuacion(Integer.valueOf(idUsuario), resultados);

	}

	@GET
	@Path("/createPartida/{idPartida}/{idUsuario}")
	public void createPartida(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		Damas partida = new Damas(idPartida, idUsuario);

		listaPartidasEJB.add(partida);
	}

	@GET
	@Path("/getTablero/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int[][] getTablero(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		return listaPartidasEJB.getPartida(idPartida).getTablero(idUsuario);
	}

	@GET
	@Path("/makeMovimiento/{idPartida}/{idJugador}/{filaOrigen}/{filaDestino}/{columnaOrigen}/{columnaDestino}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movimiento makeMovimiento(@PathParam("idPartida") Integer idPartida,
			@PathParam("idJugador") Integer idJugador, @PathParam("filaOrigen") int filaInicial,
			@PathParam("filaDestino") int filaFinal, @PathParam("columnaOrigen") int columnaInicial,
			@PathParam("columnaDestino") int columnaFinal) {

		Damas damas = listaPartidasEJB.getPartida(idPartida);

		Movimiento movimiento = damas.mover(idPartida, idJugador, filaInicial, filaFinal, columnaInicial, columnaFinal);

		return movimiento;
	}
}
