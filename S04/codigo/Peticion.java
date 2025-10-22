
public class Peticion implements SequentialFileReader, Comparable<Peticion>{
    private String requestID;
    private String playerID;
    private boolean premiumSubscription;
    private int skillLevel;
    private char matchType;

    public Peticion(){
        
    }

    public Peticion(String requestID, String playerID, boolean premiumSubscription, int skillLevel, char matchType) {
        this.requestID = requestID;
        this.playerID = playerID;
        this.premiumSubscription = premiumSubscription;
        this.skillLevel = skillLevel;
        this.matchType = matchType;
    }

    public int compareTo(Peticion o) {
        int ordenar = 0;
        if (premiumSubscription) {
            ordenar = Integer.compare(o.getSkillLevel(), skillLevel);;
            
        } else {
            ordenar = 0;
        }
        return ordenar; 
    }
 
    public void readData(String[] data){
        this.requestID = data[0];
        this.playerID = data[1];
        this.premiumSubscription= Boolean.parseBoolean(data[2]);
        this.skillLevel = Integer.parseInt(data[3]);
        this.matchType = data[4].charAt(0);

    }

    public String toString() {
        return "Información de cada Petición: [Id Petición: " + requestID + ", Nivel de habilidad: " + skillLevel + ", Premium: " + premiumSubscription+ ", Tipo de partido: "
            + matchType +", ID jugador: " + playerID+ " ]\n";
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public boolean isPremiumSubscription() {
        return premiumSubscription;
    }

    public void setPremiumSubscription(boolean premiumSubscription) {
        this.premiumSubscription = premiumSubscription;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public char getMatchType() {
        return matchType;
    }

    public void setMatchType(char matchType) {
        this.matchType = matchType;
    }


   

}
