package com.desafio.meli.simio.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.meli.simio.exception.DnaNaoEhSimioException;
import com.desafio.meli.simio.request.DnaVO;
import com.desafio.meli.simio.response.Response;
import com.desafio.meli.simio.response.vo.EstatisticaDNAVO;
import com.desafio.meli.simio.service.DNAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Recursos relacionados às sequências de DNAs")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
public class DNAController {

	private DNAService dnaService;
	
	public DNAController(DNAService dnaService) {
		this.dnaService = dnaService;
	}
	
	@PostMapping("/simian")
	@ApiOperation(value = "Verifica se uma sequencia de DNA é de um Símio ou não", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Response.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class)})
	public ResponseEntity<Response<Boolean>> simian(
			@ApiParam(value= "dna", example = "{\"dna\": [\"ATGC\", \"GAAT\", \"ATAC\", \"GCAA\"]}")
			@RequestBody DnaVO dna){
		
		Boolean ehSimio = dnaService.isSimian(dna.getDna());
		
		if (ehSimio) {
			return ResponseEntity.ok(Response.get(ehSimio));
		} else {
			throw new DnaNaoEhSimioException();
		}
	}
	
	@GetMapping("/stats")
	@ApiOperation(value = "Consulta o total das sequências de DNA de Símios e de Humanos cadastradas e"
			+ " a proporção de Símios em relalção aos Humanos", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Response.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class)})
	public ResponseEntity<EstatisticaDNAVO> stats(){
		return ResponseEntity.ok(dnaService.stats());
	}
}
