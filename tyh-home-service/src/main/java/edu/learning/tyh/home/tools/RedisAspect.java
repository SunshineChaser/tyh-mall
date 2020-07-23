package edu.learning.tyh.home.tools;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 使用AOP将首页缓存加入到Redis中，并从Redis中获取数据
 * @author lenovo
 *
 */
@Aspect
public class RedisAspect {

	@Autowired
	private RedisTemplate redisTemplate;
	
	public List<?> redisAop(ProceedingJoinPoint joinPoint) throws Throwable{
		List<?>list=null;
		String key=getRedisKey(joinPoint);
		System.out.println("redis的key:"+key);
		if(redisTemplate.hasKey(key)) {
			list=redisTemplate.boundListOps(key).range(0, -1);
			System.out.println("get data from redis...");
		}
		else{
			list=(List<?>) joinPoint.proceed();
			System.out.println("get data from database...");
			if(list!=null && list.size()!=0) {
				redisTemplate.boundListOps(key).rightPushAll(list.toArray());
				System.out.println("save data to redis...");
			}
		}
		
		return list;
	}

	/**
	 * 获取Redis的key
	 * @param joinPoint
	 * @return
	 */
	private String getRedisKey(ProceedingJoinPoint joinPoint) {
		String key="";
		//被切入类的全限定名
		String cls=joinPoint.getSignature().getDeclaringTypeName();
		//被切入方法名
		String method=joinPoint.getSignature().getName();
		//传入方法的参数
		Object[] params=joinPoint.getArgs();
		String param="";
		for (Object p : params) {
			param+=p.toString()+".";
			//param=param.substring(0,param.length()-1);
		}
		
		key=cls+"."+method+"."+param;
		key=key.substring(0,key.length()-1);
		return key;
	}
}
