package com.baiyang.yangoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baiyang.yangoj.model.entity.Question;
import com.baiyang.yangoj.service.QuestionService;
import com.baiyang.yangoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 29413
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2025-03-02 21:01:10
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




