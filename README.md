# spring-redis-cache-example
example redis cache


download redis di link https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100

di application.yaml

masukan configurasi sebagai berikut

spring:
  cache:
    type: redis
    cache-names: user
    redis:
      key-prefix: user-test
      cache-null-values: false
      time-to-live: 1000000

  profiles:
    active: dev

  redis:
    host: localhost
    port: 6379
    database: 0
    timeout: 60000
    

note response data harus di serialize dulu 


untuk run applikasi ini tinggal klik run saja hehe
