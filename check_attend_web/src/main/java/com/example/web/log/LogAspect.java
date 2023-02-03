package com.example.web.log;

import com.example.core.model.vo.Result;
import com.example.core.utils.JWTUtils;
import com.example.core.utils.SnowflakeIdWorker;
import com.example.web.mapper.RecordMapper;
import com.example.web.model.pojo.Record;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lincheon
 * @since 2022/3/4 16:55
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    private final RecordMapper recordMapper;

    @Autowired
    public LogAspect(RecordMapper recordMapper){
        this.recordMapper=recordMapper;
    }

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置切入点
     */
    @Pointcut("@annotation(com.example.web.log.LogAnnotation)")
    public void operateCut(){}

    /**
     * 记录操作日志
     */
    @AfterReturning(returning = "result",value = "operateCut()")
    public void saveOperateLog(JoinPoint joinPoint, Object result) throws Throwable{
        // 初始化Record日志
        Record record=new Record();
        // 获取RequestAttributes
        RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();
        // 从获取的RequestAttributes中获取HttpServletRequest的信息
        assert requestAttributes != null;
        HttpServletRequest request=(HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String token = request.getHeader("token");
        // 添加操作人
        record.setUserCode(JWTUtils.getUsername(token)!=null?JWTUtils.getUsername(token):JWTUtils.getOpenId(token));
        // 通过切面织入点通过反射机制获取织入点的方法
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        // 获取织入点处的方法
        Method method=signature.getMethod();
        // 获取操作
        LogAnnotation annotation=method.getAnnotation(LogAnnotation.class);
        if(annotation!=null){
            record.setModule(annotation.operateModule());
            record.setType(annotation.operateType());
            record.setDescription(annotation.operateDesc());
        }
        // 添加操作时间
        record.setOperationTime(LocalDateTime.now());
        // 添加对应的ip
        record.setIp(request.getRemoteAddr());
        // 添加返回值信息
        record.setResult(((Result) result).getMessage());
        // 添加全局唯一id
        record.setId(new SnowflakeIdWorker(1,1).nextId());
        // 保存日志
        recordMapper.insert(record);
    }
}
