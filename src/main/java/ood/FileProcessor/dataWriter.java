package ood.FileProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class dataWriter {

    public void write(String fileName, ArrayList<LinkedHashMap<String, String>> data) {

        File writeFile = new File(fileName);

        try {
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            // write header, keys
            writeText.write(String.join(",", data.get(0).keySet()));

            // write values
            for (int i = 0; i < data.size(); i++) {
                writeText.newLine();
                writeText.write(String.join(",", data.get(i).values()));
            }

            writeText.flush();
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Write Error");
        }
    }
}
