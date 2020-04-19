package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.visitor.ExpressionVisitor;
import com.fjsh.multi.source.calcite.function.ValueTypeEnum;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.tools.RelBuilder;

public class AndExpression extends AbstractBinaryExpression {
    public AndExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public ValueTypeEnum resultType() {
        return ValueTypeEnum.BOOLEAN;
    }

    @Override
    public RexNode toRexNode(RelBuilder relBuilder) {
        return relBuilder.and(getLeft().toRexNode(relBuilder),getRight().toRexNode(relBuilder));
    }

    @Override
    public <T, C, E extends Exception> T accept(ExpressionVisitor<T, C, E> visitor, C context) throws E {
        return visitor.visitAnd(this,context);
    }
}
