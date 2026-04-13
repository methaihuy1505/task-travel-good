package model;

public class Destination {
    private long id;
    private long tripId;
    private String locationName;
    private int orderIndex;
    private String description;

    public Destination() {}

    public Destination(long id, long tripId, String locationName, int orderIndex, String description) {
        this.id = id;
        this.tripId = tripId;
        this.locationName = locationName;
        this.orderIndex = orderIndex;
        this.description = description;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getTripId() { return tripId; }
    public void setTripId(long tripId) { this.tripId = tripId; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}