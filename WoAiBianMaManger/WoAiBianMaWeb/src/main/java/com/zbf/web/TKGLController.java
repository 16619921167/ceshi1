package com.zbf.web;

import com.zbf.common.ResponseResult;
import com.zbf.core.CommonUtils;
import com.zbf.core.page.Page;
import com.zbf.core.utils.UID;
import com.zbf.service.TKGLService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tkgl")
public class TKGLController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private TKGLService tkglService;
    @RequestMapping("toaddtk")
    public ResponseResult toaddtk(HttpServletRequest request){
        Map<String, Object> map = CommonUtils.getParamsJsonMap(request);
        map.put("id", UID.next());
        tkglService.toaddtk(map);
        redisTemplate.opsForList().rightPush("tiku", map);
        ResponseResult responseResult=new ResponseResult();
        responseResult.setSuccess("ok");
        return  responseResult;
    }
    @RequestMapping("toupdatetk")
    public ResponseResult toupdatetk(HttpServletRequest request){
        Map<String, Object> map = CommonUtils.getParamsJsonMap(request);

        tkglService.toupdatetk(map);

        ResponseResult responseResult=new ResponseResult();
        responseResult.setSuccess("ok");
        return  responseResult;
    }
   @RequestMapping("gettikulist")
    public ResponseResult gettklist(HttpServletRequest request){
       Map<String, Object> map = CommonUtils.getParamsJsonMap(request);
       Page<Map<String,Object>> page=new Page<>();
       Page.setPageInfo(page, map);
       List<Map<String, Object>> gettikulist = tkglService.gettikulist(page);
       page.setResultList(gettikulist);
       ResponseResult responseResult=new ResponseResult();
       responseResult.setResult(page);
       return responseResult;
   }
   @RequestMapping("getTikuListFromRedis")
   public ResponseResult getTikuListFromRedis(){
       List<Map<String,Object>> tikutype = redisTemplate.opsForList().range("tiku", 0, -1);
       ResponseResult responseResult = ResponseResult.getResponseResult();
       responseResult.setResult(tikutype);
       return  responseResult;
   }
   @RequestMapping("toAddShiTi")
   public ResponseResult toAddShiTi(HttpServletRequest request){
       ResponseResult responseResult = ResponseResult.getResponseResult();
       Map<String, Object> parameterMap = CommonUtils.getParameterMap(request);
       tkglService.addshiti(parameterMap);

       return  responseResult;
   }
}
