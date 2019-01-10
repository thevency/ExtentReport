package com.report.core;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class ConfigReport {

    static List<String> nodeNameList = new ArrayList<>();

    public static List<String> configureReport(String filePath, List<String> nodeName) throws FileNotFoundException {

        File file = new File(filePath);
        Scanner input = new Scanner(file);
         List<String> content = new ArrayList<>();


            while (input.hasNextLine()) {
                for(String  i : nodeName) {
                    String str = "<h5 class='node-name'>" + i;

                    content.add(input.nextLine().replace(str, "<h5 style=\"color:blue;\" class='node-name'>" + i));

                }
        }
        input.close();

        return content;
    }

    public static void writeFile(String content, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }

    public static List<String> getNodeName(String nodeName){
        nodeNameList.add(nodeName);
        return nodeNameList;
    }

}
