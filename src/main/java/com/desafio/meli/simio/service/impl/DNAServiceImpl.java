package com.desafio.meli.simio.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.meli.simio.entity.DNA;
import com.desafio.meli.simio.enums.TipoDNAEnum;
import com.desafio.meli.simio.exception.DnaJaCadastradoException;
import com.desafio.meli.simio.exception.NaoHaDnaCadastradoException;
import com.desafio.meli.simio.repository.DNARepository;
import com.desafio.meli.simio.response.vo.EstatisticaDNAVO;
import com.desafio.meli.simio.service.DNAService;
import com.desafio.meli.simio.util.Encriptador;

@Service
public class DNAServiceImpl implements DNAService {
	
	private DNARepository dnaRepository;
	
	public DNAServiceImpl(DNARepository checaDnaRepository) {
		this.dnaRepository = checaDnaRepository;
	}

	@Override
	@Transactional
	public boolean isSimian(String[] dna) {
		
		DNA dnaTratado = DNA.Factory.criar(true, 4, dna); 
		
		if (contemSequenciaSimio(dnaTratado)) {
			dnaTratado.setTipoDNA(TipoDNAEnum.SIMIO);
		} else {
			dnaTratado.setTipoDNA(TipoDNAEnum.HUMANO);
		} 
		
		salvar(dnaTratado);
		
		if (dnaTratado.getTipoDNA().equals(TipoDNAEnum.SIMIO)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public EstatisticaDNAVO stats() {
		
		List<DNA> listaDeDNAs = dnaRepository.findAll();
		
		if (listaDeDNAs.size() == 0) {
			throw new NaoHaDnaCadastradoException();
		}
		
		EstatisticaDNAVO stats = new EstatisticaDNAVO();
		
		long quantTotalSimio = listaDeDNAs.stream().filter(
				(dna) -> dna.getTipoDNA().equals(TipoDNAEnum.SIMIO)).count();
		
		long quantTotalHumano = listaDeDNAs.size() - quantTotalSimio;
		float proporcaoSimio = 0.0f;
		
		if (quantTotalHumano == 0) {
			proporcaoSimio = 0.0f;
		} else {
			proporcaoSimio = (float) quantTotalSimio / quantTotalHumano;
		}
		
		stats.setQuantidadeTotalDeSimio(quantTotalSimio);
		stats.setQuantidadeTotalDeHumano(quantTotalHumano);
		stats.setProporcaoSimioParaHumano(proporcaoSimio);
		
		return stats;
	}
	
	private DNA salvar(DNA dna) {
		dna.setDna(encriptaDNA(dna.getDna()));
		checaSeDNAEstaCadastrado(dna);
		
		return dnaRepository.save(dna);
	}
	
	
	private void checaSeDNAEstaCadastrado(DNA dna) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withIgnoreCase()
				.withMatcher("dna",GenericPropertyMatcher.of(StringMatcher.EXACT));
		
		dnaRepository.findOne(Example.of(dna, matcher)).ifPresent(
			(d) -> {throw new DnaJaCadastradoException();});
	}
	
	
	private String encriptaDNA(String dna) {
		return Encriptador.encriptaSHA256(dna);
	}
	
	private boolean contemSequenciaSimio(DNA dna) {
		return contemSequenciaSimioNaDiagonalCrescente(dna);
	}
	
	private boolean contemSequenciaSimioNaDiagonalCrescente(DNA dna){
		String valores = dna.getDna();

		int matriz = dna.getMatriz();
		
		int posDois, posTres, posQuatro, 
		linha = 0, posAtual = 3, tamanhoDaSequencia = 4;
		
		boolean condicao = posAtual < matriz;
		
		while(condicao){
			
			posDois = (matriz * 1) + posAtual - 1;
			posTres = (matriz * 2) + posAtual - 2;
			posQuatro = (matriz * 3) + posAtual - 3;
			
			if (valores.charAt(posAtual) == valores.charAt(posDois) && 
					valores.charAt(posTres) == valores.charAt(posQuatro)) {
				return true;
			}
			
			linha = posAtual / matriz;
			if (posAtual + 1 == matriz * (linha + 1)){
				posAtual = matriz * (linha + 1) + (tamanhoDaSequencia -1);
				
				condicao = !(linha + tamanhoDaSequencia + 1 > matriz);
			} else {
				posAtual++;
			}
		}
		
		return contemSequenciaSimioNaDiagonalDecrescente(dna);
	}
	
	private boolean contemSequenciaSimioNaDiagonalDecrescente(DNA dna){
		String valores = dna.getDna();

		int matriz = dna.getMatriz();
		
		int posDois, posTres, posQuatro, 
		linha = 0, posAtual = 0, tamanhoDaSequencia = 4;
		
		boolean condicao = posAtual + 3 < matriz;
		
		while(condicao){
			
			posDois = (matriz * 1) + posAtual + 1;
			posTres = (matriz * 2) + posAtual + 2;
			posQuatro = (matriz * 3) + posAtual + 3;
			
			if (valores.charAt(posAtual) == valores.charAt(posDois) && 
					valores.charAt(posTres) == valores.charAt(posQuatro)) {
				return true;
			}
			
			linha = posAtual / matriz;
			if (posAtual + tamanhoDaSequencia == matriz * (linha + 1)){
				posAtual = matriz * (linha + 1);
				condicao = !(linha + tamanhoDaSequencia + 1 > matriz);
			} else {
				posAtual++;
			}
		}
		
		return contemSequenciaSimioNaHorizontal(dna);
	}
	
	private boolean contemSequenciaSimioNaHorizontal(DNA dna){
		String valores = dna.getDna();

		int matriz = dna.getMatriz();
		
		int posDois, posTres, posQuatro, 
		linha = 0, posAtual = 0, tamanhoDaSequencia = 4;
		
		boolean condicao = posAtual + 3 < matriz;
		
		while(condicao){
			
			posDois = posAtual + 1;
			posTres = posAtual + 2;
			posQuatro = posAtual + 3;
			
			if (valores.charAt(posAtual) == valores.charAt(posDois) && 
					valores.charAt(posTres) == valores.charAt(posQuatro)) {
				return true;
			}
			
			linha = posAtual / matriz;
			if (posAtual + tamanhoDaSequencia == matriz * (linha + 1)){
				posAtual = matriz * (linha + 1);
				condicao = !(linha + 1 == matriz);
			} else {
				posAtual++;
			}
		}
		
		return contemSequenciaSimioNaVertical(dna);
	}
	
	private boolean contemSequenciaSimioNaVertical(DNA dna){
		String valores = dna.getDna();

		int matriz = dna.getMatriz();
		
		int posDois, posTres, posQuatro, 
		linha = 0, posAtual = 0, tamanhoDaSequencia = 4;
		
		boolean condicao = posAtual + 3 < matriz;
		
		while(condicao){
			
			posDois = (matriz * 1) + posAtual;
			posTres = (matriz * 2) + posAtual;
			posQuatro = (matriz * 3) + posAtual;
			
			if (valores.charAt(posAtual) == valores.charAt(posDois) && 
					valores.charAt(posTres) == valores.charAt(posQuatro)) {
				return true;
			}
			
			linha = posAtual / matriz;
			if (posAtual + 1 == matriz * (linha + 1)){
				posAtual = matriz * (linha + 1);
				condicao = !(linha + tamanhoDaSequencia + 1 > matriz);
			} else {
				posAtual++;
			}
		}
		
		return false;
	}
	
}
