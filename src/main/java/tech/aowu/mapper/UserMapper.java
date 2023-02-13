package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.UmUser;


@Mapper
public interface UserMapper extends BaseMapper<UmUser> {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return UmUser
     */
    UmUser getUmUserByUsername(String username);


    /**
     * 根据用户ID更新登陆时间
     * @return int 0更新失败 1更新成功
     */
    int updateLoginTimeByUid(long uid);

    /**
     * 用户注册
     * @param umUser
     * @return int 0注册失败 1注册成功
     */
    int regist(UmUser umUser);

}
