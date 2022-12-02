package com.hy.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hy.srb.core.mapper.DictMapper;
import com.hy.srb.core.mapper.IntegralGradeMapper;
import com.hy.srb.core.pojo.dto.ExcelDictDTO;
import com.hy.srb.core.pojo.dto.IntegralGradeDTO;
import com.hy.srb.core.service.IntegralGradeService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelIntegralDTOListener extends AnalysisEventListener<IntegralGradeDTO> {

    private IntegralGradeMapper integralGradeMapper;

    //数据列表
    private List<IntegralGradeDTO> list=new ArrayList<>();

    //list中每存入5条数据，就执行mapper层插入数据
    private final static Integer BATCH_COUNT=5;

    public ExcelIntegralDTOListener() {
    }

    public ExcelIntegralDTOListener(IntegralGradeMapper integralGradeMapper) {
        this.integralGradeMapper = integralGradeMapper;
    }

    @Override
    public void invoke(IntegralGradeDTO data, AnalysisContext analysisContext) {
//        log.info("解析到一条记录：{}",data);
        //将数据存入数据列表
        list.add(data);
        if(list.size()>=BATCH_COUNT){
            //调用mapper层的service方法
            saveData();
            list.clear();
        }
        //调用mapper层的save方法
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //当最后剩余的数据记录数不足五条的时候，收尾的时候调用一次mapper方法
        saveData();
        log.info("所有数据解析完成!");
    }

    public void saveData(){

//        integralGradeMapper.insertBatch(list);

    }
}
