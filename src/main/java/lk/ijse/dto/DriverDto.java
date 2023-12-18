package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DriverDto {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String experience;
}
