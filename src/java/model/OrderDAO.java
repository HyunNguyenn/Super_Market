/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import context.DBContext;
import entiry.Cart;
import entiry.Customer;
import entiry.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class OrderDAO extends DBContext {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(Customer u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String query1 = "insert into [Order] values (?,?,?)";
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setString(1, date);
            ps.setInt(2,u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();
            String query2 = "select top 1 id from [Order] order by id desc";
            con = new DBContext().getConnection();
            PreparedStatement ps1 = con.prepareStatement(query2);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String query3 = "insert into [orderline] values (?,?,?,?)";
                    con = new DBContext().getConnection();
                    PreparedStatement ps2 = con.prepareStatement(query3);
                    ps2.setInt(1, oid);
                    ps2.setInt(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setDouble(4, i.getPrice());
                    ps2.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
