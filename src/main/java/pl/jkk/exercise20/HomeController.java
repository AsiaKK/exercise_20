package pl.jkk.exercise20;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private List<Product> products = new ArrayList<>();

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @PostMapping("/")
    public String addProduct(Model model, @RequestParam String name, @RequestParam String price) {

        double priceDouble = 0;

        if (name != "" && price != "") {
            priceDouble = Double.parseDouble(price.replaceAll(",", "."));
            Product product = new Product(name, priceDouble);
            products.add(product);

            model.addAttribute("products", products);
        }
        return "home";
    }

    @GetMapping("/list")
    public String showList(Model model) {

        String sum = getSumOfProductsPrice(products);

        model.addAttribute("products", products);
        model.addAttribute("sum", sum);

        return "list";
    }

    @GetMapping("/table")
    public String showTable(Model model) {

        String sum = getSumOfProductsPrice(products);

        model.addAttribute("products", products);
        model.addAttribute("sum", sum);

        return "table";
    }

    private String getSumOfProductsPrice(List<Product> products) {

        double sum = 0;

        for (Product product : products)
            sum += product.getPrice();

        String sumString = String.format("%.2f", sum);
        return sumString;
    }
}