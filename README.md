# FO4-Helper

## 설명
넥슨에서 제공하는 **FC ONLINE** 오픈 **API**를 활용하여 유저들의 **경기 전적**, 경기에서 사용한 **선수** 및 **포메이션**, 그리고 **경기의 상세 기록**을 확인할 수 있는 애플리케이션입니다. <br> 또한, **수수료 계산기** 기능을 통해 선수거래 시 발생하는 수수료를 쉽게 계산할 수 있습니다.


## 기능

- **FCONLINE4** 오픈 **API**를 활용하여 유저의 **경기 전적**, **선수**, **포메이션** 및 **상세 기록**을 실시간으로 확인.
- **수수료 계산기** 기능을 통해 선수 거래 시 발생하는 수수료를 쉽게 계산.
- 직관적이고 간단한 사용자 인터페이스로 유저 경험을 개선.
- 유저가 선택한 경기 기록 및 선수 정보를 빠르고 정확하게 조회할 수 있는 기능.

## 기술 스택

- **언어 (Languages)**: Java, XML
- **도구 (Tools)**: Android Studio
- **라이브러리 (Libraries)**: Android SDK, Glide, Retrofit, Room
- **기술 적용**:
  - **Retrofit**: **넥슨 FCONLINE4 오픈 API**와의 통신을 통해 유저의 경기 전적 및 상세 기록 조회.
  - **Glide**: 이미지 로딩 및 표시 최적화, 사용자 프로필 이미지 등 관리.
  - **Room**: 로컬 데이터베이스 사용, 유저의 즐겨찾기 선수 및 경기 기록 저장.
  - **RecyclerView**: 유연하고 효율적인 리스트 구현, 명언 및 경기 기록 표시.


  
## Screenshots

### Login Screen, Register Screen
<div style="display: flex; flex-wrap: wrap; gap: 10px;">
<img src="screenshots/login.png" alt="login" width="200">
<img src="screenshots/Register.png" alt="Register" width="200">
</div>
  

- 이메일과 패스워드를 이용해 간단 로그인, 회원가입 기능 구현


### Home Screen
<img src="screenshots/home.png" alt="home" width="200">

- 앱 실행 시 보이는 홈 화면.  
- 세 가지 카테고리(**Success**, **Life**, **Motivation**) 중 하나를 선택할 수 있습니다.


### Quote Categories
<img src="screenshots/quotes.png" alt="quotes" width="200">

- 선택한 카테고리에 따라 명언이 표시됩니다.  
- 예: **Success** 카테고리를 선택한 경우.


### Add Quote
<img src="screenshots/savenew.png" alt="savenew" width="200">

- 카테고리를 롱 클릭하면 dialog가 출력됩니다.  
- 새로운 명언을 입력하고 추가 버튼을 클릭하면 데이터베이스에 추가됩니다.


### Delete Quote
<img src="screenshots/optionmenu.png" alt="optionmenu" width="200">

- 오른쪽 상단의 아이콘을 클릭하면 옵션메뉴가 열립니다.
- 목록을 선택하면 각 카테고리별로 저장된 명언들이 새로운 액티비티에 출력됩니다.

  
<img src="screenshots/delete.png" alt="delete" width="200">

- 각 명언 항목들을 길게 클릭하면 삭제됩니다.


## 실행 화면

<img src="video/testvideo.gif" alt="App Demo" width="250">

- 페이드 인/아웃 애니메이션으로 부드럽게 명언이 전환 됩니다.
