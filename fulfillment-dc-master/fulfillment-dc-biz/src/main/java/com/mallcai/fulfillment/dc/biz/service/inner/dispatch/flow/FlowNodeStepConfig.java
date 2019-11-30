package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 12:10:27
 */
@Data
public class FlowNodeStepConfig implements Serializable {

    private static final long serialVersionUID = -3878684246700428120L;

    @XStreamAsAttribute
    private String type;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String handler;

    @XStreamAsAttribute
    private String isNeedDelay = "false";

    /**
     * 不是驱动节点的节点需要放到配置最后，且配置成false
     */
    @XStreamAsAttribute
    private String isDrive = "true";

    /**
     * 距离上一步骤的间隔时间，单位：分钟
     */
    @XStreamAsAttribute
    private String interval;

    @XStreamAsAttribute
    private String intervalHandler;

    @XStreamOmitField
    private String node;

    @XStreamOmitField
    private String group;

    @XStreamOmitField
    private String bizTypeName;

    @XStreamOmitField
    private String version;

    /**
     * 期待完成时间，单位：分钟
     */
    @XStreamAsAttribute
    private String expectCompleteTime;

    @XStreamImplicit(itemFieldName = "event")
    private List<FlowNodeStepEventConfig> events;

    public void build(String bizTypeName, String group, String node, String version) {
        this.bizTypeName = bizTypeName;
        this.node = node;
        this.group = group;
        this.version = version;
    }
}
