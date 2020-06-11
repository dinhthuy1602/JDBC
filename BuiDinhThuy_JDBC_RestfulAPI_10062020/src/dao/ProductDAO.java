package dao;

import model.Product;

import javax.xml.transform.Result;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    public boolean checkID(int id){
        try{
            String sql = "select * from products where id = "+id+";";
            Statement stm = ConnectDB.getConnect().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                return  true;
            }
            else{
                return  false;
            }
        }catch (SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkCondition(Product product){
        if(product.getName()==null || product.getBrand()==null||product.getMadein()==null ){
            return false;
        }
        return true;
    }
    public List<Product> getList(){
        List<Product> listProduct = new ArrayList<Product>();;
        try {
            String sql = "select * from products";
            Statement stm = ConnectDB.getConnect().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                Product product = new Product(rs.getInt("id"),rs.getString("name"),rs.getString("brand"),rs.getString("madein"),rs.getFloat("price"));
                listProduct.add(product);
            }
        }catch (SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public Product getProductByID(int id){
        Product product = new Product();
        try{
            String sql = "select * from products where id =" + id + ";";
            Statement stm = ConnectDB.getConnect().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            product = new Product(rs.getInt("id"),rs.getString("name"),rs.getString("brand"),rs.getString("madein"),rs.getFloat("price"));
//            product.setId(rs.getInt("id"));
//            product.setName(rs.getString("name"));
//            product.setBrand(rs.getString("brand"));
//            product.setMadein(rs.getString("madein"));
//            product.setPrice(rs.getFloat("price"));
        }catch(SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    public boolean addProduct(Product product){
        try{
            String sql = "insert into products(name , brand , madein, price) values(? , ? , ?, ?)";
            PreparedStatement preparedStatement = ConnectDB.getConnect().prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setString(3, product.getMadein());
            preparedStatement.setFloat(4, product.getPrice());
            preparedStatement.execute();
            return  true;
        }catch (SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean editProduct(Product product){
        try{
            String sql = "update products set name=? , brand =?, madein= ?, price= ?";
            PreparedStatement preparedStatement = ConnectDB.getConnect().prepareStatement(sql); //truy vấn có tham só
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setString(3, product.getMadein());
            preparedStatement.setFloat(4, product.getPrice());
            preparedStatement.execute();

            return true;
        }catch (SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProduct(Product product){
        try{
            String sql = "delete from products where id = ?";
            PreparedStatement preparedStatement = ConnectDB.getConnect().prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
            return true;
        }catch (SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
