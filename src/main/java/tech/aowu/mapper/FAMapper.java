package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.FaFixedasset;
import tech.aowu.entity.po.FaType;
import tech.aowu.entity.vo.FAView;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: FAMapper
 * @Author: Aealen
 * @Date: 2023/2/18 21:56
 */
@Mapper
public interface FAMapper extends BaseMapper<FaFixedasset> {

    List<FAView> getFaByPage(String keyword, int currIndex, Integer pageSize);
    List<FAView> getFaByTDID(Long tid,Long did);

    FAView getFaByID(Long id);

    int getFaCount();


    int delFaByFid(Long fid);

    // Type Ref
    List<FaType> getAllFaType();
    List<FaType> getAllFaTypeByPage(String keyword,int currIndex,int pageSize);
    FaType getTypeByName(String name);
    int addType(String name);
    int getTypeCount();
    int updateTypeInfo(FaType faType);
    FaType getTypeByTid(Long tid);
}
