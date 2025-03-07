package com.baiyang.yangoj.model.dto.questionsubmit;

import com.baiyang.yangoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author
 * @from
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 题目 id
     */
    private Long questionId;
    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 提交状态
     */
    private Integer status;
    /**
     * 编程语言
     */
    private String language;
}