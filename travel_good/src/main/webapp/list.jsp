<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TravelGood - Lộ trình chi tiết</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="trips">Trang chủ</a></li>
            <li class="breadcrumb-item active">Chi tiết lộ trình #${currentTripId}</li>
        </ol>
    </nav>

    <div class="row">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-header bg-info text-white">
                    <h4 class="mb-0">Trình tự các điểm đến</h4>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped mb-0">
                        <thead>
                        <tr>
                            <th width="15%">Thứ tự</th>
                            <th width="35%">Địa danh</th>
                            <th width="50%">Mô tả chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty destinations}">
                            <tr><td colspan="3" class="text-center py-4 text-muted">Chưa có điểm đến nào được thêm vào.</td></tr>
                        </c:if>
                        <c:forEach var="d" items="${destinations}">
                            <tr>
                                <td class="text-center fw-bold"><span class="badge bg-secondary rounded-pill">${d.orderIndex}</span></td>
                                <td>${d.locationName}</td>
                                <td>${d.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow border-primary">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">Thêm điểm dừng mới</h5>
                </div>
                <div class="card-body">
                    <form action="destinations" method="post">
                        <input type="hidden" name="tripId" value="${currentTripId}">

                        <div class="mb-3">
                            <label class="form-label text-secondary small fw-bold">Tên địa danh</label>
                            <input type="text" name="locationName" class="form-control" placeholder="VD: Bến Ninh Kiều" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-secondary small fw-bold">Thứ tự trong chuyến đi</label>
                            <input type="number" name="orderIndex" class="form-control" placeholder="1, 2, 3..." required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-secondary small fw-bold">Ghi chú/Mô tả</label>
                            <textarea name="description" class="form-control" rows="3" placeholder="Ăn gì, chơi gì ở đây?"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Lưu vào lộ trình</button>
                    </form>
                </div>
            </div>
            <div class="mt-3">
                <a href="trips" class="text-decoration-none">← Quay lại danh sách Chuyến đi</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>