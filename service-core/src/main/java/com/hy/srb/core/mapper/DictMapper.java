package com.hy.srb.core.mapper;

import com.hy.srb.core.pojo.dto.ExcelDictDTO;
import com.hy.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);
}
