package com.example.demo.cache;

import org.springframework.cache.annotation.CacheEvict;

public interface CacheService {
    Account getAccount(int no);

    void expireAccount(int no);
}
