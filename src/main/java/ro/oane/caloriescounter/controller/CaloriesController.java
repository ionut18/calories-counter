package ro.oane.caloriescounter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.oane.caloriescounter.record.SearchItem;
import ro.oane.caloriescounter.service.CaloriesDataService;

@Controller
@RequiredArgsConstructor
public class CaloriesController {

    private final CaloriesDataService caloriesDataService;

    @RequestMapping("/")
    public String home(final Model model) {
        model.addAttribute("searchItem", "");
        model.addAttribute("foodList", caloriesDataService.getFoodList().subList(0, 50));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("item") final String item,
                        final Model model) {
        final SearchItem searchItem = new SearchItem(item);
        model.addAttribute("searchItem", searchItem.name());
        model.addAttribute("foodList", caloriesDataService.getFoodListByName(item));
        return "index";
    }

}
