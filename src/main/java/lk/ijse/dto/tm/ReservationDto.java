package lk.ijse.dto.tm;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ReservationDto {
    private String id;
    private LocalDate date;
    private int qty;
    private String c_id;
    private String s_id;
    private String o_id;

    List<AddTm> list = new ArrayList<>();

    public ReservationDto() {
    }

    public ReservationDto(String id, LocalDate date, int qty, String c_id, String s_id,String o_id, List<AddTm> list) {
        this.id = id;
        this.date = date;
        this.qty = qty;
        this.c_id = c_id;
        this.s_id = s_id;
        this.o_id = o_id;
        this.list = list;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public List<AddTm> getList() {
        return list;
    }

    public void setList(List<AddTm> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", qty=" + qty +
                ", c_id='" + c_id + '\'' +
                ", s_id='" + s_id + '\'' +
                ", list=" + list +
                '}';
    }
}
