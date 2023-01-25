package edu.miu.mpp.librarysystem.dao.model;

public enum MaxBookCheckout {
    SEVEN_DAYS(7),
    TWENTY_ONE_DAYS(21);

    private final int days;

    MaxBookCheckout(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
