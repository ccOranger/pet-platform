CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `phone` varchar(60) NOT NULL COMMENT '电话号码',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `name` varchar(60) DEFAULT NULL COMMENT '用户名称',
  `head_path` varchar(60) DEFAULT NULL COMMENT '用户头像',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信openId',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


CREATE TABLE `pet_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `head_path` varchar(60) DEFAULT NULL COMMENT '头像',
  `birthday` varchar(150) DEFAULT NULL COMMENT '出生年月 yyyy-MM-dd',
  `age` char(3) DEFAULT NULL COMMENT '年龄',
  `sex_code` char(2) DEFAULT NULL COMMENT '性别（男：M，女：F）',
  `is_private` char(2) DEFAULT 0 COMMENT '设置私有的（0-未设置 1-设置）',
  `introduction` varchar(500) DEFAULT NULL COMMENT '简介',
  `variety_parent_id` bigint(20) NOT NULL COMMENT '宠物类型id',
  `variety_id` bigint(20) DEFAULT NULL COMMENT '品种类型id',
  `location_id` bigint(20) DEFAULT NULL COMMENT '位置id',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宠物信息表';

CREATE TABLE `location_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `province` int(11) DEFAULT NULL COMMENT '省份',
  `city` int(11) DEFAULT NULL COMMENT '城市',
  `county` int(11) DEFAULT NULL COMMENT '县区',
  `district` int(11) DEFAULT NULL COMMENT '街道',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `whole_address` varchar(500) DEFAULT NULL COMMENT '完整地址（省市区街道+详细地址）',
  `lng` decimal(18,15) DEFAULT NULL COMMENT '地理坐标x(经度,高德)',
  `lat` decimal(18,15) DEFAULT NULL COMMENT '地理坐标y(纬度,高德)',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='位置信息表';


CREATE TABLE `variety_dic` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id（宠物类型id）',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='品种类型字典表';

CREATE TABLE `user_cat_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '人员id',
  `pet_id` bigint(20) NOT NULL COMMENT '宠物id',
  `type` tinyint(20) DEFAULT '0' COMMENT '类型（1-我的宠物 2-我关注的宠物（关注宠物的人））',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='人员与宠物关系表';




CREATE TABLE `user_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '关注人员id',
	`focus_user_id` bigint(20) NOT NULL COMMENT '被关注人员id',
  `type` tinyint(20) DEFAULT '0' COMMENT '类型（1-我关注的人（关注我的人））',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='人员关系表';



CREATE TABLE `cat_dynamic` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `pet_id` bigint(20) NOT NULL COMMENT '宠物id',
  `is_private` char(2) DEFAULT 0 COMMENT '设置私有的（0-未设置 1-设置）',
  `title` varchar(250) NOT NULL COMMENT '动态标题',
  `tag` varchar(250) DEFAULT NULL COMMENT '动态标签',
  `content` varchar(500) DEFAULT NULL COMMENT '动态内容',
  `status` tinyint(20) DEFAULT '0' COMMENT '状态（0正常  1删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='宠物的动态表';



CREATE TABLE `attaches` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `dynamic_id` bigint(20) NOT NULL COMMENT '动态id',
  `size` varchar(11) DEFAULT NULL COMMENT '文件大小',
  `down_name` varchar(50) DEFAULT NULL COMMENT '文件别名',
  `path` varchar(400) DEFAULT NULL COMMENT '文件地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0 正常 1删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态附件表';

