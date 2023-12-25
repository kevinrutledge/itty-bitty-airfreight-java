package main.java.main;

import main.java.utils.*;

/**
 * Main class for the Cargo Management application.
 * This class is the entry point of the application, responsible for initializing
 * the cargo management process.
 */
public class CargoManagement {

    /**
     * Main method to start the application.
     * Invokes the readInput method from CargoUtility to begin processing cargo data.
     *
     * @param args Command-line arguments, not used in this application.
     */
    public static void main(String[] args) {
        CargoUtility.readInput();
    }
}