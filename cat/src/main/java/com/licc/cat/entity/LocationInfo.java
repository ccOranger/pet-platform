package com.licc.cat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 位置信息表
 * </p>
 *
 * @author 李臣臣
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LocationInfo对象", description = "位置信息表")
public class LocationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "省份")
    private Integer province;

    @ApiModelProperty(value = "城市")
    private Integer city;

    @ApiModelProperty(value = "县区")
    private Integer county;

    @ApiModelProperty(value = "街道")
    private Integer district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "完整地址（省市区街道+详细地址）")
    private String wholeAddress;

    @ApiModelProperty(value = "地理坐标x(经度,高德)")
    private BigDecimal lng;

    @ApiModelProperty(value = "地理坐标y(纬度,高德)")
    private BigDecimal lat;

    @ApiModelProperty(value = "状态（0正常  1删除）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;


}
