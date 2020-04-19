package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.ValueTypeEnum;
import com.fjsh.multi.source.calcite.function.visitor.ExpressionVisitor;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.tools.RelBuilder;

import java.io.Serializable;
import java.util.List;

public interface Expression extends Serializable {

    /**
    * @Description: 子表达式
    * @Param:
    * @return:
    */
    List<Expression> getChildren();
    /**
    * @Description: 返回数据类型
    * @Param:
    * @return:
    */
    ValueTypeEnum resultType();
    /**
    * @Description: 转换为RexNode类型
    * @Param:
    * @return:
    */
    RexNode toRexNode(RelBuilder relBuilder);
    /**
    * @Description: 访问数据信息
    * @Param: T 返回值泛型，C 上下文泛型， E 异常信息泛型
    * @return:
    */
    <T,C,E extends Exception> T accept(ExpressionVisitor<T,C,E > visitor, C context) throws E;
    /**
    * @Description: 判断是否相等
    * @Param:
    * @return:
    */
    boolean checkEquals(Expression other);
}
