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
 * 宠物信息表
 * </p>
 *
 * @author 李臣臣
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PetInfo对象", description = "宠物信息表")
public class PetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String headPath;

    @ApiModelProperty(value = "出生年月 yyyy-MM-dd")
    private String birthday;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "性别（男：M，女：F）")
    private String sexCode;

    @ApiModelProperty(value = "设置私有的（0-未设置 1-设置）")
    private String isPrivate;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "宠物类型id")
    private Long varietyParentId;

    @ApiModelProperty(value = "品种类型id")
    private Long varietyId;

    @ApiModelProperty(value = "位置id")
    private Long locationId;

    @ApiModelProperty(value = "状态（0正常  1删除）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;


}
