package tech.aowu.service;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.aowu.entity.FaDepartment;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUserRole;
import tech.aowu.entity.vo.QueryByPageParams;

import javax.servlet.http.HttpServletRequest;

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
