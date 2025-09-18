package com.LibraNet.LibraNet.Service;

import com.LibraNet.LibraNet.Dto.Borrowed;
import com.LibraNet.LibraNet.Dto.BorrowedRecordDto;
import com.LibraNet.LibraNet.Dto.ReturnBorrowedDto;
import com.LibraNet.LibraNet.Model.Borrow;
import com.LibraNet.LibraNet.Model.LibItem;
import com.LibraNet.LibraNet.Repo.BorrowRepo;
import com.LibraNet.LibraNet.Repo.LibItemRepo;
import com.LibraNet.LibraNet.Utilityclass.BorrowDurationParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {

    private final BorrowRepo borrowRepo;
    private final LibItemRepo itemRepository;

    private static final double FINE_PER_DAY = 10.0;


    public BorrowedRecordDto borrowItem(Borrowed request) {
        LibItem item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + request.getItemId()));

        if (!item.getAvailable()) {
            throw new IllegalStateException("Item is not available for borrowing");
        }

        int durationDays = BorrowDurationParser.parsing(request.getDuration());

        Borrow record = Borrow.builder()
                .item(item)
                .borrowerName(request.getBorrowerName())
                .borrowedDate(LocalDate.now())
                .durationTime(durationDays)
                .fine(0.0)
                .build();

        item.setAvailable(false);
        itemRepository.save(item);

        Borrow saved = borrowRepo.save(record);
        return toDto(saved);
    }


    public BorrowedRecordDto returnItem(ReturnBorrowedDto request) {
        List<Borrow> all = borrowRepo.findAll();

        Borrow active = all.stream()
                .filter(r -> r.getItem().getId().equals(request.getItemId())
                        && r.getReturnedDate() == null
                        && r.getBorrowerName().equals(request.getBorrowerName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No active borrowing found for this item and borrower"));

        LocalDate returned = LocalDate.now();
        active.setReturnedDate(returned);

        long actualDays = ChronoUnit.DAYS.between(active.getBorrowedDate(), returned);
        long allowed = active.getDurationTime() != null ? active.getDurationTime() : 0;
        long overdue = Math.max(0, actualDays - allowed);
        active.setFine(overdue * FINE_PER_DAY);

        LibItem item = active.getItem();
        item.setAvailable(true);
        itemRepository.save(item);

        return toDto(borrowRepo.save(active));
    }


    private BorrowedRecordDto toDto(Borrow record) {
        return BorrowedRecordDto.builder()
                .id(record.getId())
                .itemId(record.getItem().getId())
                .borrowerName(record.getBorrowerName())
                .borrowedOn(record.getBorrowedDate())
                .durationDays(record.getDurationTime())
                .returnedOn(record.getReturnedDate())
                .fine(record.getFine())
                .build();
    }
}
