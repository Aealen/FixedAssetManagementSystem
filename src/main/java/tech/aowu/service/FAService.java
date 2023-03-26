package tech.aowu.service;

import tech.aowu.entity.po.FaFixedasset;
import tech.aowu.entity.po.FaType;
import tech.aowu.entity.vo.FAQueryParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;

/**
 * @Description: TODO
 * @ClassName: FAService
 * @Author: Aealen
 * @Date: 2023/2/18 21:55
 */
public interface FAService {
    ResponseResult queryFaByPage(QueryByPageParams params);

    ResponseResult getFaCount();

    ResponseResult changeFaStatus(Long fid, Integer status);

    ResponseResult addFa(FaFixedasset faFixedasset);

    ResponseResult getAllType();

    ResponseResult getAllTypeByPage(QueryByPageParams queryByPageParams);

    ResponseResult getTypeCount();

    ResponseResult addType(String typename);
    ResponseResult updateTypeInfo(FaType faType);

    ResponseResult queryFaByPageByID(Long id);

    ResponseResult updatefa(FaFixedasset faFixedasset);

    ResponseResult queryFaByTDID(FAQueryParams params);

    ResponseResult getFASearchCount(QueryByPageParams params);
}
