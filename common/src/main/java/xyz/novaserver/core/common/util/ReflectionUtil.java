package xyz.novaserver.core.common.util;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class ReflectionUtil {

    /**
     * @param prefix The package prefix to search for subtypes in
     * @param type   The class to search for subtypes of
     * @return A set of classes within the given package that extend the type
     */
    public static <T> Set<Class<? extends T>> getSubTypes(String prefix, Class<T> type, ClassLoader classLoader) {
        return new Reflections(new ConfigurationBuilder()
                .forPackage(prefix, classLoader)
                .setScanners(Scanners.SubTypes))
                .getSubTypesOf(type);
    }
}
