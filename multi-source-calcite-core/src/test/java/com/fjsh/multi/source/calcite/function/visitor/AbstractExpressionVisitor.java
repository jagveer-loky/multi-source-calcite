package com.fjsh.multi.source.calcite.function.visitor;

import com.fjsh.multi.source.calcite.function.expression.AndExpression;
import com.fjsh.multi.source.calcite.function.expression.LiteralExpression;
import com.fjsh.multi.source.calcite.function.expression.MinusExpression;

public abstract class AbstractExpressionVisitor<T,C,E extends Exception> implements ExpressionVisitor<T,C,E> {
    @Override
    public T visitAnd(AndExpression andExpression, C context) {
        return visitAnd(andExpression,context);
    }

    @Override
    public T visitLiteral(LiteralExpression literalExpression, C context) throws E {
        return visitLiteral(literalExpression,context);
    }

    @Override
    public T visitMinus(MinusExpression minusExpression, C context) {
        return visitMinus(minusExpression,context);
    }
}
