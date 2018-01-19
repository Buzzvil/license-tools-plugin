# license-tools-plugin 설치 가이드

이 문서는 개인적으로 자유롭게 할 수 있는 방법인 로컬 Maven 저장소에 [license-tools-plugin](https://github.com/Buzzvil/license-tools-plugin)을 올리고, 이것을 프로젝트/모듈에서 가져와서 사용하는 방법에 대해서 안내합니다. 추후 원격 저장소가 지원되는 경우, 원격 저장소를 사용하는 방법에 대한 가이드도 변경할 예정입니다.

## 로컬 Maven 저장소를 이용하기
이 방법을 사용하면 [license-tools-plugin](https://github.com/Buzzvil/license-tools-plugin)가 `~/.m2/` 밑에 저장됩니다. 허니스크린 프로젝트에서는 여기에서 이 플러그인을 로드해서 사용할 수 있게 됩니다.

### license-tools-plugin 올리기
다음 명령어들을 셸에서 순서대로 실행하면 됩니다.

```bash
git clone git@github.com:Buzzvil/license-tools-plugin.git
cd license-tools-plugin
git checkout buzzvil
./gradlew clean
./gradlew build
./gradlew publishToMavenLocal
```

### 프로젝트/모듈에서 license-tools-plugin 불러오기

1. Android Studio에서 `(module/)build.gradle`에 다음 작업을 합니다.

```gradle
bulidscript {
    repositories {
        mavenLocal() // local repository에 라이브러리가 있으면 먼저 사용하도록 앞에 명시
        jcenter() // license-tools-plugin의 dependency를 위해 추가
        ...
    }

    dependencies {
        classpath ('com.cookpad.android.licensetools:license-tools-plugin:1.1.0') {changing=true}
    }
}

apply plugin: 'com.cookpad.android.licensetools'

...

licenseTools {
    ignoredGroups = ['com.buzzvil.buzzscreen.ext'] // 이 그룹을 무시하게 하지 않으면 에러가 발생함
}
```

2. gradle을 새로고침합니다.
