import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.WalletController;
import org.example.module.entity.Wallet;
import org.example.repository.WalletRepository;
import org.example.service.IWalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Slf4j
class SpringCrudApplicationTests  extends AbstractSpringBootTest {
    @Resource
    IWalletService walletService;
    @Resource
    WalletController walletController;
    @Resource
    WalletRepository walletRepository;

    @Test
    void contextLoads() {
    }

   @Test
    void saveWalletTest(){
        Wallet wallet = new Wallet();
        wallet.setAssetId(1L);
        wallet.setAccountId(1L);
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setBalance(BigDecimal.ZERO);
       log.info("wallet test save result {}", wallet);
       // 调用创建钱包的方法
       ResponseEntity<Wallet> response = walletController.createWallet(wallet);

       // 断言返回的 ResponseEntity 不为空
       Assertions.assertNotNull(response);

       // 断言状态码为 201 Created
       Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

       // 断言返回的钱包对象不为空
       Wallet createdWallet = response.getBody();
       Assertions.assertNotNull(createdWallet);

       // 断言返回的钱包对象与输入的钱包对象具有相同的属性
       // 这取决于您的业务需求，您可以根据需要进行进一步的断言
       Assertions.assertEquals(wallet.getAccountId(), createdWallet.getAccountId());
       Assertions.assertEquals(wallet.getAssetId(), createdWallet.getAssetId());
       Assertions.assertEquals(wallet.getBalance(), createdWallet.getBalance());
       // 还可以添加其他属性的断言

       // 如果需要，您还可以根据返回的钱包对象的其他属性进行更多断言
   }
}
