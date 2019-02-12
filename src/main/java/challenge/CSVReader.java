package challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Jogador> lerCSV() {

        String csvFile = "/home/beblue/codenation/java-3/src/main/resources/data.csv";
        String line = "";
        String cvsSplitBy = ",";


        List<Jogador> jogadoresList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            br.readLine(); //ler a primeira linha
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] jogador = line.split(cvsSplitBy);

                if (jogador.length > 0) {
                    Jogador jog = new Jogador(jogador[0],
                            jogador[1],
                            jogador[2],
                            jogador[3],
                            jogador[6],
                            jogador[8],
                            jogador[14],
                            jogador[17],
                            jogador[18]);
                    jogadoresList.add(jog);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jogadoresList;
    }
}
