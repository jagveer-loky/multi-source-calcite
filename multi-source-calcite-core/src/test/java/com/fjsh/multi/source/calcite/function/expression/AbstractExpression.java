package com.fjsh.multi.source.calcite.function.expression;

import java.util.List;
import java.util.Objects;

public abstract class AbstractExpression implements Expression {
    private final List<Expression> children;
    public AbstractExpression(List<Expression> children) {
        this.children = children;
    }

    @Override
    public List<Expression> getChildren() {
        return children;
    }

    public boolean checkEquals(Expression other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        if (!Objects.equals(this.resultType(), other.resultType())) {
            return false;
        }
        if (this.getChildren().size() != other.getChildren().size()) {
            return false;
        }
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (!this.getChildren().get(i).checkEquals(other.getChildren().get(i))) {
                return false;
            }
        }
        return true;
    }
}
