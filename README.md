# 방화벽 정보를 관리하는 관리툴 제작하기
java Spring framework를 이용하여 Spring Boot 로 어플리케이션 띄우기, insert 할 수 있는 API 만들기 (POST 방식으로), 입력페이지는 POSTMAN 을 이용해 호출하기

## 저장해야 하는 정보
- hostName, fromIP, toIP, toPort, description, expireDate, registeredId, insertDateTime, updateDateTime
- 그외에 필요하다고 생각되는 필드 추가
## 필요한 기능
- 입력기능, 수정기능, 삭제기능 : 입력화면 없이 postman을 통해서 테스트
- 조회기능 : 응답을 json으로 리턴
## Skills to use
- SpringFramework, SpringBoot(Tomcat), mariaDB
## 그 외
- 입력,수정,삭제,조회 화면 구현 (html)
- Jmeter를 이용한 성능테스트 후 성능측정 결과 제출 (Jmeter가 아닌 다른 측정툴을 사용하여도 무방)
    - insert 만 할때의 성능
    - select 만 할때의 성능
## 이론

## Getting Started
1. IntelliJ 사용시작 (2021/08/09,10)
- JDK 다운로드 (버전 16)
- Community 버전 다운로드 -> 유료버전을 다운받지 않아도 Spring 라이브러리를 사용할 수 있다!
- 연습: test web 띄우기
    - Spring Initializr: https://start.spring.io/
    - Gradle Project -> Java -> 2.5.3 -> jar -> java 16 -> Dependencies: Spring Web -> Generate! -> intelliJ 에서 Open Project
    - src>main>java>com>DemoApplication --> Run 'DemoApplication.main()'
          방법 1) src>main>resources>static> index.html 파일 만들기
          방법 2) DemoApplication.java 파일 수정
            ```
            @SpringBootApplication
             public class DemoApplication {

             public static void main(String[] args) {
             SpringApplication.run(DemoApplication.class, args);}

             @Get Mapping //Get방식으로 경로를 받는 mapping annotation
             public String HelloWorld(){
                return "수빈";}
                }
            ```

     - http://localhost:8080 접속하여 확인
     ![first spring boot page](https://github.com/soobin9711/spring/blob/4ecbbd2bb85b05676f00a27eb4b514a013acdbfb/first.png)

 - 단축키
    - 프로젝트 창: alt+1
    - 에디터 창: esc
    - 프로젝트 창 미리보기: space
    - 새 파일 생성: ctrl+alt+insert
    - 단어별로 이동: ctrl+방향키
    - 라인 시작/끝 이동: home / end
    - 선택 확장 / 축소: ctrl+ W / ctrl+shift+W
    - comment 처리하기: ctrl+/ (한줄), shift+ctrl+/ (블록)
    - 자동indent: ctrl+alt+I

2. Spring Boot 란 (2021/08/10)
- Spring Framework 에 속하는 도구로 Spring 에서 불편하게 사용자가 일일히 의존관계를 설정하는 것을 없앴다
- 원문 설명
- Servlet Containers (tomcat, jetty, undertow 등) 을 사용하여 독립 실행이 가능한 스프링 기반 어플리케이션을 쉽게 만들 수 있다
- 스타터를 통한 자동화된 스프링 설정을 제공한다
- jar 로 실행하는 java 어플리케이션을 만들 수 있다
- build tool: Maven (pom.xml), Gradle(build.gradle)

## 2021/08/11 일지
- MVC : Model - View - Controller 패턴

1. 프로젝트를 위한 spring boot 프로젝트 새로 생성 (start.spring.io)
	- Dependencies:
		- Spring Web(building web using Tomcat servelet)
		- DevTools(developer tool?)
		- Lombok (java annotaion library to reduce "boilerplate code" -> generate getters and setters automatically)
		- MariaDB Driver (to use mariaDB)
		- Tymeleaf (template to build html -> 뷰 구성 툴)
		- JPA (persisting data in SQL?)
		- H2 (SQL database?)

2. MariaDB 를 SpringBoot에 연동
  1) 테이블 생성 (user name: root, pwd: mint) 1- MySQL Client 에서 pw 를 사용하여 데이터베이스 접속 2- create database soodb; 3- show databases;
  2) intelliJ에서 application.properties 파일에 mariaDB dependency 추가

