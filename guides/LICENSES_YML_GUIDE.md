# licenses.yml 변경 가이드
여기서는 원하는 라이센스를 추가하거나, 변경하거나, 삭제하기 위해서 `licenses.yml` 파일을 변경하는 방법에 대해서 안내합니다.
yml은 YAML 파일입니다. YAML의 문법에 대해서는 [스펙 문서](http://www.yaml.org/spec/)를 참고하거나, 위키피디아([한글](https://ko.m.wikipedia.org/wiki/YAML), [영문](https://en.wikipedia.org/wiki/YAML) 등의 문서를 참고하시기 바랍니다.

## 라이센스 해시 설명
`licenses.yml` 파일은 각 라이센스를 해시로 하는 리스트로 구성되어 있습니다. 라이센스 해시에 대한 구체적인 예시는 아래와 같습니다.

```yml
- artifact: com.google.code.gson:gson:+
  name: Gson
  year: 2008
  copyrightHolder: Google Inc.
  license: apache2
  licenseUrl: https://github.com/google/gson/blob/master/LICENSE
  url: https://github.com/google/gson
  skip: false
```
여기서는 gson을 예시로 들었습니다.
* `artifact`는 해당 소프트웨어를 가져올 때 사용하는 [gradle의 compile](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html#sec:declaring_your_dependencies)의 3가지 인자인 group, name, version을 `:` 으로 이어서 차례로 적어줍니다. 여기서 name과 version은 wildcard로 `+`를 사용할 수 있습니다.
* `name`은 해당 소프트웨어의 이름을 적어줍니다. 라이센스 목록에 표시될 이름입니다.
* `year`는 저작권자가 명시한 기간을 적어줍니다. 없으면 생략 가능합니다.
* `copyrightHolder`는 저작권자의 이름이나 이메일 등을 적어줍니다. 명시하지 않은 경우 빈 스트링 `''`을 넣어주면 라이센스 목록에서 라이센스 문구가 제거됩니다. 이 기능은 아파치 라이센스 2.0에서만 테스트되었습니다.
* `license`는 해당 소프트웨어에서 명시한 라이센스의 종류를 적습니다. 보통 README 파일의 맨 아래나 LICENSE 파일에 라이센스의 종류가 적혀 있습니다. 대표적인 라이센스들은 다음과 같고, [LibraryInfo.groovy의 normalizeLicense()](https://github.com/Buzzvil/license-tools-plugin/blob/buzzvil/plugin/src/main/groovy/com/cookpad/android/licensetools/LibraryInfo.groovy#L100)에서 상세한 목록을 확인할 수 있습니다.
    * `apache2`, `mit`, `bsd 2 license`, `bsd license`, `custom`
* `licenseUrl`은 해당 소프트웨어의 LICENSE 파일의 링크를 넣습니다. 없는 경우에는 공식 라이센스 템플릿의 주소를 넣어도 됩니다.
* `url`은 해당 소프트웨어의 홈페이지나, 해당 소프트웨어의 소스코드를 볼 수 있는 주소를 넣습니다. 라이센스 목록에서 하이퍼링크가 제공됩니다.
* `skip`은 해당 소프트웨어의 라이센스는 출력하지 않고 싶을 때, true를 넣어줍니다. 기본값은 false이므로, 생략 가능합니다.
* `licenseBody`는 `custom` 라이센스일 때 사용합니다. 자세한 사항은 아래의 *커스텀 라이센스 가이드*를 참고하세요.
* `noticeUrl`은 해당 소프트웨가 NOTICE 파일을 제공하는 경우, 이것에 대한 링크를 넣어주면 라이센스 목록에서 접근할 수 있는 링크를 만들어 줍니다.

## 라이센스 추가하기
위의 *라이센스 해시 설명* 부분을 참고해서, 새로운 라이센스 해시를 리스트에 추가합니다.

## 라이센스 변경하기
위의 *라이센스 해시 설명* 부분을 참고해서, 기존의 라이센스 해시를 변경합니다.

## 라이센스 삭제하기
위의 *라이센스 해시 설명* 부분을 참고해서, 해당 라이센스 해시를 제거합니다.

## Buzzvil에서 추가한 기능 사용법

### 커스텀 라이센스 가이드
널리 알려진 라이센스를 사용하지 않는 경우, 다음과 같이 사용하면 커스텀 라이센스를 쓸 수 있습니다.
```yml
- artifact: com.test:+:+
  license: custom
  licenseBody: |
      license body 1
      license body 2
      license body 3
...
```

### 저작권자 제거 기능
저작권자(copyrightHolder)에 빈 문자열을 넣으면 저작권자 부분이 제거됩니다.
apache2 라이센스에 대해서만 테스트되었습니다.

```yml
- artifact: com.test:+:+
  copyrightHolder: ''
  license: apache2
...
```

### NOTICE 링크 기능
소프트웨어에서 사용한 오픈 소스 소프트웨어의 라이센스 목록을 NOTICE 파일로 제공하는 경우, 이 파일에 대한 링크를 만들 수 있습니다.

```yml
- artifact: com.test:+:+
  noticeUrl: kakao_sdk_notice.html
...
```
