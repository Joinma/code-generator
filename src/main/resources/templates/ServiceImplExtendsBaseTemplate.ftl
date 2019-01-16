package com.liori.service.${entityNameLowerCase}.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liori.mapper.base.BaseMapper;
import com.liori.mapper.${entityNameLowerCase}.${entityName}Mapper;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.model.${entityNameLowerCase}.${entityName}Example;
import com.liori.service.base.impl.BaseServiceImpl;
import com.liori.service.${entityNameLowerCase}.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * <p>${description}的接口实现类</p>
 * <b>created on ${createTime}</b>
 *
 * @author ${author}
 * @since ${version}
 */
@Service
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}, ${entityName}Example> implements ${entityName}Service {


    @Autowired
    private ${entityName}Mapper ${entityNameCamelCase}Mapper;

     @Override
    public BaseMapper<${entityName}, ${entityName}Example> getSpecificMapper() {
        return ${entityNameCamelCase}Mapper;
    }

    @Override
    public PageInfo<${entityName}> select${entityNamePlural}ByExample(${entityName} ${entityNameCamelCase}, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        ${entityName}Example ${entityNameCamelCase}Example = new ${entityName}Example();
        List<${entityName}> ${entityNameCamelCasePlural} = ${entityNameCamelCase}Mapper.selectByExample(${entityNameCamelCase}Example);
        return new PageInfo<>(${entityNameCamelCasePlural});
    }
}
