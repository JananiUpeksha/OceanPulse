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

public class BoatModel {
    public boolean saveBoat(BoatDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO boat VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getCapacity());
        pstm.setString(3,dto.getStatus());
        pstm.setString(4,dto.getModel());
        return pstm.executeUpdate()>0;
    }

    public static BoatDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM boat WHERE b_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        BoatDto dto = null;
        if (rs.next()){
            String b_id = rs.getString(1);
            String b_capacity = rs.getString(2);
            String b_status = rs.getString(3);
            String b_model = rs.getString(4);

            dto = new BoatDto(b_id,b_capacity,b_status,b_model);
        }
        return  dto;
    }

    public boolean updateBoat(BoatDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE boat SET capacity = ? , status = ? , model = ? WHERE b_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getCapacity());
        pstm.setString(2, dto.getStatus());
        pstm.setString(3, dto.getModel());
        pstm.setString(4, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteBoat(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM boat WHERE b_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public static List<BoatDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM boat";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<BoatDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String b_id = rs.getString(1);
            String b_capacity = rs.getString(2);
            String b_status = rs.getString(3);
            String b_model = rs.getString(4);

            dtoList.add(new BoatDto(b_id,b_capacity,b_status,b_model));
        }
        return dtoList;
    }
}
