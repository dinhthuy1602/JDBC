package service;

import dao.ProductDAO;
import model.Product;
import ReadJSON.ConvertByJSON;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class ProductService {
    public String getList(){
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getList();

        Gson gs = new Gson();
        String listProductJson = gs.toJson(productList);
        return listProductJson;
    }
    public String getProductByID(int id){
        ProductDAO productDAO = new ProductDAO();
        String getByIDJson = null;
        if(productDAO.checkID(id)){
            Product getByID = productDAO.getProductByID(id);
            Gson gs = new Gson();
            getByIDJson = gs.toJson(getByID);
        }
        else{
            getByIDJson = "Không tồn tại";
        }
        return  getByIDJson;
    }

    public String addProduct(HttpServletRequest request) throws IOException{
        ConvertByJSON convertByJSON = new ConvertByJSON();
        ProductDAO productDAO = new ProductDAO();

        String jsonProduct = convertByJSON.getBody(request);
        String mess = null;

        Gson gs = new Gson();
        Product product = gs.fromJson(jsonProduct, Product.class);

            if(productDAO.addProduct(product)){
                mess = "Thành Công";
            }
            else mess ="Thất Bại";
        return mess;
    }
    public String editProduct(HttpServletRequest request) throws IOException{
        ConvertByJSON convertByJSON = new ConvertByJSON();
        ProductDAO productDAO = new ProductDAO();

        String jsonProduct = convertByJSON.getBody(request);
        String mess;

        Gson gs= new Gson();
        Product product = gs.fromJson(jsonProduct,Product.class);
       if(productDAO.editProduct(product)){
           return  mess = "Thành Công";
       }
       else return  mess = "Thất bại";
    }

    public String deleteProduct(HttpServletRequest request) throws IOException{
        ConvertByJSON convertByJSON = new ConvertByJSON();
        ProductDAO productDAO = new ProductDAO();

        String jsonProduct = convertByJSON.getBody(request);
        String mess;

        Gson gs= new Gson();
        Product product = gs.fromJson(jsonProduct,Product.class);

        if(productDAO.checkID(product.getId())){
            if(productDAO.deleteProduct(product)){
               return mess="Delete Success";
            }
            else return mess="Delete error";
        }
        else return  mess="ID is not in DB";
    }
}
