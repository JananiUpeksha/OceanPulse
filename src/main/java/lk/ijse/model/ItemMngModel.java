package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.AddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMngModel {
    public static boolean UpdateItem(int qty, List<AddTm> list) throws SQLException {
        for(AddTm tm : list) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getId(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public static boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "UPDATE item SET qtyOnHand= qtyOnHand -? WHERE i_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0;
    }

    public boolean saveItem(ItemDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();;
        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getBrand());
        pstm.setString(4,dto.getSize());
        pstm.setDouble(5,dto.getPrice());
        pstm.setInt(6,dto.getQty());
        return pstm.executeUpdate()>0;
    }

    public boolean updateItem(ItemDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE item SET name = ? , brand = ? , size = ?, price = ? ,qtyOnHand = ? WHERE i_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getBrand());
        pstm.setString(3, dto.getSize());
        pstm.setDouble(4, dto.getPrice());
        pstm.setInt(5, dto.getQty());
        pstm.setString(6, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public static ItemDto SearchAll(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE i_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        ItemDto dto = null;
        if (rs.next()){
            String i_id = rs.getString(1);
            String i_name = rs.getString(2);
            String i_brand = rs.getString(3);
            String i_size = rs.getString(4);
            String i_price = rs.getString(5);
            String i_qty = rs.getString(6);

            dto = new ItemDto(i_id,i_name,i_brand,i_size, Double.parseDouble(i_price), Integer.parseInt(i_qty));
        }
        return  dto;
    }

    public boolean deleteItem(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM item WHERE i_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public static List<ItemDto> getAll() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<ItemDto> dtoList = new ArrayList<>();

        while (rs.next()){
            String i_id = rs.getString(1);
            String i_name = rs.getString(2);
            String i_brand = rs.getString(3);
            String i_size = rs.getString(4);
            String i_price = rs.getString(5);
            String i_qty = rs.getString(6);

            dtoList.add(new ItemDto(i_id,i_name,i_brand,i_size, Double.parseDouble(i_price), Integer.parseInt(i_qty)));
        }
        return dtoList;
    }
}
