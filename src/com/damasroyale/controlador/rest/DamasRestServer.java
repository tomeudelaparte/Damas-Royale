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

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

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

//	private HashMap<Integer, Damas> listaPartidas = new HashMap<Integer, Damas>();

	@GET
	@Path("/getPartida/{idPartida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida actualizarInformacion(@PathParam("idPartida") String idPartida) {

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

//	@GET
//	@Path("/createPartida/{idPartida}")
//	public void createPartida(@PathParam("idPartida") Integer idPartida) {
//
//		Damas damas = new Damas();
//
//		listaPartidas.put(idPartida, damas);
//
//	}

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
