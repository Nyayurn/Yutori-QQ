package com.yurn.satori.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author Yurn
 */
@Slf4j
@Component
@EnableAsync
@ComponentScan("com.yurn.satori.framework")
public class AutoConfiguration {}
