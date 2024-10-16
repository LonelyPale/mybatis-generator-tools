/**
* Created by Mybatis Generator on 2024-07-05 16:42:36
*/

package com.example.mapper;

import com.example.model.TbBxBkAccTenant;
import com.example.model.TbBxBkAccTenantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBxBkAccTenantMapper {
    long countByExample(TbBxBkAccTenantExample example);

    int deleteByExample(TbBxBkAccTenantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbBxBkAccTenant row);

    int insertSelective(TbBxBkAccTenant row);

    List<TbBxBkAccTenant> selectByExample(TbBxBkAccTenantExample example);

    TbBxBkAccTenant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TbBxBkAccTenant row, @Param("example") TbBxBkAccTenantExample example);

    int updateByExample(@Param("row") TbBxBkAccTenant row, @Param("example") TbBxBkAccTenantExample example);

    int updateByPrimaryKeySelective(TbBxBkAccTenant row);

    int updateByPrimaryKey(TbBxBkAccTenant row);
}