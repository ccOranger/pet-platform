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
 * 动态附件表
 * </p>
 *
 * @author 李臣臣
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Attaches对象", description="动态附件表")
public class Attaches implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "动态id")
    private Long dynamicId;

    @ApiModelProperty(value = "文件大小")
    private String size;

    @ApiModelProperty(value = "文件别名")
    private String downName;

    @ApiModelProperty(value = "文件地址")
    private String path;

    @ApiModelProperty(value = "状态(0 正常 1删除)")
    private Integer status;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime modifyTime;


}
