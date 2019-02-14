package br.com.movile.motoboy.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.motoboy.repository.MotoBoyRepository;

@RestController
@RequestMapping("motoboy")
public class MotoBoyEndPoint {
	
	private final MotoBoyRepository motoBoyDAO;
	
	public MotoBoyEndPoint(MotoBoyRepository motoBoyDAO) {
		this.motoBoyDAO = motoBoyDAO;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){		
		return new ResponseEntity<>(motoBoyDAO.findAll(), HttpStatus.OK);
	}
	
}
