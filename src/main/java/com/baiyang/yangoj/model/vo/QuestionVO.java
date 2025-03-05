package com.baiyang.yangoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baiyang.yangoj.model.dto.question.JudgeConfig;
import com.baiyang.yangoj.model.entity.Question;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@TableName(value = "question")
@Data
public class QuestionVO implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 标签列表 (为了方便返回给前端，所以是List)
     */
    private List<String> tags;
    /**
     * 题目提交数
     */
    private Integer submitNum;
    /**
     * 题目通过数
     */
    private Integer acceptedNum;
    /**
     * 判题配置(json对象)
     */
    private JudgeConfig judgeConfig;
    /**
     * 点赞数
     */
    private Integer thumbNum;
    /**
     * 收藏数
     */
    private Integer favourNum;
    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建题目人的信息
     */
    private UserVO userVO;


    /**
     * 包装类转对象(包装类是给前端看的、此处是为了方便存储到数据库中)
     *
     * @param questionVO
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList = questionVO.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }

        JudgeConfig voJudgeConfig = questionVO.getJudgeConfig();
        if (voJudgeConfig != null){
            question.setJudgeConfig(JSONUtil.toJsonStr(voJudgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类(question 转 VO (可以展示给前端的数据))
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setTags(JSONUtil.toList(question.getTags(), String.class));

        String judgeConfigStr = question.getJudgeConfig();
        questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, JudgeConfig.class));
        return questionVO;
    }
}