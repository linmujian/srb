package com.hy.srb.core.service;

import com.github.pagehelper.PageInfo;
import com.hy.srb.core.pojo.dto.IntegralGradeDTO;
import com.hy.srb.core.pojo.entity.IntegralGrade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 积分等级表 服务类
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
public interface IntegralGradeService extends IService<IntegralGrade> {

    PageInfo<IntegralGrade> listByPage(Integer pageNum,Integer pageSize);

    public List<IntegralGradeDTO> listIntegralGradeDTO();
}
