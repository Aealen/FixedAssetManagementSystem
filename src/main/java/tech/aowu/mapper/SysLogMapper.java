package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.SysLog;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: SystemLogMapper
 * @Author: Aealen
 * @Date: 2023/3/26 16:23
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {


    List<SysLog> getAllLogs();

    List<SysLog> getLogsByPage(int currIndex, Integer perPage);
}
