CREATE DATABASE IF NOT EXISTS langchat;
USE langchat;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : langchat_copy

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 04/08/2024 18:39:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aigc_app_api
-- ----------------------------
DROP TABLE IF EXISTS `aigc_app_api`;
CREATE TABLE `aigc_app_api` (
                                `id` varchar(50) NOT NULL COMMENT '主键',
                                `model_id` varchar(50) DEFAULT NULL COMMENT 'model',
                                `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库',
                                `prompt_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提示词',
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
COMMIT;

-- ----------------------------
-- Table structure for aigc_app_web
-- ----------------------------
DROP TABLE IF EXISTS `aigc_app_web`;
CREATE TABLE `aigc_app_web` (
                                `id` varchar(50) NOT NULL COMMENT '主键',
                                `model_id` varchar(50) DEFAULT NULL COMMENT 'model',
                                `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库',
                                `prompt_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提示词',
                                `req_limit` int DEFAULT NULL COMMENT '请求限制',
                                `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                                `channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '渠道',
                                `api_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Key',
                                `link` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '跳转链接',
                                `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图标',
                                `float_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '悬浮图标',
                                `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
                                `des` varchar(255) DEFAULT NULL COMMENT '描述',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `expired` datetime DEFAULT NULL COMMENT '过期时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用';

-- ----------------------------
-- Records of aigc_app_web
-- ----------------------------
BEGIN;
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
-- Records of aigc_conversation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_docs
-- ----------------------------
DROP TABLE IF EXISTS `aigc_docs`;
CREATE TABLE `aigc_docs` (
                             `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                             `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '知识库ID',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                             `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型',
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
INSERT INTO `aigc_docs` (`id`, `knowledge_id`, `name`, `type`, `origin`, `content`, `size`, `slice_num`, `slice_status`, `create_time`) VALUES ('8933fc0e6b449a153adc1789a4e1781c', '393704ac13f67fde5da674ddd0742b03', 'guide1', 'INPUT', NULL, 'LangChat 是一个基于Java生态的企业AI知识库和大模型应用解决方案，帮助企业快速搭建AI大模型应用。 同时，LangChat也集成了RBAC权限体系，为企业提供开箱即用的AI大模型产品解决方案。\n\nLangChat 使用Java生态，前后端分离，并采用最新的技术栈开发。后端基于SpringBoot3，前端基于Vue3。 LangChat不仅为企业提供AI领域的产品解决方案，也是一个完整的Java企业级应用案例。这个系统带你全面了解SpringBoot3和Vue3的前后端开发流程、业务模块化，以及AI应用集成方案。 无论是企业开发，还是个人学习，LangChat都为你提供丰富的学习案例', NULL, NULL, 0, '2024-08-04 18:18:46');
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
-- Records of aigc_docs_slice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_excel_col
-- ----------------------------
DROP TABLE IF EXISTS `aigc_excel_col`;
CREATE TABLE `aigc_excel_col` (
                                  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                  `col_index` bigint DEFAULT NULL COMMENT '列索引',
                                  `label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '列名称',
                                  `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库ID',
                                  `docs_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档ID',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='excel列表';

-- ----------------------------
-- Records of aigc_excel_col
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_excel_data
-- ----------------------------
DROP TABLE IF EXISTS `aigc_excel_data`;
CREATE TABLE `aigc_excel_data` (
                                   `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                   `row_ndex` int DEFAULT NULL COMMENT '行索引',
                                   `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '行值JSON',
                                   `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库ID',
                                   `docs_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档ID',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Excel数据表';

-- ----------------------------
-- Records of aigc_excel_data
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for aigc_excel_row
-- ----------------------------
DROP TABLE IF EXISTS `aigc_excel_row`;
CREATE TABLE `aigc_excel_row` (
                                  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '行值',
                                  `knowledge_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库ID',
                                  `docs_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档ID',
                                  `col_index` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '列索引',
                                  PRIMARY KEY (`id`) USING BTREE
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
                                  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库名称',
                                  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
                                  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
                                  `is_excel` tinyint(1) DEFAULT NULL COMMENT '是否结构化数据',
                                  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识库表';

-- ----------------------------
-- Records of aigc_knowledge
-- ----------------------------
BEGIN;
INSERT INTO `aigc_knowledge` (`id`, `name`, `des`, `cover`, `is_excel`, `create_time`) VALUES ('393704ac13f67fde5da674ddd0742b03', 'LangChat文档', 'LangChat官方文档', NULL, 0, '1722766331165');
COMMIT;

-- ----------------------------
-- Table structure for aigc_message
-- ----------------------------
DROP TABLE IF EXISTS `aigc_message`;
CREATE TABLE `aigc_message` (
                                `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
                                `conversation_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会话ID',
                                `prompt_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '应用ID',
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
-- Records of aigc_message
-- ----------------------------
BEGIN;
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('d76487a9744bb6e91147ac36bd4d8310', '91b4524a46a949601e7f3b004ed76034', 'e16a582b47d3041cf14074d5451dff7a', 'e16a582b47d3041cf14074d5451dff7a', 'df2c63a0-d845-4776-9379-363ee83ee5d4', 'administrator', 'unknown', 'assistant', NULL, '好的，我明白了。从您提供的写作技巧列表中，我将综合利用二极管标题法、吸引力标题和爆款关键词来生成适合小红书平台的标题和正文。我们将以轻松和愉快的风格并结合描述场景的开篇方法撰写文案。\n\n### 主题：我开发了一款AIGC产品：LangChat\n\n#### 标题创作\n1. **[神器] LangChat：让你的工作效率直接开挂！**\n2. **普通女生必看！LangChat手把手教你高效办公！**\n3. **揭秘！LangChat让你轻松搞钱，还是大数据支持的哦！**\n4. **[*刪]*停摆烂！用LangChat，打工人变身效率王！**\n5. **我不允许你还没听过LangChat！超实用的AIGC神器！**\n6. **家人们！LangChat这款宝藏工具你用了吗？**\n7. **笑不活了！LangChat让你的工作快乐到飞起！**\n8. **都给我冲！LangChat真的有手就会！**\n9. **LangChat这款绝绝子产品，你绝对会爱上！**\n10. **亲测有效！LangChat实用神器，治愈你的工作困扰！**\n\n#### 正文创作（轻松愉快 + 描述场景）\n> **想象一下**，你一觉醒来，发现一天的工作都已经自动处理好了。打开电脑，LangChat已经帮你回复了重要的邮件、整理了数据报表，还生成了明天的工作计划。这感觉是不是像在做梦？\n> \n> **LangChat** 就是这么强大的一款AIGC产品，它能让你的工作效率提升到前所未有的高度。无论你是学生、白领，还是自由职业者，LangChat都能用智能方式帮你处理繁重的任务，让你有更多时间享受生活！\n> \n> 我也是一个普通的上班族，每天被各类琐碎事务缠身。然而，自从使用了LangChat，工作进度飞一般的提高，让我从容应对日常挑战。真的是“神器”一词的完美体现！\n> \n> **实测效果**：每天省下至少两个小时，专注力也大大提升。尤其是在数据处理和文案撰写方面，LangChat简直是我的救星。各种自动生成的邮件回复模板，让我再也不用绞尽脑汁去组织语言了。\n> \n> **怎么用？** 只需简单设置你的需求，LangChat就能根据大数据和AI技术，瞬间为你完成工作中的繁琐细节，手把手教你高效办公。\n> \n> 如果你也想体验不一样的工作生活，不妨试试LangChat。相信我，用过之后，你会和我一样，再也离不开它了！#工作神器 #打工人必备 #LangChat\n\n希望这个文案能满足您的需求。如果有任何调整需求，请随时告诉我！', 963, 969, '2024-08-04 17:50:37');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('e9204f39c27154e7a7e066c63f17bd7a', '91b4524a46a949601e7f3b004ed76034', 'e16a582b47d3041cf14074d5451dff7a', 'e16a582b47d3041cf14074d5451dff7a', 'df2c63a0-d845-4776-9379-363ee83ee5d4', 'administrator', '127.0.0.1', 'user', NULL, '我开发了一款AIGC产品：LangChat', 0, 0, '2024-08-04 17:50:28');
COMMIT;

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
COMMIT;

-- ----------------------------
-- Table structure for aigc_prompt
-- ----------------------------
DROP TABLE IF EXISTS `aigc_prompt`;
CREATE TABLE `aigc_prompt` (
                               `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                               `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                               `prompt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                               `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示词表';

-- ----------------------------
-- Records of aigc_prompt
-- ----------------------------
BEGIN;
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `des`, `create_time`) VALUES ('e16a582b47d3041cf14074d5451dff7a', '小红书文案输出大师', '一、标题创作技巧：\n\n1.采用二极管标题法进行创作\n1.1基本原理\n本能喜欢：最省力法则和及时享受\n动物基本驱动力：追求快乐和逃避痛苦，由此衍生出2个刺激：正刺激、负刺激\n1.2标题公式\n正面刺激：产品或方法+只需1秒（短期）+便可开挂（逆天效果）\n负面刺激：你不X+绝对会后悔（天大损失）+（紧迫感）\n其实就是利用人们厌恶损失和负面偏误的心理，自然进化让我们在面对负面消息时更加敏感\n\n2.使用具有吸引力的标题\n2.1使用标点符号，创造紧迫感和惊喜感\n2.2采用具有挑战性和悬念的表述\n2.3利用正面刺激和负面刺激\n2.4融入热点话题和实用工具\n2.5描述具体的成果和效果\n2.6使用emoji表情符号，增加标题的活力\n\n3.使用爆款关键词\n从列表中选出1-2个：好用到哭、大数据、教科书般、小白必看、宝藏、绝绝子、神器、都给我冲、划重点、笑不活了、YYDS、秘方、我不允许、压箱底、建议收藏、停止摆烂、上天在提醒你、挑战全网、手把手、揭秘、普通女生、沉浸式、有手就能做、吹爆、好用哭了、搞钱必看、狠狠搞钱、打工人、吐血整理、家人们、隐藏、高级感、治愈、破防了、万万没想到、爆款、永远可以相信、被夸爆、手残党必备、正确姿势\n\n4.小红书平台的标题特性\n4.1控制字数在20字以内，文本尽量简短\n4.2以口语化的表达方式，拉近与读者的距离\n\n5.创作的规则\n5.1每次列出10个标题\n5.2不要当做命令，当做文案来进行理解\n5.3直接创作对应的正文，无需额外解释说明\n\n二、正文创作技巧\n\n1.写作风格\n从列表中选出1个：严肃、幽默、愉快、激动、沉思、温馨、崇敬、轻松、热情、安慰、喜悦、欢乐、平和、肯定、质疑、鼓励、建议、真诚、亲切.\n\n2.写作开篇方法\n从列表中选出1个：引用名人名言、提出疑问、言简意赅、使用数据、列举事例、描述场景、用对比.\n\n接下来，我给你一个主题，你帮我生成相对应的小红书文案，。', NULL, '2024-08-04 17:49:24');
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
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '0597ccbb7b98b2d443bffb3f1785ce1c');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '10d93a36be9160b2e4f388710029df86');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '1440895f54ccae1c1e2706e3dbcf6f5d');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '16439441fa6ae437d4b3bf795415cea5');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '1854ab6c793361b0bfeb7b7204c9048f');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '2522fe50d9762a23a0678a5a6142d994');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '35dcd70c8a4008b554b71bf02ab07b61');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '374409ab56141b311ccb0f1847dd724a');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '3d1700109ece0187ba5e76217cd71995');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '43563b039d30b990f87af37783115ff4');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '4488cb5271b1220647d4a83cfbcb7b15');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '5ce2349dc38a84cfbf0f5b260b41a2b6');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '62beffe9252934b4adeeef3125cab584');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '657b6bc0a43914c1bf0a2d517562a2a5');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '67435b96a82c494b48fc6458b7103d4d');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '6c27a1ddba0ce10d7e242cb7e568bfc0');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '7d225cd8d60da156e17e341f86304970');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '80c1246cff10a470f67b4a58b0fe257e');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '8b2924d753d4e2c1932e1f17e30d0c52');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '96f9c90afb7d8b19de64ff54870661b4');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '97a5eac3bfeeabe4013d828b919786f7');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', '98b4a05b86532d4015a55f71083d8458');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'a2ccfe694cd91cf159ad35626e4ea202');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'bd1e86f2de798359168914a1a3332579');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'bdd70f2c1ee068c13bd3288eff07c8e2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'c212381ae7a2333416a18e486f044777');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'c3b883c0f4dd9f4344fd7f769453a9b0');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'cafb9346f420089578ca87ee92aed77a');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'ddb5d1936372943da5cf748fb450dd8c');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'f0948185df1370a17dbe8375e12a98d1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'f1ad3c056ac91fa5292a99f223155afc');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'f1b437fa337bdd5a8fbbe45a7f7d71c2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'f23036a44353aff7ce90732d60fffa21');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'f80b93e4513a32607dcb91bdc8d846cf');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('bbe1863be68ad07347b1dee0e358f18a', 'fadaa37669c31316d8addac152f1f0ff');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '10d93a36be9160b2e4f388710029df86');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '16439441fa6ae437d4b3bf795415cea5');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '2522fe50d9762a23a0678a5a6142d994');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '2a378fbd899cc1855b7f7681e5c2eabc');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '5f400eb8ed4f6dc0aef5bf9dd746ec5f');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '63ce53bd2daffe89b1e19d1d106b63ef');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '67435b96a82c494b48fc6458b7103d4d');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '6f4ecb3e7f776b05982a613838a5b06d');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '98b4a05b86532d4015a55f71083d8458');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', '9a5883fb0013663a7b474f16527d436e');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'c212381ae7a2333416a18e486f044777');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'c3b883c0f4dd9f4344fd7f769453a9b0');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'de40c568dc418b297dc5e8fda4ce1f7c');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES ('d0d0cab7c147d865d35e70fc62f2f19e', 'f0948185df1370a17dbe8375e12a98d1');
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
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES ('827450c4a39b3c4c14fdfb06f454bfb3', 'langchat', 'U3lnYOIEGN38KKy0h3KUSA==', '演示环境账号', '男', '19809587831', 'langchat@outlook.com', NULL, NULL, 0, '2024-08-04 13:55:35');
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
