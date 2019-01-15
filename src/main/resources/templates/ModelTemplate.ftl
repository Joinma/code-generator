package com.liori.model.${entityNameLowerCase};

import com.liori.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
<#list tableColumns as column>
    <#if column.columnType == "Date">
import java.util.Date;
        <#break>
    </#if>
</#list>

/**
* <p>${description}的实体类</p>
* <b>created on ${createTime}</b>
*
* @author ${author}
* @since ${version}
*/
@ApiModel("${description}")
public class ${entityName} extends BaseEntity implements Serializable {

<#list tableColumns as column>
    @ApiModelProperty(value = "${column.columnComment}")
    private ${column.columnType} ${column.columnName};

</#list>

<#list tableColumns as column>
    <#if column.columnType == "Boolean">
    public ${column.columnType} is${column.columnNameCameCase}() {
        return ${column.columnName};
    }
    <#else>
    public ${column.columnType} get${column.columnNameCameCase}() {
        return ${column.columnName};
    }
    </#if>

    public void set${column.columnNameCameCase}(${column.columnType} ${column.columnName}) {
        this.${column.columnName} = ${column.columnName};
    }

</#list>
}