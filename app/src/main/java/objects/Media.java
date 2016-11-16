package objects;

/**
 * Created by juanlucena on 14/08/15.
 */
public class Media {

    public Media(String name, String objectType ,String path){
        this.name = name;
        this.objectType = objectType;
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String name;
    private String objectType;
    private String path;


}
