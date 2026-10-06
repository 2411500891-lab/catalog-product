package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.exception.ProductNotFoundException;
import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
        productRepository.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Mini Challenge: ambil query parameter ?id=
        String id = request.getParameter("id");
        List<Product> products = null;
        String error = null;

        if (id != null && !id.isBlank()) {
            try {
                products = List.of(productRepository.findById(id));
            } catch (ProductNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                error = e.getMessage();
            }
        } else {
            products = productRepository.findAll();
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Katalog Produk Web</title></head>");
        out.println("<body>");

        if (error != null) {
            out.println("<h2 style='color:red;'>Error 404 - Produk Tidak Ditemukan</h2>");
            out.println("<p style='color:red;'>" + escape(error) + "</p>");
        } else {
            out.println("<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>ID Produk</th><th>Nama Produk</th><th>Harga Satuan</th></tr>");
            for (Product p : products) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>Rp" + p.getPrice() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    // cegah HTML injection dari input user
    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}