package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.UmUser;
import tech.aowu.entity.vo.CustodianView;
import tech.aowu.entity.vo.UserView;

import java.util.List;


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

    /**
     * 分页查询所有用户
     * @param keyword
     * @param pageSize
     * @param currIndex
     * @return
     */
    List<UserView> getUserByPage(String keyword,int currIndex,int pageSize);
    /**
     * 根据ID查询用户信息
     * @param uid
     * @return
     */
    List<UserView> getUserViewById(Long uid);

    /**
     * 获得未删除的用户数量
     * @return 用户数量
     */
    int getUserCount();

    /**
     * 修改用户密码
     * @param uid
     * @param password
     * @return int 0更新失败 1更新成功
     */
    int changeuserPwd(Long uid,String password);

    /**
     * 修改用户邮箱
     * @param uid
     * @param email
     * @return int 0更新失败 1更新成功
     */
    int changeUserEmail(Long uid,String email);

    /**
     * 修改用户电话
     * @param uid
     * @param phone
     * @return int 0更新失败 1更新成功
     */
    int changeUserPhone(Long uid, String phone);


    List<CustodianView> getAllCustodian();

    List<UserView> getWorker(Long did);
}
