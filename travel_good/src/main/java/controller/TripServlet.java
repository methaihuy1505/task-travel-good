package controller;

import dao.TripDAO;
import model.Trip;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/trips")
public class TripServlet extends HttpServlet {
    private TripDAO tripDAO = new TripDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            long id = Long.parseLong(request.getParameter("id"));
            tripDAO.delete(id);
            response.sendRedirect("trips");
            return;
        }

        if ("toggle".equals(action)) {
            long id = Long.parseLong(request.getParameter("id"));
            tripDAO.toggleStatus(id);
            response.sendRedirect("trips");
            return;
        }

        if ("edit".equals(action)) {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("trip", tripDAO.getById(id));
            request.getRequestDispatcher("trip-edit.jsp").forward(request, response);
            return;
        }

        request.setAttribute("trips", tripDAO.getAll());
        request.getRequestDispatcher("trip-list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String tripIdStr = request.getParameter("id");

        if (tripIdStr != null && !tripIdStr.isEmpty()) {
            // Chỉ cập nhật Tên theo yêu cầu mới
            long id = Long.parseLong(tripIdStr);
            String name = request.getParameter("tripName");
            tripDAO.updateName(id, name);
        } else {
            String name = request.getParameter("tripName");
            tripDAO.add(new Trip(0, 1, name, "PLANNED"));
        }
        response.sendRedirect("trips");
    }


}