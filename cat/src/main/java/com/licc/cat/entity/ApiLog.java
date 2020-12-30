package com.licc.cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 接口调用频率及响应日志监控
 * </p>
 *
 * @author 李臣臣
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ApiLog对象", description = "接口调用频率及响应日志监控")
public class ApiLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "访问接口")
    private String url;

    @ApiModelProperty(value = "参数")
    private String param;

    @ApiModelProperty(value = "描述")
    private String content;

    @ApiModelProperty(value = "日志信息")
    private String result;

    @ApiModelProperty(value = "方式")
    private String method;

    @ApiModelProperty(value = "状态（0-正常 1-删除）")
    private String status;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "修改日期")
    private Date modifyTime;


}
