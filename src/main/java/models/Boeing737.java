package main.java.models;

/**
 * Represents cargo specific to Boeing 737 aircraft.
 * This class extends the Cargo class and adds specific checks for the maximum weight allowed on a Boeing 737.
 * It maintains a running total of the weight of all Boeing 737 cargo items.
 */
public class Boeing737 extends Cargo {
    // Maximum load capacity for Boeing 737 aircraft in kilograms
    private static final int MAXLOAD737 = 46000;

    // Running total of the weight of all Boeing 737 cargo items
    private static int total737Wt = 0;

    /**
     * Constructs a Boeing737 cargo item with specified details.
     *
     * @param uldType       The type of Unit Load Device (ULD).
     * @param abbreviation  The abbreviated identification of the cargo.
     * @param uldId         The unique identifier of the ULD.
     * @param aircraftType  The type of aircraft intended for transport (should be 737 for this class).
     * @param weight        The weight of the cargo.
     * @param destination   The destination of the cargo.
     */
    public Boeing737(String uldType, String abbreviation, String uldId, int aircraftType, double weight, String destination) {
        super(uldType, abbreviation, uldId, aircraftType, weight, destination);
    }

    /**
     * Checks if adding the current cargo's weight exceeds the maximum load capacity for Boeing 737 aircraft.
     * Throws an exception if the total weight (including this cargo) exceeds the maximum allowed.
     *
     * @param wt The weight of this cargo item.
     * @throws Exception if adding this cargo's weight exceeds the maximum capacity of Boeing 737.
     */
    @Override
    public void checkMaxWeight(double wt) throws Exception {
        if (total737Wt + wt > MAXLOAD737) {
            throw new Exception("Unit " + getUldType() + " not added due to weight restrictions for 737.\n");
        } else {
            total737Wt += wt;
        }
    }

    /**
     * Retrieves the total accumulated weight of all Boeing 737 cargo items.
     *
     * @return The total weight of all loaded Boeing 737 cargo.
     */
    public static int getTotal737Wt() {
        return total737Wt;
    }
}