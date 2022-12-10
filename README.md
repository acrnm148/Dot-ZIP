# DotZip🏠
똑똑한 내 집 마련
부동산 거래 정보 관리 플랫폼

SSAFY 관통프로젝트

![image](https://user-images.githubusercontent.com/38212743/123923071-9cf3b700-d9c3-11eb-9d96-6ed5b8e9f4eb.png)

<br><br>

<h2> 1️⃣ 기획배경 </h2>

*이 곳 저 곳 비교하지 않고, 한 곳에서 원하는 조건의 집을 찾을 수 없을까?*
<br>

이사를 하고자 할 동네에서 원하는 주택을 찿아낼 목적으로 주택 매물, 동네 정보 등을 조회하고 사용자 편의성을 높일 동네 커뮤니티 등 다양한 서비스를 제공하고자 한다.

<br>

## 2️⃣ 목표

1. 다양한 매물 정보 검색
2. 사용자 맞춤 서비스 제공 
    - 관심 매물, 관심 동네를 등록하고 한 눈에 모아볼 수 있도록 한다.
3. 자치구 별 통계 및 학군 정보 제공
    - 이사갈 동네를 비교하고 선택하는데 도움을 준다
4. 우리동네 커뮤니티로 활용
    - 지역 커뮤니티 활성화에 기여한다.
<br>

## 3️⃣ 역할

<h3>Backend (Spring) & Front-end(Vue)</h3>

  - 로그인, 회원가입, 마이페이지 개발  
  - 지역 별 통계 정보 검색 페이지 개발  
  - 학군 정보 검색 페이지 개발  
  - 메인 페이지 및 팀 소개 페이지 개발  
<br>

## 4️⃣ 개발 환경 & 기술 스택
  - 운영체제 : Window 10, Mac OS X
  - Front : Vue.js, Vue-CLI, Vue-router, Vuex, axios, HTML5, CSS5, BootStrap5, JavaScript, Chart.js
  - Back : SpringBoot, MyBatis, GSON
  - Database : MySQL
  - Etc : Git, Gitlab
<br>

## 5️⃣ 프로젝트 설계

### Client (Vue)
```
happy-house
.
dist/
public/
src/
    assets/
        css/
        img/
        upload/
    common/
        axios.js
        kakao.js
        ...
    components/
        modals/
        Bookmark.vue
        BookmarkArea.vue
        ...
    routers/
        router.js
    store/
        store.js
    vendor/
        notyf/
        sweetalert2/
    App.vue
    main.js
babel.config.js
package.js
package-lock.json
README.md
    
```

### Server (Spring Boot)

```
HappyHouse
.
bin/
gradle/
src/
    main/
        java/
            com.ssafy.happyhouse/
                common/
                    LoggerAspect.java
                    LoginInterceptor.java
                config/
                    GlobalExceptionHandler.java
                    WebMvcConfig.java
                controller/
                    BoardController.java
                    CodeController.java
                    ...
                dao/
                    BoardDao.java
                    CodeDao.java
                    ...
                dto/
                    BoardDto.java
                    BoardFileDto.java
                    ...
                service/
                    BoardService.java
                    BoardServiceImpl.java
                    ...
                HappyHouseApplication.java
        resources/
            config/
            mapper/
                board_query.xml
                code_query.xml
                ...
            static/
                css/
                img/
                js/
                upload/
                favicon.ico
                index.html
            logback.xml
    test/
build.gradle
gradlew
gradlew.bat
settings.gradle
...

```

1. ERD  
    ![image](https://user-images.githubusercontent.com/38212743/123883235-90e80500-d983-11eb-8961-2e69441046a6.png)

2. Class Diagram  
    - DTO  
        ![image](https://user-images.githubusercontent.com/38212743/123883257-a0674e00-d983-11eb-8ee6-9c9e422d81be.png)

    - Controller  
        ![image](https://user-images.githubusercontent.com/38212743/123883264-a3623e80-d983-11eb-982c-d4ffd3af1e27.png)
    
    - Service  
        ![image](https://user-images.githubusercontent.com/38212743/123883278-aa894c80-d983-11eb-923b-da42726aad3f.png)

    - DAO  
        ![image](https://user-images.githubusercontent.com/38212743/123883288-af4e0080-d983-11eb-985f-60f2f93ed1c4.png)



<br>

## 6️⃣ 개발 내용 / 성과

  - **공공 데이터**를 활용한 서울시 아파트 매물, 학교, 통계 정보 (인구수 외 7가지) **DB 생성**
  - Kakao Map API를 활용한 **매물 정보, 학군 정보** 시각화
  - Interceptor를 활용한 로그인, Kakao API를 활용한 **SNS 로그인** 구현
  - 회원정보 수정, 탈퇴, 프로필 사진 업로드(파일 업로드)가 가능한 **MY PAGE** 구현
  - **Validation과 을 사용한 회원가입** 구현
  - 사용자 중심 **MY 관심 서비스** 구현 (관심 매물, 관심 지역 설정)
  - **Chart.js**를 활용한 자치구별 통계 정보 시각화
  - File Upload, Pagenation, CkEditor를 적용한 **커뮤니티** 구현
  - **Git**을 활용한 협업관리


  프로젝트 경진대회 최우수상 수상
  
<br>

## 8️⃣ 발표 자료 / 시연 동영상
![슬라이드1](https://user-images.githubusercontent.com/38212743/119929581-c7afb180-bfb8-11eb-9d56-e7378800666b.PNG)
![슬라이드2](https://user-images.githubusercontent.com/38212743/119929585-c9797500-bfb8-11eb-843c-c47d63b9df7e.PNG)
![슬라이드3](https://user-images.githubusercontent.com/38212743/119929587-c9797500-bfb8-11eb-8463-8598712e9735.PNG)
![슬라이드4](https://user-images.githubusercontent.com/38212743/119929589-caaaa200-bfb8-11eb-8581-dfe3575a4301.PNG)
![슬라이드5](https://user-images.githubusercontent.com/38212743/119929591-caaaa200-bfb8-11eb-8f07-9123a5b58a8b.PNG)
![슬라이드6](https://user-images.githubusercontent.com/38212743/119929592-cb433880-bfb8-11eb-8866-379d90f50be3.PNG)
![슬라이드7](https://user-images.githubusercontent.com/38212743/119929593-cbdbcf00-bfb8-11eb-9d7d-87f011096c1f.PNG)
![슬라이드8](https://user-images.githubusercontent.com/38212743/119929595-cc746580-bfb8-11eb-90c7-0a40856bef6e.PNG)
![슬라이드9](https://user-images.githubusercontent.com/38212743/119929596-cc746580-bfb8-11eb-9451-b1b6ed46f3fe.PNG)
![슬라이드10](https://user-images.githubusercontent.com/38212743/119929597-cd0cfc00-bfb8-11eb-9a8c-13c7aecbd831.PNG)
![슬라이드11](https://user-images.githubusercontent.com/38212743/119929599-cda59280-bfb8-11eb-8e99-e358218a062a.PNG)
![슬라이드12](https://user-images.githubusercontent.com/38212743/119929601-ce3e2900-bfb8-11eb-94ba-8fa96ba46186.PNG)
![슬라이드13](https://user-images.githubusercontent.com/38212743/119929603-ced6bf80-bfb8-11eb-8b1b-733c1f0ebcdb.PNG)
![슬라이드14](https://user-images.githubusercontent.com/38212743/119929605-ced6bf80-bfb8-11eb-9c48-4ffead311ade.PNG)
![슬라이드15](https://user-images.githubusercontent.com/38212743/119929606-cf6f5600-bfb8-11eb-8dd4-7dda26d086ed.PNG)
![슬라이드16](https://user-images.githubusercontent.com/38212743/119929610-d007ec80-bfb8-11eb-9895-f8ca5dee44a3.PNG)
![슬라이드17](https://user-images.githubusercontent.com/38212743/119929613-d007ec80-bfb8-11eb-9e7b-088ed9b111b6.PNG)
![슬라이드18](https://user-images.githubusercontent.com/38212743/119929620-d0a08300-bfb8-11eb-88d3-e4a97d8f4cbc.PNG)
![슬라이드19](https://user-images.githubusercontent.com/38212743/119929621-d1391980-bfb8-11eb-88b9-d73e50d76e9b.PNG)
![슬라이드20](https://user-images.githubusercontent.com/38212743/119929623-d1d1b000-bfb8-11eb-81cf-e6d5dd8683e6.PNG)
![슬라이드21](https://user-images.githubusercontent.com/38212743/119929625-d1d1b000-bfb8-11eb-9c27-e10c724d0daf.PNG)

### 시연영상
https://youtu.be/WNUg9eH2q6s
