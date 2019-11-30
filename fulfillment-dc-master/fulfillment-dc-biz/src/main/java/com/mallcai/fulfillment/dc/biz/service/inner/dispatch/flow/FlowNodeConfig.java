package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 11:48:16
 */
@Data
@XStreamAlias("node")
public class FlowNodeConfig implements Serializable {

    private static final long serialVersionUID = 866788713584343412L;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String isFirst = "false";

    @XStreamAsAttribute
    private String condition;

    @XStreamImplicit(itemFieldName = "step")
    private List<FlowNodeStepConfig> steps;

    @XStreamOmitField
    private String group;
}
