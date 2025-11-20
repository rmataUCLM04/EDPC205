package Vancouver;

public class Intersection {

    private float lon;
    private float lat;

    public Intersection(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public void readData(String[] data) {
        this.lon = Float.parseFloat(data[0]);
        this.lat = Float.parseFloat(data[1]);
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public String toString() {
        return lon + "," + lat;
    }

    public int hashCode() {
        return Float.hashCode(lon) * 31 + Float.hashCode(lat);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Intersection))
            return false;
        Intersection other = (Intersection) obj;
        return this.lon == other.lon && this.lat == other.lat;
    }
}
