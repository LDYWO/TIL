# WebView
## 1. About WebView Component
- **웹뷰**는 **안드로이드 프레임워크**에 내장된 **웹 브라우저 컴포넌트**이다.
- **뷰(View)** 형태로 앱에 임베딩이 가능하다.
- **안드로이드 앱** 안에서 **HTML**을 호출하여 구현하는 **하이브리드 앱 개발**에도 사용된다.
- **HTML 기반**인 만큼 상대적으로 **반응성이 약하고 UI 효과또한 넣기 어렵다**는 단점이 있다.
- **웹 브라우저 컴포넌트**는 **OS**에 맞게 일부 기능을 제약했기 때문에 **HTML5 등 호환성 문제**가 있다.

***
## 2. Using WebView

  1. Manifest 파일에 인터넷 접속 권한을 등록한다.

  ```<uses-permission android:name="android.permission.INTERNET"/>```
  2. 레이아웃에 웹 뷰를 추가한다.

  3. 웹뷰를 선언 및 인스턴스를 생성하고 설정을 한다.

  ```
  webView = (WebView) findViewById(R.id.webview);
  WebSetting websetting = webView.getSettings();
  webSetting.setJavaScriptEnabled(true); // 웹 뷰에 JS 사용
  webSetting.setSupportZoom(true); // 손으로 확대, 축소를 할 수 있도록 사용
  webBuiltInZoomControls(true); // WebView 내장 Zoom 사용
  ```
  4. 웹뷰 내에서 웹 브라우저를 띄워주기 위해 메소드를 설정한다.
  ```
  webView.setWebViewClient(new WebViewClient(){
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        et.setText(url);

    }
  });
  ```
  ```
  .onPageStarted(): 웹뷰에서 url이 로드 될 때 호출되는 함수
  .onPageFinished(): 웹뷰에서 url 로딩이 완료되면 호출되는 함수
  ```
  5. 웹 뷰를 실행한다.

  ```
  WebView webView = (WebView)findViewById(R.id.webview);
  webView.setWebViewClient(new WebViewClient());
  webView.loadUrl(url);
  ```
***
## 3. Upgrade Performance of WebView
> 웹뷰의 성능에 관한 문제가 많다. **하이브리드 앱** 개발 부분에서는 다양한 솔루션이 제공되고 있다.

### 3.1 크롬 뷰 사용
  - 안드로이드 4.4 (API level 19)부터는 웹뷰에 대한 성능 개선의 일환으로 [**Chromium**](http://www.chromium.org/Home)이라는 오픈소스가 지원된다.
  - **Chromium** 기반의 웹뷰는 성능 향상 뿐만 아니라, HTML 호환성 개선도 같이 이루어졌다.

### 3.2 일반적인 튜닝 옵션
  1. 캐쉬 사용하지 않기
  ```
  webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
  ```
  2. 하드웨어 가속 기능 사용하기
  ```
  AndroidManifest.xml
  android:hardwareAccelrated: true;
  ```
  **이 정도로 정리해볼 수 있겠다.**

***
## 4. 응용 예제
> **WebView**를 **Custom Dialog**에 띄워 **앱의 업데이트 정보**를 출력하는 예제 구현

- [소스 코드 링크](https://github.com/LDYWO/TIL/tree/master/Android/Source%20Code/WebView)

    1. 커스텀 다이얼로그를 출력하는 메소드 관리 클래스 생성

    2. 메소드 내에서 커스텀 다이얼로그의 인스턴스 생성

    3. Dialog를 extend하는 PopupDialog 클래스 생성

    4. View 부분을 PopupDialog 클래스 내에서 구성

    5. 반드시 Dialog를 출력한 후에는 dismiss를 호출하고 다음 액티비티로 넘어간다.

***
> **게임 개발이나 일반 애플리케이션**의 경우 **네이티브 앱** 개발이 **성능적인 측면이나 지원 기능**에서 많은 두각을 나타낼 것이다.
> 하지만, **SNS나 미디어성 컨텐츠**는 **개발의 용이성과 호환성** 그리고 크로스 콘텐츠 포멧을 지원하는 점에서 필수적이라 볼 수 있다.
