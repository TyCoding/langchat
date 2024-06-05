/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : langchat

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 05/06/2024 22:47:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aigc_conversation
-- ----------------------------
DROP TABLE IF EXISTS `aigc_conversation`;
CREATE TABLE `aigc_conversation` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `prompt_id` varchar(50) DEFAULT NULL COMMENT '提示词ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话窗口表';

-- ----------------------------
-- Records of aigc_conversation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_docs
-- ----------------------------
DROP TABLE IF EXISTS `aigc_docs`;
CREATE TABLE `aigc_docs` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `knowledge_id` varchar(50) NOT NULL COMMENT '知识库ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `origin` varchar(50) DEFAULT NULL COMMENT '来源',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容或链接',
  `size` int DEFAULT NULL COMMENT '文件大小',
  `slice_num` int DEFAULT NULL COMMENT '切片数量',
  `slice_status` tinyint(1) DEFAULT NULL COMMENT '切片状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档表';

-- ----------------------------
-- Records of aigc_docs
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_docs_slice
-- ----------------------------
DROP TABLE IF EXISTS `aigc_docs_slice`;
CREATE TABLE `aigc_docs_slice` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `vector_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '向量库的ID',
  `docs_id` varchar(50) NOT NULL COMMENT '文档ID',
  `knowledge_id` varchar(50) NOT NULL COMMENT '知识库ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '切片内容',
  `word_num` int DEFAULT NULL COMMENT '字符数',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档切片表';

-- ----------------------------
-- Records of aigc_docs_slice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_excel_col
-- ----------------------------
DROP TABLE IF EXISTS `aigc_excel_col`;
CREATE TABLE `aigc_excel_col` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `col_index` bigint DEFAULT NULL COMMENT '列索引',
  `label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '列名称',
  `knowledge_id` varchar(50) DEFAULT NULL COMMENT '知识库ID',
  `docs_id` varchar(50) DEFAULT NULL COMMENT '文档ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='excel列表';

-- ----------------------------
-- Records of aigc_excel_col
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_excel_row
-- ----------------------------
DROP TABLE IF EXISTS `aigc_excel_row`;
CREATE TABLE `aigc_excel_row` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '行值',
  `knowledge_id` varchar(50) DEFAULT NULL COMMENT '知识库ID',
  `docs_id` varchar(50) DEFAULT NULL COMMENT '文档ID',
  `col_index` varchar(50) DEFAULT NULL COMMENT '列索引',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='excel行表';

-- ----------------------------
-- Records of aigc_excel_row
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `aigc_knowledge`;
CREATE TABLE `aigc_knowledge` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
  `is_excel` tinyint(1) DEFAULT NULL COMMENT '是否结构化数据',
  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识库表';

-- ----------------------------
-- Records of aigc_knowledge
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_message
-- ----------------------------
DROP TABLE IF EXISTS `aigc_message`;
CREATE TABLE `aigc_message` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `conversation_id` varchar(50) DEFAULT NULL COMMENT '会话ID',
  `prompt_id` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `chat_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '消息的ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色，user和assistant',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型名称',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '消息内容',
  `tokens` int DEFAULT NULL,
  `prompt_tokens` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `conversation_id` (`conversation_id`),
  KEY `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话消息表';

