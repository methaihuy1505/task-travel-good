package controller;

import dao.DestinationDAO;
import model.Destination;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/destinations")
public class DestinationServlet extends HttpServlet {
    private DestinationDAO dao = new DestinationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tripIdParam = request.getParameter("tripId");

        // Nếu không có tripId, quay về trang trips chứ không hiện form lỗi
        if (tripIdParam == null || tripIdParam.isEmpty()) {
            response.sendRedirect("trips");
            return;
        }

        long tripId = Long.parseLong(tripIdParam);
        // Gọi hàm getByTripId (Bạn nhớ thêm hàm này vào DAO như mình hướng dẫn ở turn trước)
        List<Destination> list = dao.getByTripId(tripId);

        request.setAttribute("destinations", list);
        request.setAttribute("currentTripId", tripId);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("locationName");
        int index = Integer.parseInt(request.getParameter("orderIndex"));
        long tripId = Long.parseLong(request.getParameter("tripId"));
        String desc = request.getParameter("description");

        Destination d = new Destination(0, tripId, name, index, desc);
        dao.add(d);

        // Lưu xong quay lại đúng trang destination của Trip đó
        response.sendRedirect("destinations?tripId=" + tripId);
    }
}