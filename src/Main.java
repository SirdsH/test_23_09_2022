import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("world.csv"));
            String line;
            line = br.readLine();
            List<Worlds> listOfWorlds = new ArrayList<Worlds>();
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.replace("NA", "-1").split(";");
                listOfWorlds.add(new Worlds(splitLine[0].replace("\"", ""), splitLine[1], Long.parseLong(splitLine[3].replace("\"", "")), Long.parseLong(splitLine[4].replace("\"", ""))));
            }



            List overTwo = listOfWorlds.stream().filter(i -> i.getPopulation() > 200000000).map(i -> i.getName() + "-" + i.getGdp() / i.getPopulation()).collect(Collectors.toList());

            long russia = listOfWorlds.stream().filter(i -> i.getName().equals("Russia")).findFirst().get().getPopulation();
            List biggerThanRussia = listOfWorlds.stream().filter(i -> i.getPopulation() > russia).map(i -> i.getName()).collect(Collectors.toList());
            
            System.out.println(biggerThanRussia);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}