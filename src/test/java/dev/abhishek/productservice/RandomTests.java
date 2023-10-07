package dev.abhishek.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class RandomTests {
    @Test
    void testLessThan3(){//Flaky Tests as it uses randomization i.e. sometimes pass and sometimes don't
        Random random = new Random();
        int number = random.nextInt();
        assert (number < 100000);
    }
}
