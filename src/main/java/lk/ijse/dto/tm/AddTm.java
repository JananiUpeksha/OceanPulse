package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddTm {
    private String id;
    private String size;
    private String name;
    private String brand;
    private double unitPrice;
    private int qty;
    private Button btn;

}
