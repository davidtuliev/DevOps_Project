package mk.ukim.finki.avtopolo.web;

import mk.ukim.finki.avtopolo.model.Automobile;
import mk.ukim.finki.avtopolo.model.Brand;
import mk.ukim.finki.avtopolo.model.Type;
import mk.ukim.finki.avtopolo.service.AutomobileService;
import mk.ukim.finki.avtopolo.service.BrandService;
import mk.ukim.finki.avtopolo.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/automobiles")
public class AutomobileController {

    private final AutomobileService automobileService;
    private final TypeService typeService;
    private final BrandService brandService;

    public AutomobileController(AutomobileService automobileService, TypeService typeService, BrandService brandService) {
        this.automobileService = automobileService;
        this.typeService = typeService;
        this.brandService = brandService;
    }


    @GetMapping
    public String getAutomobilePage(@RequestParam(required = false) String error,
                                    @RequestParam(required = false) Long brandId,
                                    @RequestParam(required = false) Long typeId,
                                    Model model) {
        return viewPage(1, error, brandId, typeId, model);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteAutomobile(@PathVariable Long id) {
        this.automobileService.deleteById(id);
        return "redirect:/automobiles";
    }

    @GetMapping("/add-car")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addAutomobilePage(Model model) {
        List<Type> types = this.typeService.findAll();
        List<Brand> brands = this.brandService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        model.addAttribute("bodyContent", "add-car");
        return "master-template";
    }

    @GetMapping("/edit-car/{id}")
    public String editAutomobilePage(@PathVariable Long id, Model model) {
        if (this.automobileService.findById(id).isPresent()) {
            Automobile automobile = this.automobileService.findById(id).get();
            List<Type> types = this.typeService.findAll();
            List<Brand> brands = this.brandService.findAll();
            model.addAttribute("types", types);
            model.addAttribute("brands", brands);
            model.addAttribute("automobile", automobile);
            model.addAttribute("bodyContent", "add-car");
            return "master-template";
        }
        return "redirect:/automobiles?error=AutomobileNotFound";
    }

    @PostMapping("/add")
    public String saveAutomobile(@RequestParam(required = false) Long id,
                                 @RequestParam String name,
                                 @RequestParam Double price,
                                 @RequestParam String sellingLocation,
                                 @RequestParam String imageURL,
                                 @RequestParam Long typeId,
                                 @RequestParam Long brandId) {

        if (id != null) {
            this.automobileService.edit(id, name, price, sellingLocation,
                    imageURL, typeId, brandId);
        } else {
            this.automobileService.save(name, price, sellingLocation,
                    imageURL, typeId, brandId);
        }
        return "redirect:/automobiles";
    }

    @GetMapping("/page/{pageNo}")
    public String viewPage(@PathVariable int pageNo,
                           @RequestParam(required = false) String error,
                           @RequestParam(required = false) Long brandId,
                           @RequestParam(required = false) Long typeId,
                           Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        int pageSize = 3;
        Page<Automobile> page = this.automobileService.paginate(pageNo, pageSize);
        List<Automobile> automobiles;
        List<Brand> brands = this.brandService.findAll();
        List<Type> types = this.typeService.findAll();
        if (brandId == null && typeId == null) {
            automobiles = page.getContent();
        } else {
            automobiles = this.automobileService.listAutomobilesByBrandAndType(brandId, typeId);
        }

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("automobiles", automobiles);
        model.addAttribute("brands", brands);
        model.addAttribute("types", types);
        model.addAttribute("bodyContent", "automobiles");
        return "master-template";
    }
}
