package com.liori.mapper.${entityNameLowerCase};

import com.liori.mapper.base.BaseMapper;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.model.${entityNameLowerCase}.${entityName}Example;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>${description}的映射器</p>
 * <b>created on ${createTime}</b>
 *
 * @author ${author}
 * @since ${version}
 */
@Mapper
@Repository
public interface ${entityName}Mapper extends BaseMapper<${entityName}, ${entityName}Example> {

}