package testdata;

public class JsonData<T> {

    String name;
    T object;

    public JsonData(String name, T object) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "name='" + name + '\'' +
                ", object=" + object +
                '}';
    }
}
