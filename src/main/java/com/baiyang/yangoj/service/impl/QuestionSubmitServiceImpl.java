package com.baiyang.yangoj.service.impl;

import com.baiyang.yangoj.common.ErrorCode;
import com.baiyang.yangoj.exception.BusinessException;
import com.baiyang.yangoj.mapper.QuestionSubmitMapper;
import com.baiyang.yangoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.baiyang.yangoj.model.entity.Question;
import com.baiyang.yangoj.model.entity.QuestionSubmit;
import com.baiyang.yangoj.model.entity.User;
import com.baiyang.yangoj.model.enums.QuestionSubmitLanguageEnum;
import com.baiyang.yangoj.model.enums.QuestionSubmitStatusEnum;
import com.baiyang.yangoj.service.QuestionService;
import com.baiyang.yangoj.service.QuestionSubmitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 29413
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2025-03-02 21:01:43
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {
    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言不合法");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交数据
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        // 设置初始状态

        questionSubmit.setStatus(QuestionSubmitStatusEnum.PENDING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据插入失败");

        }
        return questionSubmit.getId();
    }
}




