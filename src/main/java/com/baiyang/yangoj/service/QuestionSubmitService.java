package com.baiyang.yangoj.service;

import com.baiyang.yangoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.baiyang.yangoj.model.entity.QuestionSubmit;
import com.baiyang.yangoj.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 29413
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2025-03-02 21:01:43
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);



}
