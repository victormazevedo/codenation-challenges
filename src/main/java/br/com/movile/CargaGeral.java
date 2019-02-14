package br.com.movile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.cliente.repository.ClienteRepository;
import br.com.movile.estabelecimento.repository.EstabelecimentoRepository;
import br.com.movile.motoboy.repository.MotoBoyRepository;

@RestController
@RequestMapping("carga")
public class CargaGeral {

	private final MotoBoyRepository motoDAO;
	private final ClienteRepository clienteDAO;
	private final EstabelecimentoRepository estabelecimentoDAO;

	public CargaGeral(MotoBoyRepository motoDAO, ClienteRepository clienteDAO,
			EstabelecimentoRepository estabelecimentoDAO) {
		this.motoDAO = motoDAO;
		this.clienteDAO = clienteDAO;
		this.estabelecimentoDAO = estabelecimentoDAO;
	}
	
	@GetMapping
	public String carga() {
		Carga carga = new Carga();
		carga.cargaGeral();
		
		carga.getMotoboy().stream().forEach(x -> motoDAO.save(x));
		carga.getClientes().stream().forEach(x -> clienteDAO.save(x));
		carga.getEstabelecimentos().stream().forEach(x -> estabelecimentoDAO.save(x));
		
		return "Carga Completa !";
	}

}
