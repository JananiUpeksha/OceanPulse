package lk.ijse.model;

import lk.ijse.db.Dbconnection;
import lk.ijse.dto.tm.ReservationDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationModel {
    private static OrderModel orderModel = new OrderModel();
    private ItemMngModel itemMngModel = new ItemMngModel();

    public static boolean reservation(ReservationDto dto, String i_id) throws SQLException {
        String id = dto.getId();
        LocalDate date = dto.getDate();
        int qty = dto.getQty();
        String c_id = dto.getC_id();
        String s_id = dto.getS_id();
        String o_id = dto.getO_id();

        Connection connection = null;
        try {
            connection = Dbconnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = OrderModel.saveOrder(o_id,date,qty,c_id,s_id);
            if (isOrderSaved){
                boolean isUpdated = ItemMngModel.UpdateItem(dto.getQty(),dto.getList());
                if (isUpdated){
                    boolean isOrderDetailSaved = orderModel.saveOrderDetails(o_id,i_id);
                    if (isOrderDetailSaved){
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

}
