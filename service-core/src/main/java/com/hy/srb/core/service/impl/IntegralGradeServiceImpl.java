package com.hy.srb.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.srb.core.pojo.dto.ExcelDictDTO;
import com.hy.srb.core.pojo.dto.IntegralGradeDTO;
import com.hy.srb.core.pojo.entity.Dict;
import com.hy.srb.core.pojo.entity.IntegralGrade;
import com.hy.srb.core.mapper.IntegralGradeMapper;
import com.hy.srb.core.service.IntegralGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 积分等级表 服务实现类
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
@Service
public class IntegralGradeServiceImpl extends ServiceImpl<IntegralGradeMapper, IntegralGrade> implements IntegralGradeService {

    @Resource
    private IntegralGradeMapper integralGradeMapper;

    @Override
    public PageInfo<IntegralGrade> listByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IntegralGrade> list = integralGradeMapper.selectList(null);
        PageInfo<IntegralGrade> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    //导出数据到Excel
    @Override
    public List<IntegralGradeDTO> listIntegralGradeDTO() {

        List<IntegralGrade> integralGrades = integralGradeMapper.selectList(null);
        ArrayList<IntegralGradeDTO> integralGradeDTOS = new ArrayList<>();
        for (IntegralGrade integralGrade : integralGrades) {
            IntegralGradeDTO integralGradeDTO = new IntegralGradeDTO();
            BeanUtils.copyProperties(integralGrade, integralGradeDTO);
            integralGradeDTOS.add(integralGradeDTO);
        }
        return integralGradeDTOS;
    }
}
