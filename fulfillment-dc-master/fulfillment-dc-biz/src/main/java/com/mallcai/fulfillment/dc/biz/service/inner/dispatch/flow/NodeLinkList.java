package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * @description:
 * @author: chentao
 * @create: 2019-11-15
 */
@Slf4j
public class NodeLinkList<T extends FlowNodeConfig> {

    @Getter
    private Node<T> head;

    public void add(String name) {
        FlowNodeConfig flowNodeConfig = new FlowNodeConfig();
        flowNodeConfig.setName(name);

        Node newNode = new Node(flowNodeConfig);

        if (head == null) {
            this.head = newNode;
            return;
        }
        Node<T> lastTaskNode = getLastNode(head);
        lastTaskNode.setNext(newNode);
    }

    private Node<T> getLastNode(Node<T> currentTaskNode) {
        if (currentTaskNode == null) {
            return null;
        }
        if (currentTaskNode.getNext() == null) {
            return currentTaskNode;
        } else {
            return getLastNode(currentTaskNode.getNext());
        }
    }

    public String getNextNodeName(String nodeName) {

        Node<FlowNodeConfig> currNode = (Node<FlowNodeConfig>)head;

        do{

            if (currNode.getNode().getName().equals(nodeName)){

                return currNode.getNext().getNode().getName();
            }

            currNode = currNode.getNext();

        }while (currNode.getNext() != null);

        return null;
    }
}