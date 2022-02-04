package skirental;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SkiRental {
    private Map<String, Equipment> rentals = new TreeMap<>();

    public Map<String, Equipment> getRentals() {
        return rentals;
    }

    public void loadFromFile(Path path){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                    parseLine(line);
            }
        }catch(IOException ioe){
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public List<String> listChildren(){
        return rentals.entrySet().stream()
                .filter(e -> e.getValue().getSizeOfBoot() <= 37 && e.getValue().getSizeOfSkis() <= 120)
                .map(e -> e.getKey())
                .toList();

    }

    public String getNameOfPeopleWithBiggestFoot(){
        return rentals.entrySet().stream()
                .filter(e -> e.getValue().getSizeOfSkis() > 0 && e.getValue().getSizeOfBoot() > 0)
                .max(Comparator.comparing(e -> e.getValue().getSizeOfBoot()))
                .map(e -> e.getKey())
                .orElse("");
    }

    private void parseLine(String line) {
        String[] temp = line.split(";");
        String[] sizes = temp[1].split(" ");

        rentals.put(temp[0], new Equipment(Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1]))) ;
    }
}
