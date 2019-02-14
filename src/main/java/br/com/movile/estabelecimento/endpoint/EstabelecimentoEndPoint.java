package br.com.movile.estabelecimento.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.estabelecimento.model.Estabelecimento;
import br.com.movile.estabelecimento.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoEndPoint {

	private final EstabelecimentoRepository estabelecimentoDAO;

	public EstabelecimentoEndPoint(EstabelecimentoRepository estabelecimentoDAO) {
		this.estabelecimentoDAO = estabelecimentoDAO;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Estabelecimento> findAll = estabelecimentoDAO.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}/produtos")
	public ResponseEntity<?> findProdutosByIdEstabelecimento(@PathVariable("id") String id) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoDAO.findById(id);
		if (!estabelecimento.isPresent()) {
			return new ResponseEntity<>("Não foi encontrado nenhum restaurante !", HttpStatus.OK);
		} else if (estabelecimento.get().getProdutos().isEmpty()) {
			return new ResponseEntity<>("Nenhum item foi encontrado !", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(estabelecimento.get().getProdutos(), HttpStatus.OK);
		}

	}
}
