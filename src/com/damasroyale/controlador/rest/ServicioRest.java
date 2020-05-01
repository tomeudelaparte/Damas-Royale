package com.damasroyale.controlador.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
import com.damasroyale.modelo.juego.DamasOnline;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@Path("/Rest")
public class ServicioRest {

	@Context
	HttpServletRequest request;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

	@GET
	@Path("/createPartida/{idPartida}/{idUsuario}")
	public void createPartida(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		DamasOnline partida = new DamasOnline(idPartida, idUsuario);

		listaPartidasEJB.add(partida);
	}

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

	}

	@GET
	@Path("/getEstadoPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getEstadoPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getPartidaByID(Integer.valueOf(idPartida)).isFinalizada();

	}

	@GET
	@Path("/getResultadoPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getResultadoPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getResultadoByPartidaID(Integer.valueOf(idPartida));
	}

	@GET
	@Path("/abandonarPartida/{idPartida}/{idUsuario}/{idOponente}")
	public void finalizarPartida(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario,
			@PathParam("idOponente") String idOponente) {

		partidaEJB.getPartidaByID(Integer.valueOf(idPartida)).setFinalizada(true);

		if (idOponente == null) {

			partidaEJB.delPartidaByIdPartida(Integer.valueOf(idPartida));

		} else {

			Partida partida = partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

			Date date = new Date();

			Timestamp fecha_hora = new Timestamp(date.getTime());

			Resultado resultado = new Resultado(0, Integer.valueOf(idPartida), fecha_hora, false,
					Integer.valueOf(idOponente), Integer.valueOf(idUsuario));

			partidaEJB.addResultadoPartida(resultado);

			partida.setFinalizada(true);

			partidaEJB.updatePartida(partida);

		}

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

		DamasOnline damas = listaPartidasEJB.getPartida(idPartida);

		Movimiento movimiento = damas.mover(idPartida, idJugador, filaInicial, filaFinal, columnaInicial, columnaFinal);

		return movimiento;
	}
}
