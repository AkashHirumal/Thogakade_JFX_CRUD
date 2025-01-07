package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Item {
    private String itemCode;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}