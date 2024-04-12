import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day6 {
    public static void main(String[] args) throws IOException {

        //1. & 2. Problem:  add up all possible wins (reach des distance before the time runs out) and get the power
        //                  between multiple games
        //                  The only difference is the size of the numbers, so we need int and long
        //valid numbers are 1 and 2 which are representing the problems
        //choice one would mean many games and choice two would mean one game
        int pow = powerOfPossibilities(input(1));
        int possibilities = powerOfPossibilities(input(2));
        System.out.println("First Problem: " + pow);
        System.out.println("Second Problem: " + possibilities);
    }
    public static String[] input(int choice) throws IOException {
        //find the file
        File file = new File("Inputs/Day6_Input");
        //read the file
        FileReader fileReader = new FileReader(file);
        //split the file to read every line separate
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] inputSorted = new String[2];
        int counter = 0;
        String line;
        while((line = bufferedReader.readLine()) != null){
            line = normalize(line, choice);
            inputSorted[counter] = line;
            counter++;
        }
        return inputSorted;
    }
    public static String normalize(String line, int choice){
        //differentiate between both problems
        if(choice == 1) {
            //From: Time:        35     93     73     66
            //      Distance:   212   2060   1201   1044
            //To:   35;93;73;66
            //      212;2060;1201;1044
            return line.replace("Time:", "").replace("Distance:", "")
                    .replaceAll("\\s+", " ").replace(" ", ";")
                    .replaceFirst(";", "");
        }else{
            //From: Time:        35     93     73     66
            //      Distance:   212   2060   1201   1044
            //To:   35937366
            //      212206012011044
            return line.replace("Time:", "").replace("Distance:", "")
                    .replaceAll("\\s+", "");
        }
    }
    public static int powerOfPossibilities(String[] inputSorted){
        //splitting the input to useful numbers
        String[] timeTMP = inputSorted[0].split(";");
        String[] distanceTMP = inputSorted[1].split(";");
        //get the numbers as numbers rdy
        long[] time = fillArray(timeTMP);
        long[] distance = fillArray(distanceTMP);
        int[] allChances = new int[time.length];
        int pow = 1;
        //the winning chance is calculated for each game from zero sec to available time sec
        for (int k = 0; k < time.length; k++) {
            int chances = 0;
            for (int i = 0; i < time[k]; i++){
                long mm = i * (time[k] - i);
                if ((Long) mm > (Long) distance[k]){
                    chances++;
                }
            }
            allChances[k] = chances;
        }
        //getting the Power of all games by multiplying the chances of winning
        for (int j : allChances) {
            pow = pow * j;
        }
        return pow;
    }
    public static long[] fillArray(String[] stringArray){
        //convert a String array to an Integer array
        long[] intArray = new long[stringArray.length];
        for(int i = 0; i < stringArray.length; i++){
            intArray[i] = Long.parseLong(stringArray[i]);
        }
        return intArray;
    }
}