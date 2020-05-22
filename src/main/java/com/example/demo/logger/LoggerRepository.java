package com.example.demo.logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoggerRepository {

    public String log(int no) throws InterruptedException {

        Thread.sleep(300);

        log.debug("Transaction ID : {}", MDC.get(LoggerConstant.TRASACTION_ID));
        log.debug("no : {}", no);

        return "Logger " + no;
    }
}
