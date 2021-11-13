package com.project.library.service.impl;

import com.project.library.dto.ExtensionBook;
import com.project.library.dto.FindRentalBookDto;
import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;
import com.project.library.model.Book;
import com.project.library.model.Member;
import com.project.library.model.Rental;
import com.project.library.repository.BookRepository;
import com.project.library.repository.RentalRepository;
import com.project.library.service.LibraryRentalService;
import com.project.library.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryRentalServiceImpl implements LibraryRentalService {

    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    public void rental(RentalBookDto rentalBookDto) {
        Member currentUser = currentUserUtil.getCurrentUser();

        Book getBook = bookRepository.findById(rentalBookDto.getBookIdx())
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다."));

        rentalRepository.save(rentalBookDto.toEntity(currentUser,getBook));
    }

    @Override
    public void returnBook(ReturnBookDto returnBookDto) {
        if(rentalRepository.findById(returnBookDto.getIdx()) == null) throw new IllegalArgumentException("대출 이력을 찾을 수 없습니다.");
        rentalRepository.deleteById(returnBookDto.getIdx());
    }

    @Transactional
    @Override
    public void extensionBook(ExtensionBook extensionBook) {
        Rental rental = rentalRepository.findById(extensionBook.getIdx())
                .orElseThrow(() -> new IllegalArgumentException("대출 이력을 찾을 수 없습니다."));
        rental.update(rental.getReturn_date().plusDays(7));
    }

    @Override
    public Map<Long,Rental> findAllRentalBook() {
        List<Rental> allBook = rentalRepository.findAll();
        if(allBook.isEmpty()) throw new IllegalArgumentException("대출 이력이 없습니다.");
        Map<Long,Rental> map = new HashMap<>();
        for (Rental r : allBook) {
            map.put(r.getIdx(),r);
        }
        return map;
    }

    @Override
    public Map<Long, Rental> findRentalBook(Long idx) {
        Rental findBook = rentalRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("대출 이력이 없습니다."));
        Map<Long,Rental> map = new HashMap<>();
        map.put(idx,findBook);
        return map;
    }
}
