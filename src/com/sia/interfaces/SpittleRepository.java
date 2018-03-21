package com.sia.interfaces;

import java.util.List;

import com.sia.core.Spittle;

public interface SpittleRepository {

	List<Spittle> findSpittles(long max, int count);

	Spittle findOne(long id);

	void save(Spittle spittle);
	
	List<Spittle> findRecentSpittles();
}
