import java.io.*;
import java.util.ArrayList;

public class Day8 {
    public static void main(String[] args) throws IOException {
        //1.Problem:    Getting the amount of navigation steps within a map starting from AAA and going to ZZZ
        //              with left and right instructions
        //2.Problem:    This time we need to find the steps again but from starting point that end with A like BZA
        //              and find the way to points that end with Z like FGZ to finish this problem we need to find the
        //              amount of steps when all starting points end at the same time at an ending point. Since the map
        //              and direction instruction are circular, we can assume that we need to find the lcm.
        int num = getSteps(input());
        long num2 = getStepsParallel(input());
        System.out.println(num);
        System.out.println(num2);
    }
    public static String[] input() throws IOException {
        //find the file
        File file = new File("Inputs/Day8_Input");
        //read the file
        FileReader fileReader = new FileReader(file);
        //split the file to read every line separate
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> map = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null){
            if (line.isEmpty()){
                continue;
            }
            map.add(normalize(line));
        }
        int counter = 0;
        String[] map_directions = new String[map.size()];
        for (String i : map){
           map_directions[counter] = i;
           counter++;
        }
        return map_directions;
    }
    public static String normalize(String line){
        //converting the String into something useful
        line =  line.replace(" ","").replace("=",",").replace("(","")
                    .replace(")","");
        return line;
    }
    public static int getSteps(String[] map_direction){
        //for 1.Problem the counting algorithm
        char[] direction = getDirectionArray(map_direction[0]);
        String[][] map = splitMapOpen(map_direction);
        String location = "AAA";
        int directionCounter = 0;
        int steps = 0;
        //algorithm to find the steps needed
        for (int i = 0; i < map.length; i++){
            if (location.equals(map[i][0])){
                if (direction[directionCounter] == 'R'){
                    location = map[i][2];
                }else{
                    location = map[i][1];
                }
                directionCounter++;
                steps++;
                if (directionCounter == direction.length){directionCounter = 0;}
            }
            if (i == map.length - 1){i = 0;}
            if (location.equals("ZZZ")){break;}
        }

        return steps;
    }
    public static long getStepsParallel(String[] map_direction){
        char[] direction = getDirectionArray(map_direction[0]);
        String[][] map = splitMapOpen(map_direction);
        String[][] startingPoints = getAllStartingPoints(map);
        ArrayList<Integer> allPossibleMeetings = new ArrayList<>();
        for (int i = 0; i < startingPoints.length; i++){
            allPossibleMeetings.add(getSteps(map,direction,Integer.parseInt(startingPoints[i][1]),startingPoints[i][0]));
        }
        long steps = 1;
        //assuming that all numbers have one common big prime number
        int bigPrime = bigPrimeNumber(allPossibleMeetings.getFirst());
        //getting all small prime numbers from the steps for each starting point to end point
        for (int j = 0; j < allPossibleMeetings.size(); j++){
            steps *= smallPrimeNumber(allPossibleMeetings.get(j));
        }
        return steps * bigPrime;
    }
    public static char[] getDirectionArray(String direction){
        //splitting the direction instructions
        char[] newDirection = new char[direction.length()];
        for (int i = 0; i < newDirection.length; i++){
            newDirection[i] = direction.charAt(i);
        }
        return newDirection;
    }
    public static String[][] splitMapOpen(String[] map_direction){
        //splitting the map
        String[][] map = new String[map_direction.length-1][3];
        for(int i = 1; i < map_direction.length; i++){
            String[] tmp = map_direction[i].split(",");
            map[i - 1][0] = tmp[0];
            map[i - 1][1] = tmp[1];
            map[i - 1][2] = tmp[2];
        }
        return map;
    }
    public static String[][] getAllStartingPoints(String[][] map){
        //scanning through the map and finding all starting points and returning the starting points "name" and
        //the position of it in the map with the
        ArrayList<String[]> startingPoints = new ArrayList<>();
        for (int i = 0; i < map.length; i++){
           if (map[i][0].charAt(2) == 'A'){
               startingPoints.add(new String[]{map[i][0], "" + i});
           }
       }
        String[][] points = new String[startingPoints.size()][2];
        for (int j = 0; j < startingPoints.size(); j++){
            String[] tmp = startingPoints.get(j);
            points[j][0] = tmp[0];
            points[j][1] = tmp[1];
        }
        return points;
    }
    public static int getSteps(String[][] map, char[] direction, int start, String location){
        //for 2.Problem the counting algorithm
        int steps = 0;
        int directionCounter = 0;
        for (int i = start; i < map.length; i++){
            if (location.equals(map[i][0])){
                if (direction[directionCounter] == 'R'){
                    location = map[i][2];
                }else{
                    location = map[i][1];
                }
                directionCounter++;
                steps++;
                if (directionCounter == direction.length){directionCounter = 0;}
            }
            if (i == map.length - 1){i = -1;}
            if (location.charAt(2) == 'Z'){break;}
        }
        return steps;
    }
    public static int bigPrimeNumber(int number){
        //return the biggest PrimeNumber
        int smallPrime = 0;
        if (number == 1){return smallPrime;}
        int testNumber = 2;
        while (testNumber * testNumber <= number){
            if(number% testNumber == 0){
                number /= testNumber;
                break;
            }
            testNumber++;
        }
        return number;
    }
    public static int smallPrimeNumber(int number){
        //return the smallest PrimeNumber
        int smallPrime = 0;
        if (number == 1){return smallPrime;}
        int testNumber = 2;
        while (testNumber * testNumber <= number){
            if(number% testNumber == 0){break;}
            testNumber++;
        }
        return testNumber;
    }
}
