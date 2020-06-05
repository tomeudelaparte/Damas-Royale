package com.damasroyale.controlador.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

/**
 * Servicio Rest para la comunicación entre partidas online.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Path("/PartidaRest")
public class PartidaRest {
	
	// EJB para utilizar las funciones de partida.
	@EJB
	PartidaEJB partidaEJB;

	// EJB para utilizar las funciones de usuario.
	@EJB
	UsuarioEJB usuarioEJB;

	// EJB para utilizar las funciones de puntuación.
	@EJB
	PuntuacionEJB puntuacionEJB;

	// EJB para utilizar las funciones de resultado.
	@EJB
	ResultadoEJB resultadoEJB;

	// EJB para utilizar las funciones de movimiento.
	@EJB
	MovimientoEJB movimientoEJB;

	// EJB para utilizar las funciones de mensaje.
	@EJB
	MensajeEJB mensajeEJB;

	// EJB para utilizar las funciones de la lista de partidas.
	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

	/**
	 * Crea una partida y la añade a una lista de partidas.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 */
	@GET
	@Path("/create/{idPartida}/{idUsuario}")
	public void createPartida(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		DamasOnline partida = new DamasOnline(Integer.valueOf(idPartida), Integer.valueOf(idUsuario));

		listaPartidasEJB.add(partida);
	}

	/**
	 * Añade el segundo jugador (oponente) a la partida.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 */
	@GET
	@Path("/setOponente/{idPartida}/{idUsuario}")
	public void setOponente(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).setOponente(Integer.valueOf(idUsuario));
	}
	
	/**
	 * Añade el turno al usuario.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 */
	@GET
	@Path("/setTurnoOponente/{idPartida}/{idUsuario}")
	public void setTurno(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).setTurnoUsuario(Integer.valueOf(idUsuario));
	}

	/**
	 * Obtiene la partida y la devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @return Partida
	 */
	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getPartida(@PathParam("idPartida") String idPartida) {

		return partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

	}

	/**
	 * Obtiene el usuario y lo devuelve en formato JSON.
	 * 
	 * @param idUsuario String, identificador de usuario.
	 * @return Usuario
	 */
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

	/**
	 * Obtiene la puntuación del usuario y lo devuelve en format JSON.
	 * 
	 * @param idUsuario String, identificador de usuario.
	 * @return int
	 */
	@GET
	@Path("/getPuntuacionUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getPuntuacionUsuario(@PathParam("idUsuario") String idUsuario) {

		ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(Integer.valueOf(idUsuario));

		return puntuacionEJB.getPuntuacion(Integer.valueOf(idUsuario), resultados);
	}

	/**
	 * Obtiene el tablero de la partida y lo devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 * @return int[][]
	 */
	@GET
	@Path("/getTablero/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public int[][] getTablero(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getTablero(Integer.valueOf(idUsuario));
	}

	/**
	 * Obtiene el turno de la partida y lo devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @return Integer
	 */
	@GET
	@Path("/getTurno/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getTurno(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getTurnoUsuario();
	}

	/**
	 * Obtiene el estado de la partida y lo devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @return boolean
	 */
	@GET
	@Path("/getEstado/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getEstado(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).isFinalizada();
	}

	/**
	 * Obtiene el ganador de la partida y lo devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @return Integer
	 */
	@GET
	@Path("/getGanador/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getResultado(@PathParam("idPartida") String idPartida) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).getGanador();
	}

	/**
	 * Abandona la partida dando la victoria al oponente.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 */
	@GET
	@Path("/abandonar/{idPartida}/{idUsuario}")
	public void abandonar(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).abandonarPartida(Integer.valueOf(idPartida), Integer.valueOf(idUsuario));
	}

	/**
	 * Comprueba si uno de los dos jugadores han solicitado tablas.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 * @return boolean
	 */
	@GET
	@Path("/comprobarTablas/{idPartida}/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean comprobarTablas(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		return listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).comprobarTablas(Integer.valueOf(idUsuario));
	}

	/**
	 * Solicita acordar tablas al oponente.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 */
	@GET
	@Path("/tablas/{idPartida}/{idUsuario}")
	public void solicitarTablas(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario) {

		listaPartidasEJB.getPartida(Integer.valueOf(idPartida)).solicitarTablas(Integer.valueOf(idPartida), Integer.valueOf(idUsuario));
	}

	/**
	 * Realiza el movimiento de una ficha.
	 * 
	 * @param idPartida      String, identificador de partida.
	 * @param idUsuario      String, identificador de usuario.
	 * @param filaOrigen     String, fila origen de la ficha.
	 * @param filaDestino    String, fila destino de la ficha.
	 * @param columnaOrigen  String, columna origen de la ficha.
	 * @param columnaDestino String, columa destino de la ficha.
	 * @return Movimiento
	 */
	@GET
	@Path("/makeMovimiento/{idPartida}/{idUsuario}/{filaOrigen}/{filaDestino}/{columnaOrigen}/{columnaDestino}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movimiento makeMovimiento(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario,
			@PathParam("filaOrigen") int filaOrigen, @PathParam("filaDestino") int filaDestino,
			@PathParam("columnaOrigen") int columnaOrigen, @PathParam("columnaDestino") int columnaDestino) {
		

		// Obtiene la partida.
		DamasOnline damas = listaPartidasEJB.getPartida(Integer.valueOf(idPartida));

		// Realiza y obtiene el movimiento.
		Movimiento movimiento = damas.mover(Integer.valueOf(idPartida), Integer.valueOf(idUsuario), filaOrigen, filaDestino, columnaOrigen, columnaDestino);

		// Si movimiento es diferente a null.
		if (movimiento != null) {

			// Añade el movimiento a la partida.
			movimientoEJB.addMovimiento(movimiento);
		}

		// Devuelve el movimiento
		return movimiento;
	}
	
	/**
	 * Envia un mensaje por el chat.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @param idUsuario String, identificador de usuario.
	 * @param texto     String, mensaje del chat.
	 */
	@GET
	@Path("/sendMensaje/{idPartida}/{idUsuario}/{texto}")
	public void sendMensaje(@PathParam("idPartida") String idPartida, @PathParam("idUsuario") String idUsuario, @PathParam("texto") String texto) {

		// Obtiene la fecha actual.
		Date date = new Date();

		// Formatea la fecha a un formato de hora.
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm");

		// Si el mensaje cumple con el límite de carácteres.
		if (texto.length() > 0 && texto.length() < 255) {

			// Crea el mensaje y se añade.
			Mensaje mensaje = new Mensaje(0, Integer.valueOf(idPartida), Integer.valueOf(idUsuario), hora.format(date), texto);

			mensajeEJB.addMensaje(mensaje);
		}
	}

	/**
	 * Obtiene todos los mensajes de la partida y los devuelve en formato JSON.
	 * 
	 * @param idPartida String, identificador de partida.
	 * @return ArrayList(Mensaje)
	 */
	@GET
	@Path("/getMensajes/{idPartida}")
	public ArrayList<Mensaje> getMensajes(@PathParam("idPartida") String idPartida) {

		return mensajeEJB.getAllMensajeByIdPartida(Integer.valueOf(idPartida));
	}
}
