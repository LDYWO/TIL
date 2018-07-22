## 1. Annotation in JAVA
> JEE5 부터 새롭게 추가된 요소이다. 어노테이션의 본질적인 목적은 소스코드에 메타데이터를 표현하는 것이다.
- **리플렉션(Reflection)** 을 이용하면 어노테이션 지정만으로 원하는 클래스를 주입할 수 있다.
- 클래스나 메소드 위에 붙는다.
- @(at) 기호로 이름이 시작된다.
- 클래스가 컴파일되거나 실행될 때 어노테이션의 유무나 설정된 값을 통해 클래스가 다르게 실행될 수 있다.
- 어노테이션은 기본적으로 제공해주는 방식과, 사용자가 직접 설정하는 방식이 있다.

## 1.1 Built-in Annotation
> 자바에서는 기본적으로 제공하는 어노테이션들이 존재한다.
- ```@Override```: 메소드가 오버라이드 됐는지 검증한다. 만약 부모클래스 또는 구현해야할 인터페이스에서 해당 메소드를
찾을 수 없다면 컴파일 오류가 난다.
- ```@Deprecated```: 메소드를 사용하지 말도록 유도한다. 만약 사용한다면 컴파일 경고를 일으킨다.
- ```@SuppressWarning```: 컴파일 경고를 무시하도록 한다.
- ```@SafeVarargs```: 제네릭같은 가변인자 매개변수를 사용할 때 경고를 무시한다. (자바 7이상)
- ```@FunctionalInterface```: 람다 함수등을 위한 인터페이스를 지정한다. 메소드가 없거나 두개 이상이 되면 컴파일 오류가 난다.

## 1.2 Meta Annotation
> 위의 기본 어노테이션 이외에도 메타 어노테이션이 있다. 이를 바탕으로 커스텀 어노테이션을 만들 수 있다.
- ```@Retention```: 어노테이션 범위라고 할 수 있다. 어떤 시점까지 어노테이션이 영향을 미치는지 결정한다.
- ```@Documented```: 문서에 어노테이션의 정보가 표현된다.
- ```@Target```: 어노테이션이 적용할 위치를 결정한다.
- ```@Inherited```: 이 어노테이션을 선언하면 자식 클래스가 어노테이션을 상속 받을 수 있다.
- ```@Repeatable```: 반복적으로 어노테이션을 선언할 수 있다.

## 1.3 Declare Custom Annotation
- 커스텀 어노테이션을 선언하는 방법은 다음과 같다.
```JAVA
public @interface MyAnnotation {}
```
```JAVA
import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해서 참조가 가능합니다.
//@Retention(RetentionPolicy.CLASS) // 컴파일러가 클래스를 참조할 때까지 유효합니다.
//@Retention(RetentionPolicy.SOURCE) // 어노테이션 정보는 컴파일 이후 없어집니다.
@Target({
        ElementType.PACKAGE, // 패키지 선언시
        ElementType.TYPE, // 타입 선언시
        ElementType.CONSTRUCTOR, // 생성자 선언시
        ElementType.FIELD, // 멤버 변수 선언시
        ElementType.METHOD, // 메소드 선언시
        ElementType.ANNOTATION_TYPE, // 어노테이션 타입 선언시
        ElementType.LOCAL_VARIABLE, // 지역 변수 선언시
        ElementType.PARAMETER, // 매개 변수 선언시
        ElementType.TYPE_PARAMETER, // 매개 변수 타입 선언시
        ElementType.TYPE_USE // 타입 사용시
})
public @interface MyAnnotation {
    /* enum 타입을 선언할 수 있습니다. */
    public enum Quality {BAD, GOOD, VERYGOOD}
    /* String은 기본 자료형은 아니지만 사용 가능합니다. */
    String value();
    /* 배열 형태로도 사용할 수 있습니다. */
    int[] values();
    /* enum 형태를 사용하는 방법입니다. */
    Quality quality() default Quality.GOOD;
}
```
***
## 2. Simple Example
- 다음 예제는 ```@StringInjector```라는 간단한 커스텀 어노테이션을 만들기 위함이다.
- 이 어노테이션은 멤버 변수 선언시 해당 멤버 변수 타입이 ```String```이라면 정의된 값을 주입한다.

### 2.1 어노테이션 선언
```JAVA
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String 문자열을 주입하기 위해 선언하는 어노테이션입니다.
 * FIELD에만 선언가능하고 JVM이 어노테이션 정보를 참조합니다.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringInjector {
    String value() default "This is StringInjector.";
}
```
### 2.2 어노테이션을 적용할 클래스
```JAVA
public class MyObject {
    @StringInjector("My name is JDM.")
    private String name;

    @StringInjector
    private String defaultValue;

    @StringInjector
    private Integer invalidType;

    public String getName() {
        return name;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public Integer getInvalidType() {
        return invalidType;
    }
}
```
### 2.3 어노테이션을 찾고 주입하는 컨테이너 클래스
```JAVA
import java.lang.reflect.Field;

public class MyContextContainer {

    public MyContextContainer(){}

    /**
     * 객체를 반환하기 전 어노테이션을 적용합니다.
     * @param instance
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private <T> T invokeAnnonations(T instance) throws IllegalAccessException {
        Field [] fields = instance.getClass().getDeclaredFields();
        for( Field field : fields ){
            StringInjector annotation = field.getAnnotation(StringInjector.class);
            if( annotation != null && field.getType() == String.class ){
                field.setAccessible(true);
                field.set(instance, annotation.value());
            }
        }
        return instance;
    }

    /**
     * 매개변수로 받은 클래스의 객체를 반환합니다.
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T get(Class clazz) throws IllegalAccessException, InstantiationException {
        T instance = (T) clazz.newInstance();
        instance = invokeAnnonations(instance);
        return instance;
    }
}
```
### 2.4 테스트 클래스
```JAVA
public class AnnotationDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // 컨텍스트 컨테이너를 초기화 합니다.
        MyContextContainer demo = new MyContextContainer();

        // MyOjbect 객체를 하나 받아옵니다.
        MyObject obj = demo.get(MyObject.class);

        System.out.println(obj.getName()); // print is "My name is JDM."
        System.out.println(obj.getDefaultValue()); // print is "This is StringInjector."
        System.out.println(obj.getInvalidType()); // print is "null".
    }
}
```

***

## 3. 참고
- [Annotation](https://jdm.kr/blog/216)
