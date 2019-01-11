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

    String save${entityName}(${entityName} ${entityNameLowerCase});

    ${entityName} select${entityName}(Long id);

    PageInfo select${entityNamePlural}ByExample(${entityName} ${entityNameLowerCase}, Integer pageNum, Integer pageSize);

    String update${entityName}(${entityName} ${entityNameLowerCase});

}
