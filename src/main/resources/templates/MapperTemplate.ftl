package com.liori.mapper.${entityNameLowerCase};

import com.liori.mapper.base.BaseMapper;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.model.${entityNameLowerCase}.${entityName}Example;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ${entityName}Mapper extends BaseMapper<${entityName}, ${entityName}Example> {

}