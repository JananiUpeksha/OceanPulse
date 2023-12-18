package lk.ijse.dto;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddDto {
    private String id;
    private String size;
    private String name;
    private String brand;
    private double unitPrice;
    private int qty;
    private Button btn;
    public AddDto(String id, String size, String name, String brand, double unitPrice, int qty) {
        this.id = id;
        this.size = size;
        this.name = name;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
