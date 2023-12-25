package main.java.models;

/**
 * Represents the abstract concept of cargo intended for air transport.
 * This class serves as a base for specific types of cargo, providing common properties
 * and enforcing the implementation of cargo-specific weight checks.
 */
public abstract class Cargo {
    // Cargo properties
    protected String uldType;
    protected String abbreviation;
    protected String uldId;
    protected int aircraftType;
    protected double weight;
    protected String destination;

    /**
     * Default constructor that initializes cargo with default placeholder values.
     * Useful when actual cargo details are not yet known or for testing purposes.
     */
    public Cargo() {
        this.uldType = "XXX";
        this.abbreviation = " ";
        this.uldId = "xxxxxIB";
        this.aircraftType = 700;
        this.weight = 0.0;
        this.destination = "NONE";
    }

    /**
     * Constructs a cargo object with specified details.
     *
     * @param uldType       The type of Unit Load Device (ULD).
     * @param abbreviation  The abbreviated identification of the cargo.
     * @param uldId         The unique identifier of the ULD.
     * @param aircraftType  The type of aircraft intended for transport.
     * @param weight        The weight of the cargo.
     * @param destination   The destination of the cargo.
     */
    public Cargo(String uldType, String abbreviation, String uldId, int aircraftType, double weight, String destination) {
        this.uldType = uldType;
        this.abbreviation = abbreviation;
        this.uldId = uldId;
        this.aircraftType = aircraftType;
        this.weight = weight;
        this.destination = destination;
    }

    /**
     * Copy constructor for creating a new cargo object with the same properties as another cargo object.
     *
     * @param other Another Cargo object to copy properties from.
     */
    public Cargo(Cargo other) {
        this.uldType = other.uldType;
        this.abbreviation = other.abbreviation;
        this.uldId = other.uldId;
        this.aircraftType = other.aircraftType;
        this.weight = other.weight;
        this.destination = other.destination;
    }

    // Getters
    public String getUldType() {
        return uldType;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getUldId() {
        return uldId;
    }

    public int getAircraftType() {
        return aircraftType;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestination() {
        return destination;
    }

    // Setters
    public void setUldType(String uldType) {
        this.uldType = uldType;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setUldId(String uldId) {
        this.uldId = uldId;
    }

    public void setAircraftType(int aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Abstract method to check whether the cargo's weight exceeds the maximum allowed weight.
     * This method must be implemented by derived classes to enforce specific weight restrictions.
     *
     * @param wt The weight to check against the maximum allowed weight.
     * @throws Exception if the cargo's weight exceeds the maximum allowed weight.
     */
    public abstract void checkMaxWeight(double wt) throws Exception;

    /**
     * Outputs the cargo details in a formatted manner.
     * Prints the cargo properties in a human-readable format.
     */
    public void output() {
        System.out.printf("%-20s %-16d %-16s %-20f %-16s\n", uldType, aircraftType, uldId, weight, destination);
    }
}