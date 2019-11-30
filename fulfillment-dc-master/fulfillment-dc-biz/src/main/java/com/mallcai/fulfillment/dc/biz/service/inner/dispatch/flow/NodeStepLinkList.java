package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * @description:
 * @author: chentao
 * @create: 2019-11-15
 */
@Slf4j
public class NodeStepLinkList<T extends FlowNodeStepConfig> {

    @Getter
    private Node<T> head;

    public void add(Node<T> next){
        if(next == null ){
            return;
        }
        if(head == null){
            this.head = next;
            return;
        }
        Node<T> lastTaskNode = getLastNode(head);
        lastTaskNode.setNext(next);
    }

    private Node<T> getLastNode(Node<T> currentTaskNode){
        if(currentTaskNode == null){
            return null;
        }
        if(currentTaskNode.getNext() == null){
            return currentTaskNode;
        }else {
            return getLastNode(currentTaskNode.getNext());
        }
    }

    public Node getTaskNode(Node<T> currentTaskNode, String stepName){
        String currTaskName = currentTaskNode.getNode().getName();
        if(currTaskName.equals(stepName)){
            return currentTaskNode;
        }
        if(currentTaskNode.getNext() != null){
            return getTaskNode(currentTaskNode.getNext(),stepName);
        }else{
            return null;
        }
    }
}