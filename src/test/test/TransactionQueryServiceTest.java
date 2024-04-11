import lombok.extern.slf4j.Slf4j;
import org.example.module.dto.TransactionQuery;
import org.example.module.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@Slf4j
public class TransactionQueryServiceTest extends AbstractSpringBootTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionQueryService transactionQueryService;

    @Test
    public void testGetTransactionHistory() {
        // 模拟输入参数
        TransactionQuery query = new TransactionQuery();
        query.setFromAccountId(1L);
        query.setFromWalletId(1L);
        query.setStartTime(LocalDateTime.parse("2024-01-01T00:00:00"));
        query.setEndTime(LocalDateTime.parse("2024-12-31T23:59:59"));

//        // 模拟返回结果
//        List<Transaction> transactions = new ArrayList<>();
//        // 添加一些模拟的交易对象
//        transactions.add(new Transaction(/* 设置交易对象的属性 */));
//        transactions.add(new Transaction(/* 设置交易对象的属性 */));
//        // 设置模拟的仓库行为
//        when(transactionRepository.findByAccountIdAndWalletIdAndCreatedAtBetween(query))
//            .thenReturn(transactions);

        // 调用被测试的服务方法
        List<Transaction> result = transactionQueryService.getTransactionHistory(query);

//        // 验证结果
//        assertEquals(transactions.size(), result.size());
//        // 可以进一步验证返回的交易列表的内容是否符合预期
        log.info("query with time,{}",result);
    }
}
