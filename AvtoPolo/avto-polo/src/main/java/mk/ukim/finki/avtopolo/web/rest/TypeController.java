package mk.ukim.finki.avtopolo.web.rest;

import mk.ukim.finki.avtopolo.model.Type;
import mk.ukim.finki.avtopolo.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Type> findAll() {
        return this.typeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findById(@PathVariable Long id) {
        return this.typeService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Type> save(@RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam String imageUrl) {
        return this.typeService.save(name, description, imageUrl)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
