package Vancouver;

public class Intersection {

    private float lon;
    private float lat;

    public Intersection(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return lon + "," + lat;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(lon) * 31 + Float.hashCode(lat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Intersection))
            return false;
        Intersection other = (Intersection) obj;
        return this.lon == other.lon && this.lat == other.lat;
    }
}
