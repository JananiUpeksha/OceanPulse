package lk.ijse.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String NIC;
}
