/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import context.DBContext;
import entiry.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class CustomerDAO extends DBContext {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Customer getAcount(String user, String pass) {
        String sql = "select * from customer where username=? and password=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Customer(rs.getInt(1), 
                        rs.getString(2), rs.getDouble(3), 
                        rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void signUp(String name,int amount,String username,String password){
        String query = "insert into Customer values (?,?,?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(2, name);
            ps.setInt(3, amount);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Customer checkAccountExist(String name){
        String query = "select * from Customer where username = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Customer(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
}
