package br.com.movile.produto.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.produto.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoEndPoint {

	private final ProdutoRepository produtoDAO;
	
	public ProdutoEndPoint(ProdutoRepository produtoDAO) {
		this.produtoDAO = produtoDAO;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(produtoDAO.findAll(), HttpStatus.OK); 
	}

}
