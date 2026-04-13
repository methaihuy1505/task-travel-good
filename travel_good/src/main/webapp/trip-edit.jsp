<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Chỉnh sửa Chuyến đi | TravelGood</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Inter', sans-serif;
      background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
      height: 100vh;
      display: flex;
      align-items: center;
    }
    .edit-card {
      border: none;
      border-radius: 15px;
      overflow: hidden;
    }
    .card-header {
      background: #0d6efd;
      padding: 1.5rem;
      border-bottom: none;
    }
    .form-control:focus {
      box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.15);
      border-color: #0d6efd;
    }
    .btn-update {
      padding: 0.6rem 2rem;
      font-weight: 600;
      border-radius: 8px;
      transition: all 0.3s;
    }
    .btn-update:hover {
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(13, 110, 253, 0.3);
    }
    .status-badge {
      font-size: 0.8rem;
      padding: 0.5em 1em;
      border-radius: 50px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-5">
      <div class="card edit-card shadow-lg">
        <div class="card-header text-center">
          <h4 class="text-white mb-0">Chỉnh sửa Chuyến đi</h4>
          <small class="text-white-50">Mã chuyến đi: #${trip.id}</small>
        </div>
        <div class="card-body p-4">
          <form action="trips" method="post">
            <input type="hidden" name="id" value="${trip.id}">

            <div class="mb-4">
              <label class="form-label fw-bold text-dark">Tên chuyến đi mới</label>
              <input type="text" name="tripName" class="form-control form-control-lg"
                     value="${trip.tripName}" placeholder="Nhập tên chuyến đi..." required>
              <div class="mt-2">
                <span class="text-muted small">Trạng thái hiện tại:</span>
                <span class="badge status-badge
                                        ${trip.status == 'PLANNED' ? 'bg-warning text-dark' :
                                          trip.status == 'CANCELLED' ? 'bg-danger text-white' : 'bg-success text-white'}">
                  ${trip.status == 'PLANNED' ? ' Đang kế hoạch' :
                          trip.status == 'CANCELLED' ? ' Đã hủy' : ' Đã đặt'}
                </span>
              </div>
            </div>

            <div class="d-grid gap-2">
              <button type="submit" class="btn btn-primary btn-update">
                 Cập nhật thông tin
              </button>
              <a href="trips" class="btn btn-link text-decoration-none text-muted">
                ← Quay lại danh sách
              </a>
            </div>
          </form>
        </div>
      </div>
      <p class="text-center mt-4 text-secondary small">© 2026 TravelGood System - Huy Mè</p>
    </div>
  </div>
</div>
</body>
</html>