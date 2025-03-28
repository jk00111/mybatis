package com.example.mybatis.config.converter;

import com.example.mybatis.config.typeHandler.Enumerable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToEnumTypeConverterFactory implements ConverterFactory<String, Enum<? extends Enumerable>> {

    @Override
    public <T extends Enum<? extends Enumerable>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumTypeConverter<>(targetType);
    }

    private static final class StringToEnumTypeConverter<T extends Enum<? extends Enumerable>> implements Converter<String, T> {

        private final Class<T> targetType;

        private StringToEnumTypeConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (targetType == null) {
                throw new IllegalArgumentException("type is null.");
            }

            T[] enumConstants = targetType.getEnumConstants();

            if (enumConstants == null) {
                throw new IllegalArgumentException("enum constants of type is null.");
            }

            for (T enumConstant : enumConstants) {
                 if (enumConstant.name().equals(source)){
                     return enumConstant;
                 }
            }
            throw new IllegalArgumentException("not registered EnumeratedValueType : " + source);
        }
    }
}
