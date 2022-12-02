package com.hy.srb.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.srb.core.pojo.bo.TransFlowBO;
import com.hy.srb.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
public interface TransFlowService extends IService<TransFlow> {

    void saveTransFlow(TransFlowBO transFlowBO);

    Boolean isSaveTransFlow(String agentBillNo);

    IPage<TransFlow> selectByUserId(Long userId, Page<TransFlow> transFlowPage);
}
