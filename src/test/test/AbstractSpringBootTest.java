import lombok.extern.slf4j.Slf4j;
import org.example.DemoApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * @Author Fox
 * @Date 2024/4/8
 */
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@Slf4j
public abstract class AbstractSpringBootTest {
}
