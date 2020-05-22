package com.example.demo.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final AccountRepository accountRepository;

    @Cacheable(value = "Account", key="#no")
    public Account getAccount(int no) {
        return accountRepository.getAccount(no);
    }
}
