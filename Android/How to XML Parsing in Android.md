[XML 파싱하여 Text 출력하기]
--------
***
## 1. AsyncTask
 > **AsyncTask**를 이용하여 **XML**을 파싱하여 Text를 출력해보자
### 1.1 Sync & Async
 **Sync**라는 말은 이미 수행되고 있는 **Task1**이 존재하고 어떤 새로운 **Task2**를
  수행하고자 할 때 앞선 **Task1**이 종료되기를 기다리는 것을 말한다.

 반대로 **Async**라는 이러한 기다림이 없는 개념이라고 할 수 있다.
**비동기적**으로 **Task**를 실행하면 이전 **Task**가 종료되기 전에 다른 태스크를 수행할 수 있다.

 ### 1.2 AsyncTask 개념
 **안드로이드 어플리케이션**이 실행되면 시스템은 **메인 스레드**를 생성한다. 이 스레드는 안드로이드 UI 툴 키트에 접근한다. 그리고 사용자의 입력을 기다리거나 디바이스의 **View**에 관련된 작업들을 다룬다. 따라서 이를 **UI스레드**라고도 부른다.

 앱의 모든 __컴포넌트(Activity, Service, Content Provider, Broadcast Receiver)__ 들은 모두 같은 스레드 내에서 실행된다. 필요에 따라 추가 스레드를 생성할 수 있다.

> **UI 스레드**가 **Thread-safe**하지 않기 때문에 스레드 사용 시 다음 **2가지**를 지켜야 한다.
* UI 스레드가 블록되지 않도록 해야한다.
* UI 스레드 외에 다른 스레드에서 UI 컴포넌트 접근을 하면 안된다.

**UI 반응성 향상**과 처리 시간이 오래 걸리는 작업 처리를 같이 해결하기 위해선 별도의 스레드가 필요하다. 예를 들어 **네트워크 관련 처리는 메인 스레드에서 수행하는 것이 금지되어 있다.**

> 이 문제를 해결하기 위해 **안드로이드**에서는 **Handler, Runnable, AsyncTask**를 제공한다.

**AsyncTask**는 메인 스레드에서 생성 후 실행되며, *메인 스레드에서 처리시간이 오래 걸리는 작업을 백그라운드 스레드로 넘기고 계속 메인 스레드 작업을 진행하기 위해 사용된다.*

**AsyncTask**를 사용하면 백그라운드 스레드와 메인 스레드 간의 커뮤니케이션이 간단해진다. *종료 후 결과를 UI에 반영할수도 있고*, *백그라운드 스레드 작업 중에 UI 요청을 처리할수도 있다.*

### 1.3 AsyncTask와 Activity Lifecycle
> **몇 초 정도의 짧은 시간이 걸리는 작업에 대해서만 AsyncTask**를 사용하도록 권장하며,
그 이상의 작업은 **Executor, ThreadPoolExecutor, FutureTask**등을 사용한다.

#### 1.3.1 문제점
  __1. 메모리릭__
  > **AsyncTask**를 생성한 **Activity**가 먼저 **Destroy**되는 경우 메모리릭이 발생될 수 있다. 이미 자신을 실행시킨 **Activity**가 존재하지 않는데 **AsyncTask가 UI** 처리를 하게되는 경우 메모리 상에 존재하지 않는 것을 참조하게되어 **메모리릭**이 발생한다. 반드시 **Activity** 종료 전에 **AsyncTask를 Cancel**해주어 백그라운드 스레드의 작업이 종료되도록 해야한다.

  __2. 디바이스의 화면 회전__
  > **Activity**에서 **AsyncTask**를 실행하고 난 후 *디바이스를 회전*시키면 **Activity**는 **Destroy**되고 모든 멤버 변수의 인스턴스가 새로 생성된다. 이 때 **AsyncTask**는 계속 작업을 진행하고 있게 된다. 작업이 완료된 후 **UI를 업데이트 할 때 잘못된 인스턴스에 접근하게 되어 오류가 발생한다.**  

***

## 2. Using AsyncTask
### 2.1 AsyncTask의 생성 및 실행

**AsyncTask**의 **execute** 메소드를 호출함으로써 시작되며, 바로 **AsyncTask**의 **onPreExecute** 메소드가 호출되게 된다. **execute 메소드의 인자**로 전달한 값은 **doInBackground** 메소드가 **파라미터**로 받게 된다.

```
GetXMLTask task = new GetXMLTask(); // XML을 파싱해서 Global.UpdateList에 저장
task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125"); // 예외처리 하기
```

### 2.2 AsyncTask 병렬 실행

```
AsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
```

### 2.3 AsyncTask의 메소드
* **onPreExecute()**
```
 UI 스레드 상에서 실행되며 doInBackground 메소드 전에 호출된다.
 프로그레스 바를 보여주는 등 초기화 작업에 사용된다.
```
* **doInBackground(Params...)**
```
 백그라운드 스레드 상에서 처리되며 바로 UI처리를 하면 안된다.
 값을 리턴하면 onPostExecute에서 파라미터로 받게된다.
```
* **onProgressUpdate (Progress...)**
```
 doInBackground 메소드에서 publishProgress 메소드를 호출함으로써 UI 스레드 상에서 실행된다.
 publishProgress에서 인자로 전달한 값을 파라미터로 받아 UI 작업 시에 사용한다.
```
* **onPostExecute(Result)**
```
 UI 스레드 상에 실행되며 doInBackground 메소드에서 리턴한 값을 파라미터로 전달받는다.
 Progressbar를 감추는 UI 작업을 할 수 있다.
```
* **onCancelled()**
```
 doInBackground 메소드에서 수행중인 작업이 취소되면 onPostExecute 메소드 대신에 호출된다.
```
***

## 3. 실습 코드

```
    private class GetXMLTask extends AsyncTask <String, Void, Document>{
```
> 위에서 선언해준 **파라메터 타입 세가지**와 **메소드의 파라메터 타입**이 일치 해야한다.
순서대로 **doInBackground, onProgressUpdate, onPostExecute** 이다.

    @Override
    protected Document doInBackground(String... urls) {
        try {
            url = new URL(urls[0]);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

```
    @Override
    protected void onPostExecute(Document doc) {

        String s = "";
        // data 태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
        NodeList nodeList = doc.getElementsByTagName("data");
        // data 태그를 가지는 노드를 찾음. 계층적인 노드 구조를 반환

        for (int i = 0; i<nodeList.getLength(); i++){
            // 날씨 데이터를 추출
            s += "" + i + ": 날씨 정보";
            Node node = nodeList.item(i);
            Element fstElmnt =(Element)node;
            NodeList nameList = fstElmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();
            s += " 온도 =" + ((Node) nameList.item(0)).getNodeValue() + " ,";

            NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
            s += "날씨 =" + websiteList.item(0).getChildNodes().item(0).getNodeValue() +"\n";
        }

        Global.UpdateList = s;// 로컬에 저장 (업데이트 리스트)

        super.onPostExecute(doc);
    }
}
