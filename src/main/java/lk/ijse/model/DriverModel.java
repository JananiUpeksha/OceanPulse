package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.DriverDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverModel {

    public boolean saveDriver(DriverDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO driver VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        pstm.setString(5,dto.getExperience());
        return pstm.executeUpdate()>0;
    }

    public boolean updateDriver(DriverDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE driver SET name = ? , address = ? , contact = ?, experience = ? WHERE d_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getExperience());
        pstm.setString(5, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteDriver(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM driver WHERE d_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public DriverDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM driver WHERE d_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        DriverDto dto = null;
        if (rs.next()){
            String d_id = rs.getString(1);
            String d_name = rs.getString(2);
            String d_contact = rs.getString(3);
            String d_address = rs.getString(4);
            String d_experience = rs.getString(5);

            dto = new DriverDto(d_id,d_name,d_address,d_contact,d_experience);
        }
        return  dto;
    }

    public List<DriverDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM driver";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        //emty arraylist ekak hdagnnwa dto objects save krgnna
        List<DriverDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String d_id = rs.getString(1);
            String d_name = rs.getString(2);
            String d_contact = rs.getString(3);
            String d_address = rs.getString(4);
            String d_experience = rs.getString(5);

            dtoList.add(new DriverDto(d_id,d_name,d_contact,d_address,d_experience));
        }
        return dtoList; //arraylist athuwa list ekakin return klma list interface eke one ekak ekka wda krnna pluwn

    }
}
