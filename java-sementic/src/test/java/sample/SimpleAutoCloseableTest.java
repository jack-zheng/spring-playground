package sample;

import org.junit.jupiter.api.Test;

class SimpleAutoCloseableTest {
    @Test
    public void test_AutoCloseable_class_will_execute_close_after_try_block() throws Exception {
        Client client = new Client();
        client.doFilter();
    }
}