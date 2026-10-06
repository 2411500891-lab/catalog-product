package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private List<Category> categoryList;

    @Override
    public void init() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("CAT-01", "Keyboard"));
        categoryList.add(new Category("CAT-02", "Mouse"));
        categoryList.add(new Category("CAT-03", "Monitor"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Daftar Kategori</title></head>");
        out.println("<body>");
        out.println("<h2>=== DAFTAR KATEGORI PRODUK ===</h2>");
        out.println("<ul>");
        for (Category c : categoryList) {
            out.println("<li>" + c.getId() + " - " + c.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}