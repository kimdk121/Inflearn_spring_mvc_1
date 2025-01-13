# Inflearn_spring_mvc_1
스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술

## 웹 애플리케이션 이해
### web server, web application server
- Web Server
  - HTTP 기반
  - 정적 리소스 제공 (HTML, CSS, JS, 이미지, 영상)
  - 예) NGINX, APACHE
- Web Application Server
  - HTTP 기반
  - 웹서버 기능 포함
  - 프로그램 코드를 실행해서 애플리케이션 로직 수행
    - 동적 HTML, HTTP API (JSON)
    - 서블릿, JSP, 스프링 MVC
  - 톰캣 (Tomcat) Jetty, Undertow
- 시스템 구성
  - Web Server -> WAS -> DB
  - 정적 리소스는 Web Server가 담당하고 애플리케이션 로직은 WAS로 위임하는 이 구성이 유리

### Servlet
- Client의 요청에 Web Server는 그 요청을 Web Container에 위임
- Web Container는 각 요청에 해당하는 Servlet을 찾아서 실행
- Servlet은 그 요청에 대한 기능을 수행한 수 결과를 Client에 반환

#### Servlet Container
- WAS는 Web Server + Web Container (Servlet Container)
- 자바는 웹 구현 기술로 Servlet을 사용하는데 이 Servlet을 관리하고 jsp파일을 실행할 수 있게 해주는 것이 Servlet Container
- Tomcat, Jetty 등이 Servlet Container
- 클라이언트로 부터 HTTP 요청을 받아 서블릿 객체를 실행하고 결과를 응답으로 반환

#### DispatcherServlet 
Spring MVC에서 모든 HTTP 요청을 중앙에서 처리하는 프론트 컨트롤러 서블릿 <br>
Spring MVC에서의 HTTP 요청 응답 과정 
1. 클라이언트가 HTTP 요청을 전송
2. Web Server가 Web Container (Tomcat 등) 에 요청을 전달
3. Web Container가 DispatcherServlet에 요청을 전달
5. DispatcherServlet이 HandlerMapping을 사용해 적절한 Controller로 요청을 전달
6. Controller에서 요청 처리된 결과를 다시 DispatcherServlet로 전달
7. DispatcherServlet이 ViewResolver를 통해 View를 생성
8. DispatcherServlet은 최종 결과를 클라이언트에게 반환

### 쓰레드
- 애플리케이션 코드를 하나하나 순차적으로 실행하는 것
- 자바 메인 메서드를 처음 실행하면 main이라는 이름의 쓰레드가 실행됨
- 쓰레드는 한번에 하나의 코드 라인만 수행
- 동시 처리가 필요하면 쓰레드를 추가로 생성

#### 요청 마다 쓰레드 생성의 장단점
- 장점
  - 동시 요청을 처리할 수 있다.
  - 리소스 (CPU, 메모리)가 허용할 때 까지 처리가능
  - 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작한다.
- 단점
  - 쓰레드는 생성 비용이 매우 비싸다.
    - 고객의 요청이 올 때 마다 쓰레드를 생성하면 응답 속도가 늦어진다.
  - 쓰레드는 컨텍스트 스위칭 비용이 발생한다
  - 쓰레드 생성에 제한이 없다.
    - 고객 요청이 너무 많이 오면 CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다.

#### 쓰레드 풀
- 특징
  - 필요한 쓰레드를 쓰레드 풀에 보관하고 관리한다.
  - 쓰레드 풀에 생성 가능한 쓰레드의 최대치를 관리한다. 톰캣은 최대 200개 기본 설정 (변경 가능)
- 사용
  - 쓰레드가 필요하면 이미 생성되어 있는 쓰레드를 쓰레드 풀에서 꺼내서 사용한다.
  - 사용을 종료하면 쓰레드 풀에 해당 쓰레드를 반납한다.
  - 최대 쓰레드가 모두 사용중이어서 쓰레드 풀에 쓰레드가 없으면?
    - 기다리는 요청은 거절하거나 특정 숫자만큼만 대기하도록 설정할 수 있다.
- 장점
  - 쓰레드가 미리 생성되어 있으므로, 쓰레드를 생성하고 종료하는 비용 (CPU)이 절약되고 응답시간이 빠르다.
  - 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다.
- 실무 팁
  - WAS의 주요 튜닝 포인트는 최대 쓰레드의 수이다.
  - 이 값을 너무 낮게 설정하면?
    - 동시 요청이 많으면 서버 리소스는 여유롭지만 클라이언트는 금방 응답 지연
  - 이 값을 너무 높게 설정하면?
    - 동시 요청이 많으면 CPU, 메모리 리소스 임계점 초과로 서버 다운
  - 장애 발생시
    - Cloud 서버면 일단 서버부터 늘리고 이후에 튜닝
    - Cloud 서버가 아니면 바로 튜닝

 ### CSR, SSR
 - Server Side Rendering
   - 서버에서 최종 HTML을 생성해서 클라이언트에 전달
   - 주로 정적인 화면에 사용
   - 관련기술 : JSP, 타임리프 -> 백엔드 개발자
 - Client Side Rendering
   - HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용
   - 주로 동적인 화면에 사용, 웹 환경을 마치 앱처럼 필요한 부분부분 변경할 수 있음
   - 예) 구글 지도, Gmail, 구글 캘린더
   - 관련기술 : React, Vue.js -> 웹 프론트엔드 개발자

## 서블릿
### HTTP 요청 데이터
- GET - 쿼리 파라미터
  - /url?username=hello&age=20
  - 메시지 바디 없이 URL의 쿼리 파라미터에 데이터를 포함해서 전달
  - 검색, 필터, 페이징 등에서 많이 사용
- POST - HTML Form
  - Content-Type : application/x-www-form-urlencoded
  - 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
  - 회원가입, 상품주문, HTML Form 사용
- HTTP message body에 데이터를 직접 담아서 요청
  - HTTP API에서 주로 사용 JSON, XML, TEXT
  - 데이터 형식은 주로 JSON
  - POST, PUT, PATCH




WEB-INF : JSP 파일들을 브라우저에서 직접 접근 못하게 함

핸들러 매핑, 핸들러 어댑터
- HandlerMapping
  1. RequestMappingHandlerMapping : 어노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
  2. BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
- HandlerAdapter
  1. RequestMappingHandlerAdapter : 어노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
  2. HttpRequestHandlerAdapter : HttpRequestHandler 처리
  3. SimpleControllerHandlerAdapter : Controller 인터페이스 (어노테이션X, 과거에 사용) 처리

@PathVariable <br>
@GetMapping("/mapping/{userId}") <br>
    public String mappingPath(@PathVariable("userId") String data) { <br>
    public String mappingPath(@PathVariable String data) { <br>
<br>
@GetMapping("/{userId}") <br>
    public String findUser(@PathVariable String userId) { <br>

HttpEntity : HTTP header, body 정보를 편리하게 조회 <br>
HttpEntity는 응답에도 사용이 가능
- 메시지 바디 정보 직접 반환
- 헤더 정보 포함 가능
- view 조회 X

링크 표현식 : th:href="@{/css/bootstrap.min.css}"
th:onclick="|location.href='@{/basic/items/add}'|"

리터럴 대체 문법 - |...|
- <span th:text="'Welcome to our application, ' + ${user.name} + '!'">
- <span th:text="|Welcome to our application, ${user.name}!|">























