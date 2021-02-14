package fr.esgi.wallet.model;

public class Utils {

    public double roundDouble(double number) {
        return (double) Math.round(number * 100) / 100;
    }
}
