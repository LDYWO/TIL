# Generics in Java
## 1. Generics
> 제네릭은 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시 **타입 체크**를 해주는 기능이다.

- 클래스 내부에서 사용할 **데이터 타입**을 나중에 인스턴스를 생성할 때 확정하는 것을 **제네릭**이라 한다.
- 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형 변환의 번거로움이 줄어든다.
- ArrayList와 같은 컬렉션 클래스는 다양한 종류의 객체를 담을 수 있긴하지만 보통 한 종류의 객체를 담는 경우가 많다.
- 그런데, 꺼낼 때 마다 타입 체크를 하고 형 변환을 하는 것은 아무래도 불편할 수 밖에 없다.

```Java
class Person<T> {
  public T info;
}

Person<String> p1 = new Person<String>();

Person<StringBuilder> p2 = new Person<StringBuilder>();
```
- 즉, 클래스를 정의 할 때는 데이터 타입을 정하지 않고, 인스턴스를 생성할 때 데이터 타입을 지정하는 기능이다.

### 1.1 제네릭의 장점
- 타입 안정성을 제공한다.
- 타입 체크와 형 변환을 생략할 수 있으므로 코드가 간결해진다.
- 타입을 지정하지 않으면 Object 타입으로 간주된다.

### 1.2 컬렉션 클래스에 특정 타입의 객체 선언하기
```Java
ArrayList<TV> tvList = new ArrayList<TV>();

tvList.add(new TV());
tvList.add(new Radio()); // 컴파일 에러
```

### 1.3 다형성 적용
- 다형성을 사용해야하는 경우에는 부모 타입을 지정함으로써 여러 종류의 객체를 저장할 수 있다.
```Java
class Product{}
class TV extends Product{}
class Audido extends Product{}

// Product 클래스의 자손 객체들을 저장할 수 있는 ArrayList를 생성
ArrayList<Product> list = new ArrayList<Product>();
    list.add(new Product());
    list.add(new TV());
    list.add(new Audio());

Product p = list.get(0); // 형 변환이 필요 없다.
TV t = (TV).list.get(i); // 형 변환을 필요로 한다.
```
- ArrayList가 Product 타입의 객체를 저장하도록 지정하면, 이들의 자손인 TV와 Audio 타입의 객체도 저장할 수 있다.
- 하지만, 꺼내올 때 원래의 타입으로 형 변환을 해야한다.

```Java
ArrayList<Produdct> list = new ArrayList<TV>(); // 허용 안 함
List<TV> tvList = new ArrayList<TV>(); // 허용 된다.
```
- Product 클래스가 TV 클래스의 조상이라 할지라도 위와 같이는 할 수 없다.

### 1.4 와일드 카드
- 보통 제네릭에서는 단 하나의 타입을 지정한다.
- 하지만 와일드카드 ?를 사용하면 하나 이상의 타입 지정이 가능하다.
```Java
public static void printAll (ArrayList<? extends Product> list){
    for (Unit u : list) {
        System.out.println(u);
  }
}
```
- 위와 같은 경우는 Product를 상속받는 ```ArrayList<TV>```, ```ArrayList<Audio>```를 매개 변수로 넘겨줄 수 있다.

### 1.5 복수의 제네릭
- 클래스 내에서 여러 개의 제네릭을 필요로 하는 경우가 있다.
```Java
class EmployeeInfo {
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}

// 복수의 제네릭을 사용할 시 에는 ','를 사용한다.
class Person<T, S> {
    public T info;
    public S id;

    Person(T info, S id) {
        this.info = info;
        this.id = id;
  }
}

public class GenericDemo {
    public static void main(String[] args){
        Person<EmployeeInfo, int> p1 = new Person<EmployeeInfo, int>(new EmployeeInfo(1), 1);
  }
}
```
- 복수의 제네릭을 사용할 때는 <T, S>와 같은 형식을 사용한다.

### 1.6 기본 데이터 타입과 제네릭
- 제네릭은 **참조 데이터 타입**에 대해서만 사용할 수 있다.
- **기본 데이터 타입**에서는 사용할 수 없다.
- 기본 데이터 타입을 객체 타입으로 만드는 Wrapper 클래스를 사용하면 사용할 수 있다.

```Java
class EmployeeInfo {
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}

class Person<T, S> {
    public T info;
    public S id;
    Person(T info, S id){
        this.info = info;
        this.id = id;
  }
}

public class GeneticDemo {
    public static void main(String[] args) {
        EmployeeInfo e = new EmployeeInfo(1);
        Integer i = new Integer(10);
        Person<EmployeeInfo, Integer> p1 = new Person<EmployeeInfo, Integer>(e, i);
        System.out.println(p1.id.intValue());
    }
}
```
- new Integer는 기본 데이터 타입인 int를 참조 데이터 타입으로 변환하여 준다.
- 이러한 클래스를 Wrapper 클래스라고 한다.

### 1.7 제네릭의 생략
- 제네릭은 메소드에 적용할 수 있다.
```Java
class EmployeeInfo{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}

class Person<T, S>{
    public T info;
    public S id;

    Person(T info, S id){
        this.info = info;
        this.id = id;
    }

    public <U> void printInfo(U info){// U 데이터 타입은 info라는 매개변수의 데이터타입(EmployeeInfo)이 된다.
        System.out.println(info);
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        EmployeeInfo e = new EmployeeInfo(1);
        Integer i = new Integer(10);
        Person<EmployeeInfo, Integer> p1 = new Person<EmployeeInfo, Integer>(e, i);
        p1.<EmployeeInfo>printInfo(e);//
        p1.printInfo(e);// 제네릭 생략이 가능함
    }
}
```

### 1.8 제네릭의 제한
- 제네릭으로 올 수 있는 데이터 타입을 특정 클래스의 자식으로 제한할 수 있다.
```Java
abstract class Info{//부모 클래스가 반드시 추상클래스일 필요가 없다.
    public abstract int getLevel();
}

class EmployeeInfo extends Info{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
    public int getLevel(){
        return this.rank;
    }
}

// info 클래스나 info의 자식클래스만이 제네릭으로 올 수 있는 데이터 타입이 된다.
// (info의 자식이면 OK, 자식이 아니면 컴파일 에러)
class Person<T extends Info>{
    public T info;
    Person(T info){ this.info = info; }
}

public class GenericDemo {
    public static void main(String[] args) {
        Person p1 = new Person(new EmployeeInfo(1));
        Person<String> p2 = new Person<String>("부장");
    }
}
```
- 위 부분에서 ```Person<T extends Info>```에는 Info나 그 자식 외에는 올 수 없다는 것이다.
- 상속 뿐만 아니라 구현의 관계에서도 사용할 수 있다.

***
## 2. 참고
- [Generics](http://devbox.tistory.com/entry/Java-%EC%A0%9C%EB%84%A4%EB%A6%AD)
- [웹 개발 블로그](http://doublesprogramming.tistory.com/191)
