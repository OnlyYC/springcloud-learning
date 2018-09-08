package com.liaoyb.order.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * id 生成器
 *
 * @author liaoyb
 */
public class IdGenerator {
    /**
     * 根据时间、随机数
     *
     * @return
     */
    public static String generate() {
        Random random = new Random();
        int randomValue = random.nextInt(1000000);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        return format.format(new Date()) + String.valueOf(randomValue);
    }
}
