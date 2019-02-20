package br.com.movile;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.motoboy.repository.MotoboyRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carga")
public class CargaGeral {

    @Autowired
    private MotoboyRepository motoDAO;
    @Autowired
    private CustomerRepository clienteDAO;
    @Autowired
    private RestaurantRepository estabelecimentoDAO;
    @Autowired
    private ItemRepository produtoDAO;


    @GetMapping
    public String carga() {
        Carga carga = new Carga();
        carga.cargaGeral();

        carga.getMotoboy().stream().forEach(x -> motoDAO.save(x));
        carga.getClientes().stream().forEach(x -> clienteDAO.save(x));
        carga.getEstabelecimentos().stream().forEach(x -> estabelecimentoDAO.save(x));
        carga.getProdutos().stream().forEach(x -> produtoDAO.save(x));

        return "Carga Completa !";
    }

}
