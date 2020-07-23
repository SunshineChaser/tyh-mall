package edu.learning.tyh.sso.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.aspectj.lang.ProceedingJoinPoint;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.rpc.RpcContext;

import edu.learning.tyh.pojo.TyhUsers;
import edu.learning.tyh.sso.dao.TyhUsersMapper;
import edu.learning.tyh.sso.interfaces.TyhUserService;

/**
 * 用户管理实现类，并提供dubbo服务
 * @author lenovo
 *
 */
@Service
@Path("/sso")
public class TyhUserServiceImpl implements TyhUserService{

	@Autowired
	private TyhUsersMapper mapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public int insertUser(TyhUsers user) {
		return mapper.insert(user);
	}

	@Override
	public TyhUsers login(TyhUsers user) {
		TyhUsers currentUser = mapper.login(user);
		String key=currentUser.getUserid();
		//String value=currentUser.toString();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			String value=objectMapper.writeValueAsString(currentUser);
			redisTemplate.boundValueOps(key).set(value, 30*60, TimeUnit.SECONDS);
		} catch (JsonGenerationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return currentUser;
	}

	@Override
	public String getUserByToken(String token) {
		String user = redisTemplate.boundValueOps(token).get().toString();
		//访问一次后，需要刷新Redis的过期时间
		redisTemplate.boundValueOps(token).expire(30*60, TimeUnit.SECONDS);
		return user;
	}

	@Path("/getuser/{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Consumes({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Override
	public String getUserById(@PathParam("id")String id) {
		System.out.println("id="+id);
		HttpServletResponse response=(HttpServletResponse) RpcContext.getContext().getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE");
		response.setHeader("Access-Control-Max-Age" ,"3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
		String user="";
		Object val = redisTemplate.boundValueOps(id).get();
		//判空校验，防止空指针
		if(val!=null)user=val.toString();
		//System.out.println("user="+user);
		redisTemplate.boundValueOps(id).expire(30*60, TimeUnit.SECONDS);
		return user;
	}

	@Override
	public void logout(String userId) {
		redisTemplate.delete(userId);
	}
	
}
