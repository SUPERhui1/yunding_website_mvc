CREATE TABLE w_member
(
  member_id          INT(11) UNSIGNED AUTO_INCREMENT
  COMMENT 'id'
    PRIMARY KEY,
  member_name        VARCHAR(20)     NOT NULL
  COMMENT '姓名',
  member_photo       VARCHAR(1024)   NOT NULL
  COMMENT '照相',
  member_grade       VARCHAR(255)    NULL
  COMMENT '专业年级',
  member_position    VARCHAR(255)    NOT NULL
  COMMENT '身份',
  member_achievement VARCHAR(1024)   NULL
  COMMENT '代表作',
  member_joined_at   VARCHAR(100)    NULL
  COMMENT '加入时间',
  member_created_at  DATETIME        NOT NULL
  COMMENT '创建时间',
  member_updated_at  DATETIME        NOT NULL
  COMMENT '更新时间',
  member_state       INT DEFAULT '0' NOT NULL
  COMMENT '成员状态：-1(以退出的成员),0(现有的除六大咖位成员),>0(1～6分别代表六大咖位的成员)'
)
  ENGINE = InnoDB;


