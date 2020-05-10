package com.damasroyale.controlador.rest;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.damasroyale.modelo.ejb.ListaPartidasEJB;
import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.juego.DamasOnline;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@Path("/PartidaRest")
public class PartidaRest {

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	ResultadoEJB resultadoEJB;

	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

	@GET
	@Path("/create/{idPartida}/{idUsuario}")
	public void createPartida(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		DamasOnline partida = new DamasOnline(idPartida, idUsuario);

		listaPartidasEJB.add(partida);
	}

	@GET
	@Path("/setOponente/{idPartida}/{idUsuario}")
	public void setOponente(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		listaPartidasEJB.getPartida(idPartida).setOponente(idUsuario);
	}

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getPartida(@PathParam("idPartida") Integer idPartida) {

		return partidaEJB.getPartidaByID(idPartida);

	}

	@GET
	@Path("/getTablero/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int[][] getTablero(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		return listaPartidasEJB.getPartida(idPartida).getTablero(idUsuario);
	}

	@GET
	@Path("/getTurno/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getTurno(@PathParam("idPartida") Integer idPartida) {

		return listaPartidasEJB.getPartida(idPartida).getTurnoUsuario();
	}

	@GET
	@Path("/getEstado/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getEstadoPartida(@PathParam("idPartida") Integer idPartida) {

		return listaPartidasEJB.getPartida(idPartida).isFinalizada();

	}

	@GET
	@Path("/getGanador/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getResultado(@PathParam("idPartida") Integer idPartida) {

		return listaPartidasEJB.getPartida(idPartida).getGanador();
	}

	@GET
	@Path("/abandonar/{idPartida}/{idUsuario}")
	public void abandonarPartida(@PathParam("idPartida") Integer idPartida, @PathParam("idUsuario") Integer idUsuario) {

		listaPartidasEJB.getPartida(idPartida).finalizarPartida(idPartida);

		listaPartidasEJB.remove(listaPartidasEJB.getPartida(idPartida));
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

	@GET
	@Path("/getUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("idUsuario") Integer idUsuario) {

		Usuario usuario = usuarioEJB.getUsuarioByID(idUsuario);

		usuario.setContrasenya(null);
		usuario.setEmail(null);
		usuario.setRegistro(null);

		return usuario;

	}

	@GET
	@Path("/getPuntuacionUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getPuntuacionUsuario(@PathParam("idUsuario") Integer idUsuario) {

		ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(idUsuario);

		return puntuacionEJB.getPuntuacion(idUsuario, resultados);

	}

}
