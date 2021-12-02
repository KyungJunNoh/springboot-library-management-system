package com.project.library.service.impl;

import com.project.library.dto.ExtensionBook;
import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;
import com.project.library.exception.rental.BookLoanHistoryNotFoundException;
import com.project.library.exception.rental.BookNotFoundException;
import com.project.library.model.Book;
import com.project.library.model.Member;
import com.project.library.model.Rental;
import com.project.library.repository.BookRepository;
import com.project.library.repository.RentalCustomRepository;
import com.project.library.repository.RentalRepository;
import com.project.library.service.LibraryRentalService;
import com.project.library.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LibraryRentalServiceImpl implements LibraryRentalService {

    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final RentalCustomRepository rentalCustomRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    public void rental(RentalBookDto rentalBookDto) {
        Member currentUser = currentUserUtil.getCurrentUser();

        System.out.println("currentUser = " + currentUser.toString());

        Book getBook = bookRepository.findById(rentalBookDto.getBookIdx())
                .orElseThrow(BookNotFoundException::new);

        rentalRepository.save(rentalBookDto.toEntity(currentUser,getBook));
    }

    @Override
    public void returnBook(ReturnBookDto returnBookDto) {
        if(rentalRepository.findById(returnBookDto.getIdx()) == null) throw new BookLoanHistoryNotFoundException();
        rentalRepository.deleteById(returnBookDto.getIdx());
    }

    @Transactional
    @Override
    public void extensionBook(ExtensionBook extensionBook) {
        Rental rental = rentalRepository.findById(extensionBook.getIdx())
                .orElseThrow(BookLoanHistoryNotFoundException::new);
        rental.update(rental.getReturn_date().plusDays(7));
    }

    @Override
    public List<Rental> findAllRentalBook() {
        List<Rental> allBook = rentalRepository.findAll();
        if(allBook.isEmpty()) throw new BookLoanHistoryNotFoundException();
        return allBook;
    }

    @Override
    public Map<Long, Rental> findRentalBook(Long idx) {
        Rental findBook = rentalRepository.findById(idx)
                .orElseThrow(BookLoanHistoryNotFoundException::new);
        Map<Long,Rental> map = new HashMap<>();
        map.put(idx,findBook);
        return map;
    }

    @Override
    public List<Rental> findRentalBookByBookName(String book) {
        List<Rental> rental = rentalCustomRepository.findRentalBookByBookName(book);

        if (rental.isEmpty()) throw new BookNotFoundException();

        return rental;
    }
}
