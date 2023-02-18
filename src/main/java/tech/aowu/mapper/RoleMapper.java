package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.UmRole;
import tech.aowu.entity.UmUserRole;

import java.util.List;

/**
 * @Description: 角色两表mapper
 * @ClassName: RoleMapper
 * @Author: Aealen
 * @Date: 2023/2/14 10:54
 */
@Mapper
public interface RoleMapper extends BaseMapper<UmUserRole> {

    /**
     * 用户注册时插入角色关系
     * @param uid
     * @param rid
     * @return int 0更新失败 1更新成功
     */
    int insetWhenRegist(long uid,long rid);

    /**
     * 更新用户角色
     * @param uid
     * @param rid
     * @return int 0更新失败 1更新成功
     */
    int updateUserRole(long uid,long rid);

    /**
     * 根据用户id获取 rid
     *
     * @param uid
     * @return rid
     */
    long getRoleByUid(long uid);

    /**
     * 获取所有角色信息
     * @return
     */
    List<UmRole> getAllRoles();

    /**
     * 获取指定角色信息
     * @param rid
     * @return
     */
    UmRole getRoleById(Long rid);

}
