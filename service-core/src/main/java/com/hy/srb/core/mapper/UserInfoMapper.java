package com.hy.srb.core.mapper;

import com.hy.srb.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户基本信息 Mapper 接口
 * </p>
 *
 * @author xiaolin
 * @since 2022-11-02
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info where is_deleted=0")
    List<UserInfo> queryList();

}
