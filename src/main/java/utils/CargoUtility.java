package main.java.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import main.java.models.*;

/**
 * Provides utility methods for reading input data, validating cargo information,
 * loading cargo onto specific aircraft types, and printing cargo details.
 * This class centralizes common operations used across the cargo management system.
 */
public class CargoUtility {

    /**
     * Reads cargo data from a user-specified file, processes it, and prints the cargo details.
     * The method prompts the user for the file name, reads the file, and processes each line to load cargo
     * into the respective Boeing 737 or 767 lists. It handles potential errors during file reading and data processing.
     */
    public static void readInput() {
        String type, abrv, id, dest;
        int air;
        double wt;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the name of your data file (located in /src):");
        String datafile = scanner.nextLine();

        File inputFile = new File(datafile);
        List<Boeing737> list737 = new LinkedList<>();
        List<Boeing767> list767 = new LinkedList<>();
        int unitSequence = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length != 6) continue;

                type = parts[0];
                abrv = parts[1];
                id = parts[2];
                air = Integer.parseInt(parts[3]);
                wt = Double.parseDouble(parts[4]);
                dest = parts[5];

                try {
                    unitSequence++;
                    checkUldType(type);
                    checkPlaneType(air);
                    checkAbbreviation(abrv, air);

                    if (air == 737) {
                        load737(list737, type, abrv, id, air, wt, dest);
                    } else if (air == 767) {
                        load767(list767, type, abrv, id, air, wt, dest);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        printCargo(list737, list767);
    }

    /**
     * Validates the aircraft type.
     *
     * @param aircraftType The type of the aircraft.
     * @throws Exception if the aircraft type is not 737 or 767.
     */
    public static void checkPlaneType(int aircraftType) throws Exception {
        if (aircraftType != 737 && aircraftType != 767) {
            throw new Exception(aircraftType + " bad airplane type");
        }
    }

    /**
     * Validates the ULD (Unit Load Device) type.
     *
     * @param type The ULD type to check.
     * @throws Exception if the ULD type is neither "Container" nor "Pallet".
     */
    public static void checkUldType(String type) throws Exception {
        if (!type.equals("Container") && !type.equals("Pallet")) {
            throw new Exception(type + " not Container or Pallet, rejected load.");
        }
    }

    /**
     * Checks if the abbreviation is valid for the given aircraft type.
     *
     * @param abrv The abbreviation to check.
     * @param aircraftType The type of the aircraft (737 or 767).
     * @throws Exception if the abbreviation is not compatible with the specified aircraft type.
     */
    public static void checkAbbreviation(String abrv, int aircraftType) throws Exception {
        if (aircraftType == 737) {
            if (!(abrv.equals("AYF") || abrv.equals("AYK") || abrv.equals("AAA") ||
                    abrv.equals("AYY") || abrv.equals("PAG") || abrv.equals("PMC") ||
                    abrv.equals("PLA"))) {
                throw new Exception("The " + abrv + " container is not compatible with the 737 aircraft.\n");
            }
        } else if (aircraftType == 767) {
            if (!(abrv.equals("AKE") || abrv.equals("APE") || abrv.equals("AKC") ||
                    abrv.equals("AQP") || abrv.equals("AQF") || abrv.equals("AAP") ||
                    abrv.equals("P1P") || abrv.equals("P6P"))) {
                throw new Exception("The " + abrv + " container is not compatible with the 767 aircraft.\n");
            }
        } else {
            throw new Exception("Invalid aircraft type: " + aircraftType);
        }
    }

    /**
     * Loads a Boeing737 cargo item onto the list.
     *
     * @param list737      The list of Boeing737 cargo.
     * @param type         The ULD type of the cargo.
     * @param abrv         The abbreviation of the cargo.
     * @param id           The unique identifier of the ULD.
     * @param air          The type of aircraft (should be 737).
     * @param wt           The weight of the cargo.
     * @param dest         The destination of the cargo.
     * @throws Exception if the cargo exceeds the maximum weight capacity of the aircraft.
     */
    public static void load737(List<Boeing737> list737, String type, String abrv, String id, int air, double wt, String dest) throws Exception {
        Boeing737 unitObj737 = new Boeing737(type, abrv, id, air, wt, dest);
        unitObj737.checkMaxWeight(wt);
        list737.add(unitObj737);
    }

    /**
     * Loads a Boeing767 cargo item onto the list.
     *
     * @param list767      The list of Boeing767 cargo.
     * @param type         The ULD type of the cargo.
     * @param abrv         The abbreviation of the cargo.
     * @param id           The unique identifier of the ULD.
     * @param air          The type of aircraft (should be 767).
     * @param wt           The weight of the cargo.
     * @param dest         The destination of the cargo.
     * @throws Exception if the cargo exceeds the maximum weight capacity of the aircraft.
     */
    public static void load767(List<Boeing767> list767, String type, String abrv, String id, int air, double wt, String dest) throws Exception {
        Boeing767 unitObj767 = new Boeing767(type, abrv, id, air, wt, dest);
        unitObj767.checkMaxWeight(wt);
        list767.add(unitObj767);
    }

    /**
     * Prints the details of all cargo items in the provided Boeing 737 and 767 lists.
     *
     * @param list737 The list of Boeing737 cargo.
     * @param list767 The list of Boeing767 cargo.
     */
    public static void printCargo(List<Boeing737> list737, List<Boeing767> list767) {
        printHeader("737", "IBA0123");
        for (Cargo cargo : list737) {
            cargo.output();
        }
        printHeader("767", "IBA0456");
        for (Cargo cargo : list767) {
            cargo.output();
        }
    }

    /**
     * Prints a header for the cargo details display.
     *
     * @param aircraftType The type of the aircraft.
     * @param aircraftId   The identifier of the aircraft.
     */
    private static void printHeader(String aircraftType, String aircraftId) {
        System.out.println("\nLoad out of " + aircraftType + " aircraft " + aircraftId);
        System.out.printf("%-20s %-16s %-16s %-20s %-16s\n", "Pallet/Container", "Aircraft Type", "Identifier", "Weight (pounds)", "Destination");
    }
}