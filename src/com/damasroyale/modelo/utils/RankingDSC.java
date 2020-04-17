package com.damasroyale.modelo.ejb;

import java.util.Comparator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.pojo.Rank;

@Stateless
@LocalBean
public class RankingEJB implements Comparator<Rank> {

    @Override
    public int compare(Rank r01, Rank r02) {
        return Integer.valueOf(r02.getPuntuacion()).compareTo(Integer.valueOf(r01.getPuntuacion()));
    }
	
}
