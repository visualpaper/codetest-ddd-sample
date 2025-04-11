# codetest-umejima

* Maven
* Java 1.8
  - lombok

* Spring Boot 2.6.3
  - spring-boot-starter-web
  - spring-boot-starter-tomcat
  - spring-boot-starter-test
  - spring-boot-starter-data-jpa

## setup

* [spring-initializer](https://start.spring.io/)  
  ※ GroupId: org.visualpaper.example  
  ※ Artifact: codetest-umejima  
  ※ Package Name: org.visualpaper.example.codetest.umejima  
  ※ Packaging: War

<br><br>

## formatter

* [Google Java Style](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)　　
  - Editor -> Code Style -> Java -> Import Scheme で取り込む


<br><br>

## open api

* [openapi-generator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-maven-plugin/README.md) 4.3.1
  - [swagger editor](https://editor.swagger.io/)

<br><br>

## formatter

* [Google Java Style](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)　　
  - Editor -> Code Style -> Java -> Import Scheme で取り込む

<br><br>

## logging

* logback
  - App Log  
    ※ WARN 以上のログを出力する。  
    ※ 検知ログとして利用する。

  - Dump Log  
    ※ INFO 以上のログを出力する。  
    ※ 調査ログとして利用する。

  - コンソール  
    ※ INFO 以上のログを出力する。  
    ※ 開発時用のログとして利用する。

<br><br>

## unit test

* Junit5
  - junit-jupiter-engine
  - junit-jupiter-params
  - junit-jupiter-api
  - [assertj](https://joel-costigliola.github.io/assertj/)  
    ※ アサーションに利用

<br><br>

## env

* application
  - 本番環境用
　　- mysql
     ※ 別コンテナの 3306 port にアクセスしている。

<br>

* application-dev
  - ローカル環境用  
    ※ Plugins -> spring-boot -> spring-boot:run 右クリックで Create し「-Dspring-boot.run.fork=false」を追加しておくこと  
       > spring-boot:run -Dspring-boot.run.fork=false -Dspring.profiles.active=dev -f pom.xml　　

  - h2  
    ※ http://localhost:8080/h2-console (URL jdbc:h2:mem:db) でデバッグ可能

<br>

* application-test
  - テスト用  
    ※ `@ActiveProfiles("test")` をテストクラスに付けることで利用可能

  - h2  
    ※ http://localhost:8080/h2-console (URL jdbc:h2:mem:db) でデバッグ可能
