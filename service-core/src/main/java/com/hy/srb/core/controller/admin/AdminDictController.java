package com.hy.srb.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.api.R;
import com.hy.common.exception.BusinessException;
import com.hy.common.result.ResponseEnum;
import com.hy.common.result.Result;
import com.hy.srb.core.pojo.dto.ExcelDictDTO;
import com.hy.srb.core.pojo.dto.IntegralGradeDTO;
import com.hy.srb.core.pojo.entity.Dict;
import com.hy.srb.core.service.DictService;
import com.hy.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/")
@Slf4j
//@CrossOrigin
public class AdminDictController {

    @Resource
    private DictService dictService;
    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("excel数据的批量导入")
    @PostMapping("/dict/import")
    public Result batchImport(@RequestParam("file")MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return Result.ok().setMessage("批量导入数据成功");
        } catch (Exception e) {
            //UPLOAD_ERROR(-103, "文件上传错误")
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @ApiOperation("Excel数据的导出")
    @GetMapping("/dict/export")
    public void export(HttpServletResponse response){
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("myDict", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.EXPORT_DATA_ERROR,e);
        }
    }

    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/dict/listByParentId/{parentId}")
    public Result listByParentId(
            @ApiParam(value = "上级节点id", required = true)
            @PathVariable Long parentId) {
        List<Dict> dictList = dictService.listByParentId(parentId);
        return Result.ok().data("list", dictList);
    }


    @ApiOperation("Excel数据的导出")
    @GetMapping("/integral/export")
    public void integralExport(HttpServletResponse response){
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("myIntegralGrade", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), IntegralGradeDTO.class).sheet("integralGrade").doWrite(integralGradeService.listIntegralGradeDTO());
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.EXPORT_DATA_ERROR,e);
        }
    }


}
