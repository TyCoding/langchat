CREATE DATABASE IF NOT EXISTS langchat;
USE langchat;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aigc_app
-- ----------------------------
DROP TABLE IF EXISTS `aigc_app`;
CREATE TABLE `aigc_app` (
                            `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                            `model_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关联模型',
                            `knowledge_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关联知识库',
                            `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                            `prompt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '提示词',
                            `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
                            `save_time` datetime DEFAULT NULL COMMENT '保存时间',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示词表';

-- ----------------------------
-- Records of aigc_app
-- ----------------------------
BEGIN;
INSERT INTO `aigc_app` (`id`, `model_id`, `knowledge_ids`, `cover`, `name`, `prompt`, `des`, `save_time`, `create_time`) VALUES ('e16a582b47d3041cf14074d5451dff7a', '0c21c2f8ebd3aa3757ef1bae81154cc4', '[\"393704ac13f67fde5da674ddd0742b03\"]', 'http://127.0.0.1/langchat/2024081066b6e0fbcdb220c420fe6bae.JPEG', 'LangChat官方应用', '你是一个专业的文档分析师，你擅长从文档中提取关键内容并总结分析含义，下面你需要根据用户的问题做出解答。\n\n## 限制\n不要回答和文档无关的内容', '快速解答LangChat项目相关的内容，LangChat官方助手', '2024-08-10 11:39:41', '2024-08-04 17:49:24');
COMMIT;

-- ----------------------------
-- Table structure for aigc_app_api
-- ----------------------------
DROP TABLE IF EXISTS `aigc_app_api`;
CREATE TABLE `aigc_app_api` (
                                `id` varchar(50) NOT NULL COMMENT '主键',
                                `model_id` varchar(50) DEFAULT NULL COMMENT 'model',
                                `app_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '应用ID',
                                `req_limit` int DEFAULT NULL COMMENT '请求限制',
                                `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                                `channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '渠道',
                                `api_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Key',
                                `des` varchar(255) DEFAULT NULL COMMENT '描述',
                                `expired` datetime DEFAULT NULL COMMENT '过期时间',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用';

-- ----------------------------
-- Records of aigc_app_api
-- ----------------------------
BEGIN;
INSERT INTO `aigc_app_api` (`id`, `model_id`, `app_id`, `req_limit`, `name`, `channel`, `api_key`, `des`, `expired`, `create_time`) VALUES ('de35cff226110a7f6e5dd77823411627', '0c21c2f8ebd3aa3757ef1bae81154cc4', '', 100, 'HTTP应用', 'CHANNEL_API', 'langchat-20b418833d0f4a5483c241dee39b47b2', '将OpenAI格式的接口暴露给外部应用调用', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for aigc_app_web
-- ----------------------------
DROP TABLE IF EXISTS `aigc_app_web`;
CREATE TABLE `aigc_app_web` (
                                `id` varchar(50) NOT NULL COMMENT '主键',
                                `model_id` varchar(50) DEFAULT NULL COMMENT 'model',
                                `app_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '应用ID',
                                `req_limit` int DEFAULT NULL COMMENT '请求限制',
                                `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                                `channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '渠道',
                                `api_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Key',
                                `des` varchar(255) DEFAULT NULL COMMENT '描述',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `expired` datetime DEFAULT NULL COMMENT '过期时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用';

-- ----------------------------
-- Records of aigc_app_web
-- ----------------------------
BEGIN;
INSERT INTO `aigc_app_web` (`id`, `model_id`, `app_id`, `req_limit`, `name`, `channel`, `api_key`, `des`, `create_time`, `expired`) VALUES ('fcce651cf9fa3662dd6bfea4fd6e697e', '0c21c2f8ebd3aa3757ef1bae81154cc4', '', 100, 'Web应用', 'CHANNEL_WEB', 'langchat-337a293acb60477ea5496f49f8892b14', '通过Iframe、JS快速将应用集成到第三方应用中', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for aigc_conversation
-- ----------------------------
DROP TABLE IF EXISTS `aigc_conversation`;
CREATE TABLE `aigc_conversation` (
                                     `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                     `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
                                     `prompt_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提示词ID',
                                     `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话窗口表';


-- ----------------------------
-- Table structure for aigc_docs
-- ----------------------------
DROP TABLE IF EXISTS `aigc_docs`;
CREATE TABLE `aigc_docs` (
                             `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                             `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '知识库ID',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                             `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型',
                             `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                             `origin` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '来源',
                             `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容或链接',
                             `size` int DEFAULT NULL COMMENT '文件大小',
                             `slice_num` int DEFAULT NULL COMMENT '切片数量',
                             `slice_status` tinyint(1) DEFAULT NULL COMMENT '切片状态',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档表';

-- ----------------------------
-- Records of aigc_docs
-- ----------------------------
BEGIN;
INSERT INTO `aigc_docs` (`id`, `knowledge_id`, `name`, `type`, `url`, `origin`, `content`, `size`, `slice_num`, `slice_status`, `create_time`) VALUES ('51ae6d7356eec12b30dceb7975846c4e', '393704ac13f67fde5da674ddd0742b03', 'story-about-happy-carrot.pdf', 'UPLOAD', NULL, NULL, NULL, 35359, NULL, 0, '2024-08-08 17:02:41');
INSERT INTO `aigc_docs` (`id`, `knowledge_id`, `name`, `type`, `url`, `origin`, `content`, `size`, `slice_num`, `slice_status`, `create_time`) VALUES ('8933fc0e6b449a153adc1789a4e1781c', '393704ac13f67fde5da674ddd0742b03', 'guide1', 'INPUT', NULL, NULL, 'LangChat 是一个基于Java生态的企业AI知识库和大模型应用解决方案，帮助企业快速搭建AI大模型应用。 同时，LangChat也集成了RBAC权限体系，为企业提供开箱即用的AI大模型产品解决方案。\n\nLangChat 使用Java生态，前后端分离，并采用最新的技术栈开发。后端基于SpringBoot3，前端基于Vue3。 LangChat不仅为企业提供AI领域的产品解决方案，也是一个完整的Java企业级应用案例。这个系统带你全面了解SpringBoot3和Vue3的前后端开发流程、业务模块化，以及AI应用集成方案。 无论是企业开发，还是个人学习，LangChat都为你提供丰富的学习案例', NULL, 1, 1, '2024-08-04 18:18:46');
INSERT INTO `aigc_docs` (`id`, `knowledge_id`, `name`, `type`, `url`, `origin`, `content`, `size`, `slice_num`, `slice_status`, `create_time`) VALUES ('ec0c960461a615bb7c7648d7ee5801b5', '393704ac13f67fde5da674ddd0742b03', 'story-about-happy-carrot.pdf', 'UPLOAD', 'http://127.0.0.1/langchat/2024080866b4b069cdb262aeea8da409.pdf', NULL, NULL, 35359, 37, 1, '2024-08-08 19:47:54');
INSERT INTO `aigc_docs` (`id`, `knowledge_id`, `name`, `type`, `url`, `origin`, `content`, `size`, `slice_num`, `slice_status`, `create_time`) VALUES ('f4a465ea6bfc25c34707f1e132356192', '393704ac13f67fde5da674ddd0742b03', 'story-about-happy-carrot.pdf', 'UPLOAD', NULL, NULL, NULL, 35359, NULL, 0, '2024-08-06 22:57:32');
COMMIT;

-- ----------------------------
-- Table structure for aigc_docs_slice
-- ----------------------------
DROP TABLE IF EXISTS `aigc_docs_slice`;
CREATE TABLE `aigc_docs_slice` (
                                   `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                   `vector_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '向量库的ID',
                                   `docs_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文档ID',
                                   `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '知识库ID',
                                   `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档名称',
                                   `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '切片内容',
                                   `word_num` int DEFAULT NULL COMMENT '字符数',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档切片表';

-- ----------------------------
-- Table structure for aigc_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `aigc_knowledge`;
CREATE TABLE `aigc_knowledge` (
                                  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库名称',
                                  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
                                  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
                                  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识库表';

-- ----------------------------
-- Records of aigc_knowledge
-- ----------------------------
BEGIN;
INSERT INTO `aigc_knowledge` (`id`, `name`, `des`, `cover`, `create_time`) VALUES ('393704ac13f67fde5da674ddd0742b03', 'LangChat文档', 'LangChat官方文档', NULL, '1722766331165');
COMMIT;

-- ----------------------------
-- Table structure for aigc_message
-- ----------------------------
DROP TABLE IF EXISTS `aigc_message`;
CREATE TABLE `aigc_message` (
                                `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
                                `conversation_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会话ID',
                                `chat_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '消息的ID',
                                `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
                                `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
                                `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色，user和assistant',
                                `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型名称',
                                `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '消息内容',
                                `tokens` int DEFAULT NULL,
                                `prompt_tokens` int DEFAULT NULL,
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `conversation_id` (`conversation_id`) USING BTREE,
                                KEY `role` (`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话消息表';


-- ----------------------------
-- Table structure for aigc_model
-- ----------------------------
DROP TABLE IF EXISTS `aigc_model`;
CREATE TABLE `aigc_model` (
                              `id` varchar(50) NOT NULL COMMENT '主键',
                              `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型: CHAT、Embedding、Image',
                              `model` varchar(100) DEFAULT NULL COMMENT '模型名称',
                              `provider` varchar(100) DEFAULT NULL COMMENT '供应商',
                              `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '别名',
                              `response_limit` int DEFAULT NULL COMMENT '响应长度',
                              `temperature` double DEFAULT NULL COMMENT '温度',
                              `top_p` double DEFAULT NULL,
                              `api_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `base_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `endpoint` varchar(100) DEFAULT NULL,
                              `azure_deployment_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'azure模型参数',
                              `gemini_project` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'gemini模型参数',
                              `gemini_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'gemini模型参数',
                              `image_size` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片大小',
                              `image_quality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片质量',
                              `image_style` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片风格',
                              `dimensions` int DEFAULT NULL COMMENT '向量维数',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='LLM模型配置表';

-- ----------------------------
-- Records of aigc_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_oss
-- ----------------------------
DROP TABLE IF EXISTS `aigc_oss`;
CREATE TABLE `aigc_oss` (
                            `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                            `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
                            `oss_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                            `original_filename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
                            `filename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件存储名称',
                            `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件地址',
                            `base_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '桶路径',
                            `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
                            `size` int DEFAULT NULL COMMENT '文件大小',
                            `ext` varchar(50) DEFAULT NULL COMMENT '文件后缀',
                            `content_type` varchar(100) DEFAULT NULL COMMENT '文件头',
                            `platform` varchar(50) DEFAULT NULL COMMENT '平台',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of aigc_oss
-- ----------------------------
BEGIN;
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('496a1c3a6798e6b9f52e071d533753d1', '91b4524a46a949601e7f3b004ed76034', NULL, '36946717.JPEG', '66b6df5ecdb26cd406afc109.JPEG', 'http://127.0.0.1/langchat/2024081066b6df5ecdb26cd406afc109.JPEG', 'langchat/', '20240810', 11744, 'JPEG', 'image/jpeg', 'local', '2024-08-10 11:32:47');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('55b5b75061c0a229ec0114fc62853a0c', '91b4524a46a949601e7f3b004ed76034', NULL, 'story-about-happy-carrot.pdf', '66b4afeecdb2c038a2624532.pdf', 'http://cdn.tycoding.cn/langchat/2024080866b4afeecdb2c038a2624532.pdf', 'langchat/', '20240808', 35359, 'pdf', 'application/pdf', 'qiniu', '2024-08-08 19:45:51');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('6a91df3d44a2fdfe6c8fcc83844757c8', '91b4524a46a949601e7f3b004ed76034', NULL, 'story-about-happy-carrot.pdf', '66b239dbcdb2ff916a0a092c.pdf', 'http://cdn.tycoding.cn/langchat/2024080666b239dbcdb2ff916a0a092c.pdf', 'langchat/', '20240806', 35359, 'pdf', 'application/pdf', 'qiniu', '2024-08-06 22:57:32');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('726bc0a42f0753c78672bedb8529c2c4', '91b4524a46a949601e7f3b004ed76034', NULL, 'story-about-happy-carrot.pdf', '66b4b069cdb262aeea8da409.pdf', 'http://127.0.0.1/langchat/2024080866b4b069cdb262aeea8da409.pdf', 'langchat/', '20240808', 35359, 'pdf', 'application/pdf', 'local', '2024-08-08 19:47:54');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('7ef543675e89ef3fea19563b667c1454', '91b4524a46a949601e7f3b004ed76034', NULL, 'story-about-happy-carrot.pdf', '66b489b0cdb2a4b1a529719f.pdf', 'http://cdn.tycoding.cn/langchat/2024080866b489b0cdb2a4b1a529719f.pdf', 'langchat/', '20240808', 35359, 'pdf', 'application/pdf', 'qiniu', '2024-08-08 17:02:41');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('b572ec6532f03530b8c2b45c93a26141', '91b4524a46a949601e7f3b004ed76034', NULL, '36946717.JPEG', '66b6e0fbcdb220c420fe6bae.JPEG', 'http://127.0.0.1/langchat/2024081066b6e0fbcdb220c420fe6bae.JPEG', 'langchat/', '20240810', 11744, 'JPEG', 'image/jpeg', 'local', '2024-08-10 11:39:40');
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('cc5bd4fffb8da1296bc87cc40ececb66', '91b4524a46a949601e7f3b004ed76034', NULL, '36946717.JPEG', '66b6e0a2cdb26cd406afc10a.JPEG', 'http://127.0.0.1/langchat/2024081066b6e0a2cdb26cd406afc10a.JPEG', 'langchat/', '20240810', 11744, 'JPEG', 'image/jpeg', 'local', '2024-08-10 11:38:10');
COMMIT;

-- ----------------------------
-- Table structure for aigc_user
-- ----------------------------
DROP TABLE IF EXISTS `aigc_user`;
CREATE TABLE `aigc_user` (
                             `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户ID',
                             `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
                             `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
                             `nickname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '昵称',
                             `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机',
                             `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
                             `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
                             `chat_limit` int DEFAULT NULL COMMENT '会话次数',
                             `is_perms` tinyint(1) DEFAULT NULL COMMENT '是否有操作权限',
                             `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of aigc_user
-- ----------------------------
BEGIN;
INSERT INTO `aigc_user` (`id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `chat_limit`, `is_perms`, `status`, `create_time`) VALUES ('bcd043c9c764374a8c4574648168e6f6', 'langchat@outlook.com', 'U3lnYOIEGN38KKy0h3KUSA==', 'langchat@outlook.com', NULL, 'langchat@outlook.com', NULL, 10, 1, 1, '2024-08-10 21:25:22');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `id` varchar(50) NOT NULL COMMENT '部门ID',
                            `parent_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '上级部门ID',
                            `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '部门名称',
                            `order_no` int DEFAULT NULL COMMENT '排序',
                            `des` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('14b300858a898c6dcfd3dc95dde6df81', 'ece0a14ab891e775ff9f6252731130b7', '事业部', NULL, '事业部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('16794f488aa3b6f77012749a8160f45e', 'e8017fb290f576f5e1f60be4ab4f166a', '前端研发团队', NULL, '前端研发团队');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('3f7ed841ec5e92ee039fd83bf3fd0ee4', '14b300858a898c6dcfd3dc95dde6df81', '北区事业部', NULL, '北区事业部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('87388f69e48e53c3771bbd2a56256374', '14b300858a898c6dcfd3dc95dde6df81', '销售团队', NULL, '销售团队');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('da6b0029262feb514ab8c70d7f72c2c7', 'e8017fb290f576f5e1f60be4ab4f166a', '后端研发团队', NULL, '后端研发团队');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('e8017fb290f576f5e1f60be4ab4f166a', 'ece0a14ab891e775ff9f6252731130b7', '产品研发部', NULL, '产品研发部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES ('ece0a14ab891e775ff9f6252731130b7', '0', '组织架构', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` varchar(50) NOT NULL COMMENT '编号',
                           `type` int DEFAULT NULL COMMENT '日志类型，1正常 2异常 ',
                           `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作用户',
                           `operation` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作描述',
                           `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '请求URL',
                           `time` bigint DEFAULT NULL COMMENT '耗时(毫秒)',
                           `method` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作方法',
                           `params` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作参数',
                           `ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'IP地址',
                           `user_agent` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户代理',
                           `create_time` datetime DEFAULT NULL COMMENT '操作时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='日志表';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `id` varchar(50) NOT NULL COMMENT '主键',
                            `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单名称',
                            `parent_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '父级ID',
                            `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单路径',
                            `perms` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '权限标识',
                            `type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单类型',
                            `order_no` int DEFAULT NULL COMMENT '排序',
                            `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单图标',
                            `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '组件路径',
                            `is_disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
                            `is_ext` tinyint(1) DEFAULT NULL COMMENT '是否外链',
                            `is_keepalive` tinyint(1) DEFAULT NULL COMMENT '是否缓存',
                            `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('0597ccbb7b98b2d443bffb3f1785ce1c', '新增知识库', '97a5eac3bfeeabe4013d828b919786f7', NULL, 'aigc:knowledge:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('0825f18b3860f8c01a9b0d8221280e3b', '应用集成', 'a2ccfe694cd91cf159ad35626e4ea202', 'channel', 'aigc:channel', 'menu', 3, '', '/channel/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('0976afe16e7b328886408f3e117733c1', '新增角色', '6f8aff1f2c458e5add9adb6d284fb451', NULL, 'upms:role:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('0c8d975fdb3014629c6b1e57944d8efa', '删除API渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:api:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('0f37f45fb15c38de948b17b8a24e431b', '修改菜单', 'b1df787d8af5b728181a4b9acf38da93', NULL, 'upms:menu:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('1440895f54ccae1c1e2706e3dbcf6f5d', '文本向量化', '43563b039d30b990f87af37783115ff4', NULL, 'aigc:embedding:text', 'button', 4, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('1854ab6c793361b0bfeb7b7204c9048f', '删除平台用户', '657b6bc0a43914c1bf0a2d517562a2a5', NULL, 'aigc:user:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('194d111ff34dc147e4de66ecf0f4e261', '新增API渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:api:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('2dc3a6e16351901710060fd846ee9f19', '新增菜单', 'b1df787d8af5b728181a4b9acf38da93', NULL, 'upms:menu:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('2f5735d125b4537076893a4b4a37a188', '系统管理', '0', 'system', 'system', 'menu', 4, 'SettingsOutline', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('35dcd70c8a4008b554b71bf02ab07b61', '删除聊天记录', 'bdd70f2c1ee068c13bd3288eff07c8e2', NULL, 'chat:messages:clean', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('374409ab56141b311ccb0f1847dd724a', 'AIGC平台', '0', 'aigc', 'aigc', 'menu', 2, 'CubeOutline', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('3d1700109ece0187ba5e76217cd71995', '删除对话数据', 'f1ad3c056ac91fa5292a99f223155afc', NULL, 'aigc:message:delete', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('43563b039d30b990f87af37783115ff4', '应用管理', 'a2ccfe694cd91cf159ad35626e4ea202', 'app', 'aigc:app', 'menu', 2, '', '/app/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('4488cb5271b1220647d4a83cfbcb7b15', '文档向量化', '43563b039d30b990f87af37783115ff4', NULL, 'aigc:embedding:docs', 'button', 5, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('510a89f01571d7eaa3b1393c8534ab6f', '删除应用', '43563b039d30b990f87af37783115ff4', NULL, 'aigc:app:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('5514605bae6ffdad3e4acff3e9e9742c', '新增应用', '43563b039d30b990f87af37783115ff4', NULL, 'aigc:app:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('5cc4d975a5c8a0a6c615c9539cbd2a3e', '修改Web渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:web:update', 'button', 5, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('5ce2349dc38a84cfbf0f5b260b41a2b6', '模型管理', '374409ab56141b311ccb0f1847dd724a', 'model', 'model', 'menu', 0, '', '/aigc/model/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('5f0617b845b2e7072387360f944b9258', '修改API渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:api:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('62beffe9252934b4adeeef3125cab584', '新增模型', '5ce2349dc38a84cfbf0f5b260b41a2b6', NULL, 'aigc:model:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('657b6bc0a43914c1bf0a2d517562a2a5', '平台用户', '374409ab56141b311ccb0f1847dd724a', 'user', 'user', 'menu', 3, NULL, '/aigc/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('65deeb7aedec5490425ad2572d536ea9', 'Chat权限', '43563b039d30b990f87af37783115ff4', NULL, 'chat:completions', 'button', 6, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('67435b96a82c494b48fc6458b7103d4d', '页面预览', '43563b039d30b990f87af37783115ff4', NULL, 'chat-docs:view', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('6c27a1ddba0ce10d7e242cb7e568bfc0', '删除模型', '5ce2349dc38a84cfbf0f5b260b41a2b6', NULL, 'aigc:model:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('6cb25c77d3087d47a26c08d904a442fa', '新增部门', '8fb8756a4587cc4c76401a63ea194568', NULL, 'upms:dept:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('6f8aff1f2c458e5add9adb6d284fb451', '角色管理', '7c411c7d41034d6708103c8e0da19ced', 'role', 'role', 'menu', 2, NULL, '/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('72215ec9609e546cd56bacf4c29e482d', '修改部门', '8fb8756a4587cc4c76401a63ea194568', NULL, 'upms:dept:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('73e62c509cd647f0302d4fa12025ae66', '新增Web渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:web:add', 'button', 4, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('7b3e324f4470bbd4b8363b379fd3ed3c', '删除部门', '8fb8756a4587cc4c76401a63ea194568', NULL, 'upms:dept:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('7c411c7d41034d6708103c8e0da19ced', '权限管理', '0', 'upms', 'upms', 'menu', 3, 'KeyOutline', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('7d225cd8d60da156e17e341f86304970', '删除知识库', '97a5eac3bfeeabe4013d828b919786f7', NULL, 'aigc:knowledge:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('80c1246cff10a470f67b4a58b0fe257e', '修改知识库', '97a5eac3bfeeabe4013d828b919786f7', NULL, 'aigc:knowledge:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('89f1ba9a70e8bf72961f321156361fe6', '删除角色', '6f8aff1f2c458e5add9adb6d284fb451', NULL, 'upms:role:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('8b2924d753d4e2c1932e1f17e30d0c52', '修改模型', '5ce2349dc38a84cfbf0f5b260b41a2b6', NULL, 'aigc:model:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('8c0eb60ccef367ce7048e5d486aaa3a9', '日志管理', '2f5735d125b4537076893a4b4a37a188', 'log', 'log', 'menu', 1, NULL, '/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('8fb8756a4587cc4c76401a63ea194568', '部门管理', '7c411c7d41034d6708103c8e0da19ced', 'dept', 'dept', 'menu', 3, NULL, '/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('979631c0fae847a8dd59321b1da7d5e7', '新增用户', 'b29de942eeabc9419185951f57be11f3', NULL, 'upms:user:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('97a5eac3bfeeabe4013d828b919786f7', '知识库管理', '374409ab56141b311ccb0f1847dd724a', 'knowledge', 'knowledge', 'menu', 1, 'alert', '/aigc/knowledge/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('9e526a34052ca76cf4f1ec685187e84a', '删除菜单', 'b1df787d8af5b728181a4b9acf38da93', NULL, 'upms:menu:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('a00ca2926f617715b236c113b2ea14b9', '删除令牌', 'abb7e994494b96797b262cc2c72ea620', NULL, 'system:token:delete', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('a2ccfe694cd91cf159ad35626e4ea202', 'AIGC应用', '0', 'app', 'app', 'menu', 1, 'PaperPlaneOutline', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('a985c800d102da822b59dacc77ee6c9d', '修改用户', 'b29de942eeabc9419185951f57be11f3', NULL, 'upms:user:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('abb7e994494b96797b262cc2c72ea620', '令牌管理', '2f5735d125b4537076893a4b4a37a188', 'token', 'token', 'menu', 2, NULL, '/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('b1df787d8af5b728181a4b9acf38da93', '菜单管理', '7c411c7d41034d6708103c8e0da19ced', 'menu', 'menu', 'menu', 4, NULL, '/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('b29de942eeabc9419185951f57be11f3', '用户管理', '7c411c7d41034d6708103c8e0da19ced', 'user', 'user', 'menu', 1, NULL, '/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('b3331acdd06227088f3fb4b92b8b0365', '删除日志', '8c0eb60ccef367ce7048e5d486aaa3a9', NULL, 'system:log:delete', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('b52b25a1c477bb59f1347b5c6865524e', '删除Web渠道', '0825f18b3860f8c01a9b0d8221280e3b', NULL, 'aigc:app:web:delete', 'button', 6, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('bd1e86f2de798359168914a1a3332579', '新增平台用户', '657b6bc0a43914c1bf0a2d517562a2a5', NULL, 'aigc:user:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('bdd70f2c1ee068c13bd3288eff07c8e2', 'AI聊天助手', 'a2ccfe694cd91cf159ad35626e4ea202', 'chat', 'aigc:chat', 'menu', 1, '', '/chat/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('c212381ae7a2333416a18e486f044777', '账单统计', '374409ab56141b311ccb0f1847dd724a', 'order', 'order', 'menu', 5, NULL, '/aigc/order/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('cac8d8f2f35bd872dcc3652add9bbd08', '修改角色', '6f8aff1f2c458e5add9adb6d284fb451', NULL, 'upms:role:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('d99e460bd02a18eaf15206b09f709bfb', '修改应用', '43563b039d30b990f87af37783115ff4', NULL, 'aigc:app:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('f1ad3c056ac91fa5292a99f223155afc', '对话数据', '374409ab56141b311ccb0f1847dd724a', 'message', 'message', 'menu', 4, NULL, '/aigc/message/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('f5d6cbc1e97e2a87149598f86c1bdbbe', '删除用户', 'b29de942eeabc9419185951f57be11f3', NULL, 'upms:user:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('f80b93e4513a32607dcb91bdc8d846cf', '修改平台用户', '657b6bc0a43914c1bf0a2d517562a2a5', NULL, 'aigc:user:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('fadaa37669c31316d8addac152f1f0ff', '聊天权限', 'bdd70f2c1ee068c13bd3288eff07c8e2', NULL, 'chat:completions', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES ('ffca98852cd6faea6b20e2a339578f13', '删除令牌', 'abb7e994494b96797b262cc2c72ea620', NULL, 'system:token:delete', 'button', 2, NULL, NULL, 0, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` varchar(50) NOT NULL COMMENT '主键',
                            `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
                            `code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色别名',
                            `des` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `code`, `des`) VALUES ('2827e950043adf67b7fe10306d3e94e4', '超级管理员角色', 'administrator', '超级管理员管理员，不受权限控制，不可编辑');
INSERT INTO `sys_role` (`id`, `name`, `code`, `des`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '默认人员角色', 'default_env', '后台新用户注册角色，不可删除');
INSERT INTO `sys_role` (`id`, `name`, `code`, `des`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '演示环境角色', 'demo_env', '演示环境使用角色，拥有页面预览权限，没有操作权限');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
                                 `role_id` varchar(50) NOT NULL COMMENT '角色ID',
                                 `menu_id` varchar(50) NOT NULL COMMENT '菜单/按钮ID',
                                 PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '0825f18b3860f8c01a9b0d8221280e3b');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '2f5735d125b4537076893a4b4a37a188');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '374409ab56141b311ccb0f1847dd724a');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '43563b039d30b990f87af37783115ff4');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '5ce2349dc38a84cfbf0f5b260b41a2b6');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '657b6bc0a43914c1bf0a2d517562a2a5');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '6f8aff1f2c458e5add9adb6d284fb451');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '7c411c7d41034d6708103c8e0da19ced');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '8c0eb60ccef367ce7048e5d486aaa3a9');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '8fb8756a4587cc4c76401a63ea194568');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '97a5eac3bfeeabe4013d828b919786f7');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'a2ccfe694cd91cf159ad35626e4ea202');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'abb7e994494b96797b262cc2c72ea620');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'b1df787d8af5b728181a4b9acf38da93');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'b29de942eeabc9419185951f57be11f3');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'bdd70f2c1ee068c13bd3288eff07c8e2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'c212381ae7a2333416a18e486f044777');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'f1ad3c056ac91fa5292a99f223155afc');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` varchar(50) NOT NULL COMMENT '用户ID',
                            `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
                            `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码',
                            `real_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '真实姓名',
                            `sex` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '性别',
                            `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机',
                            `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
                            `dept_id` varchar(50) DEFAULT NULL COMMENT '部门ID',
                            `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
                            `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES ('827450c4a39b3c4c14fdfb06f454bfb3', 'langchat', 'U3lnYOIEGN38KKy0h3KUSA==', '演示环境账号', '男', '19809587831', 'langchat@outlook.com', '14b300858a898c6dcfd3dc95dde6df81', NULL, 1, '2024-08-04 13:55:35');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES ('91b4524a46a949601e7f3b004ed76034', 'administrator', 'U3lnYOIEGN38KKy0h3KUSA==', '超级管理员', '男', '19809587831', 'langchat@outlook.com', NULL, NULL, 0, '2024-08-04 13:55:35');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `user_id` varchar(50) NOT NULL COMMENT '用户ID',
                                 `role_id` varchar(50) NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('827450c4a39b3c4c14fdfb06f454bfb3', 'd0d0cab7c147d865d35e70fc62f2f19e');
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('91b4524a46a949601e7f3b004ed76034', '2827e950043adf67b7fe10306d3e94e4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
