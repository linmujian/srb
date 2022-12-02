package com.hy.srb.core.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.common.result.Result;
import com.hy.srb.base.utils.JwtUtils;
import com.hy.srb.core.pojo.entity.TransFlow;
import com.hy.srb.core.service.TransFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "资金记录")
@RestController
@RequestMapping("/api/core/transFlow")
@Slf4j
public class TransFlowController {

    @Resource
    private TransFlowService transFlowService;

    @ApiOperation("获取列表")
    @GetMapping("/list/{page}/{limit}")
    public Result list(HttpServletRequest request,
                       @ApiParam(value = "当前页码", required = true)
                       @PathVariable Long page,
                       @ApiParam(value = "每页记录数", required = true)
                       @PathVariable Long limit) {
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        Page<TransFlow> transFlowPage = new Page<>(page, limit);
        IPage<TransFlow> list = transFlowService.selectByUserId(userId,transFlowPage);
        return Result.ok().data("list", list);
    }
}