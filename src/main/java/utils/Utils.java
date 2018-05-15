package utils;

import com.google.gson.Gson;
import objects.BaseObject;
import objects.BaseObject.TYPE;
import objects.Planet;
import objects.Planets;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Utils {

    private static Gson gson = new Gson();
    private static FileReader fileReader = initFileReader();

    public static FileReader initFileReader(){
        if(fileReader != null)
            return fileReader;
        try {
            return new FileReader(Constants.DATA_PATH);
        } catch (FileNotFoundException e){
        }
        return null;
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNull(String str){
        return null == str || "".equals(str) || "null".equalsIgnoreCase(str);
    }

    public static BaseObject getFromJson(TYPE type, String jsonAsString) {
        switch (type){
            case Planet:
                return gson.fromJson(jsonAsString, Planet.class);
            case Planets:
                return gson.fromJson(jsonAsString, Planets.class);
        }
        return null;
    }

}
