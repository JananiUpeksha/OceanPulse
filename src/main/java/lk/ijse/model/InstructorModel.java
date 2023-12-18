package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.DriverDto;
import lk.ijse.dto.InsructorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorModel {
    public boolean saveInstructor(InsructorDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO instructor VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        pstm.setString(5,dto.getQualification());
        return pstm.executeUpdate()>0;
    }

    public boolean updateInstructor(InsructorDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE instructor SET name = ? , address = ? , contact = ?, qualification = ? WHERE ins_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getQualification());
        pstm.setString(5, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public static InsructorDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM instructor WHERE ins_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        InsructorDto dto = null;
        if (rs.next()){
            String ins_id = rs.getString(1);
            String ins_name = rs.getString(2);
            String ins_address = rs.getString(3);
            String ins_contact = rs.getString(4);
            String ins_qualification = rs.getString(5);

            dto = new InsructorDto(ins_id,ins_name,ins_address,ins_contact,ins_qualification);
        }
        return  dto;
    }

    public boolean deleteInstructor(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM instructor WHERE ins_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public static List<InsructorDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM instructor";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<InsructorDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String i_id = rs.getString(1);
            String i_name = rs.getString(2);
            String i_address = rs.getString(3);
            String i_contact = rs.getString(4);
            String i_qualification = rs.getString(5);

            dtoList.add(new InsructorDto(i_id,i_name,i_address,i_contact,i_qualification));
        }
        return dtoList;
    }
}
