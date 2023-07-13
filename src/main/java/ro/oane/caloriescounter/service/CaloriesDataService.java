package ro.oane.caloriescounter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.oane.caloriescounter.record.FoodPiece;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaloriesDataService {

    private final XlsxConverter xlsxConverter;

    public List<FoodPiece> getFoodList() {
        return xlsxConverter.extractData("src/main/resources/calories-data/Food_Display_Table.xlsx");
    }

    public List<FoodPiece> getFoodListByName(final String name) {
        return getFoodList().stream()
                .filter(foodPiece -> foodPiece.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
