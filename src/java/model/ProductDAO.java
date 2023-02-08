/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import context.DBContext;
import entiry.Product;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ProductDAO extends DBContext {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Product p = new Product(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    public Product getProductByID(int id){
        String query = "select * from Product where id = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(id,
                        rs.getString("name"), rs.getString("image"),
                        rs.getInt("price"), rs.getInt("quantity"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void insertProduct(String name,String image,float price,int quantity){
        String query = "insert into Product values (?,?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setFloat(3, price);
            ps.setInt(4, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteProduct(String name){
        String query = "delete from Product where name=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void updateProduct(String image,float price,String name) {
        String query = "update Product set image=?, price=? where name=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, image);
            ps.setDouble(2, price);
            ps.setString(3, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
}
