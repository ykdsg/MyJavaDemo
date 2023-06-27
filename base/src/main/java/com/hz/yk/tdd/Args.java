package com.hz.yk.tdd;

import com.google.common.collect.Maps;
import com.hz.yk.tdd.exception.IllegalOptionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wuzheng.yk
 * @date 2023/6/5
 */
public class Args<T> {

    private static Map<Class<?>, OptionParser> PARSERS = Maps.newHashMap();
    static {
        PARSERS.put(boolean.class, OptionParsers.bool());
        PARSERS.put(int.class, OptionParsers.unary(Integer::parseInt, 0));
        PARSERS.put(String.class, OptionParsers.unary(String::valueOf, ""));
        PARSERS.put(String[].class, OptionParsers.list(String[]::new, String::valueOf));
        PARSERS.put(Integer[].class, OptionParsers.list(Integer[]::new, Integer::parseInt));
    }

    private Class<T> optionsClass;
    private Map<Class<?>, OptionParser> parserMap;

    public Args(Class<T> optionsClass, Map<Class<?>, OptionParser> parserMap) {
        this.optionsClass = optionsClass;
        this.parserMap = parserMap;
    }

    
    public static <T> T parse(Class<T> optionsClass, String... args) {
        return new Args<T>(optionsClass,PARSERS).parse(args);
    }

    //将静态方法转为调用实例方法
    //这种将所有直接耦合都视为坏味道的设计取向，会将功能需求的上下文打散到一组细碎的对象群落中，增加理解的难度。最终滑向过度设计（Over Design）的深渊。
    @NotNull
    public T parse(String... args) {
        try {
            final List<String> arguments = Arrays.asList(args);
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            final Object[] values = Arrays.stream(constructor.getParameters()).map(p -> parseOption(p, arguments,
                                                                                                    parserMap))
                    .toArray();

            return (T) constructor.newInstance(values);
        } catch (IllegalOptionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private static Object parseOption(Parameter parameter, List<String> arguments,
            Map<Class<?>, OptionParser> parserMap) {
        final Option option = parameter.getAnnotation(Option.class);
        if (option == null) {
            throw new IllegalOptionException(parameter.getName());
        }
        final Class<?> type = parameter.getType();

        OptionParser parser = parserMap.get(type);
        return parser.parse(arguments, option);
    }

    

}
