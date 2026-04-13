<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>TravelGood - Quản lý Chuyến đi</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h2 class="mb-0">Danh sách Chuyến đi của tôi</h2>
    </div>
    <div class="card-body">
      <form action="trips" method="post" class="row g-3 mb-4">
        <div class="col-auto">
          <input type="text" name="tripName" class="form-control" placeholder="Tên chuyến đi mới (VD: Đi Đà Lạt)" required>
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-success">Tạo Chuyến đi</button>
        </div>
      </form>

      <table class="table table-hover border">
        <thead class="table-dark">
        <tr>
          <th>Mã số</th>
          <th>Tên Chuyến đi</th>
          <th>Trạng thái</th>
          <th class="text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${trips}">
          <tr>
            <td>#${t.id}</td>
            <td class="fw-bold text-primary">${t.tripName}</td>
            <td>
                                    <span class="badge ${t.status == 'PLANNED' ? 'bg-warning' : 'bg-info'} text-dark">
                                        ${t.status == 'PLANNED' ? 'Đang lập kế hoạch' : t.status}
                                    </span>
            </td>
            <td class="text-center">
              <a href="destinations?tripId=${t.id}" class="btn btn-sm btn-outline-primary"> Lộ trình</a>
              <a href="trips?action=edit&id=${t.id}" class="btn btn-sm btn-warning"> Sửa</a>

              <c:choose>
                <c:when test="${t.status == 'CANCELLED'}">
                  <a href="trips?action=toggle&id=${t.id}" class="btn btn-sm btn-success">
                    Hoàn tác
                  </a>
                </c:when>
                <c:otherwise>
                  <a href="trips?action=toggle&id=${t.id}" class="btn btn-sm btn-outline-secondary"
                     onclick="return confirm('Bạn có chắc muốn hủy chuyến đi này?')">
                    Hủy
                  </a>
                </c:otherwise>
              </c:choose>

              <a href="trips?action=delete&id=${t.id}" class="btn btn-sm btn-danger"
                 onclick="return confirm('Xóa vĩnh viễn?')">Xóa</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>