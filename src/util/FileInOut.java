package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileInOut {

    public static int[] fromFile(int fdata, int mdata, String name){
        int[] arr = new int[mdata];
        try(FileReader fis = new FileReader(name)){
            char[] st = new char[fdata];
            int i, j = 0;
            while((i = fis.read(st))!=-1){}
            for(int k = 0; k<13; k++){
                if(st[k]=='\n'||st[k]=='\r'||st[k]==0){
                    continue;
                }
                arr[j]=Integer.parseInt(String.valueOf(st[k]));
                j++;
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
