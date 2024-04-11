import org.example.module.entity.Wallet;
import org.example.repository.WalletRepository;
import org.example.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    public void testCreateWallet() {
        // Mocking data
        Wallet wallet = new Wallet();
        wallet.setWalletId(1L);
        wallet.setAccountId(1L);
        wallet.setAssetId(1L);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());

        // Mocking repository method
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        // Call service method
        Wallet createdWallet = walletService.createWallet(wallet);

        // Assertions
        assertEquals(1L, createdWallet.getWalletId());
        assertEquals(BigDecimal.ZERO, createdWallet.getBalance());
        // Add more assertions based on your requirements
    }
}
