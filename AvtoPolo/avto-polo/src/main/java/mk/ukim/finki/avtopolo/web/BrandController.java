package mk.ukim.finki.avtopolo.web;

import mk.ukim.finki.avtopolo.model.Brand;
import mk.ukim.finki.avtopolo.service.BrandService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getBrandsPage(Model model){
        model.addAttribute("brands", this.brandService.findAll());
        model.addAttribute("bodyContent", "brands");
        return "master-template";
    }

    @GetMapping("/add-brand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBrandPage(Model model) {
        List<Brand> brands = this.brandService.findAll();
        model.addAttribute("brands", brands);
        model.addAttribute("bodyContent", "add-brand");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBrand(@RequestParam String name,
                            @RequestParam String country,
                            @RequestParam String imageUrl) {

        this.brandService.save(name, country, imageUrl);
        return "redirect:/brands";
    }


}
