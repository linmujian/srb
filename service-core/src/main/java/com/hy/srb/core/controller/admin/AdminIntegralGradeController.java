package com.hy.srb.core.controller.admin;

import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageInfo;
import com.hy.common.exception.Assert;
import com.hy.common.exception.BusinessException;
import com.hy.common.result.ResponseEnum;
import com.hy.common.result.Result;
import com.hy.srb.core.pojo.entity.IntegralGrade;
import com.hy.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "积分等级管理")//swagger里的api
@RestController
@RequestMapping("/admin/core/integralGrade")
//@CrossOrigin //跨域请求
@Slf4j
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @GetMapping("/list")
    @ApiOperation("积分等级列表")//swagger里的api
    public Result listAll(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        PageInfo<IntegralGrade> page = integralGradeService.listByPage(pageNum, pageSize);
        return Result.ok().setMessage("查询列表成功").data("page",page);
    }

    @ApiOperation(value = "根据id删除积分等级列表的记录", notes = "逻辑删除记录")//notes展示更为详细的接口方法的说明
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = integralGradeService.removeById(id);
        if(b){
            return Result.ok().setMessage("删除成功");
        }
        return Result.error().setMessage("删除失败");
    }

    @ApiOperation(value = "根据批量id删除积分等级列表的记录", notes = "逻辑删除记录")//notes展示更为详细的接口方法的说明
    @DeleteMapping("/batchDelete")
    public Result delete(@RequestBody List<IntegralGrade> lists){
        for (IntegralGrade list : lists) {
            System.out.println(list.getId());
            integralGradeService.removeById(list.getId());
        }
//        boolean b = integralGradeService.removeById(id);
//        if(b){
//            return Result.ok().setMessage("删除成功");
//        }
//        return Result.error().setMessage("删除失败");
        return Result.ok().setMessage("删除成功");
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public Result save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){

        //如果借款额度为空就手动抛出一个自定义的异常！
        Assert.notNull(integralGrade.getBorrowAmount(),ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        /*if(integralGrade.getBorrowAmount() == null){
            //BORROW_AMOUNT_NULL_ERROR(-201, "借款额度不能为空"),
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }*/
        boolean result = integralGradeService.save(integralGrade);
        if (result) {
            return Result.ok().setMessage("保存成功");
        } else {
            return Result.error().setMessage("保存失败");
        }
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public Result getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if(integralGrade != null){
            return Result.ok().data("record", integralGrade);
        }else{
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public Result updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return Result.ok().setMessage("修改成功");
        }else{
            return Result.error().setMessage("修改失败");
        }
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> data){
        String username = data.get("username");
        if(username.equals("admin")){
            return Result.ok().data("token","admin");
        }
        return Result.error().data("token","admin").setMessage("用户名或密码错误！");

    }

    @GetMapping("/info")
    public Result info(){
        return Result.ok()
                .data("roles","管理员")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
