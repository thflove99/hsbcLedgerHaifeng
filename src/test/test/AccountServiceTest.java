import org.example.config.AccountStatus;
import org.example.module.entity.Account;
import org.example.repository.AccountRepository;
import org.example.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest  {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void createAccount_shouldSaveAccount() {
        // 创建一个账号对象
        Account account = new Account();
        account.setAccountId(12L);
        account.setAccountName("test");
        // 调用 Service 方法
        accountService.createAccount(account);
        // 验证是否调用了 Repository 的 save 方法
        verify(accountRepository).save(account);
    }

    @Test
    public void testUpdateAccountStatus() {
        // Create a mock account
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setStatus(AccountStatus.OPEN.getStatus());

        // Mock the behavior of the repository
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));

        // Perform the service method
        boolean updated = accountService.updateAccountStatus(1L, AccountStatus.OPEN.getStatus());

        // Assert
        assertTrue(updated);
        assertEquals(AccountStatus.OPEN.getStatus(), mockAccount.getStatus());
    }

    @Test
    public void testGetAccountById() {
        // Create a mock account
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setStatus("active");

        // Mock the behavior of the repository
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));

        // Perform the service method
        Account result = accountService.getAccountById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getAccountId().longValue());
        assertEquals("active", result.getStatus());
    }
}
