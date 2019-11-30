package com.mallcai.fulfillment.dp.biz.service.impl.Repository;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 配送单号生成代码
 */
public class IDBuilderUtils {

  private static final char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

  private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  public static String build() {
    String timeStamp = DTF.format(LocalDateTime.now());
    StringBuilder idBuilder = new StringBuilder();
    idBuilder.append(timeStamp);
    idBuilder.append("0000");
    idBuilder.append(RandomStringUtils.random(6,chr));
    idBuilder.append(RandomStringUtils.randomNumeric(6));
    return idBuilder.toString();
  }
}
