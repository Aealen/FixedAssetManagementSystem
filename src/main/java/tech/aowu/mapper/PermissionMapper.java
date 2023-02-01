package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.aowu.entity.dto.UmPermission;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: MenuMapper
 * @Author: Aealen
 * @Date: 2023/2/1 16:25
 */
public interface PermissionMapper extends BaseMapper<UmPermission> {

    List<String> selectPermsByUserId(Long userid);

}
