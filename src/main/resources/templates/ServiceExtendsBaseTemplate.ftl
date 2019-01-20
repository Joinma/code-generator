package com.liori.service.${entityNameLowerCase};

import com.github.pagehelper.PageInfo;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.model.${entityNameLowerCase}.${entityName}Example;
import com.liori.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>${description}的接口类</p>
 * <b>created on ${createTime}</b>
 *
 * @author ${author}
 * @since ${version}
 */
@Service
public interface ${entityName}Service extends BaseService<${entityName}, ${entityName}Example> {

    /**
     * 分页查询${description}
     *
     * @param ${entityNameCamelCase}
     * @param pageNum 页码
     * @param pageSize 每页加载量
     * @return PageInfo<${entityName}>
     */
    PageInfo<${entityName}> select${entityNamePlural}ByExample(${entityName} ${entityNameCamelCase}, Integer pageNum, Integer pageSize);

}
