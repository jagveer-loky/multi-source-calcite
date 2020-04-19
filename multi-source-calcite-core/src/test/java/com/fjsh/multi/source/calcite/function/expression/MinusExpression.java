package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.visitor.ExpressionVisitor;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;

public class MinusExpression extends AbstractBinaryArithmeticExpression {
    public MinusExpression(Expression left, Expression right) {
        super(left, right, SqlStdOperatorTable.MINUS);
    }

    @Override
    public <T, C, E extends Exception> T accept(ExpressionVisitor<T, C, E> visitor, C context) throws E {
        return visitor.visitMinus(this,context);
    }
}
