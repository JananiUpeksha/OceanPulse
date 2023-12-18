package lk.ijse.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDto {
    private String id;
    private Timestamp eventDateTime;
    private String b_id;
    private String ins_id;

}
