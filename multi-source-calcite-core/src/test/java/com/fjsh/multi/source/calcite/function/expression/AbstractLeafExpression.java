package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.expression.AbstractExpression;
import com.google.common.collect.ImmutableList;

public abstract class AbstractLeafExpression  extends AbstractExpression {
    protected AbstractLeafExpression(){
        super(ImmutableList.of());
    }
}