3. Model Entity 에 해당하는 User.java 추가
	- 필드: id, firstName, lastName, emailID
  
4. UserRepository.java 인터페이스 생성

5. Controller 생성 - UserController.java: Repository에서 처리된 데이터를 뷰 쪽으로 맵핑

6. Run Application (application.java에서 run)

7. Testing with Postman Client

  1) Create data - POST : http://localhost:8080/api/v1/users
   ![first create data page](https://github.com/soobin9711/spring/blob/4ecbbd2bb85b05676f00a27eb4b514a013acdbfb/first1.png)

	- Error msg: \Content type 'text/plain;charset=UTF-8' not supported
    	- 해결: Headers 중 Content-Type:application/json 추가

  2) Read data - GET: http://localhost:8080/api/v1/users/1 (삽입된 데이터의 id 가 1일 경우)
	- Error msg: Constructor for User (정확한 에러가 기억이 안남 ㅠ constructor 가 필요하다는 뜻)
		- 해결: User.java 에 빈 constructor 코드 추가
      
  3) Read all data in db - GET: http://localhost:8080/api/v1/users

  4) Update data - PUT: http://localhost:8080/api/v1/users/1

  5) Delete data - DELETE: http://localhost:8080/api/v1/users/1
  
  ![postman delete complete json](https://github.com/soobin9711/spring/blob/4ecbbd2bb85b05676f00a27eb4b514a013acdbfb/delete.png)
    
## 08/13 (금) - 중간 점검 & 이론 공부
- Feedback:
  - PUT, DELETE 방식 사용안함 (과거 금융권 보안 이슈) -> GET이랑 POST만 쓸 것
  - utf8 말고 utfmb4 (mySQL) : emoji 사용 시 
  - db의 pwd 암호화하기: enc - dnc
- Q:
  - Spring 에서 index.html 파일 이름 제한? - 설정에서 바꿀 수 있음

## 08/17 (화), 18 (수) , 20 (금) 일지
### 완료
  - html
    - 전체 데이터 조회하는 홈페이지 구현 성공 (index.html)
    - 입력하는 페이지 구현 성공 (newUser.html)
    - 삭제 기능 구현 성공
    - ![first index](https://github.com/soobin9711/spring/blob/399cb8bc819623ccc11f1709689f028318692146/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202021-08-23%20170321.png)
    
  - mint - web 프로젝트 새로 생성 (MVC 모델에서 Service 추가 포함)
  	- User.java(Entity), UserController.java, UserRepository.java, UserService.java, UserServiceImpl.java


### 어려웠던 점
  - 기존 mint-rest (REST API & JPA) 프로젝트의 경우 RestController를 사용했는데 리턴하는 값이 페이지가 아니라 데이터 자체여서 html 구현하는 방법에서 많이 헤맸다 ----> Controller 를 사용하는 mint-web 프로젝트 새로 생성 
  - 참고하는 책에 의하면 ajax 통신 방법을 사용하라는데, 비동기식 방법이 무엇인지 몰라 많이 헤맸으나 부서 사람들의 도움으로 이해 성공
  - emailId 라는 필드를 설정해놓았으나 html 파일에는 emailID 라고 표기하는 바람에 계속 오류가 떠서 헤맸다
  - github 커밋을 하지 못했다
  
## 8/23 (월) 일지
### 중간점검: 앞으로 TO-DO (우선 순위 순)
- VIEW (html) 
  - html 수정 페이지 마무리
  - 삭제 기능 수행 시 경고메시지 혹은 팝업 혹은 안내 메시지 등 삭제 전 표시 (사용자가 실수로 삭제해버릴 경우를 대비)
  - 데이터 입력 기능 수행 시 중복 데이터 처리 방법 수행하기
  - 데이터 입력 기능 수행 시 포맷에 맞는 데이터만 입력하도록 수행하기
  - 데이터 입력 기능 수행 시 그 외 생각해봐야할 오류 및 특이케이스 생각하기
  - html 홈에 해당하는 페이지 주소 오류 확인하기
- html pagination 페이징 기능 추가!
- jmeter 성능 테스트

### 완료
- html 수정 페이지 구현 성공

### 어려웠던 점
- github push 강제로 시켰다가 이전 프로젝트 모두 override 되버려서 없어짐... -f 혹은 sudo 는 조심하자!

## 8/24 (화) 일지
### 완료
- 유저가 emailId 입력 시 이메일 포맷만 입력 가능하도록 구현: [참고한 링크](https://www.w3schools.com/html/html_form_input_types.asp)
- 유저가 데이터 입력 시 필수로 입력하도록 설정 [참고한 링크](https://www.w3schools.com/html/html_form_input_types.asp)
- mariaDB 한글과 이모지 데이터 입력 가능 설정: [참고한 링크](https://juniwoo.tistory.com/4),[참고한 자료](https://nakanara.tistory.com/230)
  - 과정 중 mysql 접속 불가 오류가 떠서 해결: 로컬에서 mariadb 에러 로그 파일 확인 후 수정 + [참고한 링크](https://stackoverflow.com/questions/10892689/cant-connect-to-mysql-server-on-localhost-10061-after-installation#:~:text=The%20error%20(2003)%20Can',one%20configured%20on%20the%20server.)
- 유저가 중복 데이터 입력 시 db에서 확인 할 수 있는 유니크 키를 emailId로 설정: [참고한 링크](https://reddb.tistory.com/120?category=925278)

![duplicate alert page](https://github.com/soobin9711/spring/blob/4ecbbd2bb85b05676f00a27eb4b514a013acdbfb/duplicate.png)
^중복데이터를 넣을 시 중복된 데이터라고 확인하여 페이지에서 오류 페이지로 넘어가는 모습

### 어려웠던 점
- db 암호화 기능이 빨리 끝날 줄 알고 시도했으나 실패. 검색해봐도 너무 다양하게 시도할 수 있어서, 여러 방법을 해봤지만 모두 암호화 이외의 과정 중 여러 설정 오류나 다른 기능과 부딪혀 실패... 우선 순위가 아니니 나중에 도전하기로..!
- 깃헙 복구 작업 ^^

## 8/25 (수) 일지
### 완료
- 수정 완료 시 알림 메시지 뜨게 설정 (수정 필요) 
- 삭제 전 경고 메시지 뜨게 설정 완료
- 삭제 완료 알림 메시지 뜨도록 진행중
- 중복 데이터 확인 후 오류 메시지 뜨도록 진행중
- 

### 어려웠던 점
- html 파일 중 script 안의 method 를 읽지 못하는 오류 (해결 못 하고 다른 문법을 썼다)
- 필요한 설정을 다 해놨는데도 어플리케이션 재시작 필요 없이 자동 로딩되는 건 왜 안되는가??? (내일 부서 개발자님께 물어볼 예정...)
- 중복 데이터를 확인하는 로직을 어디서 진행할 지 헷갈린다
- 빨리 많이 하고 싶은 마음은 앞서는데 따라가지 못하는 중이라고 느낌


## 8/26 (목) 일지
### 완료
- 에러 잡는 걸 확인하기 위해 SQL 로그 뜨는 것 설정 ![참고자료](https://goddaehee.tistory.com/207?category=367461)
- 삭제 완료, 삭제 취소 알림 메시지 뜨도록 진행중
- 중복 데이터 확인 후 오류 메시지 뜨도록 진행중
- paging 진행중
- (어제) html 파일 중 script 안의 method 를 읽지 못하는 오류 수정 완료

### 어려웠던 점
- 빨리 많이 하고 싶은 마음은 앞서는데 따라가지 못하는 중이라고 느낌 2
- 이것저것 하다보면 궁금한게 생겨서 자꾸 서치하면서 다른 길로 새면서 시간이 금방 감 


## 8/30
db paging
![db](https://github.com/soobin9711/spring/blob/7cd8637448e8fe03fc1305c2d237ed81929cf854/DB.png)
