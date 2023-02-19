package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import tech.aowu.entity.FaFixedasset;
import tech.aowu.entity.FaType;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.FAView;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.FAMapper;
import tech.aowu.service.FAService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: FAServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/18 21:55
 */
@Service
public class FAServiceImpl implements FAService {
    @Resource
    FAMapper faMapper;

    @Override
    public ResponseResult queryFaByPage(QueryByPageParams params) {
        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();
        List<FAView> userByPage = faMapper.getFaByPage(params.getKeyword(),currIndex, params.getPerPage());

        return new ResponseResult(200,"查询成功",userByPage);

    }

    @Override
    public ResponseResult getFaCount() {

        int count=faMapper.getFaCount();

        return new ResponseResult(200,"查询成功",count);
    }

    @Override
    public ResponseResult addFa(FaFixedasset faFixedasset) {
        int insert = faMapper.insert(faFixedasset);
        if (insert==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"success");

    }

    @Override
    public ResponseResult changeFaStatus(Long fid, Integer status) {

        /*FaFixedasset faFixedasset=new FaFixedasset();
        faFixedasset.setFid(fid);
        faFixedasset.setDelFlag(status);
        int change = faMapper.updateById(faFixedasset);*/


        int i = faMapper.delFaByFid(fid);

        if (i==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }

        return new ResponseResult(200,"success");
    }


    /**
     * 获取所有资产类型
     * @return
     */
    @Override
    public ResponseResult getAllType() {

        List<FaType> faTypeList =faMapper.getAllFaType();

        if (Objects.isNull(faTypeList.get(0))){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");}

        return new ResponseResult(200,"success",faTypeList);

    }

    @Override
    public ResponseResult getAllTypeByPage(QueryByPageParams queryByPageParams) {
        if (queryByPageParams.getKeyword()==null||queryByPageParams.getKeyword().isEmpty()){
            queryByPageParams.setKeyword("");
        }
        int currIndex=(queryByPageParams.getPage()-1)*queryByPageParams.getPerPage();

        List<FaType> faTypeList =faMapper.getAllFaTypeByPage(queryByPageParams.getKeyword(),currIndex,queryByPageParams.getPerPage());

        if (Objects.isNull(faTypeList.get(0))){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");}

        return new ResponseResult(200,"success",faTypeList);
    }

    @Override
    public ResponseResult getTypeCount() {
        int typeCount = faMapper.getTypeCount();
        if (typeCount==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
    }

        return new ResponseResult(200,"success",typeCount);
    }

    @Override
    public ResponseResult addType(String typename) {
        //验证是否存在
        FaType typeByName = faMapper.getTypeByName(typename);
        if (Objects.nonNull(typeByName)){
            return new ResponseResult(170,"类型已存在");
        }else {
            int i = faMapper.addType(typename);
            if (i==0){
                return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
            }
        }
        return new ResponseResult(200,"success");
    }

    /**
     * 更新类型信息  删除或编辑
     * @param typeObject
     * @return
     */
    @Override
    public ResponseResult updateTypeInfo(FaType typeObject) {
        //验证是否存在
        FaType typeByName = faMapper.getTypeByTid(typeObject.getTid());
        if (Objects.isNull(typeByName)){
            //未找到
            return new ResponseResult(171,"类型不存在");
        }else {
            int i = faMapper.updateTypeInfo(typeObject);
            if (i==0){
                return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
            }
        }
        return new ResponseResult(200,"success");
    }



}