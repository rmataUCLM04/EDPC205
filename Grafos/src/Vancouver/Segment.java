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

    private Intersection origin;
    private Intersection destination;

    private boolean virtual;

    public Segment(int objectId,
            String routeName,
            String streetName,
            String bikewayType,
            Integer speedLimit,
            String surfaceType,
            boolean snowRemoval,
            float length,
            Integer year,
            Intersection origin,
            Intersection destination,
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
        this.origin = origin;
        this.destination = destination;
        this.virtual = virtual;
    }

    // Getters

    public int getObjectId() {
        return objectId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getBikewayType() {
        return bikewayType;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public boolean hasSnowRemoval() {
        return snowRemoval;
    }

    public float getLength() {
        return length;
    }

    public Integer getYear() {
        return year;
    }

    public Intersection getOrigin() {
        return origin;
    }

    public Intersection getDestination() {
        return destination;
    }

    public boolean isVirtual() {
        return virtual;
    }

    // Filtros del enunciado

    public boolean isValidBase() {
        return speedLimit != null && speedLimit >= 30
                && length > 10
                && year != null && year >= 1990;
    }

    public boolean isProtectedWithSnow() {
        return "Protected Bike Lanes".equalsIgnoreCase(bikewayType)
                && snowRemoval;
    }

    public boolean isSpeed30() {
        return speedLimit != null && speedLimit == 30;
    }

    public String toString() {
        return "ID " + objectId + " | " + routeName + " | " + length + " m";
    }
}
