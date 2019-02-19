package br.com.movile.cliente.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.cliente.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteEndPoint {

	private final ClienteRepository clienteDAO;
	
	public ClienteEndPoint(ClienteRepository clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(clienteDAO.findAll(), HttpStatus.OK);
	}
}
