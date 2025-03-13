package com.baiyang.yangoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baiyang.yangoj.judge.codesandbox.model.JudgeInfo;
import com.baiyang.yangoj.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;


@Data
public class QuestionSubmitVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态(0-待判题, 1-判题中, 2-成功, 3-失败)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交者信息
     */
    private UserVO userVO;
    /**
     * 对应题目信息
     */
    private QuestionVO questionVO;


    /**
     * 包装类转对象(包装类是给前端看的、此处是为了方便存储到数据库中)
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo voJudgeInfo = questionSubmitVO.getJudgeInfo();
        if (voJudgeInfo != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(voJudgeInfo));
        }

        return questionSubmit;
    }

    /**
     * 对象转包装类(questionSubmit 转 VO (可以展示给前端的数据))
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        questionSubmitVO.setJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class));

        return questionSubmitVO;
    }
}