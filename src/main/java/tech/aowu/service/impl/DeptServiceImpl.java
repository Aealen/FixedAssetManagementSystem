package tech.aowu.service.impl;

import org.springframework.stereotype.Service;
import tech.aowu.entity.FaDepartment;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUser;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.DeptMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.DeptService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: DeptServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/18 16:40
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    DeptMapper deptMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public ResponseResult getAllDepts() {

        List<FaDepartment> allDepts = deptMapper.getAllDepts();
        if (Objects.isNull(allDepts)){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"success",allDepts);
    }

    @Override
    public ResponseResult getAllDeptsByPage(QueryByPageParams params) {
        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();

        List<FaDepartment> departmentList=deptMapper.getAllDeptsByPage(params.getKeyword(),currIndex, params.getPerPage());
        if (Objects.isNull(departmentList.get(0))){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"查询成功",departmentList);
    }

    @Override
    public ResponseResult setUserDept(Long uid, Long did) {
        //合法性判断
        List<UserView> userViewById = userMapper.getUserViewById(uid);
        FaDepartment deptByDid = deptMapper.getDeptByDid(did);
        if (Objects.isNull(userViewById.get(0))||Objects.isNull(deptByDid)){
            return new ResponseResult(152,"参数非法");
        }
        UmUser umUser=new UmUser();
        umUser.setUid(uid);
        umUser.setDepartment(did);
        int updateById = userMapper.updateById(umUser);
        if (updateById==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"success");
    }
}
