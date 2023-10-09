# 🙋🏻‍♂개모임 : 개발자를 위한 스터디 모임

**개발 : Full-Stack develop**  
**기간** : 2022.10 ~ 2022.12  

---

- 인원 : 총 4명(기획30%, DB30%, 백엔드35%, 프론트30%)
- 언어 : JAVA, JavaScript

---
<br>

### 목차
- [🙋🏻‍♂개모임 : 개발자를 위한 스터디 모임](#목차)
  * [📄 서비스 소개](#서비스-소개)
  * [🛠 기술 스택](#-기술-스택)
  * [🖥 개발 내용](#-개발-내용)
    + [아키텍처](#아키텍처)
  * [🔨 담당 기능](#-담당-기능)
  * [⚙️ 개발 과정](#EF%B8%8F-개발-과정)
    + [1. 이벤트 스토밍 : DDD(Domain Driven Design)](1-이벤트-스토밍--ddddomain-driven-design)
    + [2. DB 테이블 설계](#2-db-테이블-설계)
    + [3. 화면 설계 : Figma 활용](#3-화면-설계--figma-활용)
    + [4. 샘플 데이터 추가](#4-샘플-데이터-추가)
    + [5. SQL 도출](#5-sql-도출)
    + [6. Repository 설계 : Interface class 기반 구현체](#6-repository-설계--interface-class-기반-구현체)
    + [7. Service 설계 : Interface class 기반 기능 개발](#7-service-설계--interface-class-기반-기능-개발)
    + [8. Controller 설계 : Rest API 개발](#8-controller-설계--rest-api-개발)
    + [9. TEST : Junit5, PostMan](#9-test--junit5-postman)
    + [10. 화면 구현 : JS, HTML, CSS](#10-화면-구현--js-html-css)
  * [📓 협업](#-협업)
  * [💡 성장하고 배운점](#-성장하고-배운점)
    + [1. 전반적인 웹 개발 능력 함양](#1-전반적인-웹-개발-능력-함양)
    + [2. 문제 해결](#2-문제-해결)
    + [3. 팀원과의 협업](#3-팀원과의-협업)
  * [👀 서비스 화면](#서비스-화면)
  * [🏆 수상](#-수상)

<br>

## 📄 서비스 소개

개모임은 개발자를 위한 스터디 챌린지 서비스입니다.  
1일 1커밋 운동 등 개발자의 특수성을 고려한 스터디 운영 및 자동 관리 서비스입니다.  
기존 챌린지 형태에서 챌린지장의 역할을 최소화하기 위해 출결 체크, 과제 수행 여부 체크 등을 자동화 하였습니다.  

**✨ 위 서비스에서 제공하는 핵심 기능 4가지입니다.**

1. (스터디장) 스터디 개설, (스터디원) 내게 필요한 스터디 검색 및 참여
2. 과제 및 출결 자동 체크 - Github 커밋 로그 기반
3. 스터디원 진행률 시각화(달력)
4. 챌린지 종료시 성실도 기반 참가비 자동 정산
<br>

## 🛠 기술 스택

- BACK  : Spring, Mavne, OracleDB, Tomcat
- FRONT : Ajax, jQuery, HTML, CSS
- TEST  : Junit5, Postman
- TOOL  : Github, SourceTree, Notion, Figma
<br>

## 🖥 개발 내용

### Spring MVC 구조 설계

- Spring MVC 기반으로 웹 애플리케이션 개발하였습니다.
- Tomcat을 이용하여 백서버를 구성하고, Front와 Http 프로토콜을 기반으로 통신하였습니다.
- MyBatis를 이용하여 Oracle DB에 접근하였고, Github API 활용과 Oauth(Kakao)를 이용하였습니다. 

### 아키텍처  
<img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/bed27214-a3dd-4c8d-bbc7-1b892b958a07" width="80%">
    
<br><br><br>

## 🔨 담당 기능

💬 팀원간 포지션 구분 없이, 전 과정을 두루 맡아 개발을 진행하였습니다.  
그 중에 Back-End에서의 개인으로 맡은 기능을 요약하였습니다.  

- Github API 활용 과제 제출 및 과제 유효성 체크
- 스터디 종료시 정산 로직(참가비, 성실도)
- 스터디 목록 페이징
- 스터디 검색 기능 및 필터 기능
- 테스트 데이터 생성 및 입력 값 유효성 QA
<br>

## ⚙️ 개발 과정


💬 **기본에 충실한 Step by Step**  
결과물 내기에 급급하기보다 과정을 잘 밟아가며 배운 것을 활용하는 것이 프로젝트의 목표입니다.


- **개발 과정 10단계**
    
    ### 1. 이벤트 스토밍 : DDD(Domain Driven Design)
    
    이벤트 스토밍을 통해 도메인을 도출하였습니다.  
    도메인 이벤트 도출 → 커맨드 도출 → 외부 시스템 도출 → 액터 도출  
    → 애그리거트 도출 → 컨텍스트 경계 그리기 → 정책 도출
  
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/34d97168-ed39-44ab-aa32-b143cd1ff345" width="60%">
    
    ### 2. DB 테이블 설계
    
    도출된 도메인을 기반으로 ERD를 이용하여 DB 테이블을 설계했습니다.
    최종 5번의 수정을 거쳐 설계되었습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/71be75ef-10a9-424d-a89a-c66c0ca73a3c" width="60%">
    
    ### 3. 화면 설계 : Figma 활용
    
    예상되는 실제 화면을 구성하며 부가적으로 필요한 기능들을 도출하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/399bd1cf-2855-4556-9cdc-77116b3e030a" width="60%">
    
    ### 4. 샘플 데이터 추가
    
    서비스에 필요한 샘플 데이터를 추가하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/7679cb25-e200-47b0-9fc3-6fa25da7bd09" width="60%">
    
    ### 5. SQL 도출
    
    MyBatis를 이용하여 mapper.xml을 작성하였습니다. ResultMap을 설계하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/ff0df06c-8c83-48f1-965d-ed5a4d7cc639" width="60%">
    
    도출된 필요한 쿼리문을 작성하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/116917a6-de8e-40b0-aca4-04eb2e49010f" width="60%">
    
    ### 6. Repository 설계 : Interface class 기반 구현체
    
    객체에 따라 Study, User, Wallet 3개의 Interface class를 작성하고,  
    그에 맞추어 DB에 접근하는 RepositoryOracle class를 구현하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/e262faf2-8b24-4775-a9b0-8a883518bfa6" width="60%">
    
    ### 7. Service 설계 : Interface class 기반 기능 개발
    
    Interface class의 메소드를 호출하여 기능을 수행하는 Service class를 구현하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/90a44885-6bf8-4a40-a9b9-196fba567498" width="60%">
    
    ### 8. Controller 설계 : Rest API 개발
    
    컨테이너의 요청을 1차로 응답할 Controller를 설계하였습니다.  
    Restful한 API를 설계하려 노력했습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/ddf29810-c6f0-4690-b191-cb2de33d2eff" width="60%">
    
    ### 9. TEST : Junit5, PostMan
    
    Service class의 메서드 단위로 예상 결과값과 비교하여 테스트를 진행하였고,  
    PostMan을 이용하여 요청에 대한 응답을 체크하였습니다.
    
    <img src="https://github.com/sungchanmin/sungchanmin/assets/97079985/4e7bc0d3-b735-4d9f-a054-9b4d82614db1" width="60%">
    
    ### 10. 화면 구현 : JS, HTML, CSS
    
    JavaScript의 Ajax를 이용하여 Back 서버와 통신을 구성하였고,  
    페이징과 같은 기능들을 처리하였습니다.  
    사진은 아래의 서비스 화면을 참고해주세요.
    
<br>

## 📓 협업

- **소스코드 관리** : GitHub와 SourceTree 툴을 이용하여 소스코드를 관리하였습니다.
- **개발 컨벤션 설정** : 코드 작성법과 팀원들간의 룰을 정했습니다.
  * [🔗 가이드라인(규약)](https://user26.notion.site/73e0265b31f04df09110774b2ca7212b?pvs=4)
- **개발 진행사항 체크** : 노션을 이용하여 현재 진행도와 구현 내역을 관리하였습니다.  
  * [🔗 Service, Repo 설계](https://user26.notion.site/8cd47e6da1d8463899e2a7643e31e9d9?v=5bde9fd603234930bded09c8bb073b36&pvs=4)
  * [🔗 Controller 설계](https://user26.notion.site/fd29f2d7f0464876ac8df71583ed817b?v=9d95ff788b69459b97b47e5f000f20e3&pvs=4)
<br>

## 💡 성장하고 배운점

### 1. 전반적인 웹 개발 능력 함양

- Spring의 구조와 컨테이너 작동 및 MVC 패턴에 대한 이해를 얻었으며, 아키텍처를 직접 설계하고  
  개발의 전 과정을 경험하며 백엔드 뿐만 아니라 프론트 엔드, 테이블 및 DTO 설계 등 전반적인
  웹 개발 능력을 키울 수 있었습니다.
- 인터페이스를 통한 개발로 객체지향적인 관점에 대한 이해와 특히 다형성을 통한 설계의 장점을 배웠습니다.
- 로그 요청 및 Oauth 등 API에 대한 이해와 활용 능력을 키울 수 있었습니다.
- Rest API에 대한 이해와 설계 능력을 키웠습니다.
- 동적 쿼리 작성 등 MyBatis 활용 능력을 키웠습니다.

### 2. 문제 해결

- DTO 설계 : HAS-A 관계를 고려하지 못해, 중간에 많은 수정 과정을 거쳐야했습니다.  
  DTO 설계에 대한 관점을 조금 더 확장할 수 있었습니다.
- 테이블 설계 : 도메인 설계 이후 테이블 설계에 여러번의 수정을 거쳤고, 특히 충전 금액을 다루는 방식에 대해  
  고심하며 더 나은 방식을 찾으려 노력했습니다.
- CORS 이슈 해결 : 서버 분리에 따른 CORS 이슈에 대한 이해와 대처법을 익혔습니다.
- 병합 충돌 : Git을 통한 코드 형상 관리 과정에서 발생한 여러 충돌을 해결하며 Git 활용 능력을 키웠습니다.

### 3. 팀원과의 협업

- 총 4명의 팀원들과 기획 단계부터 함께 참여했으며, 서로의 의견을 존중함으로 자유롭게 아이디어가 나올 수 있는  
  분위기를 조성하여 개발 전 과정에 거쳐 끊임없는 대화를 나누었습니다.
- 효과적인 소통을 위해 노션을 통하여 개발 진행 사항을 체크하였으며, 기술적으로 해결할 수 있는 문제와 그렇지
  않은 문제에 대해 함께 논의하며 서비스를 발전시켰습니다.
- 항상 열린 마음으로 상대를 이해하려는 태도가 협업의 출발점이라는 것을 배웠습니다.
<br>

## 👀 서비스 화면

![12](https://github.com/sungchanmin/sungchanmin/assets/97079985/78adc3e8-e4c0-4723-8dec-f428ec750dd6)

<br><br>

## 🏆 수상

- 한국SW산업협회 프로젝트 발표회 대상
