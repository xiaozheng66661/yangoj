package com.baiyang.yangoj.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目更新请求(管理员)
 *
 * @author  
 * @from  
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;


    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCase;

    private static final long serialVersionUID = 1L;
}