package com.mallcai.manager.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/10/14 3:25 PM
 */
public class ListUtlis {

    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : (i + 1) * len));
            result.add(subList);
        }
        return result;
    }

}
