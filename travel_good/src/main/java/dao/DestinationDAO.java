package dao;

import model.Destination;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {

    // READ: Lấy danh sách điểm đến sắp xếp theo trình tự
    public List<Destination> getAll() {
        List<Destination> list = new ArrayList<>();
        String sql = "SELECT * FROM destinations ORDER BY order_index ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Destination(
                        rs.getLong("id"),
                        rs.getLong("trip_id"),
                        rs.getString("location_name"),
                        rs.getInt("order_index"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // CREATE: Thêm một điểm đến mới vào hành trình
    public void add(Destination dest) {
        String sql = "INSERT INTO destinations (trip_id, location_name, order_index, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, dest.getTripId());
            ps.setString(2, dest.getLocationName());
            ps.setInt(3, dest.getOrderIndex());
            ps.setString(4, dest.getDescription());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Thêm hàm này vào DestinationDAO.java
    public List<Destination> getByTripId(long tripId) {
        List<Destination> list = new ArrayList<>();
        String sql = "SELECT * FROM destinations WHERE trip_id = ? ORDER BY order_index ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, tripId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Destination(
                        rs.getLong("id"), rs.getLong("trip_id"),
                        rs.getString("location_name"), rs.getInt("order_index"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}