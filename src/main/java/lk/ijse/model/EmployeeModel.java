package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.DriverDto;
import lk.ijse.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO employee VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        return pstm.executeUpdate()>0;
    }

    public EmployeeDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE e_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        EmployeeDto dto = null;
        if (rs.next()){
            String e_id = rs.getString(1);
            String e_name = rs.getString(2);
            String e_address = rs.getString(3);
            String e_contact = rs.getString(4);

            dto = new EmployeeDto(e_id,e_name,e_address,e_contact);
        }
        return  dto;
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE employee SET name = ? , address = ? , contact = ? WHERE e_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM employee WHERE e_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public List<EmployeeDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<EmployeeDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String e_id = rs.getString(1);
            String e_name = rs.getString(2);
            String e_address = rs.getString(3);
            String e_contact = rs.getString(4);

            dtoList.add(new EmployeeDto(e_id,e_name,e_address,e_contact));
        }
        return dtoList;
    }
}
