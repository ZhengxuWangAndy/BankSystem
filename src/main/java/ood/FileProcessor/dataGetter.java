package ood.FileProcessor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class dataGetter {
    public dataGetter() {
    }

    // get the whole data stored in csv, a list of map.
    public ArrayList<LinkedHashMap<String, String>> getData(String path){
        ArrayList<LinkedHashMap<String,String>> data = new ArrayList<>();

        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(path));
            String line;

            // read the header, set as key.
            LinkedList<String> key = new LinkedList<>();
            String keys = br.readLine();
            for (String s : keys.split(",")) {
                key.add(s);
            }

            // read content as values.
            while ((line = br.readLine()) != null) {

                LinkedHashMap tmp = new LinkedHashMap<>();
                // use comma as separator
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++){
                    tmp.put(key.get(i), values[i]);
                }

                data.add(tmp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;

    }

    // get a list of values, e.g. a list of transaction values in file.
    public ArrayList<String> getValues(String path, String key){
        ArrayList<LinkedHashMap<String,String>> data = this.getData(path);
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            values.add(data.get(i).get(key));
        }
        return values;
    }

}
