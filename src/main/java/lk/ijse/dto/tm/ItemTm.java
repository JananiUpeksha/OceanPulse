package lk.ijse.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemTm {
    private String id;
    private String name;
    private String brand;
    private String size;
    private double price;
    private int qty;
}
