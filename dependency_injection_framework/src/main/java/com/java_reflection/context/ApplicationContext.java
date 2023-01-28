package com.java_reflection.context;

import com.java_reflection.annotation.AutoComponent;
import com.java_reflection.annotation.AutoComponentScan;
import com.java_reflection.annotation.AutoConfig;
import com.java_reflection.annotation.AutoInject;
import com.java_reflection.config.AppConfig;
import com.java_reflection.service.ProductService;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * this is kind of container
 * we store data in a hashmap
 */
@AutoComponent
public class ApplicationContext {

    /**
     * when application start up this container is created
     * the spring finds out all the required resources and fills up this hashmap container
     * with those resources
     * so here when the constructor calls.
     */
    private static HashMap<Class<?>, Object> map = new HashMap<>();

    public ApplicationContext(Class<AppConfig> appConfigClass) {
        Spring.initializeSpringContext(appConfigClass);
    }

    /**
     * method for create bean instance
     */
    public <T> T getBean(Class<T> classObj) {
        T object = (T) map.get(classObj);
        // we need to check that field has @AutoInject annotation
        Field[] declaredFields = classObj.getDeclaredFields();
        injectBean(object, declaredFields);
        return object;
    }

    /**
     * inject inner beans of class
     */
    private <T> void injectBean(T object, Field[] declaredFields) {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(AutoInject.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObj = map.get(type);
                try {
                    // setting inner field of main object
                    field.set(object, innerObj);

                    // what happen when inner beans have its own @ÁutoInject fields
                    // we need to inject them also
                    // so we need to use recursive approach here
                    Field[] innerDeclaredFields = type.getDeclaredFields();
                    injectBean(innerObj, innerDeclaredFields);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * this is backbone method of dependency injection
     */
    private static class Spring {

        /**
         * if a class not AutoConfig we are not going to read the beans of that class
         * we need to find beans inside AutoComponentScan annotation parameter
         */
        public static void initializeSpringContext(Class<?> classObj) {
            if (!classObj.isAnnotationPresent(AutoConfig.class)) {
                throw new RuntimeException("The file is not a AutoConfiguration file.");
            } else {
                AutoComponentScan annotation = classObj.getAnnotation(AutoComponentScan.class);
                String value = annotation.value();

                // this list contain our compiled java class locations
                List<String> packageList = getPackageList(value);
                List<File> fileList = packageList.stream().map(packageName -> new File(packageName)).collect(Collectors.toList());

                // lets find classes in package structure
                List<File> files = findClasses(fileList);

                if (files != null) {
                    for (File file : files) {
                        // here we have issue we need to change value here
                        // otherwise app will trow exception
                        String name = value + "." + file.getName().replace(".class", "");
                        try {
                            Class<?> loadingClass = Class.forName(name);

                            // check loadingClass has @AutoComponent annotation
                            if (loadingClass.isAnnotationPresent(AutoComponent.class)) {
                                Constructor<?> constructor = loadingClass.getConstructor();
                                Object instance = constructor.newInstance();
                                map.put(loadingClass, instance);
                            }

                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        private static List<String> getPackageList(String value) {
            return Stream.of(
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/"),
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/annotation",
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/config",
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/context",
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/pojo",
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/repository",
                    "dependency_injection_framework/target/classes/" + value.replace(".", "/") + "/service"
            ).collect(Collectors.toList());

        }

        private static  List<File> findClasses(List<File> files) {
            List<File> classFiles = new ArrayList<>();
            for (File file : files) {
                if (!file.exists()) {
                    throw new RuntimeException("Package " + file + " does not exist");
                } else {
                    File[] classFileArr = file.listFiles(f -> f.getName().endsWith(".class"));
                   for (File f:classFileArr){
                       classFiles.add(f);
                   }
                }
            }
            return classFiles;
        }
    }
}
