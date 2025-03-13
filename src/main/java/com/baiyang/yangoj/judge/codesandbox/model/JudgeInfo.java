package com.baiyang.yangoj.judge.codesandbox.model;

import lombok.Data;

/**
 * 判题信息(提交后返回给你的)
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 消耗内存(KB)
     */
    private Long time;
    /**
     * 消耗时间(KB)
     */
    private Long memory;



}
