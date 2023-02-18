package tech.aowu.service;

import tech.aowu.entity.FaFixedasset;
import tech.aowu.entity.ResponseResult;
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
}
