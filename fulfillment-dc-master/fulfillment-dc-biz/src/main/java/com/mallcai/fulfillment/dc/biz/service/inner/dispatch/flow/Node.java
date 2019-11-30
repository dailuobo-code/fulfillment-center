package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-15
 */
@Data
public class Node<T>  {

    private T node;

    private Node<T> next;

    public Node(){};

    public Node(T node){

        this.node = node;
    }
}