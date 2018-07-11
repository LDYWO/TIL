[이미 Commit된 파일 무시하기]
==============
***
> **로그나 바이너리 파일**과 같이 **git**으로 관리가 불필요한 파일이 있을 수 있다.

> **.gitignore**에 추가하는 것을 잊었다면 다음과 같이 해보자.

### 1. 진행 절차
  이러한 경우 **working directory**에는 남겨두고 **staging area**에서만 삭제해야 한다.
  따라서 ```git rm --cashed``` 명령어를 이용하여 해당 파일을 더 이상 추적하지 않게 하고 다시 **commit**을 해 준 다음, **.gitignore**에 해당 파일을 추가해주고 **.gitignore**도 **commit** 해주면 된다.
  
  #### 1.1 명령어
  ```
  $ git rm --cashed log.text
  $ git commit -m 'untrack log.txt'
  $vi .gitignore
  '''
  log.txt # 추가
  '''
  $ git commit -am 'ignore log.txt'
  ```

  ***
  ### 2. 참고
  * [Git Ignore](http://gumpcha.github.io/blog/gitignore-tracked-file/)
