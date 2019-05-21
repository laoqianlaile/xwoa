------加司机联系方式字段
alter table OA_CARINFO add RANGE_TYPE VARCHAR2(1);

------加工会内部车和外租车字段
alter table OA_CAR_APPLY add RANGE_TYPE VARCHAR2(1);