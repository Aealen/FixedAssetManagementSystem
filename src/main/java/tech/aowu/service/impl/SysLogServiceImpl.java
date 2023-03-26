package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;
import tech.aowu.entity.po.SysLog;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.mapper.SysLogMapper;
import tech.aowu.service.SysLogService;
import tech.aowu.utils.JwtUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description: TODO
 * @ClassName: SysLogServiceImpl
 * @Author: Aealen
 * @Date: 2023/3/26 16:31
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    SysLogMapper sysLogMapper;

    @Override
    public void WriteLog(@Nullable String position,@Nullable String opreater, String content) {
        SysLog sysLog = new SysLog();
        if (position!=null)
            sysLog.setPosition(position);
        if (opreater!=null)
        {
            try {
                Claims claims = JwtUtil.parseJWT(opreater);

                String userSubject = claims.getSubject();//处理 token JSON对象
                JSONObject jsonObject = JSON.parseObject(userSubject);
                sysLog.setOperator((String) jsonObject.get("uid"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        sysLog.setContent(content);


        sysLogMapper.insert(sysLog);
    }

    @Override
    public ResponseResult getAllLogs() {
        List<SysLog> allLogs = sysLogMapper.getAllLogs();
        return new ResponseResult(200,"查询成功",allLogs);
    }
}
