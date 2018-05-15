package objects;

import testdata.TestData;

public class BaseObject {

    protected TYPE type;
    protected TestData testData = new TestData();
    public enum TYPE{Planet, Planets}

    public BaseObject(){
        type = TYPE.valueOf(getClass().getSimpleName());
    }

}
