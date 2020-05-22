package com.example.demo.logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public String log(int no) throws InterruptedException {

        Thread.sleep(200);

        log.debug("Transaction ID : {}", MDC.get(LoggerConstant.TRASACTION_ID));
        log.debug("no : {}", no);

        return loggerRepository.log(no);
    }
}
