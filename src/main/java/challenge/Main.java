package challenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Main {

    List<Jogador> listaJogadores = new ArrayList<>();

    // Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
    public int q1() {
        listaJogadores = CSVReader.lerCSV();

        return (int) listaJogadores.stream()
                .map(Jogador::getNationality)
                .distinct()
                .count();
    }

    // Quantos clubes (coluna `club`) diferentes existem no arquivo?
    // Obs: Existem jogadores sem clube.
    public int q2() {
        listaJogadores = CSVReader.lerCSV();

        return (int) listaJogadores.stream()
                .filter(Jogador -> !Jogador.getClub().isEmpty())
                .map(Jogador::getClub)
                .distinct()
                .count();
    }

    // Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
    public List<String> q3() {
        listaJogadores = CSVReader.lerCSV();

        return listaJogadores.stream()
                .map(Jogador::getFull_name)
                .limit(20)
                .collect(toList());
    }

    // Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
    // (utilize as colunas `full_name` e `eur_release_clause`)
    public List<String> q4() {
        listaJogadores = CSVReader.lerCSV();

        return listaJogadores.stream()
                .filter(Jogador -> !Jogador.getEur_release_clause().isEmpty())
                .sorted(Comparator.comparingDouble((Jogador j) -> Double.parseDouble(j.getEur_release_clause())).reversed())
                .map(Jogador::getFull_name)
                .limit(10)
                .collect(toList());
    }

    // Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    public List<String> q5() {
        listaJogadores = CSVReader.lerCSV();

        return listaJogadores.stream()
                .sorted(Comparator.comparing(Jogador::getBirth_date)
                        .thenComparing(Jogador::getEur_wage))
                .map(Jogador::getFull_name)
                .limit(10)
                .collect(toList());

    }

    // Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    public Map<Integer, Integer> q6() {

        listaJogadores = CSVReader.lerCSV();

        Map<Integer, Long> mapaIdade = listaJogadores.stream()
                .collect(groupingBy((Jogador j) -> Integer.parseInt(j.getAge()),
                        Collectors.counting()));

        Map<Integer, Integer> result = mapaIdade
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, p -> p.getValue().intValue()));
        System.out.println(result);
        return result;
    }
}
