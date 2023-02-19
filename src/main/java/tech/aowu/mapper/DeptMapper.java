package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.FaDepartment;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: DeptMapper
 * @Author: Aealen
 * @Date: 2023/2/18 16:41
 */
@Mapper
public interface DeptMapper extends BaseMapper<FaDepartment> {

   List<FaDepartment> getAllDepts();

   FaDepartment getDeptByDid(Long did);
   FaDepartment getDeptByName(String name);




    List<FaDepartment> getAllDeptsByPage(String keyword, int currIndex, Integer perPage);

    int getDeptCount();

    int addDept(String name);

    int updateDeptById(FaDepartment faDepartment);
}
