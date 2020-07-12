package com.licc.cat.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宠物的动态表
 * </p>
 *
 * @author 李臣臣
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CatDynamic对象", description="宠物的动态表")
public class CatDynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "宠物id")
    private Long petId;

    @ApiModelProperty(value = "设置私有的（0-未设置 1-设置）")
    private String isPrivate;

    @ApiModelProperty(value = "动态标题")
    private String title;

    @ApiModelProperty(value = "动态标签")
    private String tag;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "状态（0正常  1删除）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;


}
