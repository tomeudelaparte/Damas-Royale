package com.damasroyale.controlador.rest;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.damasroyale.modelo.ejb.ListaPartidasEJB;
import com.damasroyale.modelo.ejb.MensajeEJB;
import com.damasroyale.modelo.ejb.MovimientoEJB;
import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.juego.DamasOnline;
import com.damasroyale.modelo.pojo.Mensaje;
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
	MovimientoEJB movimientoEJB;

	@EJB
	MensajeEJB mensajeEJB;

	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

	@GET
	@Path("/create/{idPartida}/{idUsuario}")
	public void createPartida(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		DamasOnline partida = new DamasOnline(Integer.valueOf(idPartida), Integer.valueOf(idUsuario));

		listaPartidasEJB.add(partida);
	}

	@GET
	@Path("/setOponente/{idPartida}/{idUsuario}")
	public void setOponente(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).setOponente(Integer.valueOf(idUsuario));
	}

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

	}

	@GET
	@Path("/getTablero/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int[][] getTablero(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getTablero(Integer.valueOf(idUsuario));
	}

	@GET
	@Path("/getTurno/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getTurno(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getTurnoUsuario();
	}

	@GET
	@Path("/getEstado/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getEstadoPartida(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).isFinalizada();

	}

	@GET
	@Path("/getGanador/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getResultado(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getGanador();
	}

	@GET
	@Path("/abandonar/{idPartida}/{idUsuario}")
	public void abandonarPartida(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).abandonarPartida(Integer.valueOf(idPartida),
				Integer.valueOf(idUsuario));

	}

	@GET
	@Path("/comprobarTablas/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean comprobarTablas(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).comprobarTablas(Integer.valueOf(idUsuario));
	}

	@GET
	@Path("/tablas/{idPartida}/{idUsuario}")
	public void solicitarTablas(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).solicitarTablas(Integer.valueOf(idPartida),
				Integer.valueOf(idUsuario));

	}

	@GET
	@Path("/sendMensaje/{idPartida}/{idUsuario}/{texto}")
	public void sendMensaje(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario,
			@PathParam("texto") String texto) {

		if (texto.length() > 0 && texto.length() < 255) {

			Mensaje mensaje = new Mensaje(0, Integer.valueOf(idPartida), Integer.valueOf(idUsuario), texto);

			mensajeEJB.addMensaje(mensaje);
		}

	}

	@GET
	@Path("/getMensajes/{idPartida}")
	public ArrayList<Mensaje> getMensajes(@PathParam("idPartida") String idPartida) {

		return mensajeEJB.getMensajesByIdPartida(Integer.valueOf(idPartida));

	}

	@GET
	@Path("/makeMovimiento/{idPartida}/{idJugador}/{filaOrigen}/{filaDestino}/{columnaOrigen}/{columnaDestino}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movimiento makeMovimiento(@PathParam("idPartida") String idPartida, @PathParam("idJugador") String idJugador,
			@PathParam("filaOrigen") int filaOrigen, @PathParam("filaDestino") int filaDestino,
			@PathParam("columnaOrigen") int columnaOrigen, @PathParam("columnaDestino") int columnaDestino) {

		DamasOnline damas = listaPartidasEJB.getPartida(Integer.valueOf(idPartida));

		Movimiento movimiento = damas.mover(Integer.valueOf(idPartida), Integer.valueOf(idJugador), filaOrigen,
				filaDestino, columnaOrigen, columnaDestino);

		if (movimiento != null) {
			movimientoEJB.addMovimiento(movimiento);
		}

		return movimiento;
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

		ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(Integer.valueOf(idUsuario));

		return puntuacionEJB.getPuntuacion(Integer.valueOf(idUsuario), resultados);

	}

}
