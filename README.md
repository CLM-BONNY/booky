# Booky
소프트웨어학부 20203035 김민선
<hr />

### 목차
- 실행 환경 및 개발 환경
- 구현 내용

### 실행 환경 및 개발 환경
- 실행 환경
  - Android Virtual Device (AVD): Pixel 2 API 31 (Android 12)
  - 내부저장소 접근
- 개발 환경
  - IDE: Android Studio 11.0.13
  - SDK: API 32 (Android 12)

### 구현 내용
- 로그인 화면(activity_login)
  - 로그인 버튼 클릭
    - editText에 텍스트가 입력되었는지, 입력한 아이디와 일치하는 파일명이 내부 저장소에 존재하는지, 해당 파일(입력한 아이디와 일치하는 파일명을 가진 파일) 내용에 존재하는 비밀번호인지 확인한다.
    - 위의 조건에 만족하지 않는다면, 각각의 에러 메시지를 Toast로 출력한다.
    - 위의 조건에 모두 만족한 경우, 상품 화면(activity)으로 이동한다.
  - 회원가입 버튼 클릭
    - 회원가입 화면(activity_signup)으로 이동한다.
  - 상품보러가기 버튼 클릭
    - 상품 화면(activity_goods)으로 이동한다.

- 회원가입 화면(activity_signup)
  - 아이디
    - 중복 확인 버튼
      - 입력한 아이디와 일치하는 파일명이 내부 저장소에 존재하는지 확인한다.
      - 존재한다면 에러메시지를 Toast로 출력한다.
  - 비밀번호
    - 비밀번호 확인 버튼
      - 비밀번호가 영문자와 숫자를 포함한 6자리 이상인지 확인한다.
      - 조건에 맞지 않는다면 에러메시지를 Toast로 출력한다.
  - 기타 정보(이름, 전화번호, 주소)
  - 개인정보 이용 동의
    - 초기에는 개인정보 이용약관을 보여주지 않는다.
    - 개인정보 이용동의 텍스트뷰 홀수번 클릭시, 개인정보 이용약관을 보여준다.
    - 개인정보 이용동의 텍스트뷰 짝수번 클릭시, 개인정보 이용약관을 보여주지 않는다.
    - 개인정보 이용약관은 스크롤이 가능하다.
