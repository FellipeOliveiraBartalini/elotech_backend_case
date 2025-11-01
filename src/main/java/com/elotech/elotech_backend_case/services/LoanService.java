package com.elotech.elotech_backend_case.services;

import com.elotech.elotech_backend_case.dtos.LoanCreateDto;
import com.elotech.elotech_backend_case.models.BookModel;
import com.elotech.elotech_backend_case.models.LoanModel;
import com.elotech.elotech_backend_case.models.UserModel;
import com.elotech.elotech_backend_case.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, UserService userService, BookService bookService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<LoanModel> findAll() {
        return loanRepository.findAll();
    }

    public Optional<LoanModel> findById(Long id) {
        return loanRepository.findById(id);
    }

    public LoanModel create(LoanCreateDto loanCreateDto) {
        boolean isBookAvailable = validateIfBookIsAvailable(loanCreateDto.getBookId());
        if (!isBookAvailable) {
            throw new RuntimeException("Book is currently loaned out");
        }

        UserModel user = userService.findById(loanCreateDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        BookModel book = bookService.findById(loanCreateDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        LoanModel loan = new LoanModel();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(7));

        return loanRepository.save(loan);
    }

    public LoanModel returnBook(Long loanId) {
        LoanModel loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned");
        }

        loan.setReturnDate(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    public LoanModel renew(Long loanId) {
        LoanModel currentLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (currentLoan.getReturnDate() != null) {
            throw new RuntimeException("Cannot renew a returned loan");
        } else {
            returnBook(currentLoan.getId());
        }

        LoanCreateDto newLoanDto = new LoanCreateDto();
        newLoanDto.setUserId(currentLoan.getUser().getId());
        newLoanDto.setBookId(currentLoan.getBook().getId());

        return create(newLoanDto);
    }

    public void delete(Long id) {
        LoanModel loanModel = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loanRepository.delete(loanModel);
    }

    private boolean validateIfBookIsAvailable(Long bookId) {
        LoanModel recentLoan = loanRepository.findTopByBookIdOrderByLoanDateDesc(bookId);
        return recentLoan == null || recentLoan.getReturnDate() != null;
    }

    public List<BookModel> getRecomendedBooks(Long userId) {
        // TODO: get loaned books by userId
        List<LoanModel> userLoans = loanRepository.findByUserId(userId);

        return userLoans.stream()
            .map(loanedBook -> loanedBook.getBook().getCategorias().stream()
                .map(category -> bookService.findByCategoryId(category.getId()))
                .toList().getFirst())
            .toList().getFirst()
            .stream().filter(book -> userLoans.stream().noneMatch(loan -> loan.getBook().getId().equals(book.getId())))
            .toList();
    }
}
