package tech.aowu.service;

import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;

/**
 * @Description: TODO
 * @ClassName: SysLogService
 * @Author: Aealen
 * @Date: 2023/3/26 16:31
 */
public interface SysLogService {

    public void WriteLog(String position,String opreater,String content);

    ResponseResult getAllLogs();

    ResponseResult getAllLogsByPage(QueryByPageParams params);

    ResponseResult getLogsCount();
}
