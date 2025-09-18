package com.LibraNet.LibraNet.Controller;


import com.LibraNet.LibraNet.Dto.ItemCreateDto;
import com.LibraNet.LibraNet.Dto.ItemDto;
import com.LibraNet.LibraNet.Service.LibItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/items")

@Validated
public class LibItemController {
    private final LibItemService itemService;
    public LibItemController(LibItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemCreateDto dto) {
        ItemDto created = itemService.createItem(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> listAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> search(@RequestParam("q") String q) {
        return ResponseEntity.ok(itemService.searchByTitle(q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable Integer id) {
        return itemService.findById(id).map(itemService::toDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
