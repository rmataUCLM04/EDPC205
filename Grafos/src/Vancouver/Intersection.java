package Vancouver;
import java.math.BigDecimal;
public class Intersection implements SequentialFileReader{

    private float lat;
    private float lon;

    public Intersection(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
    // Getters
    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    // Setters
    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void readData(String[] data) {
        
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
