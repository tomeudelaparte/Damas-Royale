package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Mensaje;

public interface MensajeMapper {

	public void addMensaje(Mensaje mensaje);

	public ArrayList<Mensaje> getMensajesByIdPartida(@Param("id") Integer idPartida);
}
