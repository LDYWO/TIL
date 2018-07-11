[마크다운 작성법]
======================
***
# 1. 마크다운에 관하여
## 1.1 마크다운이란?
[**Markdown**](http://whatismarkdown.com/)은 텍스트 기반의 마크업 언어로 2004년 존그루버에 의해 만들어졌으며 쉽게 쓰고 읽을 수 있으며 HTML로 변환이 가능하다.
특수기호와 문자를 이용한 매우 간단한 구조의 문법을 사용하여 웹에서도 보다 빠르게 컨텐츠를 작성하고 보다 직관적으로 인식할 수 있다.
마크다운이 최근 각광을 받기 시작한 이유는 깃헙([https://github.com](https://github.com/)) 덕분이다.
깃헙의 저장소 Repository에 관한 정보를 기록하는 **README.md** 는 깃헙을 사용하는 사람이라면 누구나 가장 먼저 접하게 되는 마크다운 문서였다.
마크다운을 통해서 설치 방법, 소스코드 설명, 이슈 등을 간단하게 기록하고 가독성을 높일 수 있다는 강점이 부각되면서 점점 여러 곳으로 퍼져가게 된다.

## 1.2 마크다운의 특징
### 1.2.1 장점
        1. 간결하다.
        2. 별도의 도구 없이 작성 가능하다.
        3. 다양한 형태로 변환이 가능하다.
        4. 텍스트(Text)로 저장되기 때문에 용량이 적어 보관이 용이하다.
        5. 텍스트 파일이기 때문에 버전관리시스템을 이용하여 변경 이력을 관리할 수 있다.
        6. 지원하는 프로그램과 플랫폼이 다양하다.

### 1.2.2 단점
        1. 표준이 없다.
        2. 표준이 없기 때문에 도구에 따라서 변환 방식이나 생성물이 다르다.
        3. 모든 HTML 마크업을 대신하지 못한다.

***

# 2. 마크다운 사용법
## 2.1 헤더 Headers
* 큰제목: 문서 제목
  ```
  This is an H1
  ==============
  ```
  This is an H1
  ==============
* 작은제목: 문서 부제목
  ```
  This is an H2
  -------------
  ```
  This is an H2
  -------------

* 글머리: 1~6까지만 지원
  ```
  # This is a H1
  ## This is a H2
  ### This is a H3
  #### This is a H4
  ##### This is a H5
  ###### This is a H6
  ```
  # This is a H1
  ## This is a H2
  ### This is a H3
  #### This is a H4
  ##### This is a H5
  ###### This is a H6

## 2.2 BlockQuote
이메일에서 사용하는 ```>``` 블럭인용 문자를 이용한다.
```
> This is a BlockQuote
```
> This is a first BlockQuote.
>> This is a second BlockQuote.
>>> This is a third BlockQuote.

이 안에서는 다른 마크다운 요소를 포함할 수 있다.
> ### This is a H3
> * List
>   ```
>   code
>   ```

## 2.3 목록
### ● 순서있는 목록 (번호)
```
1. 첫번째
2. 두번째
3. 세번째
```
1. 첫번째
2. 두번째
3. 세번째

**현재까지는 어떤 번호를 입력해도 순서는 내림차순으로 정의된다.**
```
1. 첫번째
3. 세번째
2. 두번째
```
1. 첫번째
3. 세번째
2. 두번째

**존 그루버가 신경쓰지 않아 딱히 개선될 것 같지 않다고 한다.**

### ● 순서없는 목록 (글머리 기호)
```
* 빨강
  * 녹색
    * 파랑

+ 빨강
  + 녹색
    + 파랑

- 빨강
  - 녹색
    - 파랑
```
* 빨강
  * 녹색
    * 파랑

+ 빨강
  + 녹색
    + 파랑

- 빨강
  - 녹색
    - 파랑
혼합해서 사용하는 것도 가능하다.
```
* 1단계
  - 2단계
    + 3단계
     = 4단계
 ```
 * 1단계
   - 2단계
     + 3단계 = 4단계

## 2.4 코드 ```<pre><code></code></pre>```
4개의 공백 또는 하나의 탭으로 들여쓰기를 만나면 변환되기 시작하여 들여쓰지 않은 행을 만날 때까지 변환이 계속된다.
> 한줄 띄어쓰면 인식이 제대로 안되는 문제가 발생하기도 합니다.
```
This is a normal paragraph:

    This is a code block.
end code blcok.
```
<code>
```
This is a normal paragraph:
    This is a code block.
end code block.```
```

```
실제로 적용해보면,
This is a paragraph:

    This is a code block.
end code block.

```

## 2.5 수평선 ```</hr>```
아래 줄은 모두 수평선을 만든다.
마크다운 문서를 미리보기로 출력할 때 *페이지 나누기* 용도로 많이 사용된다.

```
* * *

***

*****

- - -

--------------------
```

* * *

***

*****

- - -

--------------------

## 2.6 링크
* 참조 링크
```
[link keyword][id]
[id]: URL "Optional Title here"

Link: [Google][googlelink]
[googlelink]: https://google.com "Go google"
```
Link: [Google][googlelink]
[googlelink]: https://google.com "Go google"

* 인라인 링크
```
Syntax: [Title](link)
```
Link: [Google](https://google.com, "google link")

* 자동 연결
```
<http://example.com>
<address@example.com>
```
<http://example.com>
<address@example.com>

## 2.7 강조
```
1. *single asterisks*
2. _single underscores_
3. **double asterisks**
4. __double underscores__
5. ++underline++
6. ~~cancelline~~
```
1. *single asterisks*
2. _single underscores_
3. **double asterisks**
4. __double underscores__
5. ++underline++
6. ~~cancelline~~

## 2.8 이미지
```
![Alt text](/path/to/img.jpg)
![Alt text](/path/to/img.jpg "Optional title")
```
![석촌호수 러버덕](http://cfile6.uf.tistory.com/image/2426E646543C9B4532C7B0)
![석촌호수 러버덕](http://cfile6.uf.tistory.com/image/2426E646543C9B4532C7B0 "RubberDuck")

사이즈 조절 기능은 없기 때문에 ```<img width="" height=""></img>``` 를 이용한다.

****
# 3. 마크다운 적용기
## 3.1 아톰(Atom) 에디터
아톰(Atom)은 자유-오픈 소스 형태의 OS X, 리눅스, 윈도용 문서 및 소스 코드 편집기이다. Node.js로 작성된 플러그인, 깃허브가 개발한 임베디드 깃 관리 지원을 포함한다. 대부분의 확장 패키지는 자유 소프트웨어 라이선스를 취하며 커뮤니티가 만들고 관리하고 있다. 아톰은 크로미엄에 기반을 두며 커피스크립트로 작성되어 있다. IDE로 사용할 수도 있다.

## 3.2 깃헙 github
깃헙과 같은 협업개발플랫폼, 비전관리 시스템에서는 마크다운을 변환하는 컨버터 기능을 기본적으로 탑재하고 있기 때문에 마크다운 문법으로 작성한 텍스트를 그대로 복사해서 붙여넣거나 업로드 하는 것만으로 마크다운의 적용이 가능하다.

## 3.3 MS워드
View 영역의 항목을 그대로 붙여넣거나 HTML 내보내기 등으로 생성한 파일을 불러오는 형태로 사용 가능하다. 적용한 헤더를 워드가 읽어들이면서 목차에 적용하기 때문에 이를 활용하면 목차까지도 손쉽게 적용이 가능해진다.

****
# 4. 정리
마크다운은 기본 문법만 알고 있다면 일반 텍스트 편집기에서도 손쉽게 작성이 가능한 마크업 언어이다.
현재 다양한 도구와 플랫폼에서 지원하고 있기 때문에 더욱 손쉽게 스타일 적용된 문서를 작성할 수 있기 때문에 점점 널리 사용되고 있다. 마크다운을 이해하고 사용하면서 쉽고 빠르게 스타일 문서를 작성할 수 있다. 본인은 GitHub과 블로그에 공부한 것을 정리하는 목적으로 이용하고 있다.

****
# 5.  참고 문서
* [78 Tools for writing and previewing Markdown](http://mashable.com/2013/06/24/markdown-tools/)
* [John gruber 마크다운 번역](http://nolboo.github.io/blog/2013/09/07/john-gruber-markdown/)
* [깃허브 취향의 마크다운 번역](http://nolboo.github.io/blog/2014/03/25/github-flavored-markdown/)
* [허니몬의 마크다운 작성법](http://www.slideshare.net/ihoneymon/ss-40575068)
* [마크다운 사용법](https://gist.github.com/ihoneymon/652be052a0727ad59601#file-gistfile1-md)

***
