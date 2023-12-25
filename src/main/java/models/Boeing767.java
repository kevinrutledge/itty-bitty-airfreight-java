package main.java.models;

/**
 * Represents cargo specific to Boeing 767 aircraft.
 * This class extends the Cargo class and adds specific checks for the maximum weight allowed on a Boeing 767.
 * It maintains a running total of the weight of all Boeing 767 cargo items.
 */
public class Boeing767 extends Cargo {
    // Maximum load capacity for Boeing 767 aircraft in kilograms
    private static final int MAXLOAD767 = 116000;

    // Running total of the weight of all Boeing 767 cargo items
    private static int total767Wt = 0;

    /**
     * Constructs a Boeing767 cargo item with specified details.
     *
     * @param uldType       The type of Unit Load Device (ULD).
     * @param abbreviation  The abbreviated identification of the cargo.
     * @param uldId         The unique identifier of the ULD.
     * @param aircraftType  The type of aircraft intended for transport (should be 767 for this class).
     * @param weight        The weight of the cargo.
     * @param destination   The destination of the cargo.
     */
    public Boeing767(String uldType, String abbreviation, String uldId, int aircraftType, double weight, String destination) {
        super(uldType, abbreviation, uldId, aircraftType, weight, destination);
    }

    /**
     * Checks if adding the current cargo's weight exceeds the maximum load capacity for Boeing 767 aircraft.
     * Throws an exception if the total weight (including this cargo) exceeds the maximum allowed.
     *
     * @param wt The weight of this cargo item.
     * @throws Exception if adding this cargo's weight exceeds the maximum capacity of Boeing 767.
     */
    @Override
    public void checkMaxWeight(double wt) throws Exception {
        if (total767Wt + wt > MAXLOAD767) {
            throw new Exception("Unit " + getUldType() + " not added due to weight restrictions for 767.\n");
        } else {
            total767Wt += wt;
        }
    }

    /**
     * Retrieves the total accumulated weight of all Boeing 767 cargo items.
     *
     * @return The total weight of all loaded Boeing 767 cargo.
     */
    public static int getTotal767Wt() {
        return total767Wt;
    }
}