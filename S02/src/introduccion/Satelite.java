package introduccion;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import utils.SequentialFileReader;

public class Satelite implements SequentialFileReader {
    private String objectName;
    private String objectId;
    private LocalDateTime epoch;
    private double meanMotion;
    private double eccentricity;
    private double inclination;
    private double raan;                 // antes 'rean' (typo)
    private double argumentPericenter;

    // ✅ Constructor vacío para poder hacer: new Satelite()
    public Satelite() { }

    public Satelite(String objectName, String objectId, LocalDateTime epoch,
                    double meanMotion, double eccentricity, double inclination,
                    double raan, double argumentPericenter) {
        this.objectName = objectName;
        this.objectId = objectId;
        this.epoch = epoch;
        this.meanMotion = meanMotion;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.raan = raan;
        this.argumentPericenter = argumentPericenter;
    }

    public String getObjectName() { return objectName; }
    public void setObjectName(String objectName) { this.objectName = objectName; }

    public String getObjectId() { return objectId; }
    public void setObjectId(String objectId) { this.objectId = objectId; }

    public LocalDateTime getEpoch() { return epoch; }
    public void setEpoch(LocalDateTime epoch) { this.epoch = epoch; }

    public double getMeanMotion() { return meanMotion; }
    public void setMeanMotion(double meanMotion) { this.meanMotion = meanMotion; }

    public double getEccentricity() { return eccentricity; }
    public void setEccentricity(double eccentricity) { this.eccentricity = eccentricity; }

    public double getInclination() { return inclination; }
    public void setInclination(double inclination) { this.inclination = inclination; }

    public double getRaan() { return raan; }
    public void setRaan(double raan) { this.raan = raan; }

    public double getArgumentPericenter() { return argumentPericenter; }
    public void setArgumentPericenter(double argumentPericenter) { this.argumentPericenter = argumentPericenter; }

    public void readData(String[] data){
    this.objectName = data[0];
    this.objectId   = data[1];

    String epochStr = data[2].trim();

    
    try {
        this.epoch = OffsetDateTime
                        .parse(epochStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                        .toLocalDateTime();
    } catch (DateTimeParseException e) {
        // Si no hay offset, parseamos como ISO_LOCAL_DATE_TIME
        this.epoch = LocalDateTime.parse(epochStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    this.meanMotion         = Double.parseDouble(data[3]);
    this.eccentricity       = Double.parseDouble(data[4]);
    this.inclination        = Double.parseDouble(data[5]);
    this.raan               = Double.parseDouble(data[6]);          
    this.argumentPericenter = Double.parseDouble(data[7]);
}

    public String toString() {
        return "Satelite{" +
               "objectName='" + objectName + '\'' +
               ", objectId='" + objectId + '\'' +
               ", epoch=" + epoch +
               ", meanMotion=" + meanMotion +
               ", eccentricity=" + eccentricity +
               ", inclination=" + inclination +
               ", raan=" + raan +
               ", argumentPericenter=" + argumentPericenter +
               '}';
    }
}
