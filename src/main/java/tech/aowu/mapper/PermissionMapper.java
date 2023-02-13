package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.UmPermission;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: MenuMapper
 * @Author: Aealen
 * @Date: 2023/2/1 16:25
 */
@Mapper
public interface PermissionMapper extends BaseMapper<UmPermission> {

    List<String> selectPermsByUserId(Long userid);

}
