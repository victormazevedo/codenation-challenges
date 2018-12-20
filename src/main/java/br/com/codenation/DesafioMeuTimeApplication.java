package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.domain.Jogador;
import br.com.codenation.domain.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private Map<Long, Time> times = new HashMap<>();
    private Map<Long, Jogador> jogadores = new HashMap<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		try {
            if(times.containsKey(id))
                throw new IdentificadorUtilizadoException();
			times.put(id, new Time(
					id,
					nome,
					dataCriacao,
					corUniformePrincipal,
					corUniformeSecundario));
        }
		catch (Exception e){
	        System.out.println("Id do Time já existente!");
        }

	    System.out.println(Collections.singletonList(times));//testando
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		try {
			if(jogadores.containsKey(id))
				throw new IdentificadorUtilizadoException();
			Time time = buscarTime(idTime);//buscando o time para verificar se o mesmo existe

			Jogador jogador = new Jogador( //carregando o objeto jogador com as variáveis inseridas
					id,
					idTime,
					nome,
					dataNascimento,
					nivelHabilidade,
					salario);

			jogadores.put(id,jogador);//inserindo na hashmap
			time.getJogadores().add(jogador);//inserindo o jogador dentro da classe jogador

		}
		catch (IdentificadorUtilizadoException ie){
			System.out.println("Id do jogador já existente!");
		}
		catch (TimeNaoEncontradoException te){
			System.out.println("Id do time não encontrado!");
		}

		System.out.println(Collections.singletonList(times));
		System.out.println(Collections.singletonList(jogadores));//testando
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		try {
			Jogador jogador = buscarJogador(idJogador);

			buscarTime(jogador.getIdTime()).setIdJogadorCapitao(idJogador);
		}
		catch (JogadorNaoEncontradoException je) {
			System.out.println("Id do jogador não encontrado!");
		}

		System.out.println(Collections.singletonList(times));
		System.out.println(Collections.singletonList(jogadores));//testando
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		Long idCapitao = 0L;
		try {
			Time time = buscarTime(idTime);

			if(time.getIdJogadorCapitao() == null)
				throw new CapitaoNaoInformadoException();

			idCapitao = time.getIdJogadorCapitao();
		}
		catch (TimeNaoEncontradoException te){
			System.out.println("Id do time não encontrado!");
		}
		catch (CapitaoNaoInformadoException ce) {
			System.out.println("O time informado não possui capitão");
		}
		return idCapitao;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {

		String nomeJogador = "";
		try {
			nomeJogador = buscarJogador(idJogador).getNome();
		}
		catch (JogadorNaoEncontradoException je){
			System.out.println("Jogador não encontrado");
		}
		return nomeJogador;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {

		String nomeTime = "";
		try {
			nomeTime = buscarTime(idTime).getNome();
		}
		catch (JogadorNaoEncontradoException je){
			System.out.println("Jogador não encontrado");
		}
		return nomeTime;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		List<Long> listId = new ArrayList<>();
		try {
			Time time = buscarTime(idTime);

			listId = time.getJogadores()
					.stream()
					.map(Jogador::getId)
					.collect(Collectors.toList());
		}
		catch (TimeNaoEncontradoException te) {
			System.out.println("O time informado não possui capitão");
		}

		return listId;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

	private Time buscarTime(Long idTime){
		if(!times.containsKey(idTime))
			throw new TimeNaoEncontradoException("Time não encontrado!");

		return times.get(idTime);
	}

	private Jogador buscarJogador(Long idJogador){
		if(!jogadores.containsKey(idJogador))
			throw new JogadorNaoEncontradoException("Jogador não encontrado!");

		return jogadores.get(idJogador);
	}

}
