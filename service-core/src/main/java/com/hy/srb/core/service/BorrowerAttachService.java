package com.hy.srb.core.service;

import com.hy.srb.core.pojo.entity.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.srb.core.pojo.vo.BorrowerAttachVO;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {
    
    List<BorrowerAttachVO> selectBorrowerAttachVOList(Long borrowerId);

}
