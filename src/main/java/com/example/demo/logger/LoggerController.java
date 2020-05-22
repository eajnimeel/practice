package com.example.demo.logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoggerController {

    private final LoggerService loggerService;

    @GetMapping("/logger/{no}")
    public String logger(@PathVariable int no) throws InterruptedException {
        Thread.sleep(100);

        log.debug("Transaction ID : {}", MDC.get(LoggerConstant.TRASACTION_ID));
        log.debug("no : {}", no);

        return loggerService.log(no);
    }
}
