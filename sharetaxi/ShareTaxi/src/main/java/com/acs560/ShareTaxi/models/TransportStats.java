package com.acs560.ShareTaxi.models;

public class TransportStats {
    private String transportMode;
    private int count;

    public TransportStats() {}

    public TransportStats(String transportMode, int count) {
        this.transportMode = transportMode;
        this.count = count;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
