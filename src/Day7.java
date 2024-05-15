import java.io.*;
import java.util.Arrays;

public class Day7 {
    public static void main(String[] args) throws IOException{
        int num = sortArray(input(),1);
        int num2 = sortArray(input(),2);
        System.out.println(num);
        System.out.println(num2);
    }
    public static String[] input() throws IOException {
        //find the file
        File file = new File("Inputs/Day7_Input");
        //read the file
        FileReader fileReader = new FileReader(file);
        //split the file to read every line separate
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int numberOfLines = linesCounter(file);
        String[] handWorth = new String[numberOfLines];
        int counter = 0;
        String line;
        //filling the array up
        while((line = bufferedReader.readLine()) != null){
            handWorth[counter] = line.replace(" ",";");
            counter++;
        }
        return handWorth;
    }
    public static int linesCounter(File file) throws IOException {
        //its purpose is to find the max lines in a txt file
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        lineNumberReader.skip(Long.MAX_VALUE);
        int numberOfLines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        return numberOfLines;
    }
    public static int sortArray(String[] handWorth, int choice){
        //initializing separated arrays for easier sorting
        String[][] unsorted = new String[handWorth.length][2];
        String[][] fiveKind = new String[handWorth.length][2];
        String[][] fourKind = new String[handWorth.length][2];
        String[][] fullHouse = new String[handWorth.length][2];
        String[][] threeKind = new String[handWorth.length][2];
        String[][] twoPair = new String[handWorth.length][2];
        String[][] onePair = new String[handWorth.length][2];
        String[][] highCard = new String[handWorth.length][2];
        // The cards which are same    5; 4; [3,2]; 3; [2,2]; 2; 1
        int[] quickSorted = {0,0,0,0,0,0,0};
        int counter = 0;
        //splitting the combinations of hand and worth
        for(String i : handWorth){
            String[] tmp = i.split(";");
            unsorted[counter][0] = tmp[0];
            unsorted[counter][1] = tmp[1];
            counter++;
        }
        //separating the hands of each other so that only same winning category remains in an array
        for(int j = 0; j < unsorted.length; j++){
            String tmp = unsorted[j][0];
            char[] characters = {tmp.charAt(0), tmp.charAt(1), tmp.charAt(2), tmp.charAt(3), tmp.charAt(4)};
            String hand = amountOfCharacters(characters,choice);
            switch (hand){
                case "FIVE OF A KIND":  fiveKind[quickSorted[0]][0] = unsorted[j][0];
                                        fiveKind[quickSorted[0]][1] = unsorted[j][1];
                                        quickSorted[0] += 1;
                                        break;
                case "FOUR OF A KIND":  fourKind[quickSorted[1]][0] = unsorted[j][0];
                                        fourKind[quickSorted[1]][1] = unsorted[j][1];
                                        quickSorted[1] += 1;
                                        break;
                case "FULL HOUSE":      fullHouse[quickSorted[2]][0] = unsorted[j][0];
                                        fullHouse[quickSorted[2]][1] = unsorted[j][1];
                                        quickSorted[2] += 1;
                                        break;
                case "THREE OF A KIND": threeKind[quickSorted[3]][0] = unsorted[j][0];
                                        threeKind[quickSorted[3]][1] = unsorted[j][1];
                                        quickSorted[3] += 1;
                                        break;
                case "TWO PAIR":        twoPair[quickSorted[4]][0] = unsorted[j][0];
                                        twoPair[quickSorted[4]][1] = unsorted[j][1];
                                        quickSorted[4] += 1;
                                        break;
                case "ONE PAIR":        onePair[quickSorted[5]][0] = unsorted[j][0];
                                        onePair[quickSorted[5]][1] = unsorted[j][1];
                                        quickSorted[5] += 1;
                                        break;
                case "HIGH CARD":       highCard[quickSorted[6]][0] = unsorted[j][0];
                                        highCard[quickSorted[6]][1] = unsorted[j][1];
                                        quickSorted[6] += 1;
                                        break;
            }
        }
        String[][] bigArray = new String[handWorth.length][2];
        getBigArray(bigArray,bubblesort(removeNull(fiveKind),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(fourKind),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(fullHouse),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(threeKind),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(twoPair),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(onePair),choice),handWorth.length);
        getBigArray(bigArray,bubblesort(removeNull(highCard),choice),handWorth.length);
        System.out.println(Arrays.deepToString(bigArray));

        return multiply(bigArray);
    }
    public static String amountOfCharacters(char[] characters,int choice){
        //counting the amount of same letters to determine which hand rank it has
        //                   A,K,Q,J,T,9,8,7,6,5,4,3,2
        Integer[] counter = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        Character[] stamp = {'A','K','Q','J','T','9','8','7','6','5','4','3','2'};
        for (char i : characters){
            for (int j = 0; j < stamp.length; j++){
                if (i == stamp[j]){
                    counter[j] += 1;
                }
            }
        }
        //getting the numbers to sort the hand in its rank
        int count = 0;
        int[] amount = new int[2];
        //differentiating between part one and two
        if (choice == 1) {
            for (int k : counter) {
                if (k == 5 ) {return "FIVE OF A KIND";}
                if (k == 4 ) {return "FOUR OF A KIND";}
                if (k == 3 || k == 2) {
                    amount[count] = k;
                    count++;
                }
            }
            return switch (Arrays.stream(amount).sum()) {
                case 5: yield "FULL HOUSE";
                case 4: yield "TWO PAIR";
                case 3: yield "THREE OF A KIND";
                case 2: yield "ONE PAIR";
                default: yield "HIGH CARD";
            };
        }else {
            for (int k : counter) {
                if (k == 5 || k == 4 && counter[3] > 0) {return "FIVE OF A KIND";}
                if (k == 4 ) {return "FOUR OF A KIND";}
                if (k == 3 || k == 2) {
                    amount[count] = k;
                    count++;
                }
            }
            if (Arrays.stream(amount).sum() == 5 && counter[3] > 0){return "FIVE OF A KIND";}
            if (Arrays.stream(amount).sum() == 4 && counter[3] > 1){return "FOUR OF A KIND";}
            if (Arrays.stream(amount).sum() == 4 && counter[3] > 0){return "FULL HOUSE";}
            if (Arrays.stream(amount).sum() == 3 && counter[3] > 0){return "FOUR OF A KIND";}
            if (Arrays.stream(amount).sum() == 2 && counter[3] > 0){return "THREE OF A KIND";}
            if (Arrays.stream(amount).sum() == 0 && counter[3] > 0){return "ONE PAIR";}

            return switch (Arrays.stream(amount).sum()) {
                case 5: yield "FULL HOUSE";
                case 4: yield "TWO PAIR";
                case 3: yield "THREE OF A KIND";
                case 2: yield "ONE PAIR";
                default: yield "HIGH CARD";
            };
        }
    }
    public static String[][] bubblesort(String[][] array, int choice){
        //the easiest sorting algorithm
        for (int i = 0; i < array.length - 1; i++){
            for (int j = 0; j < array.length - i - 1; j++){
                if(getHigherHand(array[j][0],array[j + 1][0], choice) == 1){
                    String tmpHand = array[j][0];
                    String tmpWorth = array[j][1];
                    array[j][0] = array[j + 1][0];
                    array[j][1] = array[j + 1][1];
                    array[j + 1][0] = tmpHand;
                    array[j + 1][1] = tmpWorth;
                }
            }
        }
        return  array;
    }
    public static String[][] removeNull(String[][] array){
        //removing the null from arrays created with larger dataset length
        int counter = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i][0] != null){
                counter++;
            }
        }
        String[][] noNull = new String[counter][2];
        for (int j = 0; j < noNull.length; j++){
            noNull[j][0] = array[j][0];
            noNull[j][1] = array[j][1];
        }
        return noNull;
    }
    public static int getHigherHand(String first, String second, int choice){
        //the reason we need another algorithm for the bubblesort is that
        //we try to compare Strings as greater of each other
        for (int i = 0; i < first.length(); i++){
            if (first.charAt(i) ==  second.charAt(i)){continue;}
            if (getChracternumber(first.charAt(i),choice) >  getChracternumber(second.charAt(i),choice)){ return 0;}
            else{return 1;}
        }
        return 0;
    }
    public static int getChracternumber(char c, int choice){
        //getting for comparison for each char a number value
        int num;
        if (choice == 1) {
           switch (c) {
               case 'T' -> num = 10;
               case 'J' -> num = 11;
               case 'Q' -> num = 12;
               case 'K' -> num = 13;
               case 'A' -> num = 14;
               default -> num = Integer.parseInt("" + c);
           }
       }else{
           switch (c) {
               case 'T' -> num = 10;
               case 'J' -> num = 1;
               case 'Q' -> num = 12;
               case 'K' -> num = 13;
               case 'A' -> num = 14;
               default -> num = Integer.parseInt("" + c);
           }
       }
       return num;
    }
    public static String[][] getBigArray(String[][] bigArray, String[][] array, int length){
        //combining every separated String back to one
        try {
            int counter = 0;
            for (int i = 0; i < length; i++) {
                if (bigArray[i][0] != null) {continue;}
                if (array[counter][0] == null) {break;}
                bigArray[i][0] = array[counter][0];
                bigArray[i][1] = array[counter][1];
                counter++;
            }
        }catch (ArrayIndexOutOfBoundsException ignored){}
        return bigArray;
    }
    public static int multiply(String[][] array){
        //multiplying the worth of each hand by its rank where the first and highest rank gets the highest multiplier
        int num = 1;
        int counter = 0;
        for (int i = array.length; i > 0; i--){
            num +=  i * Integer.parseInt(array[counter][1]);
            counter++;
        }
        return num - 1;
    }
}
