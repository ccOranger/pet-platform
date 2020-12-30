package com.licc.cat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人员关系表
 * </p>
 *
 * @author 李臣臣
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserRelation对象", description = "人员关系表")
public class UserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "关注人员id")
    private Long userId;

    @ApiModelProperty(value = "被关注人员id")
    private Long focusUserId;

    @ApiModelProperty(value = "类型（1-我关注的人（关注我的人））")
    private Integer type;

    @ApiModelProperty(value = "状态（0正常  1删除）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;


}
