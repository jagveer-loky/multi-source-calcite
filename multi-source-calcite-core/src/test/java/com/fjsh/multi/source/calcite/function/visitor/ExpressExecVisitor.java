package com.fjsh.multi.source.calcite.function.visitor;

import com.fjsh.multi.source.calcite.function.EvalResult;
import com.fjsh.multi.source.calcite.function.ExpressExecContext;
import com.fjsh.multi.source.calcite.function.exception.FeatureExpressExecException;
import com.fjsh.multi.source.calcite.function.expression.AndExpression;
import com.fjsh.multi.source.calcite.function.expression.LiteralExpression;
import com.fjsh.multi.source.calcite.function.expression.MinusExpression;
import com.fjsh.multi.source.calcite.function.visitor.AbstractExpressionVisitor;
import org.apache.calcite.runtime.SqlFunctions;

public class ExpressExecVisitor extends AbstractExpressionVisitor<EvalResult, ExpressExecContext, FeatureExpressExecException> {


    @Override
    public EvalResult visitAnd(AndExpression andExpression, ExpressExecContext context) {
        EvalResult leftResult = andExpression.getLeft().accept(this, context);
        if (!leftResult.isSuccess()) {
            return leftResult;
        }
        boolean left = (boolean) leftResult.getValue();
        if (Boolean.FALSE.equals(left)) {
            return EvalResult.successResult(false);
        }
        EvalResult rightResult = andExpression.getRight().accept(this, context);
        if (!rightResult.isSuccess()) {
            return rightResult;
        }
        boolean right = (Boolean) rightResult.getValue();
        if (Boolean.FALSE.equals(right)) {
            return EvalResult.successResult(false);
        }
        if (Boolean.TRUE.equals(left) && Boolean.TRUE.equals(right)) {
            return EvalResult.successResult(true);
        }
        return EvalResult.successResult(false);

    }

    @Override
    public EvalResult visitLiteral(LiteralExpression literalExpression, ExpressExecContext context) throws FeatureExpressExecException {
        return EvalResult.successResult(literalExpression.resultType().convert(literalExpression.getValue()));
    }

    @Override
    public EvalResult visitMinus(MinusExpression minusExpression, ExpressExecContext context) throws FeatureExpressExecException {
        EvalResult left = minusExpression.getLeft().accept(this, context);
        if (!left.isSuccess()) {
            return left;
        }
        EvalResult right = minusExpression.getRight().accept(this, context);
        if (!right.isSuccess()) {
            return left;
        }
        Object leftValue = left.getValue();
        Object rightValue = right.getValue();
        Object result = SqlFunctions.minusAny(leftValue, rightValue);
        result = minusExpression.resultType().convert(result);
        return EvalResult.successResult(result);
    }
}
