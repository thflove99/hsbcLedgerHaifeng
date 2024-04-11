package org.example.repository;

import org.example.module.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    // Create a transaction record
    Transaction save(Transaction transaction);

    // Update a transaction record
    Transaction saveAndFlush(Transaction transaction);

    List<Transaction> findByFromAccountIdAndFromWalletId(Long fromAccountId, Long fromWalletId);

    @Query("SELECT t FROM Transaction t WHERE t.fromAccountId = :accountId AND t.fromWalletId =" +
            " :walletId AND t.createdAt >= :startTime AND t.createdAt <= :endTime")
    List<Transaction> findByFromAccountIdAndFromWalletIdAndCreatedAtBetween(@Param("accountId")
                                                                            Long accountId, @Param("walletId") Long walletId,
                                                                            @Param("startTime") LocalDateTime startTime,
                                                                            @Param("endTime") LocalDateTime endTime);
}
