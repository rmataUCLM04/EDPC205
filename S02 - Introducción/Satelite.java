import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Satelite implements SequentialFileReader{
private String objectName;
private String objectId;
private LocalDateTime epoch;
private double meanMotion;
private double eccentricity;
private double inclination;
private double rean; 
private double argumentPericenter;
public Satelite(String objectName, String objectId, LocalDateTime epoch, double meanMotion, double eccentricity,
        double inclination, double rean, double argumentPericenter) {
    this.objectName = objectName;
    this.objectId = objectId;
    this.epoch = epoch;
    this.meanMotion = meanMotion;
    this.eccentricity = eccentricity;
    this.inclination = inclination;
    this.rean = rean;
    this.argumentPericenter = argumentPericenter;
}
public String getObjectName() {
    return objectName;
}
public void setObjectName(String objectName) {
    this.objectName = objectName;
}
public String getObjectId() {
    return objectId;
}
public void setObjectId(String objectId) {
    this.objectId = objectId;
}
public LocalDateTime getEpoch() {
    return epoch;
}
public void setEpoch(LocalDateTime epoch) {
    this.epoch = epoch;
}
public double getMeanMotion() {
    return meanMotion;
}
public void setMeanMotion(double meanMotion) {
    this.meanMotion = meanMotion;
}
public double getEccentricity() {
    return eccentricity;
}
public void setEccentricity(double eccentricity) {
    this.eccentricity = eccentricity;
}
public double getInclination() {
    return inclination;
}
public void setInclination(double inclination) {
    this.inclination = inclination;
}
public double getRean() {
    return rean;
}
public void setRean(double rean) {
    this.rean = rean;
}
public double getArgumentPericenter() {
    return argumentPericenter;
}
public void setArgumentPericenter(double argumentPericenter) {
    this.argumentPericenter = argumentPericenter;
}
public void readData(String[] data){
    this.objectName =data[0];
    this.objectId = data[1];
    this.epoch = LocalDateTime.parse(data[2]);
    this.meanMotion = Double.valueOf(data[3]);
    this.eccentricity = Double.valueOf(data[4]);
    this.inclination = Double.valueOf(data[5]);
    this.rean = Double.valueOf(data[6]);
    this.argumentPericenter = Double.valueOf(data[7]);

}
@Override
public String toString() {
    return "Satelite [objectName=" + objectName + ", objectId=" + objectId + ", epoch=" + epoch + ", meanMotion="
            + meanMotion + ", eccentricity=" + eccentricity + ", inclination=" + inclination + ", rean=" + rean
            + ", argumentPericenter=" + argumentPericenter + "]";
}


}