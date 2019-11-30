package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author gaoguoming
 * @date 2019-10-30 13:56:43
 */
@Data
public class BatchDistOrderMultiPkgDTO implements Serializable {

    private static final long serialVersionUID = -3365474763352228664L;

    private List<DistOrderMultiPkgDTO> distOrderMultiPkgDTOList;
}
