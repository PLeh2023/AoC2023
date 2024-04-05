import java.io.*;

public class Day2 {
    public static void main(String[] args){

        //1. Problem:   Finding all games in which the maximum amount of cubes for any colour is not exceeded.
        //              Then adding all game IDs like "Game 5" would be 5 together
        //2. Problem:   Finding in each game the maximum amount of cubes for any colour and
        //              then multiplying them together. The game number is not important now.

        int sum = input(1);
        int pow = input(2);
        System.out.println("First Problem: " + sum);
        System.out.println("Second Problem: " + pow);
    }
    public static int input(int choice){
        int sum = 0;
        try {
            //selecting of the file
            File file = new File("Inputs/Day2_Input");
            //reading of the file
            FileReader fileReader = new FileReader(file);
            //buffering the content of the file for line reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            //differentiate between both problems
            if(choice == 1) {
                while ((line = bufferedReader.readLine()) != null) {
                    line = normalizeInput(line);
                    sum += possibleGames(line);
                }
            }else{
                while ((line = bufferedReader.readLine()) != null) {
                    line = normalizeInput(line);
                    sum += powerOfGame(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }
    public static String normalizeInput(String line){
        //From: Game 13: 2 blue, 5 green; 2 blue, 2 green; 2 blue, 2 red, 4 green
        //To:   13;2b;5g;2b;2g;2b;2r;4g
        return  line.replace("Game ","").replace(":",";")
                    .replace("red","r").replace("blue","b")
                    .replace("green","g").replace(" ","")
                    .replace(",",";");
    }
    public static int possibleGames(String line){
        int game;
        int cubeCount;
        boolean red = true,green = true,blue = true;
        String[] cubes;
        //splitting each line into their cube count
        cubes = line.split(";");
        //getting the game ID for the sum of the problem
        game = Integer.parseInt(cubes[0]);
        //filter for the colour in each part of the line
        for(String i: cubes){
            if(i.contains("r")){
                cubeCount = getNumberFromString(i);
                //deciding if the game is with the amount of cubes possible
                if(cubeCount > 12){
                    red = false;
                }
            }else if(i.contains("g")){
                cubeCount = getNumberFromString(i);
                if(cubeCount > 13){
                    green = false;
                }
            }else if(i.contains("b")){
                cubeCount = getNumberFromString(i);
                if(cubeCount > 14){
                    blue = false;
                }
            }
        }
        //return the game ID if the game is successful else just return 0
        if(red && green && blue){
            return game;
        }else{
            return 0;
        }
    }
    public static int getNumberFromString(String i){
        int number;
        //getting the length of the Strings, so that the number can be extracted
        if(i.length() == 2){
            number = Integer.parseInt("" + i.charAt(0));
        }else{
            number = Integer.parseInt("" + i.charAt(0) + i.charAt(1));
        }
        return number;
    }
    public static int powerOfGame(String line){
        int cubeCount;
        int red = 0, green = 0, blue = 0;
        String[] cubes;
        //splitting each line into their cube count
        cubes = line.split(";");
        //filter for the colour in each part of the line
        for(String i: cubes){
            if(i.contains("r")){
                cubeCount = getNumberFromString(i);
                //finding the biggest amount of cubes of each colour
                if(cubeCount > red){
                    red = cubeCount;
                }
            }else if(i.contains("g")){
                cubeCount = getNumberFromString(i);
                if(cubeCount > green){
                    green = cubeCount;
                }
            }else if(i.contains("b")){
                cubeCount = getNumberFromString(i);
                if(cubeCount > blue){
                    blue = cubeCount;
                }
            }
        }
        //multiplying all colours together
        return red * green * blue;
    }
}
