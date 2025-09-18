package com.LibraNet.LibraNet.Service;


import com.LibraNet.LibraNet.Dto.ItemCreateDto;
import com.LibraNet.LibraNet.Dto.ItemDto;
import com.LibraNet.LibraNet.Model.Audio;
import com.LibraNet.LibraNet.Model.Book;
import com.LibraNet.LibraNet.Model.EMagazine;
import com.LibraNet.LibraNet.Model.LibItem;
import com.LibraNet.LibraNet.Repo.LibItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LibItemService {
    private final LibItemRepo itemRepository;

    public ItemDto createItem(ItemCreateDto dto) {
        String type = dto.getItemType().toUpperCase();
        LibItem entity;
        switch (type) {
            case "BOOK":
                entity = Book.builder()
                        .title(dto.getTitle())
                        .authorName(dto.getAuthorName())
                        .available(true)
                        .pageCount(dto.getPageCount())
                        .build();
                break;
            case "AUDIO":
                entity = Audio.builder()
                        .title(dto.getTitle())
                        .authorName(dto.getAuthorName())
                        .available(true)
                        .durationInSeconds(dto.getDurationInSeconds())
                        .build();
                break;
            case "EMAGAZINE":
                entity = EMagazine.builder()
                        .title(dto.getTitle())
                        .authorName(dto.getAuthorName())
                        .available(true)
                        .issueNumber(dto.getIssueNumber())
                        .build();
                break;
            default:
                throw new IllegalArgumentException("Unknown item type: " + dto.getItemType());
        }

        LibItem saved = itemRepository.save(entity);
        return toDto(saved);
    }

    public ItemDto toDto(LibItem item) {
        ItemDto.ItemDtoBuilder b = ItemDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .authorName(item.getAuthorName())
                .available(item.getAvailable());

        if (item instanceof Book) b.itemType("BOOK").pageCount(((Book) item).getPageCount());
        else if (item instanceof Audio) b.itemType("AUDIOBOOK").durationInSeconds(((Audio) item).getDurationInSeconds());
        else if (item instanceof EMagazine) b.itemType("EMAGAZINE").issueNumber(((EMagazine) item).getIssueNumber());
        else b.itemType("UNKNOWN");

        return b.build();
    }

    public List<ItemDto> searchByTitle(String title) {
        return itemRepository.findByTitle(title).stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<LibItem> findById(Integer id) {
        return itemRepository.findById(id);
    }

    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
