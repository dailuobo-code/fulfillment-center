package com.mallcai.fulfillment.pe.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtils {

    public static <S, T> List<T> toList(List<S> source, Function<S, T> mapper) {
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> asList(T... array) {
        if (null == array) {
            return null;
        }

        List<T> result = new ArrayList<>(array.length);
        for (T ele : array) {
            result.add(ele);
        }

        return result;
    }

    public static <T> List<T> convertList(List<List<T>> itemsList){

        List<T> list = new ArrayList<>();

        for (List<T> item : itemsList){

            list.addAll(item);
        }

        return list;
    }
}
