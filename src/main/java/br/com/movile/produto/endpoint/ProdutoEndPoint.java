package br.com.movile.produto.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.estabelecimento.repository.EstabelecimentoRepository;
import br.com.movile.produto.model.Produto;

@RestController
@RequestMapping("produtos")
public class ProdutoEndPoint {

	private final EstabelecimentoRepository estabelecimentoDAO;

	public ProdutoEndPoint(EstabelecimentoRepository estabelecimentoDAO) {
		this.estabelecimentoDAO = estabelecimentoDAO;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Produto> produtos = new ArrayList<>();
		List<List<Produto>> collect = estabelecimentoDAO.findAll()
		.stream()
		.map(x -> x.getProdutos()).collect(Collectors.toList());
		System.out.println(collect.size());
		
		List<List<Produto>> collect2 = collect.stream().filter(x -> x.size() > 0).collect(Collectors.toList());
		
		System.out.println("\n\n\n" + collect2.size());
		
		collect2.stream().forEach(x -> x.stream().forEach(j -> produtos.add(j)));
		
		System.out.println("\n\n" + produtos.size());
		
//		.filter(x -> x.size() > 0)
//				.collect(Collectors.toList())
//				.forEach(x -> produtos.addAll(x));
		System.out.println(produtos.size());
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

}
