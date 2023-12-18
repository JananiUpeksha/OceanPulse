package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String NIC;
}
