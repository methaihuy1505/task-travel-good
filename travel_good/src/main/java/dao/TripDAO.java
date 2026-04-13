package dao;

import model.Trip;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    public List<Trip> getAll() {
        List<Trip> list = new ArrayList<>();
        String sql = "SELECT * FROM trips ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Trip(rs.getLong("id"), rs.getLong("user_id"),
                        rs.getString("trip_name"), rs.getString("status")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void add(Trip trip) {
        String sql = "INSERT INTO trips (user_id, trip_name, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, trip.getUserId());
            ps.setString(2, trip.getTripName());
            ps.setString(3, "PLANNED"); // Mặc định khi tạo mới
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(long id) {
        String sql = "DELETE FROM trips WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Cập nhật tên chuyến đi
    public void updateName(long id, String newName) {
        String sql = "UPDATE trips SET trip_name = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void toggleStatus(long id) {
        // Truy vấn trạng thái hiện tại trước
        String currentStatus = "";
        String getSql = "SELECT status FROM trips WHERE id = ?";
        String updateSql = "UPDATE trips SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Lấy trạng thái hiện tại
            try (PreparedStatement ps = conn.prepareStatement(getSql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) currentStatus = rs.getString("status");
            }

            // 2. Đảo trạng thái: Nếu đang CANCELLED -> PLANNED, ngược lại -> CANCELLED
            String newStatus = "CANCELLED".equals(currentStatus) ? "PLANNED" : "CANCELLED";

            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setString(1, newStatus);
                ps.setLong(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Trip getById(long id) {
        String sql = "SELECT * FROM trips WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Trip(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("trip_name"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}