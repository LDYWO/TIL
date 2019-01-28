# Network

## 1. HTTP의 GET과 POST 비교
둘 다 HTTP 프로토콜을 이용해서 서버에 무엇인가를 요청할 때 사용하는 방식이다.
하지만 둘의 특징을 제대로 이해하여 기술의 목적에 알맞은 용도에 사용해야한다.

### GET
우선 GET 방식은 요청하는 데이터가 ```HTTP Request Message``` 의 Header 부분에 url에 담겨서 전송된다.
떄문에 url 상에 ```?``` 뒤에 데이터가 붙어 request를 보내게 되는 것이다. 이러한 방식은 url이라는 공간에 담겨가기 때문에 전송할 수 있는 데이터의 크기가 제한적이다.
또 보안이 필요한 데이터에 대해서는 데이터가 그대로 url에 노출되므로 ```GET``` 방식은 적절하지 않다. (ex. password)

### POST
POST 방식의 request는 ```HTTP Message Body``` 부분에 데이터가 담겨서 전송된다. 때문에 바이너리 데이터를 요청하는 경우 POST 방식으로 보내야 하는 것처럼 데이터 크기가 GET 방식보다 크고 보안면에서 낫다.
(하지만 보안적인 측면에서는 암호화를 하지 않는 이상 고만고만 하다.)

*그렇다면 이러한 특성을 이해한 뒤에는 어디에 적용되는지를 알아봐야 그 차이를 극명하게 이해할 수 있다.*
우선 GET은 가져오는 것이다. 서버에서 어떤 데이터를 가져와서 보여준다거나 하는 용도이지 서버의 값이나 상태 등을 변경하지 않는다.
SELECT 적인 성향을 갖고 있다고 볼 수 있는 것이다. 반면에 POST는 서버의 값이나 상태를 변경하기 위해서 또는 추가하기 위해서 사용된다.
부수적인 차이점을 좀 더 살펴보자면 GET 방식의 요청은 브라우저에서 Caching 되었던 데이터가 요청될 가능성이 존재한다. 때문에 목적에 맞는 기술을 사용해야 하는 것이다.

## 2. TCP 3-WAY-HANDSHAKE
### 연결 성립 (Connection Establishment)
1. 클라이언트는 서버에 접속을 요청하는 **SYN(a)** 패킷을 보낸다.
2. 서버는 클라이언트의 요청인 **SYN(a)**을 받고 클라이언트에게 요청을 수락한다는 **ACK(a+1)**와 **SYN(b)**이 설정된 패킷을 발송한다.
3. 클라이언트는 서버의 수락 응답인 **ACK(a+1)**와 **SYN(b)** 패킷을 받고 **ACK(b+1)**를 서버로 보내면 연결이 **성립(establish)**된다.

### 연결 해제 (Connection Termination)
1. 클라이언트가 연결을 종료하겠다는 **FIN플래그**를 전송한다.
2. 서버는 클라이언트의 요청(**FIN**)을 받고 알겠다는 확이 메세지로 **ACK**를 보낸다.
3. 그리고나서 데이터를 모두 보낼 때까지 잠깐 **TIME_OUT**이 된다.
4. 데이터를 모두 보내고 통신이 끝났으면 연결이 종료되었다고 클라이언트에게 **FIN 플래그**를 전송한다.
5. 클라이언트는 **FIN 메세지**를 확인했다는 **메세지(ACK)** 를 보낸다.
6. 클라이언트의 **ACK 메세지**를 받은 서버는 소켓 연결을 **Close** 한다.
7. 클라이언트는 아직 서버로부터 받지 못한 데이터가 있을 것을 대비해 일정 시간 동안 세션을 남겨 놓고 잉여 패킷을 기다리는 과정을 거친다. (**TIME_WAIT**)

### SYN, ACK Packet?
> SYN :: synchronize sequence number
> ACK :: acknowledgement

**TCP Header** 에는 **Code Bit(Flag bit)** 라는 부분이 존재한다. 이 부분은 총 **6Bit**로 이루어져 있으며 각각 한 bit 들이 의미를 갖고 있다.
**Urg-Ack-Psh-Rst-Syn-Fin** 순서로 되어 있으며 해당 위치의 비트가 1이면 해당 패킷이 어떠한 내용을 담고 있는 패킷인지를 나타낸다.
**SYN** 패킷일 경우엔 **000010** 이 되고 **ACK** 패킷일 경우에는 **010000** 이 되는 것이다.

### Why two types of packets?
일단 연결이 성립하려면 서로 통신이 가능한지를 먼저 파악하기 위해 패킷을 먼저 주고받아야 한다는 것까지는 이해가 쉽다.
그런데 두 종류의 패킷을 주고 받는다. **요청**과 **응답**에 대한 패킷을 주고 받아야 하기 때문에 두종류 인 것이다.

### Why 3-way? Is not enough 2-way?
비유를 들어보자. 일단 클라이언트가 자신의 목소리가 들리는지 물어본다.(SYN) 서버는 클라이언트의 목소리가 들린다고 말한다.
(SYN + 1) 그리고 자신의 목소리가 들리는지 물어본다. (ACK) 클라이언트는 서버의 목소리가 들린다고 말한다. (ACK + 1) 이런 과정인 셈이다.
**TCP Connection** 은 양방향성(bidirectional) Connection 이다. 클라이언트에서 서버에게 존재를 알리고 패킷을 보낼 수 있다는 것을 알리듯,
서버에서도 클라이언트에게 존재를 알리고 패킷을 보낼 수 있다는 신호를 보내야 한다. 그렇기 때문에 2-way-handshake 로는 부족하다.

### Why randomized sequence number?
처음 클라이언트에서 SYN 패킷을 보낼 때 Sequence Number에는 랜덤한 숫자가 담겨진다. 초기 Sequence Number를 ISN이라고 한다.
ISN이 0부터 시작하지 않고 난수를 생성해서 number를 설정하는 이유는 무엇인가?
Connection을 맺을 때 사용하는 포트는 유한 범위 내에서 사용하고 시간이 지남에 따라 재사용된다.
따라서 두 통신 호스트가 과거에 사용된 포트 번호 쌍을 사용하는 가능성이 존재한다. 서버 측에는 패킷의 SYN을 보고 패킷을 구분하게 되는데 난수가 아닌
순차적인 number가 전송된다면 이전의 connection 으로부터 오는 패킷으로 인식할 수 있다.
이러한 문제가 발생할 가능성을 줄이기 위해서 난수로 ISN을 설정하는 것이다.