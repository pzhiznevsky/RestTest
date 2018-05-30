package objects;

import testdata.TestData;

public class BaseObject {

    protected TYPE type;
    protected TestData testData = new TestData();
    public enum TYPE{Planet, Planets, BaseObject}

    public BaseObject(){
        type = TYPE.valueOf(getClass().getSimpleName());
    }

    public BaseObject create(TYPE type, String key){
        switch (type){
            case Planets:
                return new Planets(key);
            case Planet:
                return new Planet(key);
        }
        return null;
    }

}
