package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {

    public static int[] fromFile(int size, String name){
        int[] arr = new int[size];
        try(FileReader fileReader = new FileReader(name)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            List<String> lineList = new ArrayList<String>();
            while((line = bufferedReader.readLine())!=null){
                lineList.add(line);
            }
            for(int i = 0; i<arr.length; i++){
                arr[i]=Integer.parseInt(lineList.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e){
            e.printStackTrace();
        }

        return arr;
    }

    public static void toFile(String name, int[] arr){
        try(FileWriter fileWriter = new FileWriter(name)){
            String str = "";
            for(int i=0; i<arr.length;i++){
                str += arr[i]+"\n";
            }
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
