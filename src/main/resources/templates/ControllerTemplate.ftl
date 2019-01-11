package com.liori.controller.user;

import com.github.pagehelper.PageInfo;
import com.liori.common.message.MessageEnum;
import com.liori.common.utils.message.MessageUtil;
import com.liori.model.${entityNameLowerCase}.${entityName};
import com.liori.service.${entityNameLowerCase}.${entityName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * <p>${description}的控制器</p>
 * <b>created on ${createTime}</b>
 *
 * @author liori
 * @since 0.1
 */
@Api(value = "${description}controller", tags = {"${description}接口"})
@RestController
@RequestMapping(value = "/api/${entityNameLowerCasePlural}")
public class ${entityName}Controller {

    private final static Logger LOG = LoggerFactory.getLogger(${entityName}Controller.class);

    @Autowired
    private ${entityName}Service ${entityNameCamelCase}Service;

    @ApiOperation(value = "新增${description}", notes = "添加一个${description}")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> save${entityName}(
            @ApiParam(name = "${description}信息", required = true) @RequestBody ${entityName} ${entityNameCamelCase}) {
        try {
            final String insertId = ${entityNameCamelCase}Service.save${entityName}(${entityNameCamelCase});
            return MessageUtil.success(insertId, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_CREATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_CREATE, throwable);
        }
    }

    @ApiOperation(value = "删除单个${description}", notes = "根据 id 删除单个${description}信息")
    @DeleteMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete${entityName}(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id) {
        try {
            final String deleteId = ${entityNameCamelCase}Service.delete${entityName}(id);
            return MessageUtil.success(deleteId, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_UPDATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_UPDATE, throwable);
        }
    }

    @ApiOperation(value = "更新${description}", notes = "更新${description}信息")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update${entityName}(
          @ApiParam(name = "${description}信息", required = true) @RequestBody ${entityName} ${entityNameCamelCase}) {
        try {
            final String updateId = ${entityNameCamelCase}Service.update${entityName}(${entityNameCamelCase});
            return MessageUtil.success(updateId, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_UPDATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_UPDATE, throwable);
        }
    }

    @ApiOperation(value = "获取单个${description}", notes = "根据 id 获取${description}信息")
    @GetMapping(value = "/query/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSingle${entityName}(@PathVariable(value = "id") String id) {
        try {
            final ${entityName} ${entityNameCamelCase} = ${entityNameCamelCase}Service.select${entityName}(id);
            return MessageUtil.success(${entityNameCamelCase}, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_QUERY.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_QUERY, throwable);
        }
    }

    @ApiOperation(value = "根据参数分页获取${description}", notes = "根据参数分页获取${description}信息")
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUsers(
            @ApiParam(value = "页码", defaultValue = "1") @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页加载量", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        try {
            ${entityName} ${entityNameCamelCase} = new ${entityName}();
            final PageInfo<${entityName}> ${entityNameCamelCasePlural} = ${entityNameCamelCase}Service.select${entityNamePlural}ByExample(${entityNameCamelCase}, pageNum, pageSize);
            return MessageUtil.success(${entityNameCamelCasePlural}, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_QUERY.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_QUERY, throwable);
        }
    }
}