package com.sia.interfaces;

import com.sia.core.Spitter;

public interface SpitterRepository {

	Spitter save(Spitter spitter);
	
	Spitter findByUsername(String username);
}
