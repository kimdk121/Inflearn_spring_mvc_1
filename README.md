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

 






































