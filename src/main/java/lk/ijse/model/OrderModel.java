package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lombok.*;

import java.sql.*;
import java.time.LocalDate;


public class OrderModel {


 /*   public static String generateNextOrderId() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "SELECT o_id FROM orders ORDER BY o_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }*/

    public static boolean saveOrder(String o_Id, LocalDate date, int qty, String c_id, String s_id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, o_Id);
        pstm.setDate(2, Date.valueOf(date));
        pstm.setInt(3,qty);
        pstm.setString(4,c_id);
        pstm.setString(5,s_id);

        return pstm.executeUpdate() > 0;
    }

    public static String generateNextOrderId() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT o_id FROM orders ORDER BY o_id DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return getNextId(  resultSet.getString(1));
        }
        return getNextId(null);


    }

    private static String getNextId(String currentId) {
        if (currentId!= null){
            String numericPart = currentId.substring(1);
            int numericValue = Integer.parseInt(numericPart);
            numericValue++;
            String nextStockId = String.format("R%03d", numericValue);
            return nextStockId;
        }
        else {
            return "R001";
        }
    }


    public boolean saveOrderDetails(String id, String iId) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "INSERT INTO order_manage_details VALUES(?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);
        pstm.setString(2, iId);



        return pstm.executeUpdate() > 0;
    }
}
