DROP DATABASE IF EXISTS restaurant;
CREATE DATABASE restaurant;
USE restaurant;
-- -------------------------
--  用户表
-- -------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE users(
  username VARCHAR(50) PRIMARY KEY ,
  password VARCHAR(50),
  name VARCHAR(50),
  gender  VARCHAR(10),
  img   VARCHAR(256),
  salt  VARCHAR(100),
  createTime BIGINT,
  updateTime BIGINT,
  locked  BOOL,
  forbidden BOOL,
  del   INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -------------------------
--  角色表
-- -------------------------
DROP TABLE IF EXISTS roles;
CREATE TABLE roles(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  createTime BIGINT,
  del INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -------------------------
--  权限表
-- -------------------------


-- -------------------------
--  用户-角色表
-- -------------------------
Drop table  if exists user_roles;
CREATE TABLE user_roles (
  create_time BIGINT,
  usersname VARCHAR(50) NOT NULL,
  roleId INT(11) NOT NULL,
  INDEX users_id_index (usersname),
  INDEX roles_id_index (roleId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  角色-权限表
-- -------------------------
DROP TABLE  IF EXISTS roles_permissions;
CREATE TABLE roles_permissions (
  create_time BIGINT,
  roleId INT(11) NOT NULL,
  permission VARCHAR(300) NOT NULL,
  INDEX roles_id_index (roleId)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- -------------------------
--  oauth2
-- -------------------------


-- -------------------------
--  计量单位表
-- -------------------------
DROP TABLE IF EXISTS measurement;
CREATE TABLE measurement (
  id INT PRIMARY KEY AUTO_INCREMENT,
  ename VARCHAR(50),
  cname VARCHAR(50),
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  菜单
-- -------------------------
DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
  id  INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(50),
  img   VARCHAR(300),
  price FLOAT, -- 单价
  unit  INT, -- 单位
  profile VARCHAR(500), -- 简介
  categoryId INT,  -- 类别
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  菜品种类
-- -------------------------
DROP TABLE IF EXISTS food_category;
CREATE TABLE food_category(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  profile VARCHAR(500),
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- -------------------------
--  菜品供应量表
-- -------------------------
DROP TABLE IF EXISTS food_supply;
CREATE TABLE food_supply(
  id INT PRIMARY KEY AUTO_INCREMENT,
  menuId INT,
  mount  INT,
  unit   INT,
  remaining   INT,
  supplyDate  DATE,
  createTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  食材
-- -------------------------
DROP TABLE IF EXISTS material;
CREATE TABLE material(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(50),
  img   VARCHAR(300),
  price FLOAT, -- 单价
  unit  INT, -- 单位
  profile VARCHAR(500), -- 简介
  categoryId INT, -- 类别
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  食材用量
-- -------------------------
DROP TABLE IF EXISTS food_material;
CREATE TABLE food_material(
  id INT PRIMARY KEY AUTO_INCREMENT,
  menuId INT,     -- 菜品
  materialId INT, -- 食材
  mount   FLOAT,  -- 用量
  unit    INT,    --
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  食材种类
-- -------------------------
DROP TABLE IF EXISTS material_category;
CREATE TABLE material_category(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  profile VARCHAR(500),
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  供应商表
-- -------------------------
DROP TABLE IF EXISTS provider;
CREATE TABLE provider(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  address VARCHAR(100),
  contactor VARCHAR(50),
  tel   VARCHAR(20),
  cityId INT,
  regionId INT,
  provinceId INT,
  createTime BIGINT,
  updateTime BIGINT,
  operator VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  采购订单
-- -------------------------
DROP TABLE IF EXISTS purchase;
CREATE TABLE purchase(
  id INT PRIMARY KEY AUTO_INCREMENT,
  number VARCHAR(50) UNIQUE,
  title   VARCHAR(100),
  expectDate  DATE, -- 预计采购日期
  operator VARCHAR(50), -- 采购人
  totalPrice  FLOAT, -- 总金额
  actualPrice FLOAT, -- 实付金额
  status    INT, -- 订单状态  0 录入 1 确认 2 完成
  completeTime BIGINT, -- 完成时间
  completePercent FLOAT, -- 采购完成 百分比
  createTime BIGINT,
  del       INT     -- 删除标记 1 删除
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  采购详单
-- -------------------------
DROP TABLE IF EXISTS purchase_detail;
CREATE TABLE purchase_detail(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  purchaseId INT,
  materialId  INT,
  expectMount FLOAT, -- 预计采购量
  actualMount FLOAT, -- 实际采购量
  providerId  INT,    -- 供应商
  unit  INT,
  price FLOAT,
  totalPrice  FLOAT,
  actualPrice FLOAT,
  status  INT DEFAULT 0,  -- 是否完成 0 未完成  1 完成
  del   INT DEFAULT 0 --  删除标记
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  顾客订单
-- -------------------------
DROP TABLE IF EXISTS custom_order;
CREATE TABLE custom_order(

  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  number VARCHAR(50) UNIQUE,
  tableNo INT,
  createTime BIGINT, -- 下单时间
  receiveTime BIGINT, -- 订单接收时间
  receiver VARCHAR(50), -- 订单接收人
  completeTime BIGINT, -- 订单完成时间
  completeOperator VARCHAR(50), -- 完成操作人
  totalPrice FLOAT,
  actualPrice FLOAT,
  isChecked  BOOL, -- 是否结算
  payment   VARCHAR(20), -- 支付方式
  checkTime BIGINT,   -- 结算时间
  checkOperator VARCHAR(50),
  pushed  BOOL, -- 是否已经推送
  rush    BOOL, -- 是否催单
  del     INT DEFAULT 0

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
--  顾客详单
-- -------------------------
DROP TABLE IF EXISTS custom_order_detail;
CREATE TABLE custom_order_detail(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  orderId BIGINT,
  menuId INT,
  mount  FLOAT, -- 需要数量
  unit   INT,
  needPush  BOOL, -- 是否需要推送
  beginTime BIGINT, -- 开始处理时间
  completeTime  BIGINT, -- 后厨完成时间
  delivered BOOL, -- 是否上菜完成
  deliverMount  FLOAT, -- 已上数量
  deliveredTime BIGINT, -- 上菜时间
  waiter VARCHAR(50),
  status INT, -- 状态 0 创建， 1 开始处理， 2 完成, 3 上菜
  del INT DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;