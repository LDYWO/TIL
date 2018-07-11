[GitHub의 저장소에 프로젝트 업로드하기]
==============
***
### 1. 기본 명령어
```
git init   : 새로운 local repository를 생성
git add .  : 변경된 파일을 storage에 추가
git commit : add 한 파일을 local repository에 저장
git push   : local repository를 remote repository에 업로드
```

***
### 2. GitHub의 Repository에 Project Upload
#### 2.1 처음 프로젝트를 올리는 경우?

 > 1. [**GitHub**](https://github.com/ "GitHub")에서 회원 가입을 한 후 **repository**를 생성한다.
 > 2. **Git Shell**을 이용하여 **repository**에 **push**할 폴더로 이동한다.
 > 3. _"git init"_ 명령어를 입력하여 프로젝트 파일에 **.git** 폴더를 생성합니다.
 > 4. _"git add ."_ 명령어를 입력하여 모든 파일을 추가 시킵니다.
 > 5. _"git commit -m "something"_ 명령어를 입력하여 **commit** 메시지를 작성합니다.
 > 6. _"git remote add origin "repository URL"_ 명령어를 입력해 특정 **repository**를 지정합니다.
 > 7. _"git push -u origin master"_ 명령어를 입력하여 **repository**에 등록합니다.
 > 8. **GitHub** 계정의 이메일 주소와 비밀번호를 입력합니다.

#### 2.2. 이미 작업한 프로젝트의 업로드
**처음 프로젝트를 올리고 이후에 수정 작업을 진행할 때는 위의 과정을 일부 생략할 수 있다.**
> 1. **Git Shell**을 이용하여 **repository**에 **push**할 폴더로 이동한다.
> 4. _"git add"_ 명령어를 입력하여 모든 파일을 추가 시킵니다.
> 5. _"git commit -m "something"_ 명령어를 입력하여 **commit** 메시지를 작성합니다.
> 7. _"git push 0u origin master"_ 명령어를 입력하여 **repository**에 등록합니다.
> 8. **GitHub** 계정의 이메일 주소와 비밀번호를 입력합니다.

***

### 3. 참고
* [GitHub 사용법](http://rogerdudler.github.io/git-guide/index.ko.html)
* [Github에 업로드하는 기본적인 방법](http://victorydntmd.tistory.com/53)
