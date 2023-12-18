package lk.ijse.dto.tm;

import lombok.*;

import java.sql.Timestamp;


public class SheduleTm {
    private String id;
    private Timestamp eventDateTime;
    private String b_id;
    private String ins_id;

    public SheduleTm(String id, Timestamp eventDateTime, String bId, String insId) {
        this.id = id;
        this.eventDateTime = eventDateTime;
        this.b_id = bId;
        this.ins_id = insId;
    }

    public SheduleTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Timestamp eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getIns_id() {
        return ins_id;
    }

    public void setIns_id(String ins_id) {
        this.ins_id = ins_id;
    }

    @Override
    public String toString() {
        return "SheduleTm{" +
                "id='" + id + '\'' +
                ", eventDateTime=" + eventDateTime +
                ", b_id='" + b_id + '\'' +
                ", ins_id='" + ins_id + '\'' +
                '}';
    }
}
