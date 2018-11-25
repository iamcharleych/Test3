package com.chaplin.test3.core.utils;

import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public final class ReflectionUtils {

    @NonNull
    public static <T> T createProxyStubImpl(final @NonNull Class<T> clazz) {
        // noinspection unchecked
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class [] {clazz},
                (proxy, method, args) -> new Object()
        );
    }

    public static Object createProxyStubFromGenericTypeClassImpl(final @NonNull Class<?> clazz) {
        return createProxyStubImpl(getGenericTypeClass(clazz));
    }

    /**
     * see https://stackoverflow.com/questions/3437897/how-to-get-a-class-instance-of-generics-type-t
     * @param clazz class to get generic type from
     * @return generic class
     */
    private static Class<?> getGenericTypeClass(final @NonNull Class<?> clazz) {
        final Type mySuperclass = clazz.getGenericSuperclass();
        final Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
        return (Class<?>) tType;
    }
}
