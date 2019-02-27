package com.zbf.mapper;

import com.zbf.core.page.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TKGLMapper {
    public int toaddtk(Map<String,Object> map);
    public List<Map<String, Object>> gettikulist(Page<Map<String, Object>> page);

  public int toupdatetk(Map<String, Object> map);

   public   int addshiti(Map<String, Object> parameterMap);
   public   int addxuanxiang(Map<String, Object> parameterMap);
}
