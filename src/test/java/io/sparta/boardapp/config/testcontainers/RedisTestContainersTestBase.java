package io.sparta.boardapp.config.testcontainers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ExtendWith(RedisTestContainersExtension.class)
@ActiveProfiles("test")
public abstract class RedisTestContainersTestBase {
}
