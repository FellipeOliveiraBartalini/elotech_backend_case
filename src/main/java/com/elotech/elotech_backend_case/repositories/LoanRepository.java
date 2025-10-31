package com.elotech.elotech_backend_case.repositories;

import com.elotech.elotech_backend_case.models.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {
    LoanModel findTopByBookIdOrderByLoanDateDesc(Long bookId);
}
