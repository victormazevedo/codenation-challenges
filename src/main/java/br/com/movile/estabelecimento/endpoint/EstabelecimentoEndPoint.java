package br.com.movile.estabelecimento.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.estabelecimento.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoEndPoint {

	private final EstabelecimentoRepository estabelecimentoDAO;
	
	public EstabelecimentoEndPoint(EstabelecimentoRepository estabelecimentoDAO) {
		this.estabelecimentoDAO = estabelecimentoDAO;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(estabelecimentoDAO.findAll(), HttpStatus.OK);
	}
}
