package com.fjsh.multi.source.calcite.function.expression;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public  abstract  class AbstractBinaryExpression  extends AbstractExpression{
    private final Expression left;
    private final Expression right;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public AbstractBinaryExpression( Expression left, Expression right) {
        super(ImmutableList.copyOf(Lists.newArrayList(left,right)));
        this.left = left;
        this.right = right;
    }
}
