package edu.learning.tyh.sso.interfaces;



import edu.learning.tyh.pojo.TyhUsers;

/**
 * 用户管理接口
 * @author lenovo
 *
 */
public interface TyhUserService {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	int insertUser(TyhUsers user);
	
	/**
     * 登录
     * @param user
     * @return
     */
    TyhUsers login(TyhUsers user);
    
    /**
     * 根据cookie中所存放的token来获取Redis中的用户信息
     * @param token 记录的是用户的ID，也是Redis中的key
     * @return
     */
    String getUserByToken(String token);
    
    public String getUserById(String id);
    
    /**
     * 退出登录
     * @param user
     */
    public void logout(String userId);
}
