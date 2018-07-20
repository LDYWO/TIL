# REST API
## 1. REST API, RESTful Service Architecture
- REST API는 **REpresentational State Transfer**의 약자
- 2000년도에 Roy Fielding의 박사학위 논문에서 최초로 소개되었다.
- **웹 아키텍쳐의 우수성을 최대한 활용할 수 있도록 만들어진 API**이다.
- **여러 컴포넌트들로 구성**되어 있는 각 **서비스들을 묶을 때** 많이 사용되는 개념이다.

### 1.1 REST의 정의
> "HTTP URL로 잘 표현된 리소스에 대한 행위를 HTTP Method로 정의한다. 리소스의 내용은 JSON, XML, YAML 등의 다양한 표현 언어로 정의된다."

- **What**: HTTP URL로 정의된 리소스
- **How**: HTTP Method + Payload
- 이와 같은 개념으로 잘 정의된 API

### 1.2 리소스 (Resource)
- REST API의 수행 대상으로 URL로 정의된다.
- 리소스라는 것은 처리되는 대상을 의미한다.
- JSON, XML, JPG, MP4 등 다양한 포멧이 될 수 있다.

```
 * 이러한 URL은 Uniform Resource Identifier의 약자로 '통합 자원 식별자'의 의미이다.

 * URL은 특정 자원의 위치를 나타내는 주소로, 인터넷 프로토콜 (HTTP) 혹은 파일 전송 프로토콜 (FTP)와 함께 사용하는 경우가 많다.
```

### 1.3 메소드 (Method, 행위(Verb))
- REST API에서는 리소스에 대한 행위가 일관되게 정의된다.
- 리소스의 포멧에 관계 없이 같은 메소드에 의해 다뤄진다.
- REST API에서 사용되는 메소드는 HTTP Method이다.
- 예를 들어, POST, GET, PUT, PATCH, DELETE 등이 있다.

  | HTTP Method | CRUD 연산|
  | :----------: | :-------:|
  | POST        | create   |
  | GET         | read     |
  | PUT         | update/ replace |
  | PATCH       | update / modify |
  | DELETE      | DELETE   |

> 리소스를 표현하는 URL과 HTTP Method를 이용하여 클라이언트가 서버에 보내는 메시지 요청은 다음과 같다.
```
GET www.plusblog.co.kr/ images/MyAnnotation
```
여기에 부가적인 정보들이 추가된다.

```
GET www.plusblog.co.kr/images/main HTTP/1.1 Accept:image/JPG
```
> /image/main 이라는 리소스에 대한 정보를 요청하는데, 사용되는 프로토콜은 HTTP/1.1, 파일 형태는 이미지로 jpg 포맷을 띄고 있다는 것이다.

### 1.4 표현 (Representation)
- HTTP 메소드와 URL로 원하는 내용을 모두 표현할 수 있다.
- 예를 들어, 새로운 유저를 생성하라는 REST API는 다음과 같다.
```
POST http://www.plusblog.co.kr/users/newuser
```
- 호스트에 있는 users에 'newuser'라는 사용자를 추가하는 REST API이다.
- 이 때 다양한 정보들이 필요한데, 이는 XML이나 JSON, YAML 같은 다양한 언어로 표현할 수 있다.

```
POST http:// www.plusblog.co.kr/users Content-Type: application/JSON
{
    "username": "newusers",
    "age": "20"
}
```
- HTTP 메소드의 정보 표현 부분을 메소드 바디 혹은 Payload라고 한다.
- 위와 같은 형태로 POST 메소드에 Payload를 붙여서 요청할 수 있다.

***
## 2. REST의 특징
1. **Uniform Interface (유니폼 인터페이스)**
- Uniform Interface는 URL로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍쳐 스타일을 말한다. HTTP를 사용할 수 있는 모든 플랫폼에서 사용이 가능한 느슨한 결합 (Loosely Coupling) 형태의 구조이다.
2. **Stateless (무상태성)**
- 작업을 위한 상태정보를 따로 저장하고 관리하지 않는다. 세션 정보나 쿠키 정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 단순히 처리하면 된다. 때문에 서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순해진다.
3. **Cacheable (캐시 가능)**
- HTTP가 가진 캐싱 기능을 그대로 적용 가능하다. HTTP 프로토콜 표준에서 사용하는 **Last-Modified** 태그나 E-Tag를 이용하면 캐싱 구현이 가능하다.
4. **Client - Server 구조**
- REST 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 직접 관리하는 구조로 각각의 역할이 확실히 구분되기 때문에 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어들게 된다.
5. **Self-descriptiveness (자체 표현 구조)**
- REST API 메시지만 보고 이를 쉽게 이해할 수 있는 자체 표현 구조로 이루어져 있다.
6. **Layered System (계층형 구조)**
- REST 서버는 다중 계층으로 구성될 수 있으며, 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있고 PROXY, 게이트 웨이 같은 네트워크 기반의 중간 매체를 사용할 수 있게 한다.
7. **Code on demand (optional)**
- 자바 애플릿이나 자바스크립트의 제공을 통해 서버가 클라이언트가 실행시킬 수 있는 로직을 전송하여 기능을 확장할 수 있다.

***
## 3. HTTP 요청에 대한 응답 전달
- 다음은 상황에 따른 HTTP 상태코드에 대한 내용이다.

1. **200 번대**

  | 상태코드 |           |
  |:-------:|:----------:|
  | 200     | 클라이언트의 요청을 정상적으로 수행함|
  | 201     | 클라이언트가 어떠한 리소스 생성을 요청, 해당 리소스가 성공적으로 생성됨 (POST)|

2. **400 번대**

  | 상태코드 |           |
  |:-------:|:----------:|
  |400 | 클라이언트의 요청이 부적절할 경우 사용|
  |401 | 클라이언트가 인증되지 않은 상태에서 보호된 리소스를 요청했을 때 사용
  |     | 로그인하지 않은 유저가 로그인 했을 때, 요청 가능한 리소스를 요청했을 때 |
  |403 | 유저 인증상태와 관계 없이 응답하고 싶지 않은 리소스를 클라이언트가 요청했을 때 사용|
  |     | 403보다는 400이나 404 사용을 권고, 403 자체가 리소스가 존재한다는 의미이기 때문|
  |405 | 클라이언트가 요청한 리소스에서는 사용 불가능한 METHOD를 이용했을 경우 사용|

3. **300 / 500 번대**

  | 상태코드 |        |
  |:--------:|:-----:|
  |301|클라이언트가 요청한 리소스에 대한 URL이 변경되었을 때 사용|
  ||응답시 Location Header에 변경된 URL을 적어줘야 함|
  |500| 서버에 문제가 있을 경우 사용 (503, 504 등등)|

  > REST API를 맞추기에는 RDBMS 보다는 NoSQL이 더 잘 맞는다.

***
## 4. 참고
- [RESTful API](http://blog.naver.com/PostView.nhn?blogId=complusblog&logNo=220986337770)
- [REST API란?](http://araikuma.tistory.com/340)
