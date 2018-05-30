package testdata;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import objects.BaseObject.TYPE;
import utils.Constants;
import objects.Planet;
import objects.Planets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    private static String testData;
    private static Gson gson = new Gson();

    public static void readTestData(){
        try {
            TestData.testData = new String(Files.readAllBytes(Paths.get(Constants.DATA_PATH)));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map createMap(TYPE type){
        switch (type){
            case Planet:
                return new HashMap<String, Planet>();
            case Planets:
                return new HashMap<String, Planets>();
        }
        return null;
    }

    public Type createType(TYPE type){
        switch (type){
            case Planet:
                return new TypeToken<List<JsonData<Planet>>>() {}.getType();
            case Planets:
                return new TypeToken<List<JsonData<Planets>>>() {}.getType();
        }
        return null;
    }

    public Object getTestData(String key, TYPE type){
        Type typeToken = createType(type);
        List<JsonData> list = gson.fromJson(TestData.testData, typeToken);
        Map map = createMap(type);
        for(JsonData jsonData: list){
            map.put(jsonData.getName(), jsonData.getObject());
        }
        return map.get(key);
    }

}
