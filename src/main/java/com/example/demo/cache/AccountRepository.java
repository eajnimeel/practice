package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AccountRepository {

    public Account getAccount(int no) {
        log.debug("Account {}, name{}", no, no);
        return new Account(no, "name" + no);
    }

}
