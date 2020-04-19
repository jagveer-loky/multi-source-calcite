package com.fjsh.multi.source.calcite.function;

import com.fjsh.multi.source.calcite.function.expression.AndExpression;
import com.fjsh.multi.source.calcite.function.expression.LiteralExpression;
import com.fjsh.multi.source.calcite.function.expression.MinusExpression;
import com.fjsh.multi.source.calcite.function.visitor.ExpressExecVisitor;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionVisitorTest {
    ExpressExecVisitor expressExecVisitor=new ExpressExecVisitor();
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void visitAnd() {
        LiteralExpression left=new LiteralExpression(true,ValueTypeEnum.BOOLEAN);
        LiteralExpression right=new LiteralExpression(false,ValueTypeEnum.BOOLEAN);
        AndExpression andExpression=new AndExpression(left,right);
        ExpressExecContext context=new ExpressExecContext(ImmutableMap.of());
        EvalResult evalResult=andExpression.accept(expressExecVisitor,context);
        Assert.assertEquals(false,evalResult.getValue());
    }
    @Test
    public void visitMinus() {
        LiteralExpression left=new LiteralExpression(3,ValueTypeEnum.INT);
        LiteralExpression right=new LiteralExpression(2,ValueTypeEnum.INT);
        MinusExpression minusExpression=new MinusExpression(left,right);
        ExpressExecContext context=new ExpressExecContext(ImmutableMap.of());
        EvalResult evalResult=minusExpression.accept(expressExecVisitor,context);
        Assert.assertEquals(1,evalResult.getValue());
    }

    @Test
    public void visitLiteral() {
    }
}