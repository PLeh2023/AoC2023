import java.io.*;
import java.util.ArrayList;

public class Day5 {
    public static void main(String[] args) throws IOException {

        //1. Problem: Finding the smallest number in the location section after processing all seeds through a mapping
        //2. problem: Finding the smallest number in the location section after processing all seeds through a mapping,
        //            but every 2nd number is a range of seeds which means we get millions more seeds and can run into
        //            space problems
        //valid numbers are 1 and 2 which are representing the problems
        long lowestLocation = input(1);
        long lowestLocationTwo = input(2);
        System.out.println("First Problem: " + lowestLocation);
        System.out.println("Second Problem: " + lowestLocationTwo);
    }
    public static long input(int choice) throws IOException {
        int counter = -1;
        //initialize all booleans for easier processing of the input
        boolean seed_to_soil = false, soil_to_fertilizer = false, fertilizer_to_water = false, water_to_light = false,
                light_to_temperature = false, temperature_to_humidity = false, humidity_to_location = false;
        //initialize all mappings
        String[] seeds = new String[100];
        ArrayList<String>   seed_to_soil_map = new ArrayList<>();
        ArrayList<String>   soil_to_fertilizer_map = new ArrayList<>();
        ArrayList<String>   fertilizer_to_water_map = new ArrayList<>();
        ArrayList<String>   water_to_light_map = new ArrayList<>();
        ArrayList<String>   light_to_temperature_map = new ArrayList<>();
        ArrayList<String>   temperature_to_humidity_map = new ArrayList<>();
        ArrayList<String>   humidity_to_location_map = new ArrayList<>();
        //selecting of the file
        File file = new File("Inputs/Day5_Input");
        //reading of the file
        FileReader fileReader = new FileReader(file);
        //buffering the content of the file for line reading
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null ){
            if(line.isBlank()){
                continue;
            }
            line = normalizeString(line);
            if(line.contains("seeds")){
                seeds = line.replace("seeds;","").split(";");
                seed_to_soil = true;
            }
            if (line.contains("soil-to-fertilizer")){
                seed_to_soil = false;
                soil_to_fertilizer = true;
            }
            if (line.contains("fertilizer-to-water")){
                soil_to_fertilizer = false;
                fertilizer_to_water = true;
            }
            if (line.contains("water-to-light")){
                fertilizer_to_water = false;
                water_to_light = true;
            }
            if (line.contains("light-to-temperature")){
                water_to_light = false;
                light_to_temperature = true;
            }
            if (line.contains("temperature-to-humidity")){
                light_to_temperature = false;
                temperature_to_humidity = true;
            }
            if (line.contains("humidity-to-location")){
                temperature_to_humidity = false;
                humidity_to_location = true;
            }

            if (seed_to_soil){
                if (counter == -1 || counter == 0){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                seed_to_soil_map.add(tmp[0]);
                seed_to_soil_map.add(tmp[1]);
                seed_to_soil_map.add(tmp[2]);
            }
            if (soil_to_fertilizer){
                if (counter == 1){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                soil_to_fertilizer_map.add(tmp[0]);
                soil_to_fertilizer_map.add(tmp[1]);
                soil_to_fertilizer_map.add(tmp[2]);
            }
            if (fertilizer_to_water){
                if (counter == 2){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                fertilizer_to_water_map.add(tmp[0]);
                fertilizer_to_water_map.add(tmp[1]);
                fertilizer_to_water_map.add(tmp[2]);
            }
            if (water_to_light){
                if (counter == 3){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                water_to_light_map.add(tmp[0]);
                water_to_light_map.add(tmp[1]);
                water_to_light_map.add(tmp[2]);
            }
            if (light_to_temperature){
                if (counter == 4){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                light_to_temperature_map.add(tmp[0]);
                light_to_temperature_map.add(tmp[1]);
                light_to_temperature_map.add(tmp[2]);
            }
            if (temperature_to_humidity){
                if (counter == 5){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                temperature_to_humidity_map.add(tmp[0]);
                temperature_to_humidity_map.add(tmp[1]);
                temperature_to_humidity_map.add(tmp[2]);
            }
            if (humidity_to_location){
                if (counter == 6){
                    counter++;
                    continue;
                }
                String[] tmp = line.split(";");
                humidity_to_location_map.add(tmp[0]);
                humidity_to_location_map.add(tmp[1]);
                humidity_to_location_map.add(tmp[2]);
            }
        }   return  getLowestLocation(getUsefulArray(seeds), getUsefulArray(seed_to_soil_map),
                getUsefulArray(soil_to_fertilizer_map), getUsefulArray(fertilizer_to_water_map),
                getUsefulArray(water_to_light_map), getUsefulArray(light_to_temperature_map),
                getUsefulArray(temperature_to_humidity_map), getUsefulArray(humidity_to_location_map), choice);
    }
    public static String normalizeString(String line){
        //it replaces space with ;
        return line.replace(": ",";").replace(":","").replace(" ",";");
    }
    public static Long[] getUsefulArray(String[] line){
        //converts String array to Long array and eliminates the null values
        ArrayList<String> tmp = new ArrayList<>();
        for(String j : line){
            if(!(j == null)){
                tmp.add(j);
            }
        }
        Long[] seeds = new Long[tmp.size()];
        for(int k = 0; k < seeds.length; k++){
            seeds[k] = Long.parseLong(tmp.get(k));
        }
        return seeds;
    }
    public static Long[] getUsefulArray(ArrayList<String> map){
        //overloaded method with other parameter
        Long[] arrayString = new Long[map.size() * 2];
        ArrayList<Long> tmp = new ArrayList<>();
        int counter = 0;
        for(int i = 0; i < map.size(); i = i+3){
            arrayString[counter] = Long.parseLong(map.get(i));
            arrayString[counter + 1] =  (Long.parseLong(map.get(i)) + Long.parseLong(map.get(i+2)));
            arrayString[counter + 2] = Long.parseLong(map.get(i + 1));
            arrayString[counter + 3] =  (Long.parseLong(map.get(i + 1)) + Long.parseLong(map.get(i+2)));
            counter += 4;
        }
        for(Long j : arrayString){
            if(!(j == null)){
                tmp.add(j);
            }
        }
        Long[] finalStringArray = new Long[tmp.size()];
        for(int k = 0; k < finalStringArray.length; k++){
            finalStringArray[k] = tmp.get(k);
        }
        return finalStringArray;
    }
    public static long getLowestLocation(Long[] seeds, Long[] seed_to_soil_map,
                                         Long[] soil_to_fertilizer_map, Long[] fertilizer_to_water_map,
                                         Long[] water_to_light_map, Long[] light_to_temperature_map,
                                         Long[] temperature_to_humidity_map, Long[] humidity_to_location_map, int choice){
        long tmp;
        long lowestNumber = 2000000000;
        //differentiate between both problems
        if(choice == 1) {
            //every seed gets send through the whole map
            for (Long i : seeds) {
                tmp = i;
                tmp = findDirection(tmp, seed_to_soil_map);
                tmp = findDirection(tmp, soil_to_fertilizer_map);
                tmp = findDirection(tmp, fertilizer_to_water_map);
                tmp = findDirection(tmp, water_to_light_map);
                tmp = findDirection(tmp, light_to_temperature_map);
                tmp = findDirection(tmp, temperature_to_humidity_map);
                tmp = findDirection(tmp, humidity_to_location_map);
                lowestNumber = getLowestNumber(lowestNumber, tmp);
            }
        }else{
            //every seed gets send through the whole map with the range aspect
            for(int j = 0; j < seeds.length; j = j + 2) {
                for (long i = seeds[j]; i < seeds[j] + seeds[j + 1]; i++) {
                    tmp = i;
                    tmp = findDirection(tmp, seed_to_soil_map);
                    tmp = findDirection(tmp, soil_to_fertilizer_map);
                    tmp = findDirection(tmp, fertilizer_to_water_map);
                    tmp = findDirection(tmp, water_to_light_map);
                    tmp = findDirection(tmp, light_to_temperature_map);
                    tmp = findDirection(tmp, temperature_to_humidity_map);
                    tmp = findDirection(tmp, humidity_to_location_map);
                    lowestNumber = getLowestNumber(lowestNumber, tmp);
                }
            }
        }
        return lowestNumber;
    }
    public static Long findDirection(Long tmp, Long[] map){
        //the seeds gets checked in the map if it is in any range and if so it gets converted to another number
        long dif;
        for(int j = 0; j < map.length; j = j + 4){
            if(tmp > map[j + 2] && tmp < map[j + 3]){
                dif = map[j + 2] - map[j];
                if(dif > 0){
                    tmp -= dif;
                }else{
                    tmp += (dif * -1);
                }
                break;
            }
        }
        return tmp;
    }
    public static Long getLowestNumber(Long lowestNumber, Long tmp){
        //simple comparison of two numbers and return the lower one
        if (lowestNumber > tmp) {
            lowestNumber = tmp;
        }
        return lowestNumber;
    }
}
