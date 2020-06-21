package com.desafio.meli.simio.service;

import com.desafio.meli.simio.response.vo.EstatisticaDNAVO;

public interface DNAService {
	
	boolean isSimian(String[] dna);
	
	EstatisticaDNAVO stats();

}
