package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.domain.Jogador;
import br.com.codenation.domain.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private Map<Long, Time> times = new HashMap<>();
    private Map<Long, Jogador> jogadores = new HashMap<>();


    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (times.containsKey(id))
            throw new IdentificadorUtilizadoException();
        times.put(id, new Time(
                id,
                nome,
                dataCriacao,
                corUniformePrincipal,
                corUniformeSecundario));

    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        if (jogadores.containsKey(id))
            throw new IdentificadorUtilizadoException();
        Time time = buscarTime(idTime);//buscando o time para verificar se o mesmo existe

        Jogador jogador = new Jogador( //carregando o objeto jogador com as variáveis inseridas
                id,
                idTime,
                nome,
                dataNascimento,
                nivelHabilidade,
                salario);

        jogadores.put(id, jogador);//inserindo na hashmap
        time.getJogadores().add(jogador);//inserindo o jogador dentro da classe jogador

    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {

        Jogador jogador = buscarJogador(idJogador);
        buscarTime(jogador.getIdTime()).setIdJogadorCapitao(idJogador);
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {

        if (buscarTime(idTime).getIdJogadorCapitao() == null)
            throw new CapitaoNaoInformadoException();

        return buscarTime(idTime).getIdJogadorCapitao();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        return buscarJogador(idJogador).getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {

        return buscarTime(idTime).getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        List<Long> listId;
        Time time = buscarTime(idTime);

        listId = time.getJogadores()
                .stream()
                .map(Jogador::getId)
                .collect(Collectors.toList());
        return listId;
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {

        return buscarMaior(idTime, Comparator.comparing(Jogador::getNivelHabilidade));
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {

        Jogador jogadorMaisVelho = null;

        List<Long> listaIdsJogadoresDoTime = buscarJogadoresDoTime(idTime);

        for (Long id : listaIdsJogadoresDoTime) {
            Jogador jogador = buscarJogador(id);

            if (jogadorMaisVelho == null)
                jogadorMaisVelho = jogador;
            else {
                if (jogadorMaisVelho.getDataNascimento().isAfter(jogador.getDataNascimento()))
                    jogadorMaisVelho = jogador;
            }
        }
        System.out.println(jogadorMaisVelho.getId());
        return jogadorMaisVelho.getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        if (times.isEmpty())
            return new ArrayList<Long>();

        return new ArrayList<Long>(times.keySet());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        return buscarMaior(idTime, Comparator.comparing(Jogador::getSalario));
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return buscarJogador(idJogador).getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        if (jogadores.isEmpty())
            return new ArrayList<>();

        List<Jogador> sortedJogadores = new ArrayList<>(jogadores.values());
        sortedJogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
                .thenComparing(Jogador::getId));

        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < top; i++) {
            ids.add(sortedJogadores.get(i).getId());
        }

        return ids;
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timeCasa = buscarTime(timeDaCasa);
        Time timeFora = buscarTime(timeDeFora);

        if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
            return timeFora.getCorUniformeSecundario();
        else
            return timeFora.getCorUniformePrincipal();
    }

    private Time buscarTime(Long idTime) {
        if (!times.containsKey(idTime))
            throw new TimeNaoEncontradoException("Time não encontrado!");

        return times.get(idTime);
    }

    private Jogador buscarJogador(Long idJogador) {
        if (!jogadores.containsKey(idJogador))
            throw new JogadorNaoEncontradoException("Jogador não encontrado!");

        return jogadores.get(idJogador);
    }

    private Long buscarMaior(Long idTime, Comparator<Jogador> comparing) {
        List<Long> listaIdsJogadoresDoTime = buscarJogadoresDoTime(idTime); //definindo a lista de jogadores do time

        Jogador maior = new Jogador();
        List<Jogador> listaJogadores = new ArrayList<>();
        try {
            for (Long id : listaIdsJogadoresDoTime) {//para cada id na lista de jogadores
                Jogador jogador = buscarJogador(id);//buscar o id do jogador relacionado
                listaJogadores.add(jogador);//adicionar o jogador encontrado na lista
            }
            maior = listaJogadores.stream().max(comparing).get();
        } catch (NoSuchElementException nse) {
            System.out.println("Time não encontado!");
        }
        return maior.getId();
    }

}
