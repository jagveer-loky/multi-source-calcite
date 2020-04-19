package com.fjsh.multi.source.calcite.function.visitor;

import com.fjsh.multi.source.calcite.function.expression.AndExpression;
import com.fjsh.multi.source.calcite.function.expression.LiteralExpression;
import com.fjsh.multi.source.calcite.function.expression.MinusExpression;

public interface ExpressionVisitor<T,C,E extends Exception> {

    T visitAnd(AndExpression andExpression, C context);
    T visitLiteral(LiteralExpression literalExpression, C context) throws E;
    T visitMinus(MinusExpression minusExpression, C context);
}
