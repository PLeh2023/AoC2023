import java.io.*;
import java.util.Arrays;

public class Day4 {
    public static void main(String[] args) throws IOException {

        //1. Problem: Adding up all Points per card together. Starting with 1,2,3,8,...
        //2. Problem: Adding up the amount of card we own per card.
        //            Every win adds another card to a further playable card.
        //The exceptions are not handled in this code
        //valid numbers are 1 and 2 which are representing the problems
        int sumOfPoints = input(1);
        int sumOfCards = input(2);
        System.out.println(sumOfPoints);
        System.out.println(sumOfCards);
    }
    public static int input(int choice) throws IOException {
        int sum = 0;
        //selecting of the file
        File file = new File("Inputs/Day4_Input");
        //reading of the file
        FileReader fileReader = new FileReader(file);
        //buffering the content of the file for line reading
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        if(choice == 1) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = normalizeString(line);
                sum += sumOfPoints(line);
            }
        }else{
            int numberOfLines = linesCounter(file);
            sum = sumOfCards(bufferedReader, numberOfLines);
        }
        fileReader.close();
        bufferedReader.close();
        return sum;
    }
    public static String normalizeString(String line){
        //From: Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        //To: 1;41,48,83,86,17;83,86,6,31,17,9,48,53
        return line .replace("Card","").replaceAll("\\s+"," ")
                    .replaceFirst(" ", "").replace(" | ",";")
                    .replace(": ",";").replace(" ",",");
    }
    public static int sumOfPoints(String line){
        int points = 0;
        //the normalized string gets split in each section, the first one isn´t usefull
        String[] split = line.split(";");
        String[] winningNumbers = split[1].split(",");
        String[] numbers = split[2].split(",");
        //finding the matches and getting the right amount of points for that
        for(int i = 0; i < winningNumbers.length; i++){
            for (int j = 0; j < numbers.length; j++) {
                if (winningNumbers[i].equals(numbers[j])) {
                    if (points == 0) {
                        points = 1;
                    } else {
                        points *= 2;
                    }
                }
            }
        }
        return points;
    }
    public static int linesCounter(File file) throws IOException {
        //its purpose is to find the max lines in a txt file
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        lineNumberReader.skip(Long.MAX_VALUE);
        int numberOfLines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        return numberOfLines;
    }
    public static int sumOfCards(BufferedReader bufferedReader, int numberOfLines) throws IOException {
        int sumOfCards = 0;
        int wins = 0;
        //creating an array to know the amount of cards we own for each card and filling it up with ones
        int[] cardCount = new int[numberOfLines];
        Arrays.fill(cardCount,1);
        int counter = 0;
        String line;
        //reading out the txt to get each line
        while ((line = bufferedReader.readLine()) != null) {
            line = normalizeString(line);
            //the normalized string gets split in each section, the first one isn´t usefull
            String[] split = line.split(";");
            String[] winningNumbers = split[1].split(",");
            String[] numbers = split[2].split(",");
            //finding only the matches
            for (int i = 0; i < winningNumbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    if (winningNumbers[i].equals(numbers[j])) {
                        wins += 1;
                    }
                }
            }
            //filling the cards in which we won in cards before
            for (int z = 0; z < cardCount[counter]; z++) {
                for (int i = 0; i < wins; i++) {
                    cardCount[counter + 1] += 1;
                    counter++;
                }
                //reset
                counter -= wins;
            }
            counter++;
            //reset
            wins = 0;
        }
        //sum up all cards
        for(int y = 0; y < cardCount.length; y++){
            sumOfCards += cardCount[y];
        }
        return sumOfCards;
    }
}
