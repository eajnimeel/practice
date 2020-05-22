package com.example.demo.cache;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheServiceTest {

    Logger log = LoggerFactory.getLogger(CacheServiceTest.class);

    @Autowired
    CacheService cacheService;

    @Test
    public void test() {
        Account account = cacheService.getAccount(1);
        log.debug(account.toString());
        Account account1 = cacheService.getAccount(2);
        log.debug(account1.toString());
        Account account2 = cacheService.getAccount(1);
        log.debug(account2.toString());
    }
}
