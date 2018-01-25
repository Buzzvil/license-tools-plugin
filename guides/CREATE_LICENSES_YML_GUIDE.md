# 3rd_party_licenses.yml 생성 가이드

만약 프로젝트/모듈 루트에 `3rd_party_licenses.yml` 파일이 없는 경우 새로 만들어야 합니다. 아래의 지시를 따라서 생성할 수 있습니다.

1. Android Studio의 메뉴에서 `View -> Tool Windows -> Gradle`을 클릭하여 Gradle projects 창을 엽니다.
2. `project (-> :module) -> Tasks -> verification -> checkLicense`를 클릭하여 실행하면 Gradle Console에 아직 `3rd_party_licenses.yml`에 등록하지 않은 라이센스 목록이 뜹니다.
3. Gradle Console의 출력을 복사해서 프로젝트/모듈 루트 밑에 `3rd_party_licenses.yml` 파일을 만들고, 그 안에 붙여넣습니다.
