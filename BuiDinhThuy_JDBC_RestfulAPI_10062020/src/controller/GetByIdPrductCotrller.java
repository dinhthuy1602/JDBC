package controller;

import model.Product;
import service.ProductService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/products")
public class GetByIdPrductCotrller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        String productID = productService.getProductByID(Integer.parseInt(id));
        pw.print(productID);

    }
}
