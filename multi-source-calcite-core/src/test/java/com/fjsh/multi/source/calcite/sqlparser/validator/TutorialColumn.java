package com.fjsh.multi.source.calcite.sqlparser.validator;

import lombok.Data;

import java.io.Serializable;

/**
 * 列信息

 */
@Data
public class TutorialColumn implements Serializable {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型名称
     */
    private String type;

}
