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


@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        if(id==null || id==""){
            String listProduct = productService.getList();
            pw.print(listProduct);
        }
        else{
            String productID = productService.getProductByID(Integer.parseInt(id));
            pw.print(productID);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter pw = resp.getWriter();
    ProductService productService = new ProductService();
    pw.print(productService.addProduct(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        ProductService productService = new ProductService();
        pw.print(productService.editProduct(req));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        ProductService productService = new ProductService();
        pw.print(productService.deleteProduct(req));
    }
}
