import lombok.extern.slf4j.Slf4j;
import org.example.exception.InsufficientBalanceException;
import org.example.exception.WalletNotFoundException;
import org.example.module.dto.TransactionHistoryRequest;
import org.example.module.dto.TransferRequest;
import org.example.module.entity.Transaction;
import org.example.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
public class TransactionServiceTest  extends AbstractSpringBootTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void testTransferFundsWalletNotFound() {
        // 准备测试数据，钱包ID设置为一个不存在的值
        Long sourceWalletId = 999L; // 不存在的钱包ID
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(100);
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(amount);
        transferRequest.setSourceWalletId(sourceWalletId);
        transferRequest.setTargetWalletId(targetWalletId);

        // 断言抛出 WalletNotFoundException 异常
        assertThrows(WalletNotFoundException.class, () -> transactionService.transferFunds(transferRequest));
    }

    @Test
    void testTransferFundsInsufficientBalance() {
        // 准备测试数据，设置一个非常大的金额，超过钱包余额
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(9999999); // 设置一个非常大的金额
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(amount);
        transferRequest.setSourceWalletId(sourceWalletId);
        transferRequest.setTargetWalletId(targetWalletId);

        // 断言抛出 InsufficientBalanceException 异常
        assertThrows(InsufficientBalanceException.class, () -> transactionService.transferFunds(transferRequest));
    }

    @Test
    void  testTransferFunds() {
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(1); // 设置一个非常大的金额
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(amount);
        transferRequest.setSourceWalletId(sourceWalletId);
        transferRequest.setTargetWalletId(targetWalletId);
        transactionService.transferFunds(transferRequest);
    }

    @Test
    public void testGetTransactionHistory() {
        TransactionHistoryRequest transactionHistoryRequest = new TransactionHistoryRequest();
        transactionHistoryRequest.setFromAccountId(1L);
        transactionHistoryRequest.setFromWalletId(1L);
        List<Transaction>  transactionList = transactionService.getTransactionHistory(transactionHistoryRequest);
        log.info("search result {}",transactionList);

    }
}
