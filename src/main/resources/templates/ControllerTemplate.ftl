package com.liori.controller.user;

import com.github.pagehelper.PageInfo;
import com.liori.common.message.MessageEnum;
import com.liori.common.utils.message.MessageUtil;
import com.liori.model.user.User;
import com.liori.service.user.UserService;
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


@Api(value = "${description}controller", tags = {"${description}接口"})
@RestController
@RequestMapping(value = "/api/${entityNamePlural}")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(${entityName}Controller.class);

    @Autowired
    private ${entityName}Service ${entityNameLowerCase}Service;

    @ApiOperation(value = "新增${description}", notes = "添加一个${description}")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> save${entityName}(
            @ApiParam(name = "${description}信息", required = true) @RequestBody ${entityName} ${entityNameLowerCase}) {
        try {
            int result = userService.save${entityName}(${entityNameLowerCase});
            return MessageUtil.success(result, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_CREATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_CREATE, throwable);
        }
    }

    @ApiOperation(value = "${description}更新", notes = "更新${description}信息")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "用户id", required = true) @PathVariable(value = "id") Long id,
            @ApiParam(name = "用户信息", required = true) @RequestBody User user) {
        try {
            user.setId(id);
            final Integer result = userService.updateUser(user);
            return MessageUtil.success(result, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_UPDATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_UPDATE, throwable);
        }
    }

    @ApiOperation(value = "获取单个${description}", notes = "根据 id 获取${description}信息")
    @GetMapping(value = "/query/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSingleUser(@PathVariable(value = "id") Long id) {
        try {
            final User user = userService.getUser(id);
            return MessageUtil.success(user, HttpStatus.OK);
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
            User user = new User();
            user.setNickName(nickName);
            final PageInfo users = userService.getUsersByExample(user, pageNum, pageSize);
            return MessageUtil.success(users, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_QUERY.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_QUERY, throwable);
        }
    }
}