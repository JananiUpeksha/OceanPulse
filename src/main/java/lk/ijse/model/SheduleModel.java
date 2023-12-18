package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.ScheduleDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheduleModel {
    public static List<String> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT s_id FROM schedule ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<String> id = new ArrayList<>();

        while (rs.next()){
            id.add(rs.getString(1));
        }
        return id;

    }

    public static List<ScheduleDto> getAllShedule() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM schedule";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<ScheduleDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String id = rs.getString(1);
            Timestamp  date = Timestamp.valueOf(rs.getString(2));
            String boat_id = rs.getString(3);
            String i_id = rs.getString(4);

            dtoList.add(new ScheduleDto(id,date,boat_id,i_id));
        }
        return dtoList;
    }

    public boolean saveSchedule(ScheduleDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO schedule VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setTimestamp(2, Timestamp.valueOf(String.valueOf(dto.getEventDateTime())));
        pstm.setString(3,dto.getB_id());
        pstm.setString(4,dto.getIns_id());

        return pstm.executeUpdate()>0;
    }
}
