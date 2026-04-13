package model;

import java.sql.Timestamp;

public class Trip {
    private long id;
    private long userId;
    private String tripName;
    private String status;
    private Timestamp createdAt;

    public Trip() {}
    public Trip(long id, long userId, String tripName, String status) {
        this.id = id;
        this.userId = userId;
        this.tripName = tripName;
        this.status = status;
    }

    // Getters & Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getTripName() { return tripName; }
    public void setTripName(String tripName) { this.tripName = tripName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}