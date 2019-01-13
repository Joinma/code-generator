package com.liori.service.${entityNameLowerCase}.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liori.common.constants.CustomizeConstants;
import com.liori.common.exceptions.CustomizeServiceException;
import com.liori.common.utils.uuid.UUIDUtil;
import com.liori.mapper.${entityNameLowerCase}.${entityName}Mapper;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.model.${entityNameLowerCase}.${entityName}Example;
import com.liori.service.${entityNameLowerCase}.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>${description}的接口实现类</p>
 * <b>created on ${createTime}</b>
 *
 * @author liori
 * @since 0.1
 */
@Service
public class ${entityName}ServiceImpl implements ${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${entityNameCamelCase}Mapper;

    @Override
    public String save${entityName}(${entityName} ${entityNameCamelCase}) {
        String id = UUIDUtil.getUUID();
        ${entityNameCamelCase}.setId(id);
        Long currentTimeMillis = System.currentTimeMillis();
        ${entityNameCamelCase}.setCreateTime(currentTimeMillis);
        ${entityNameCamelCase}.setUpdateTime(currentTimeMillis);
        ${entityNameCamelCase}.setEnabled(CustomizeConstants.ENABLED);
        if (ObjectUtils.isEmpty(${entityNameCamelCase}.getSequence())) {
            ${entityNameCamelCase}.setSequence(99);
        }
        int insertRecordNum = ${entityNameCamelCase}Mapper.insertSelective(${entityNameCamelCase});
        if (insertRecordNum == 0) {
            throw new CustomizeServiceException("新增失败");
        }
        return id;
    }

    @Override
    public String delete${entityName}(String id) {
        ${entityNameCamelCase}Mapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ${entityName} select${entityName}(String id) {
        return ${entityNameCamelCase}Mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<${entityName}> select${entityNamePlural}ByExample(${entityName} ${entityNameCamelCase}, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        ${entityName}Example ${entityNameCamelCase}Example = new ${entityName}Example();
        List<${entityName}> ${entityNameCamelCasePlural} = ${entityNameCamelCase}Mapper.selectByExample(${entityNameCamelCase}Example);
        return new PageInfo<>(${entityNameCamelCasePlural});
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String update${entityName}(${entityName} ${entityNameCamelCase}) {
        String id = ${entityNameCamelCase}.getId();
        if (StringUtils.isEmpty(id) || "undefined".equals(id)) {
            throw new CustomizeServiceException("请传入合法的 id");
        }
        ${entityNameCamelCase}.setUpdateTime(System.currentTimeMillis());
        int updateRecordNum = ${entityNameCamelCase}Mapper.updateByPrimaryKey(${entityNameCamelCase});
        if (updateRecordNum == 0) {
            throw new CustomizeServiceException("更新失败");
        }
        return id;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String update${entityName}Selective(${entityName} ${entityNameCamelCase}) {
        String id = ${entityNameCamelCase}.getId();
        if (StringUtils.isEmpty(id) || CustomizeConstants.UNDEFINED.equals(id)) {
            throw new CustomizeServiceException("请传入合法的 id");
        }
        ${entityNameCamelCase}.setUpdateTime(System.currentTimeMillis());
        int updateRecordNum = ${entityNameCamelCase}Mapper.updateByPrimaryKeySelective(${entityNameCamelCase});
        if (updateRecordNum == 0) {
            throw new CustomizeServiceException("更新失败");
        }
        return id;
    }
}
