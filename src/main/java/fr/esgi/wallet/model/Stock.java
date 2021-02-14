package fr.esgi.wallet.model;

import java.util.Objects;

public class Stock {

    private final String type;
    private final double value;

    public String type() { return type; }

    public double value() { return value; }

    public double share() { return share; }

    private final double share;

    public Stock(final String type, final double value, final double share) {
        this.type = type;
        this.value = value;
        this.share = share;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.value, this.share);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Stock)) return false;
        final Stock stock = (Stock) obj;
        return this.type.equals(stock.type) && this.value == stock.value && this.share == stock.share;
    }

    @Override
    public String toString() {
        return "Stock {" +
                " type: " + this.type + "," +
                " value: " + this.value + ',' +
                " share: " + this.share +
                " }";
    }
}
