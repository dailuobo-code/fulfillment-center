CREATE TABLE `fpe_time_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city_id` int(11) NOT NULL COMMENT '城市id',
  `warehouse_id` int(11) NOT NULL COMMENT '对应的仓库',
  `delivery_days` int(11) NOT NULL COMMENT '取货时间 T+N',
  `create_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL COMMENT '0-未删除 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间规则表'