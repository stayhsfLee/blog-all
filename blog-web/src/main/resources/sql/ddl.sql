-- 创建时间 2017/9/15
-- 版本号 v2

-- -------------------------------------------------------------------------- 角色,权限部分  ----------------------------------------------------------------------------
-- 老式的Guard插件来使用
-- 角色表
--
-- CREATE TABLE `role` (
--   `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
--   `name`         VARCHAR(32)     NOT NULL,
--   `description`  VARCHAR(128)    NOT NULL,
--   `gmt_create`   DATETIME        NOT NULL,
--   `gmt_modified` DATETIME        NOT NULL,
--   PRIMARY KEY (`id`)
-- )
--   DEFAULT CHARACTER SET = utf8;
--
-- --
-- -- 角色无关的权限表
-- --
-- CREATE TABLE `permission` (
--   `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
--   `description`  VARCHAR(64)     NOT NULL,
--   `name`         VARCHAR(128)    NOT NULL
--   COMMENT '用来标识该permission,一般采用url+Http  method的方式',
--   `gmt_create`   DATETIME        NOT NULL,
--   `gmt_modified` DATETIME        NOT NULL,
--   PRIMARY KEY (`id`)
-- )
--   DEFAULT CHARACTER SET = utf8;
--
-- --
-- -- 将角色和权限关联起来的表
-- --
-- CREATE TABLE `r_role_permission` (
--   `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
--   `role_id`       BIGINT UNSIGNED NULL,
--   `permission_id` BIGINT UNSIGNED NOT NULL,
--   `gmt_create`    DATETIME        NOT NULL,
--   `gmt_modified`  DATETIME        NOT NULL,
--   PRIMARY KEY (`id`)
-- )
--   DEFAULT CHARACTER SET = utf8;
--
-- # --
-- # -- 账户和角色相关联起来的表
-- # --
-- # CREATE TABLE `r_account_basicinfo_role`(
-- #   `rarid` INT UNSIGNED NOT NULL AUTO_INCREMENT,
-- #   `account_id` INT UNSIGNED NOT NULL ,
-- #   `role_id` INT UNSIGNED NOT NULL ,
-- #   `status` TINYINT(4) UNSIGNED  DEFAULT 1,
-- #   PRIMARY KEY (`rarid`),
-- #   UNIQUE KEY (`account_id`)
-- # )
-- #   DEFAULT CHARACTER SET = utf8
-- #   COMMENT = '这是账户信息和role信息的关联表';

-- --------------------------------------------------------------------------  账号部分  ----------------------------------------------------------------------------


--
-- 账户基本信息
--
CREATE TABLE `account` (
  `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(64)     NOT NULL,
  `password`        VARCHAR(128)    NOT NULL,
  `phone`           VARCHAR(20)              DEFAULT NULL,
  `role_id`         BIGINT UNSIGNED NOT NULL,
  `wechat_id`       VARCHAR(128)             DEFAULT NULL,
  `email`           VARCHAR(32)              DEFAULT NULL,
  `gmt_create`      DATETIME        NOT NULL,
  `gmt_modified`    DATETIME        NOT NULL,
  `last_login_time` DATETIME        NOT NULL,
  `status`          TINYINT(1) UNSIGNED      DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY uk_name (`name`)
)
  DEFAULT CHARACTER SET = utf8
  COMMENT = '这是用户基本信息表，里面存放的都是一些不经常改变的属性域。';

-- 账号和user就是通过userId来关联的

-- ------------------------------------------------------------------------  用户信息部分  ----------------------------------------------------------------------------

--
-- 用户基本信息表
--
CREATE TABLE `user` (
  `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nick`           VARCHAR(36)     NOT NULL DEFAULT 'NickNick',
  `sex`            TINYINT(1)      NOT NULL DEFAULT 1, -- 1表示男 , 2表示女 , 3表示未声明
  `introduction`   VARCHAR(188)    NOT NULL DEFAULT 'Here is introduction.',
  `avatar`         VARCHAR(64)     NOT NULL DEFAULT '/avatar/default',
  `background_img` VARCHAR(64)     NOT NULL DEFAULT '/bkimg/default',
  `gmt_create`     DATETIME        NOT NULL,
  `gmt_modified`   DATETIME        NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `pk_id_index` (`id` ASC) USING HASH
)
  DEFAULT CHARACTER SET = utf8;

-- --------------------------------------------------------------------------  文章部分  ----------------------------------------------------------------------------

--
-- 文章表
--
CREATE TABLE `article` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(128)    NOT NULL,
  `user_id`      BIGINT UNSIGNED NOT NULL,
  `view`         INT UNSIGNED    NOT NULL DEFAULT 0, -- 浏览量
  `grammar`      TINYINT(1)               DEFAULT 0, -- 语法格式
  `gmt_create`   DATETIME        NOT NULL,
  `gmt_modified` DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;

--
-- 文章内容表
--
CREATE TABLE `article_content` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `length`       INT UNSIGNED    NOT NULL DEFAULT 0,
  `content`      TEXT            NOT NULL,
  `gmt_create`   DATETIME        NOT NULL,
  `gmt_modified` DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;

--
-- 分类表 , 可在service中进行缓存，并且在update中进行更新
--
CREATE TABLE `article_group` (
  `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(18)     NOT NULL DEFAULT 'group',
  `parent_group_id` BIGINT UNSIGNED NOT NULL,
  `level`           INT UNSIGNED    NOT NULL DEFAULT 1, -- 几级分类，目前约定最大两级，所以只有1，2两种值
  `gmt_create`      DATETIME        NOT NULL,
  `gmt_modified`    DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;

--
-- 文章和分类map,一篇文章至少一个分类，不行就默认分类
--
CREATE TABLE `r_article_group` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT UNSIGNED NOT NULL,
  `group_id`   BIGINT UNSIGNED NOT NULL,
  `gmt_create`      DATETIME        NOT NULL,
  `gmt_modified`    DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;

--
-- 标签表
--
CREATE TABLE `article_tag` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(18)     NOT NULL DEFAULT 'tag',
  `creator`      BIGINT UNSIGNED NOT NULL,
  `description`  VARCHAR(58)     NOT NULL DEFAULT 'This is tag description',
  `gmt_create`   DATETIME        NOT NULL,
  `gmt_modified` DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;


--
-- 标签和文章map,一个文章 0+个tag
--
CREATE TABLE `r_article_tag` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT UNSIGNED NOT NULL,
  `tag_id`   BIGINT UNSIGNED NOT NULL,
  `gmt_create`      DATETIME        NOT NULL,
  `gmt_modified`    DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;


-- --------------------------------------------------------------------------  common部分  ----------------------------------------------------------------------------

--
-- 省份信息
--
CREATE TABLE `province_info` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(16)     NOT NULL,
  `gmt_create`   DATETIME        NOT NULL,
  `gmt_modified` DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;

--
-- 通知中心
--
CREATE TABLE `notification`(
  `id`  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `creator` BIGINT UNSIGNED NOT NULL ,
  `name` VARCHAR(188) NOT NULL DEFAULT 'title',
  `grammar`      TINYINT(1)               DEFAULT 0, -- 语法格式
  `content`       TEXT NOT NULL DEFAULT 'notification',
  `gmt_create`   DATETIME        NOT NULL,
  `gmt_modified` DATETIME        NOT NULL,
  PRIMARY KEY (`id`)
)DEFAULT CHARACTER SET = utf8