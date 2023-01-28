package com.java_reflection.config;

import com.java_reflection.annotation.AutoComponentScan;
import com.java_reflection.annotation.AutoConfig;

/**
 * we need to create @AutoConfig annotation like @Configuration
 * for achieve functionality
 * */
@AutoConfig
@AutoComponentScan("com.java_reflection")
public class AppConfig {



}
