package com.gosuncn.efs.web;

import com.gosuncn.efs.bean.UploadResult;
import com.gosuncn.efs.bean.ApiResult;
import com.gosuncn.efs.service.FileService;
import com.gosuncn.efs.utils.ApiResultUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@RestController
@Validated//使得校验注解生效
@Api(value = "AccountController", description = "账号业务接口", tags = {"账号业务"})
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @ApiOperation(value = "上传文件")
    @ApiImplicitParams({
            // @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file", paramType = "body", defaultValue = ""),
            @ApiImplicitParam(name = "module", value = "模块名,不指定则默认", required = false, dataType = "String", paramType = "query", defaultValue = "")

    })
    @ApiParam(value = "文件", required = true)
    @PostMapping("/upload")
    public ApiResult<UploadResult> upload(
            @RequestParam("file") MultipartFile file
            , @RequestParam(value = "module", required = false) String module
    ) {
        UploadResult result = fileService.storeFile(file, module);
        return ApiResultUtils.success(result);
    }

    @ApiOperation(value = "移除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessUrl", value = "从上传模块获取的accessUrl", required = true, dataType = "String", paramType = "query", defaultValue = ""),
    })
    @PostMapping("/remove")
    public ApiResult remove(
            @NotBlank(message = "accessUrl不能为空")
            @RequestParam(value = "accessUrl") String accessUrl
    ) {
        boolean b = fileService.removeFile(accessUrl);
        return b ? ApiResultUtils.success("移除成功") : ApiResultUtils.failed("url错误或文件不存在");
    }


}
