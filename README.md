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
#### 모든 HTTP 요청을 중앙에서 처리하는 프론트 컨트롤러 서블릿
1. 클라이언트의 HTTP 요청 받음
2. 적절한 컨트롤러로 요청을 전달
3. 컨트롤러에서 처리된 결과를 View로 전달
4. 최종 결과를 클라이언트에게 반환

