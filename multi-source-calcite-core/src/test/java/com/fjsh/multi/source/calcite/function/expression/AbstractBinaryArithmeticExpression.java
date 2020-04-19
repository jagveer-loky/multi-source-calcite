package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.ValueTypeEnum;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.tools.RelBuilder;

public abstract class AbstractBinaryArithmeticExpression extends AbstractBinaryExpression {
    private final SqlOperator sqlOperator;

    public SqlOperator getSqlOperator() {
        return sqlOperator;
    }

    public AbstractBinaryArithmeticExpression(Expression left, Expression right, SqlOperator sqlOperator) {
        super(left, right);
        this.sqlOperator = sqlOperator;
    }

    @Override
    public RexNode toRexNode(RelBuilder relBuilder) {
        return relBuilder.call(sqlOperator, getLeft().toRexNode(relBuilder), getRight().toRexNode(relBuilder));
    }

    @Override
    public ValueTypeEnum resultType() {
        ValueTypeEnum leftResultType = getLeft().resultType();
        ValueTypeEnum rightResultType = getRight().resultType();
        if (ValueTypeEnum.STRING.equals(leftResultType) || ValueTypeEnum.STRING.equals(rightResultType)) {
            return ValueTypeEnum.STRING;
        }
        if (ValueTypeEnum.BIGDECIMAL.equals(leftResultType) || ValueTypeEnum.BIGDECIMAL.equals(rightResultType)) {
            return ValueTypeEnum.BIGDECIMAL;
        }
        if (ValueTypeEnum.DOUBLE.equals(leftResultType) || ValueTypeEnum.DOUBLE.equals(rightResultType)) {
            return ValueTypeEnum.DOUBLE;
        }
        if (ValueTypeEnum.LONG.equals(leftResultType) || ValueTypeEnum.LONG.equals(rightResultType)) {
            return ValueTypeEnum.LONG;
        }
        if (ValueTypeEnum.INT.equals(leftResultType) || ValueTypeEnum.INT.equals(rightResultType)) {
            return ValueTypeEnum.INT;
        }

        throw new RuntimeException("get type exception");
    }
}
