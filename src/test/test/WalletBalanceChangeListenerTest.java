import org.example.event.WalletBalanceChangeEvent;
import org.example.event.WalletBalanceChangeListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WalletBalanceChangeListenerTest extends AbstractSpringBootTest{

    @Autowired
    private WalletBalanceChangeListener walletBalanceChangeListener;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void testHandleWalletBalanceChange() {
        // 模拟发布钱包余额变化事件
        WalletBalanceChangeEvent event = mock(WalletBalanceChangeEvent.class);
        when(event.getAccountId()).thenReturn(123L); // 设置账户ID为123

        // 调用监听器方法
        walletBalanceChangeListener.handleWalletBalanceChange(event);

        // 在此添加断言，验证监听器方法的行为
    }
}
