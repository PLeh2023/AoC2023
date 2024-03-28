import java.io.*;

public class Day1 {
    public static void main(String[] args){

        //1. Problem: Adding up all numbers from a big file, the first and last number in a row are one combined number
        //2. Problem: Adding up all numbers and word written numbers from a big file,
        //            the first and last number in a row are one combined number
        //The methode "wordToNumber" is only needed for the 2nd problem in which the words need to be numbers

        //valid numbers are 1 and 2 which are representing the problems
        int finalNumberOne = input(1);
        int finalNumberTwo = input(2);
        System.out.println("First Problem: " + finalNumberOne);
        System.out.println("Second Problem: " + finalNumberTwo);
    }
    public static int input(int choice){
        int finalNumber = 0;
        //try-catch: It could be possible that the mentioned File doesn´t exist and that would break the programm
        try {
            //selecting of the file
            File file = new File("Inputs/Day1_Input");
            //reading of the file
            FileReader fileReader = new FileReader(file);
            //buffering the content of the file for line reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            if (choice == 1){
                while((line = bufferedReader.readLine()) != null ){
                    finalNumber += findNumbers(line);
                }
            }else{
                while((line = bufferedReader.readLine()) != null ){
                    line = wordToNumber(line);
                    finalNumber += findNumbers(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalNumber;
    }
    public static int findNumbers(String line){
        int firstNumber = 0;
        int lastNumber = 0;
        int finalNumber;
        //finding the numbers of each line and overwriting everytime one appears
        for(int i = 0; i < line.length();i++){
            String character = ""+line.charAt(i);
            String numbers = "123456789";

            if (numbers.contains(character)){
                if (firstNumber == 0){
                    firstNumber = Integer.parseInt(character);
                }
                lastNumber = Integer.parseInt(character);
            }
        }
        String tmp = "";
        tmp += firstNumber;
        tmp += lastNumber;
        finalNumber = Integer.parseInt(tmp);
        return finalNumber;
    }
    public static String wordToNumber(String line){
        // replacing the character sequences with another one because there are combinations like threeight
        // which would be 3 and 8 but with a full word replacement from three to 3 then the eight wouldn´t
        // be recognized anymore
        return  line.replace("one","on1e").replace("two","tw2o")
                    .replace("three","thr3ee").replace("four","fo4ur")
                    .replace("five","fi5ve").replace("six","si6x")
                    .replace("seven","sev7en").replace("eight","eig8ht")
                    .replace("nine","ni9ne");
    }
}
