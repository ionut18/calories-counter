package ro.oane.caloriescounter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.oane.caloriescounter.records.SearchItem;

@Controller
public class CaloriesController {

    @RequestMapping("/")
    public String home(final Model model) {
        model.addAttribute("searchItem", "");
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("item") final String item,
                        final Model model) {
        final SearchItem searchItem = new SearchItem(item);
        model.addAttribute("searchItem", searchItem.name());
        return "index";
    }

}
