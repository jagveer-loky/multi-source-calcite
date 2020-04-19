package com.fjsh.multi.source.calcite.function;

import java.math.BigDecimal;

public enum ValueTypeEnum {
    INT(Integer.class) {
        @Override
        public Integer convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof Integer) {
                return (Integer) origin;
            }
            if (origin instanceof Number) {
                return ((Number) origin).intValue();
            }
            String originString = origin.toString();
            return Integer.valueOf(originString);
        }
    },
    BOOLEAN(Boolean.class) {
        @Override
        public Boolean convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof Boolean) {
                return (Boolean) origin;
            }
            String originString = origin.toString();
            return Boolean.valueOf(originString);
        }
    },
    LONG(Long.class) {
        @Override
        public Long convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof Long) {
                return (Long) origin;
            }
            if (origin instanceof Number) {
                return ((Number) origin).longValue();
            }
            String originString = origin.toString();
            return Long.valueOf(originString);
        }
    },
    STRING(String.class) {
        @Override

        public String convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof String) {
                return (String) origin;
            }
            return origin.toString();
        }
    },
    DOUBLE(Double.class) {
        @Override

        public Double convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof Double) {
                return (Double) origin;
            }
            if (origin instanceof Number) {
                return ((Number) origin).doubleValue();
            }
            String originString = origin.toString();
            return Double.valueOf(originString);
        }
    },
    BIGDECIMAL(BigDecimal.class) {
        @Override

        public BigDecimal convert(Object origin) {
            if (origin == null) {
                return null;
            }
            if (origin instanceof BigDecimal) {
                return (BigDecimal) origin;
            }
            String originString = origin.toString();
            return new BigDecimal(originString);
        }
    },
    OBJECT(Object.class) {
        @Override
        public Object convert(Object origin) {
            return origin;
        }
    };


    private final Class<?> classType;

    ValueTypeEnum(Class<?> classType) {
        this.classType = classType;
    }

    public static ValueTypeEnum classOf(Class<?> returnClassType) {
        for (ValueTypeEnum valueTypeEnum : ValueTypeEnum.values()) {
            if (valueTypeEnum.getClassType().equals(returnClassType)) {
                return valueTypeEnum;
            }
        }
        return null;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public boolean isNumeric() {
        return INT.equals(this) || LONG.equals(this) || DOUBLE.equals(this) || BIGDECIMAL.equals(this);
    }

    /**
     * @Description: 转换为对于的类型信息
     * @Param:
     * @return:
     */
    public abstract <T> T convert(Object origin);
}
