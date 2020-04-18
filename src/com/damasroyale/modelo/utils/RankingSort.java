package com.damasroyale.modelo.utils;

import java.util.Comparator; 

import com.damasroyale.modelo.pojo.Rank;

public class RankingSort implements Comparator<Rank> {

    @Override
    public int compare(Rank r01, Rank r02) {
        return Integer.valueOf(r02.getPuntuacion()).compareTo(Integer.valueOf(r01.getPuntuacion()));
    }
	
}
