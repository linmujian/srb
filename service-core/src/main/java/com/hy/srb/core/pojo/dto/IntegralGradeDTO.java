package com.hy.srb.core.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IntegralGradeDTO{

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "积分区间开始")
    private Integer integralStart;

    @ApiModelProperty(value = "积分区间结束")
    private Integer integralEnd;

    @ApiModelProperty(value = "借款额度")
    private BigDecimal borrowAmount;


}