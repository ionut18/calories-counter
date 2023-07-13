package ro.oane.caloriescounter.record;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodPiece {
    private Long code;
    private String name;
    private Integer portionDefault;
    private String portionAmount;
    private String portionName;
    private String increment;
    private String multiplier;
    private String calories;
}
