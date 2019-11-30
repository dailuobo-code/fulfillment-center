package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-15
 */
@Slf4j
@XStreamAlias("process")
@Data
public class OrderFlowProcessConfig implements Serializable {

    private static final long serialVersionUID = -3882373458208213457L;

    @XStreamImplicit(itemFieldName = "node")
    private List<FlowNodeConfig> nodes;

    @XStreamAsAttribute
    private String bizTypeName;

    @XStreamAsAttribute
    private String type = "1";

    @XStreamAsAttribute
    private String group;

    @XStreamAsAttribute
    private String version;
}