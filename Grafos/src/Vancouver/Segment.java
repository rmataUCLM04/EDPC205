package Vancouver;
import java.math.BigDecimal;
public class Segment implements SequentialFileReader{

    private int objectId;
    private String routeName;
    private String streetName;
    private String bikewayType;
    private Integer speedLimit;
    private String surfaceType;
    private boolean snowRemoval;
    private float length;
    private Integer year;

    

    public Segment() {
        // Constructor vacío para usar con readData
    }

    public Segment(int objectId,
            String routeName,
            String streetName,
            String bikewayType,
            Integer speedLimit,
            String surfaceType,
            boolean snowRemoval,
            float length,
            Integer year,
            String originId,
            String destinationId,
            boolean virtual) {

        this.objectId = objectId;
        this.routeName = routeName;
        this.streetName = streetName;
        this.bikewayType = bikewayType;
        this.speedLimit = speedLimit;
        this.surfaceType = surfaceType;
        this.snowRemoval = snowRemoval;
        this.length = length;
        this.year = year;
    }

    // readData 
    public void readData(String[] data) {

        this.objectId = Integer.parseInt(data[0]);
        this.routeName = data[1];
        this.streetName = data[2];
        this.bikewayType = data[3];

        this.speedLimit = data[4].isEmpty() ? null : Integer.parseInt(data[4]);
        this.surfaceType = data[5];
        this.snowRemoval = data[6].equalsIgnoreCase("Yes");

        this.length = Float.parseFloat(data[7]);
        this.year = data[8].isEmpty() ? null : Integer.parseInt(data[8]);

        // Las coordenadas se convierten en un identificador de nodo
        String latO = data[9];
        String lonO = data[10];
        String latD = data[11];
        String lonD = data[12];
    }
    public int getObjectId() {
    return objectId;
}

public void setObjectId(int objectId) {
    this.objectId = objectId;
}

public String getRouteName() {
    return routeName;
}

public void setRouteName(String routeName) {
    this.routeName = routeName;
}

public String getStreetName() {
    return streetName;
}

public void setStreetName(String streetName) {
    this.streetName = streetName;
}

public String getBikewayType() {
    return bikewayType;
}

public void setBikewayType(String bikewayType) {
    this.bikewayType = bikewayType;
}

public Integer getSpeedLimit() {
    return speedLimit;
}

public void setSpeedLimit(Integer speedLimit) {
    this.speedLimit = speedLimit;
}

public String getSurfaceType() {
    return surfaceType;
}

public void setSurfaceType(String surfaceType) {
    this.surfaceType = surfaceType;
}

public boolean isSnowRemoval() {
    return snowRemoval;
}

public void setSnowRemoval(boolean snowRemoval) {
    this.snowRemoval = snowRemoval;
}

public float getLength() {
    return length;
}

public void setLength(float length) {
    this.length = length;
}

public Integer getYear() {
    return year;
}

public void setYear(Integer year) {
    this.year = year;
}

}
