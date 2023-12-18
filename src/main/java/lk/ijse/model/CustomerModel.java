package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.InsructorDto;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerModel {

    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        pstm.setString(5,dto.getNIC());
        return pstm.executeUpdate()>0;
    }

    public CustomerDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE c_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        CustomerDto dto = null;
        if (rs.next()){
            String c_id = rs.getString(1);
            String c_name = rs.getString(2);
            String c_address = rs.getString(3);
            String c_contact = rs.getString(4);
            String c_Nic = rs.getString(5);

            dto = new CustomerDto(c_id,c_name,c_address,c_contact,c_Nic);
        }
        return  dto;
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name = ? , address = ? , contact = ?, NIC = ? WHERE c_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getNIC());
        pstm.setString(5, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE c_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public List<CustomerDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<CustomerDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String c_id = rs.getString(1);
            String c_name = rs.getString(2);
            String c_address = rs.getString(3);
            String c_contact = rs.getString(4);
            String c_Nic = rs.getString(5);

            dtoList.add(new CustomerDto(c_id,c_name,c_address,c_contact,c_Nic));
        }
        return dtoList;
    }
}
