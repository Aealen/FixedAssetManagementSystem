package tech.aowu.service;

import tech.aowu.entity.po.FaDepartment;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;

/**
 * @Description: TODO
 * @ClassName: DeptService
 * @Author: Aealen
 * @Date: 2023/2/18 16:40
 */
public interface DeptService {


    ResponseResult getAllDepts();

    ResponseResult setUserDept(Long uid, Long did);

    ResponseResult getAllDeptsByPage(QueryByPageParams params);

    ResponseResult getDeptCount();

    ResponseResult addDept(String name);

    ResponseResult updateDeptInfo(FaDepartment faDepartment);
}