-- ----------------------------
-- Records of aigc_message
-- ----------------------------
BEGIN;
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1a8daf1596ea6717da49a90ea8a01c0c', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-05 22:46:23');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2d7d914fdbff4afea89bea6e40f4ab3b', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-05 22:46:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('334095f2d2d70a2b2fadffe2c49d378a', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个人工智能助手，可以回答你的问题、提供信息或帮助你解决问题。有什么我可以帮你的吗？', 50, 103, '2024-06-05 22:46:33');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('38cd04e28b4406d033a08092e4cff8f2', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是基于OpenAI的GPT-4模型。有什么我可以帮你的吗？', 30, 83, '2024-06-04 22:45:21');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('645823d7f6d73c69f152f9ed4d380129', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 77, '2024-06-05 22:46:25');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('78ec3240d1824799b0359d13a9ce5306', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你的模型版本', 0, 0, '2024-06-04 22:45:09');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('8abdaed25609a5943be1a8016620badc', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '模型版本是多少', 0, 0, '2024-06-04 22:45:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9ef8b2e0e2689e98612047ce848b97ce', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个由OpenAI开发的AI助手，使用的是ChatGPT模型。请问有什么我可以帮助你的吗？', 43, 55, '2024-06-04 22:45:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bb0d6cae80a5127d26ec7ffc5a2b2a27', '1', '1', NULL, 'a764fd3b-2576-4bac-bd9c-a568abbb60a9', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-05 22:46:00');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c1d09111217524e9812c1f945c52dcfa', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"您好！我在的，有什么可以帮您的吗？\" }] }', 37, 28, '2024-06-04 22:41:32');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('dbf806441a91686258c0f9bfcc9d7c50', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你是谁', 0, 0, '2024-06-05 22:46:31');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fa423a797e0c119daedf6dee419c0035', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"在的，有什么可以帮助您的吗?\" }] }', 34, 53, '2024-06-05 22:46:17');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fe4ae1e276684bf7812c34ca2d6bdaa7', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-04 22:41:26');
COMMIT;

-- ----------------------------
-- Table structure for aigc_oss
-- ----------------------------
DROP TABLE IF EXISTS `aigc_oss`;
CREATE TABLE `aigc_oss` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `file_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
  `target_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件存储名称',
  `bucket` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '桶路径',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件地址',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of aigc_oss
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_prompt
-- ----------------------------
DROP TABLE IF EXISTS `aigc_prompt`;
CREATE TABLE `aigc_prompt` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `prompt` text,
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示词表';

-- ----------------------------
-- Records of aigc_prompt
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_user
-- ----------------------------
DROP TABLE IF EXISTS `aigc_user`;
CREATE TABLE `aigc_user` (
  `id` varchar(50) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `chat_limit` int DEFAULT NULL COMMENT '会话次数',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of aigc_user
-- ----------------------------
BEGIN;
INSERT INTO `aigc_user` (`id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `chat_limit`, `status`, `create_time`) VALUES ('1', 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', '测试1号', '18278982390', 'langchat@outlook.com', '/default.png', 0, 1, '2023-12-21 23:20:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1727948160722964482 DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1, 2, '测试2', 2, '测试');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (2, 0, '测试部门', 1, '测试');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (3, 2, '2', NULL, '111');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (4, 2, '222', NULL, '2222');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (5, 0, 'dfdfd', NULL, 'dfdfdf');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` int DEFAULT NULL COMMENT '日志类型，1正常 2异常 ',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `time` bigint DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1788943211071373321 DEFAULT CHARSET=utf8mb3 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `is_ext` tinyint(1) DEFAULT NULL COMMENT '是否外链',
  `is_keepalive` tinyint(1) DEFAULT NULL COMMENT '是否缓存',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1788942973669572611 DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (100, '权限管理', 0, '/upms', '', 'menu', 30, 'KeyOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (110, '用户管理', 100, 'user', '', 'menu', 31, NULL, '/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (111, '用户查看', 110, NULL, 'upms:user:view', 'button', 32, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (112, '用户新增', 110, NULL, 'upms:user:add', 'button', 33, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (113, '用户修改', 110, NULL, 'upms:user:update', 'button', 34, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (114, '重置密码', 110, NULL, 'upms:user:reset', 'button', 35, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (115, '用户删除', 110, NULL, 'upms:user:delete', 'button', 36, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (120, '角色管理', 100, 'role', '', 'menu', 40, NULL, '/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (121, '角色查看', 120, NULL, 'upms:role:view', 'button', 41, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (122, '角色新增', 120, NULL, 'upms:role:add', 'button', 42, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (123, '角色修改', 120, NULL, 'upms:role:update', 'button', 43, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (124, '角色删除', 120, NULL, 'upms:role:delete', 'button', 44, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (130, '部门管理', 100, 'dept', '', 'menu', 50, NULL, '/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (131, '部门查看', 130, NULL, 'upms:dept:view', 'button', 51, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (132, '部门新增', 130, NULL, 'upms:dept:add', 'button', 52, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (133, '部门修改', 130, NULL, 'upms:dept:update', 'button', 53, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (134, '部门删除', 130, NULL, 'upms:dept:delete', 'button', 54, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (140, '菜单管理', 100, 'menu', '', 'menu', 60, NULL, '/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (141, '菜单查看', 140, NULL, 'upms:menu:view', 'button', 61, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (142, '菜单新增', 140, NULL, 'upms:menu:add', 'button', 62, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (143, '菜单修改', 140, NULL, 'upms:menu:update', 'button', 63, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (144, '菜单删除', 140, NULL, 'upms:menu:delete', 'button', 64, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (200, '系统管理', 0, '/system', '', 'menu', 70, 'SettingsOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (210, '系统日志', 200, 'log', '', 'menu', 71, NULL, '/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (211, '日志查看', 210, NULL, 'system:log:view', 'button', 72, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (212, '日志删除', 210, NULL, 'system:log:delete', 'button', 73, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (220, '文件管理', 200, 'file', '', 'menu', 80, NULL, '/system/oss/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (221, '文件查看', 220, NULL, 'system:oss:view', 'button', 81, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (222, '文件新增', 220, NULL, 'system:oss:add', 'button', 82, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (223, '文件修改', 220, NULL, 'system:oss:update', 'button', 83, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (224, '文件删除', 220, NULL, 'system:oss:delete', 'button', 84, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (230, '令牌管理', 200, 'token', '', 'menu', 90, NULL, '/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (231, '令牌查看', 230, NULL, 'system:token:view', 'button', 91, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (232, '令牌详情', 230, NULL, 'system:token:info', 'button', 92, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (233, '令牌删除', 230, NULL, 'system:token:delete', 'button', 93, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (240, '字典管理', 200, 'dict', NULL, 'menu', 110, NULL, '/system/dict/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (241, '字典新增', 240, NULL, 'system:dict:add', 'button', 111, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (242, '字典修改', 240, NULL, 'system:dict:update', 'button', 112, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (243, '字典删除', 240, NULL, 'system:dict:delete', 'button', 112, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (244, '字典项新增', 240, NULL, 'system:dict:item:add', 'button', 113, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (245, '字典项修改', 240, NULL, 'system:dict:item:update', 'button', 114, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (246, '字典项删除', 240, NULL, 'system:dict:item:delete', 'button', 115, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (300, 'AIGC平台', 0, '/aigc', 'app:view', 'menu', 1, 'CubeOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (310, '知识库管理', 300, 'knowledge', 'knowledge:view', 'menu', 2, 'alert', '/aigc/knowledge/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (320, '对话数据', 300, 'message', 'message:view', 'menu', 8, 'alert', '/aigc/message/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (330, '提示词管理', 300, 'prompt', 'prompt:view', 'menu', 1, '', '/aigc/prompt/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (340, '账单统计', 300, 'statistics', 'statistics:view', 'menu', 6, '', '/aigc/statistics/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (350, '平台用户', 300, 'user', 'aigc:user:view', 'menu', 7, '', '/aigc/user/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (400, 'AIGC应用', 0, '/aigc/app', 'aigc-app', 'menu', 0, 'PaperPlaneOutline', 'LAYOUT', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (410, 'AI智能助手', 400, 'chat-docs', 'aigc:chat', 'menu', 5, '', '/aigc/chat/docs', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (420, 'AI聊天助手', 400, 'chat', 'ai:chat', 'menu', 1, '', '/aigc/chat/index', 0, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '操作用户ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
  `target_name` varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  `bucket` varchar(255) DEFAULT NULL COMMENT '桶路径',
  `url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `des` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件来源渠道 input/output',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1726835321112363010 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1, '超级管理员', 'administrator', '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (2, '演示环境角色', 'demo_env', '演示环境使用角色，没有页面操作权限');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (3, '111', '1111', '1111');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (4, '测试角色', 'test', '这是测试的');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (5, '客户端角色', 'client_role', '客户端用户的角色');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (6, '23', '23', '2222');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1737855609581137922 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1, 'administrator', '48kQD0O/A69LENSbk/+FxA==', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (2, 'tycoding', '48kQD0O/A69LENSbk/+FxA==', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, '/default.png', 0, '2021-02-19 11:02:08');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (3, 'demo', '48kQD0O/A69LENSbk/+FxA==', '演示环境账号', '男', '18929809812', 'ty@qq.com', 1362597682681577273, 'http://tycoding.cn/imgs/tycoding.png', 1, '2021-06-15 22:26:55');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (4, '12', '$2a$10$2HxR1V/lEvcjgp.Hp/JMiucO/u9LcrgFmcuzTCcotkkh0u4c4I36O', '122222', '男', NULL, 'tycoding@qq.com', 1362597682681577273, '/default.png', 1, '2023-11-21 12:08:23');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (5, '232323', '$2a$10$.uevNrDHgcjHhmyZtOhGj.4tMwVuUh3yDHnSsW7qEGi/cABfsT12a', '3434', '男', '34', '34', 1726842159006482433, '/default.png', 1, '2023-11-24 11:39:52');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (6, 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', NULL, NULL, NULL, 'langchat@outlook.com', NULL, '/default.png', 1, '2023-12-21 23:20:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
