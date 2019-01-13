package com.liori.service.${entityNameLowerCase};

import com.github.pagehelper.PageInfo;
import com.liori.model.${entityNameLowerCase}.${entityName};
import org.springframework.stereotype.Service;

/**
 * <p>${description}的接口类</p>
 * <b>created on ${createTime}</b>
 *
 * @author liori
 * @since 0.1
 */
@Service
public interface ${entityName}Service {

     /**
     * 新增${description}
     *
     * @param ${entityNameCamelCase} ${description}
     * @return 新增的记录的id
     */
    String save${entityName}(${entityName} ${entityNameCamelCase});

    /**
     * 根据 id 删除${description}
     *
     * @param id 删除的记录的id
     * @return 删除的记录的id
     */
    String delete${entityName}(String id);

    /**
     * 根据 id 查找${entityNameCamelCase}
     *
     * @param id 查找的记录的id
     * @return ${entityName}
     */
    ${entityName} select${entityName}(String id);

    /**
     * 分页查询${description}
     *
     * @param ${entityNameCamelCase}
     * @param pageNum 页码
     * @param pageSize 每页加载量
     * @return PageInfo<${entityName}>
     */
    PageInfo<${entityName}> select${entityNamePlural}ByExample(${entityName} ${entityNameCamelCase}, Integer pageNum, Integer pageSize);

    /**
     * 更新${description}
     *
     * @param ${entityNameCamelCase}
     * @return 更新的记录的id
     */
    String update${entityName}(${entityName} ${entityNameCamelCase});

    /**
     * 更新${description}（只更新非空字段）
     *
     * @param ${entityNameCamelCase}
     * @return 更新的记录的id
     */
    String update${entityName}Selective(${entityName} ${entityNameCamelCase});
}
