package com.yurn.satori.framework;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author Yurn
 */
@Component
@EnableAsync
@ComponentScan("com.yurn.satori.framework")
public class AutoConfiguration {}
