package com.LibraNet.LibraNet.Controller;


import com.LibraNet.LibraNet.Dto.Borrowed;
import com.LibraNet.LibraNet.Dto.BorrowedRecordDto;
import com.LibraNet.LibraNet.Dto.ReturnBorrowedDto;
import com.LibraNet.LibraNet.Service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
@Validated
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowedRecordDto> borrow(@RequestBody Borrowed req) {
        BorrowedRecordDto record = borrowService.borrowItem(req);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowedRecordDto> returnItem(@RequestBody ReturnBorrowedDto req) {
        BorrowedRecordDto record = borrowService.returnItem(req);
        return ResponseEntity.ok(record);
    }
}
