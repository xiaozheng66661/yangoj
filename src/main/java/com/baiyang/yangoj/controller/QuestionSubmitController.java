package com.baiyang.yangoj.controller;

import com.baiyang.yangoj.annotation.AuthCheck;
import com.baiyang.yangoj.common.BaseResponse;
import com.baiyang.yangoj.common.ErrorCode;
import com.baiyang.yangoj.common.ResultUtils;
import com.baiyang.yangoj.constant.UserConstant;
import com.baiyang.yangoj.exception.BusinessException;
import com.baiyang.yangoj.model.dto.question.QuestionQueryRequest;
import com.baiyang.yangoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.baiyang.yangoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.baiyang.yangoj.model.entity.Question;
import com.baiyang.yangoj.model.entity.QuestionSubmit;
import com.baiyang.yangoj.model.entity.User;
import com.baiyang.yangoj.model.vo.QuestionSubmitVO;
import com.baiyang.yangoj.service.QuestionSubmitService;
import com.baiyang.yangoj.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author  
 * @from  
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、非提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
        long size = questionSubmitQueryRequest.getPageSize();
        long current = questionSubmitQueryRequest.getCurrent();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        // 返回脱敏信息
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }

}
