package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {
    private String id;
    private String name;
    private String brand;
    private String size;
    private double price;
    private int qty;


}
