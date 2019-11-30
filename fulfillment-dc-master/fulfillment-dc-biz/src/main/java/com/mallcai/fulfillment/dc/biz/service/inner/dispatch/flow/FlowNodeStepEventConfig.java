package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 12:22:24
 */
@Data
@XStreamAlias("event")
public class FlowNodeStepEventConfig implements Serializable {

    private static final long serialVersionUID = -1623663818474556503L;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String type;
}
