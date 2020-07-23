package edu.learning.tyh.sso.dao;

import edu.learning.tyh.pojo.TyhUsers;

public interface TyhUsersMapper {
    int deleteByPrimaryKey(String userid);

    int insert(TyhUsers record);

    int insertSelective(TyhUsers record);

    TyhUsers selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(TyhUsers record);

    int updateByPrimaryKey(TyhUsers record);
    
    /**
     * 登录
     * @param user
     * @return
     */
    TyhUsers login(TyhUsers user);
}