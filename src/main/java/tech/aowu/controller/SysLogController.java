package tech.aowu.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.service.SysLogService;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @ClassName: SysLogController
 * @Author: Aealen
 * @Date: 2023/3/26 16:30
 */
@Api(tags = "SysLogController", description = "系统日志相关接口")
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Resource
    SysLogService sysLogService;

    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getAllLogs")
    public ResponseResult getAllLogs(){
        return sysLogService.getAllLogs();
    }
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getLogsCount")
    public ResponseResult getLogsCount(){
        return sysLogService.getLogsCount();
    }



    @PreAuthorize("hasAuthority('system:user:admin')")
    @PostMapping("/getAllLogsByPage")
    public ResponseResult getAllLogsByPage(@RequestBody QueryByPageParams params){
        return sysLogService.getAllLogsByPage(params);
    }




}
