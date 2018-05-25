package com.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rest.model.Vehicle;

public class VehicleDAO {
	
	private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

	public  Vehicle getVehicle(String vehicleId){
		String sql = "SELECT vid, vtype, vreg FROM vehicle where vid=?";
        Vehicle v = null;
        try (Connection conn = this.connect();
        		PreparedStatement pstmt = conn.prepareStatement(sql)){
        	
        	pstmt.setString(1,vehicleId);      
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            v=new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3));   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return v;
	}
	
	public Vehicle addVehicle(Vehicle v){
		String sql = "insert into vehicle values(?,?,?)";
		try (Connection conn = this.connect();
               PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, v.getVehicleId());
            pstmt.setString(2, v.getVehicleType());
            pstmt.setString(3, v.getRegNo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return v;
	}
	
	public Vehicle UpdateVehicle(Vehicle v){
		String sql = "UPDATE vehicle SET vtype = ? , "
                + "vregno = ? "
                + "WHERE vid = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, v.getVehicleType());
            pstmt.setString(2, v.getRegNo());
            pstmt.setString(3, v.getVehicleId());
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return v;
	}
	
	public void deleteVehicle(String vehicleId){
		String sql = "DELETE FROM vehicle WHERE vid = ?";
		 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public  List<Vehicle> getAllVehicles(){
        List<Vehicle> list = new ArrayList<Vehicle>();
        Vehicle v ;
        String sql = "SELECT vid, vtype, vreg FROM vehicle";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                v=new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
	}
	
}
