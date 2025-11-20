package Vancouver;

public class Segment {

    private int objectId;
    private String routeName;
    private String streetName;
    private String bikewayType;
    private Integer speedLimit;
    private String surfaceType;
    private boolean snowRemoval;
    private float length;
    private Integer year;

    // Ya no hay Intersection
    private String originId;      
    private String destinationId;

    private boolean virtual;

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
        this.originId = originId;
        this.destinationId = destinationId;
        this.virtual = virtual;
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

        this.originId = latO + "," + lonO;
        this.destinationId = latD + "," + lonD;

        this.virtual = data[13].equalsIgnoreCase("Yes");
    }

    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public boolean isVirtual() {
        return virtual;
    }

    // demás getters igual…
}
