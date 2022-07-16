package com.fun.model;

public record SettledAccount(String id, String name, String product, String notes, String status) {
    private final static String DELIMITER = ",";
    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id()).append(DELIMITER)
                .append(this.name()).append(DELIMITER)
                .append(this.product()).append(DELIMITER)
                .append(this.notes()).append(DELIMITER)
                .append(this.status())
                .toString();
    }
}
