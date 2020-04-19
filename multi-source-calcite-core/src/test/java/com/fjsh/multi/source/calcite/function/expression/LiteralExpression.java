package com.fjsh.multi.source.calcite.function.expression;

import com.fjsh.multi.source.calcite.function.visitor.ExpressionVisitor;
import com.fjsh.multi.source.calcite.function.ValueTypeEnum;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.tools.RelBuilder;

import java.math.BigDecimal;
import java.util.Objects;

public class LiteralExpression extends AbstractLeafExpression {
    private final Object value;
    private final ValueTypeEnum resultType;

    public LiteralExpression(Object value, ValueTypeEnum resultType) {
        this.value = value;
        this.resultType = resultType;
    }

    public Object getValue() {
        return value;
    }

    public ValueTypeEnum getResultType() {
        return resultType;
    }

    @Override
    public ValueTypeEnum resultType() {
        return resultType;
    }

    @Override
    public RexNode toRexNode(RelBuilder relBuilder) {
        switch (resultType){
            case BIGDECIMAL:
                BigDecimal bigDecValue=(BigDecimal)value;
                RelDataType relDataType=relBuilder.getTypeFactory().createSqlType(SqlTypeName.DECIMAL);
                return relBuilder.getRexBuilder().makeExactLiteral(bigDecValue,relDataType);
            case LONG:
                BigDecimal bigInt=BigDecimal.valueOf((Long)value);
                return relBuilder.getRexBuilder().makeExactLiteral(bigInt);
            default:
                return relBuilder.literal(value);
        }    }

    @Override
    public <T, C, E extends Exception> T accept(ExpressionVisitor<T, C, E> visitor, C context) throws E {
        return visitor.visitLiteral(this,context);
    }

    @Override
    public boolean checkEquals(Expression other) {
        if(!super.checkEquals(other)){
            return false;
        }
        LiteralExpression otherLiteralExpression=(LiteralExpression)other;
        if(!Objects.equals(this.getValue(),otherLiteralExpression.getValue())){
            return false;
        }
        return true;
    }
}
