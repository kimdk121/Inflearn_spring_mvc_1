# Inflearn_spring_mvc_1
스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술

## 웹 애플리케이션 이해
#### web server, web application server
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
