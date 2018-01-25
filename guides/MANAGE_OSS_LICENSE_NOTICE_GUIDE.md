# 서드파티 오픈소스 소프트웨어 라이브러리 관리 가이드

프로젝트에서 오픈 소스 소프트웨어의 목록이 변경되어서 라이센스 목록도 변경해야 할 때 해야 할 일에 대한 가이드입니다.

## 선행 작업
* [license-tools-plugin](https://github.com/Buzzvil/license-tools-plugin)이 설치되지 않은 경우, [license-tools-plugin 설치 가이드](INSTALL_GUIDE.md)를 우선 수행합니다.
* 만약 프로젝트/모듈 루트에 `3rd_party_licenses.yml` 파일이 없는 경우, [3rd_party_licenses.yml 생성 가이드](CREATE_LICENSES_YML_GUIDE.md)를 수행합니다.

## 라이센스 목록 변경하기
`3rd_party_licenses.yml` 파일을 열고, [3rd_party_licenses.yml 변경 가이드](LICENSES_YML_GUIDE.md)에 따라 기존 라이센스를 제거하거나, 새로운 라이센스 정보를 추가합니다.
[license-tools-plugin](https://github.com/Buzzvil/license-tools-plugin)은 flavor를 지원하지 않으므로, 각 flavor에서만 빌드하는 것들은 손으로 추가해서 사용하는 모든 소프트웨어의 라이센스가 기재되도록 합니다.

## HTML 파일 생성하기
[license-tools-plugin](https://github.com/Buzzvil/license-tools-plugin)은 `3rd_party_licenses.yml`을 바탕으로 `src/main/assets/3rd_party_licenses.html` 파일을 생성하는 역할을 합니다. 다음 지시를 따라서 HTML 파일을 만들 수 있습니다.

1. Android Studio의 메뉴에서 `View -> Tool Windows -> Gradle`을 클릭하여 Gradle projects 창을 엽니다.
2. `project (-> :module) -> Tasks -> other -> generateLicensePage`를 클릭하여 실행하면 HTML 파일이 생성됩니다.
3. 생성된 HTML 파일을 웹브라우저에서 실행해서 원하는 대로 정보들이 잘 표시되는지 확인하세요.

## HTML 파일 후처리하기
생성된 HTML 파일은 허니스크린 기준으로 작업이 되어 있습니다. 버즈스크린의 경우 다음의 후처리 작업이 필요합니다.
1. 제목 주석 제거
허니스크린에서는 큰 제목이 불필요해서 제목이 주석처리되어 있습니다. 찾아서 주석을 제거하세요.

2. 버즈스크린에서 사용하는 소프트웨어 라이센스만 남기기
버즈스크린에서 사용하는 소프트웨어의 라이센스만 남겨야 합니다. 나머지는 직접 수동으로 지워야 합니다.
