import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    static int length;

    public static void main(String[] args){

        int sum;
        ArrayList<String> map = input();
        sum = sumAllNumbers(map);
        System.out.println(sum);
    }
    public static ArrayList<String> input(){
        //its only purpose is to find and read the .txt
        ArrayList<String> map;
        try {
            File file = new File("Inputs/Day3_Input");
            FileReader fileReader = new FileReader(file);
            map = mapping(fileReader);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
    public static ArrayList<String> mapping(FileReader fileReader) throws IOException {
        //this method is splitting the whole text into each character and puts every character in a map
        ArrayList<String> map = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while((line = bufferedReader.readLine()) != null){
            length = line.length();
            for(int i = 0; i < line.length(); i++){
                map.add("" + line.charAt(i));
            }
        }
        return map;
    }
    public static int sumAllNumbers(ArrayList<String> map){
     //[+*/$%&=@#-] [^.\d]
        int sum = 0;
        for(int i = 0; i < map.size(); i++){
            if(map.get(i).matches("[+*/$%&=@#-]")){
                //watched as 2D
                //top left
                //three digits
                if(map.get(i-length-3).matches("\\d") && map.get(i-length-2).matches("\\d") && map.get(i-length-1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-length-3) + map.get(i-length-2) + map.get(i-length-1));
                }
                //two digits
                if(map.get(i-length-3).contains(".") && map.get(i-length-2).matches("\\d") && map.get(i-length-1).matches("\\d") && map.get(i-length).contains(".")){
                    sum += Integer.parseInt( map.get(i-length-2) + map.get(i-length-1));
                }
                //one digit
                if(map.get(i-length-3).contains(".") && map.get(i-length-2).contains(".") && map.get(i-length-1).matches("\\d") && map.get(i-length).contains(".")){
                    sum += Integer.parseInt(map.get(i-length-1));
                }
                //top right
                //three digits
                if(map.get(i-length+1).matches("\\d") && map.get(i-length+2).matches("\\d") && map.get(i-length+3).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-length+1) + map.get(i-length+2) + map.get(i-length+3));
                }
                //two digits
                if(map.get(i-length).contains(".") && map.get(i-length+1).matches("\\d") && map.get(i-length+2).matches("\\d") && map.get(i-length+3).contains(".")){
                    sum += Integer.parseInt( map.get(i-length+1) + map.get(i-length+2));
                }
                //one digit
                if(map.get(i-length).contains(".") && map.get(i-length+1).matches("\\d") && map.get(i-length+2).matches(".") && map.get(i-length+3).contains(".")){
                    sum += Integer.parseInt(map.get(i-length+1));
                }
                //top middle
                //three digits
                if(map.get(i-length-1).matches("\\d") && map.get(i-length).matches("\\d") && map.get(i-length+1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-length-1) + map.get(i-length) + map.get(i-length+1));
                }
                //three digits
                if(map.get(i-length-2).matches("\\d") && map.get(i-length-1).matches("\\d") && map.get(i-length).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-length-2) + map.get(i-length-1) + map.get(i-length));
                }
                //three digits
                if(map.get(i-length).matches("\\d") && map.get(i-length+1).matches("\\d") && map.get(i-length+2).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-length) + map.get(i-length+1) + map.get(i-length+2));
                }
                //two digits left
                if(map.get(i-length-2).contains(".") && map.get(i-length-1).matches("\\d") && map.get(i-length).matches("\\d") && map.get(i-length+1).contains(".")){
                    sum += Integer.parseInt(map.get(i-length-1) + map.get(i-length));
                }
                //two digits right
                if(map.get(i-length-1).contains(".") && map.get(i-length).matches("\\d") && map.get(i-length+1).matches("\\d") && map.get(i-length+2).contains(".")){
                    sum += Integer.parseInt(map.get(i-length) + map.get(i-length+1));
                }
                //one digit
                if(map.get(i-length-1).contains(".") && map.get(i-length).matches("\\d") && map.get(i-length+1).contains(".")){
                    sum += Integer.parseInt(map.get(i-length));
                }
                //left
                //three digits
                if(map.get(i-3).matches("\\d") && map.get(i-2).matches("\\d") && map.get(i-1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-3) + map.get(i-2) + map.get(i-1));
                }
                //two digits
                if(map.get(i-3).contains(".") && map.get(i-2).matches("\\d") && map.get(i-1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-2) + map.get(i-1));
                }
                //one digit
                if(map.get(i-3).contains(".") && map.get(i-2).contains(".") && map.get(i-1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i-1));
                }
                //right
                //three digits
                if(map.get(i+1).matches("\\d") && map.get(i+2).matches("\\d") && map.get(i+3).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+1) + map.get(i+2) + map.get(i+3));
                }
                //two digits
                if(map.get(i+1).matches("\\d") && map.get(i+2).matches("\\d") && map.get(i+3).contains(".")){
                    sum += Integer.parseInt(map.get(i+1) + map.get(i+2));
                }
                //one digit
                if(map.get(i+1).matches("\\d") && map.get(i+2).contains(".") && map.get(i+3).contains(".")){
                    sum += Integer.parseInt(map.get(i+1));
                }
                //bottom right
                //three digits
                if(map.get(i+length+1).matches("\\d") && map.get(i+length+2).matches("\\d") && map.get(i+length+3).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+length+1) + map.get(i+length+2) + map.get(i+length+3));
                }
                //two digits
                if(map.get(i+length+3).contains(".") && map.get(i+length+2).matches("\\d") && map.get(i+length+1).matches("\\d") && map.get(i+length).contains(".") ){
                    sum += Integer.parseInt( map.get(i+length+1) + map.get(i+length+2));
                }
                //one digit
                if(map.get(i+length+3).contains(".") && map.get(i+length+2).contains(".") && map.get(i+length+1).matches("\\d") && map.get(i+length).contains(".")){
                    sum += Integer.parseInt(map.get(i+length+1));
                }
                //bottom left
                //three digits
                if(map.get(i+length-1).matches("\\d") && map.get(i+length-2).matches("\\d") && map.get(i+length-3).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+length-3) + map.get(i+length-2) + map.get(i+length-1));
                }
                //two digits
                if(map.get(i+length).contains(".") && map.get(i+length-1).matches("\\d") && map.get(i+length-2).matches("\\d") && map.get(i+length-3).contains(".") ){
                    sum += Integer.parseInt( map.get(i+length-2) + map.get(i+length-1));
                }
                //one digit
                if(map.get(i+length).contains(".") && map.get(i+length-1).matches("\\d") && map.get(i+length-2).contains(".") && map.get(i+length-3).contains(".") ){
                    sum += Integer.parseInt(map.get(i+length-1));
                }
                //bottom middle
                //three digits
                if(map.get(i+length+1).matches("\\d") && map.get(i+length).matches("\\d") && map.get(i+length-1).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+length-1) + map.get(i+length) + map.get(i+length+1));
                }
                //three
                if(map.get(i+length-2).matches("\\d") && map.get(i+length-1).matches("\\d") && map.get(i+length).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+length-2) + map.get(i+length-1) + map.get(i+length));
                }
                //three
                if(map.get(i+length).matches("\\d") && map.get(i+length+1).matches("\\d") && map.get(i+length+2).matches("\\d")){
                    sum += Integer.parseInt(map.get(i+length) + map.get(i+length+1) + map.get(i+length+2));
                }
                //two digits right
                if(map.get(i+length+2).contains(".") && map.get(i+length+1).matches("\\d") && map.get(i+length).matches("\\d") && map.get(i+length-1).contains(".")){
                    sum += Integer.parseInt(map.get(i+length) + map.get(i+length+1));
                }
                //two digits left
                if(map.get(i+length+1).contains(".") && map.get(i+length).matches("\\d") && map.get(i+length-1).matches("\\d") && map.get(i+length-2).contains(".")){
                    sum += Integer.parseInt(map.get(i+length-1) + map.get(i+length));
                }
                //one digit
                if(map.get(i+length+1).contains(".") && map.get(i+length).matches("\\d") && map.get(i+length-1).contains(".")){
                    sum += Integer.parseInt(map.get(i+length));
                }
            }
        }
        return sum;
    }
}
