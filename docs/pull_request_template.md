## 참고자료
[swagger fetch error](https://velog.io/@dev_tmb/RestDocsSwagger-UI-%EC%82%AC%EC%9A%A9-%EC%A4%91-%EB%A7%88%EC%A3%BC%ED%95%9C-Swagger-Fetch-Error)
[OpenAPI Specification을 이용한 더욱 효과적인 API 문서화](https://tech.kakaopay.com/post/openapi-documentation/)
[테스트를-통한-open-api-specification-자동화](https://velog.io/@kimdoha/%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%ED%86%B5%ED%95%9C-open-api-specification-%EC%9E%90%EB%8F%99%ED%99%94)


## PR 설명
<!-- 해당 PR이 왜 발생했고, 어떤부분에 대한 작업인지 작성해주세요. -->
[STEP_1] ()  
[STEP 2] ()  
[STEP_3] ()  
[STEP 4] ()  


## 리뷰 포인트
``` shell
1. 사용자가 결제를 취소하면 취소(CANCEL) 상태로 전환하려하는데 사용자가 결제를 취소했다는 것은 어떤 기준으로 파악하시나요?
2. PG 사에 결제 요청을 보낸 후 결제 상태들이 변경될 때, 
    -  코드 상에서 각 결제 상태를 변환하는 것
    -  이벤트 혹은 기술적인 관점에서 따로 분리하는 것 중 어떻게 처리하는게 좋을까요? 

3. 쿠폰상태 변경도 동일하게 궁금합니다. 스케줄러에서 처리하는게 좋을까요?
```
## Definition of Done (DoD)
<!--
    DOD 란 해당 작업을 완료했다고 간주하기 위해 충족해야 하는 기준을 의미합니다.
    어떤 기능을 위해 어떤 요구사항을 만족하였으며, 어떤 테스트를 수행했는지 등을 명확하게 체크리스트로 기재해 주세요.
    리뷰어 입장에서, 모든 맥락을 파악하기 이전에 작업의 성숙도/완성도를 파악하는 데에 도움이 됩니다.
    만약 계획되거나 연관 작업이나 파생 작업이 존재하는데, 이후로 미뤄지는 경우 TODO -, 사유와 함께 적어주세요.

    ex:
    - [x] 상품 도메인 모델 구조 설계 완료 ( [정책 참고자료](관련 문서 링크) )
    - [x] 상품 재고 차감 로직 유닛/통합 테스트 완료
    - [ ] TODO - 상품 주문 로직 개발 ( 정책 미수립으로 인해 후속 작업에서 진행 )
-->

- [STEP-03] 시나리오 요구사항 분석 및 문서 작성
    - [x] 유저 포인트 도메인 모델 구조 설계 완료 
    - [x] 상품 도메인 모델 구조 설계 완료 
    - [x] 주문/결제 도메인 모델 구조 설계 완료 
    - [x] 쿠폰 도메인 모델 구조 설계 완료

- [STEP-04] 분석을 설계로 풀어내기
    - [x] 시퀀스 다이어그램
    - [x] ERD ( Entity - Relationship Diagram )
    - [x] 상태 다이어그램
    - [x] API Spec Documentation

- Swagger UI 적용
  - [x] OpenAPI Specification 문서 자동화 구현
  - [x] Mock API 및 Swagger-API 코드 작성
