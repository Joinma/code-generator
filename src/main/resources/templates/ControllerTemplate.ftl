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


@Api(value = "用户controller", tags = {"用户接口"})
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(
            @ApiParam(name = "code", required = true) @RequestParam(name = "code", defaultValue = "") String code) {
        try {
            final User user = userService.login(code);
            return MessageUtil.success(user, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_LOGIN.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_LOGIN, throwable);
        }
    }

    @ApiOperation(value = "用户更新", notes = "更新用户信息")
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

    @ApiOperation(value = "获取单个用户", notes = "根据id获取用户信息")
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

    @ApiOperation(value = "根据参数分页获取用户", notes = "根据参数分页获取用户信息")
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUsers(
            @ApiParam(value = "页码", defaultValue = "1") @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页加载量", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @ApiParam(value = "昵称") @RequestParam(value = "nickName", required = false) String nickName) {
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

    @ApiOperation(value = "用户新增", notes = "添加一个用户")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addUser(
            @ApiParam(name = "用户信息", required = true) @RequestBody User user) {
        try {
            int result = userService.addUser(user);
            return MessageUtil.success(result, HttpStatus.OK);
        } catch (Throwable throwable) {
            LOG.error(MessageEnum.FAIL_TO_CREATE.getMessage(), throwable);
            return MessageUtil.error(MessageEnum.FAIL_TO_CREATE, throwable);
        }
    }
}