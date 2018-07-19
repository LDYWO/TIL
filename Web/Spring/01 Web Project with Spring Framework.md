# Web Project with Spring Framework
## 1. Spring Framework
### 1.1 Spring Framework
> 자바 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크
- **자바(JAVA) 플랫폼**을 위한 **오픈 소스 애플리케이션 프레임워크(Framework)**
- **자바 개발을 위한 프레임워크**로 **종속 객체를 생성**해주고, **조립**해주는 도구
- **자바로 된 프레임워크**로 자바SE로 된 자바 객체(POJO) 를 **JAVA EE에 의존적이지 않게 해주는 역할**

![image](https://t1.daumcdn.net/cfile/tistory/21622F4254DF52EC06)
```
 * POJO: Plain Old Java Object의 약자, 자바빈즈(Javabeans) 객체를 의미한다.
```
### 1.2 스프링의 특징
- **크기와 부하의 측면에서 경량**이다.
- **제어 역행(IoC)** 이라는 기술을 통해 애플리케이션의 느슨한 결합을 도모한다.
- **관점지향(AOP) 프로그래밍**을 위한 풍부한 지원을 한다.
- 애플리케이션 **객체의 생명 주기와 설정을 포함하고 관리**한다는 점에서 일종의 **컨테이너(Container)** 라고 할 수 있다.

**자세히 설명하면 다음과 같다.**
```
 1) 경량 컨테이너로써 자바 객체를 직접 관리한다.
    - 각각의 객체 생성, 소멸과 같은 라이프 사이클을 관리하며 스프링으로부터 필요한 객체를 얻어올 수 있다.

 2) 스프링은 POJO 방식의 프레임 워크이다.
    - 일반적인 J2EE 프레임워크에 비하여 구현을 위한 특정한 인터페이스를 구현하거나 상속을
      받을 필요가 없어 기존에 존재하는 라이브러리 등을 지원하기에 용이하고 객체가 가볍다.

 3) 스프링은 제어 반전(IoC: Inversion of Control)을 지원한다.
    - 컨트롤의 제어권이 사용자가 아니라 프레임워크에 있어서 필요에 따라 스프링에서 사용자의 코드를 호출한다.

 4) 스프링은 의존성 주입(DI: Dependency Injection)을 지원한다.
    - 각각의 계층이나 서비스들 간에 의존성이 존재할 경우 프레임워크가 연결시켜준다.

 5) 스프링은 관점 지향 프로그래밍(AOP: Aspect-Oriented Programming)을 지원한다.
    - 따라서 트랜잭션이나 로깅, 보안과 같이 여러 모듈에서 공통적으로 사용하는 기능의 경우 해당 기능을 분리하여 관리할 수 있다.

 6) 스프링은 영속성과 관련된 다양한 서비스를 지원한다.
    - iBatis나 Hibernate 등 이미 완성도가 높은 데이터베이스 처리 라이브러리와 연결할 수 있는 인터페이스를 제공한다.

 7) 스프링은 확장성이 높다.
    - 스프링 프레임워크에 통합하기 위해 간단하게 기존 라이브러리를 감싸는 정도로 스프링에서 사용이 가능하기 때문에
      수많은 라이브러리가 이미 스프링에서 지원되고 있고 사용되는 라이브러리를 별도로 분리하기도 용이하다.
```
**그 외에도 동적 웹 사이트 개발을 위한 프레임워크, 대한민국 전자정부 표준 프레임워크의 기반 기술** 등의 특징이 있다.

***
## 2. 주요 구성 요소
### 2.1 핵심 개념
- DI (Dependency Injection)
- IoC
- AOP & AOP Proxy
- AOP in Spring

### 2.2 주요 구성 요소
- IoC/ DI
- AOP
- PSA

![Spring](https://t1.daumcdn.net/cfile/tistory/2562463D54E6C49826)

```
 1) POJO (Plain Old Java Object)
    - 무거운 EJB와 반대로 경량의 자바 객체를 지칭하는 용어, 자바 객체를 강조하는 의미로 사용

 2) PSA (Portable) - (쉬운) 서비스 추상화
    - 성격이 비슷한 여러 종류의 기술을 추상화하고 이를 일관된 방법으로 사용할 수 있도록 지원

 3) 트랜잭션 서비스 추상화
    - 여러 가지의 DB를 사용한다고 하면 Global Transaction 방식을 사용
    - 자바는 JDBC 외에 이런 글로벌 트랜잭션을 지원하는 트랜잭션 매니저를 지원하기 위한 API인
    - JTA(Java Transaction API)를 제공
    - 높은 응집도와 낮은 결합도를 준수
```

### 2.3 DI (Dependency Injection, 의존성 주입)
> **DI**는 _스프링 프레임워크_ 에서 생겨난 개념이 아니라, 단지 이를 **잘 지원해줄 뿐이다.**


1. **일체형** : A라는 객체의 내부 프로세스에 대해 신경 쓸 필요가 없다.
```
  - Composition: HAS-A 관계
  - A가 B를 생성자에서 생성하는 관계
```
```JAVA
SPhone a = new SPhone();
```
2. **분리/ 도킹(부착)형** : A와 B를 개별적으로 세팅해주어야 한다. (이것을 DI 개념이라고 보면 된다.)
```
  - Association 관계
  - A 객체가 아닌 다른 녀석이 만든 B 객체를 사용
```
```JAVA
Battery b = new Battery();
SPhone a = new Sphone();
a.setBattery(b);
```
**이렇게 분리형으로 개발하게 되면 각 객체간의 결합도를 낮출 수 있다.**

#### 2.3.1 DI의 종류
- Setter Injection
- Construction Injection
```JAVA
Setter Injection
B b = new B();
A a = new A();
a.setB(b);

Construction Injection
B b = new B();
A a = new A(b);
```
**JAVA에서 많이 쓰던 개념, 단지 스프링에서는 이러한 일련의 과정을 동적으로 자동화 한다.**

#### 2.3.2 DI in Spring
> 명세서에 따라 자동적으로 부품을 활요하여 제품을 조립하는 것이 스프링이다.
- 일체형 프로그램과는 반대로 부품을 생성
- 작은 부품부터 시작하여 큰 부품으로 이동하며 조립
- 즉, **Inversion of Control(IoC)**

```
  [스프링에서의 DI의 의미]

  - 부품들을 생성하고, 제품을 조립해주는 공정 과정을 대신
   해주는 라이브러리 (역할자)
```

- 개발 핵심 처리 루틴의 수정 없이 제품(객체)를 다른 제품(객체)로 쉽게 대체하여 생성하도록 하는 역할
- 명세서에 따라서 자동적으로 부품을 활용하여 제품을 조립
- 생성하기 원하는 객체를 명세서(XML)에 기술하고, 그 부품과 의존성들을 보관하는 일을 처리
- 이러한 데이터를 보관하는 공간을 **컨테이너**라 한다. ```IoC Container```

#### 2.3.3 DI 구현
- 객체의 생성과 도킹에 대한 내용이 소스 코드 상에 있는 것이 아닌 별도의 파일(XML 설정 파일) 에 분리하여 존재
- **Java소스** 컴파일 없이 **XML 변경**만으로 내용 변경 가능

```JAVA
JAVA(DI): Property 항목은 실제 값을 Record에 바로 주입하는 것이 아닌 setRecord() 함수를 호출하여 주입한다.

* JAVA (DI)

Record record = new SprRecord();
RecordView view = new SprRecordView();
view.setRecord(record);; // Injection
view.input();
view.print();
```
```XML
XML (스프링 DI): 객체 생성 시, 패키지 명을 포함한 풀 클래스 네임 작성
XML에 작성된 명세서를 보고, IoC 컨테이너가 각 객체를 생성하고, 값을 주입해준다.
여기서 ApplicationContext가 IoC 컨테이너 역할을 한다.

* XML (스프링 DI) config.xml
<bean id="record" class="di.SprRecord"></bean> // 빈 객체 생성
<bean id="view" class="di.SprRecordView"> // 빈 객체 생성
  <property name="record" ref="record"></property> // setRecord() 호출
</bean>
```
```JAVA
* JAVA

// XML을 파싱하여 컨테이너에 담는 작업
ApplicationContext ctx = new ClassPathXmlAppicationContext("config.xml");
RecordView = (RecordView) ctx.getBean("view");
```

- xml을 활용하는 경우는 VIEW에 대한 객체만을 요청했을 뿐, 실제 내부적인 사항은 JAVA코드 상에 드러나지 않는다.
- **새로운 클래스의 bean 객체를 만들어 XML에 주입만 시켜줘도, 기존 소스 변경 없이 새로운 형태의 객체 적용이 가능하다.**

#### 2.3.4 XML(Bean) Sample
- **빈(Bean) 객체**는 반드시 **클래스**를 사용, **인터페이스나 추상클래스는 객체 생성이 불가능**하다.
- 빈 객체 생성, 객체 초기화, 객체 값(또는 레퍼런스) 주입

```XML
* XML (스프링 DI) config.xml

1) 이름이 record인 빈 객체 생성, 별명 4개 (r1, r2, r3, r4), SprRecord 클래스 객체 생성
  <bean id="record" name="r1, r2, r3; r4" class="di.SprRecord">
    <property name="kor" value="20"></property>
  </bean>

2) 이름이 record인 빈 객체 생성 / 생성자(인자가 하나인)를 통해서 값 대입, 생성
  <bean id="record" name="r1, r2, r3; r4" class="di.SprRecord">
    <constructor-arg value="20"></constructor-arg>
  </bean>

3) 생성자 중에서 kor 값을 입력받는 생성자를 통해서 20값 대입하고, 생성
  <bean id="record" name="r1, r2, r3; r4" class="d1.SprRecord">
    <constructor-arg name-"kor" value="20"></constructor-arg>
  </bean>

4) 3개의 인자를 받는 생성자를 통해 kor = 50, eng = 60, math = 70 대입 & 생성
  <bean id="record" name="r1, r2, r3; r4" class="d.SprRecord"
    p:kor="50" p:eng="60" p:math="70">

5) 생성된 record 객체를 set함수를 통해 프로퍼티에 저장하고 SprRecordView를 생성
  <bean id="view" class="di.SprRecordView">
    <property name="record" ref="record"></property>
  </bean>
```
```
- id: 빈 객체의 고유 이름 (접근 가능자)

- name: 객체의 이름 (별칭)

- class: 생성할 클래스

- constructor-arg: 초기 값 설정 (생성자 함수 사용)

- property: 초기 값 설정 (Setter 함수 사용)
```
참고로 **값**을 대입하는 경우에는 **value**, **참조**를 대입하는 경우에는 **ref**를 사용한다.
***
## 참고
- [스프링 프레임워크 기본 개념 강좌](http://ooz.co.kr/170)

***
