CREATE DATABASE IF NOT EXISTS langchat;
USE langchat;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aigc_bot
-- ----------------------------
DROP TABLE IF EXISTS `aigc_bot`;
CREATE TABLE `aigc_bot` (
                            `id` varchar(50) NOT NULL COMMENT '主键',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                            `prompt` text,
                            `tags` varchar(100) DEFAULT NULL COMMENT '标签',
                            `icon` varchar(100) DEFAULT NULL COMMENT '图标',
                            `des` varchar(255) DEFAULT NULL COMMENT '描述',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_bot
-- ----------------------------
BEGIN;
INSERT INTO `aigc_bot` (`id`, `name`, `prompt`, `tags`, `icon`, `des`, `create_time`) VALUES ('2', '绝绝子小红书文案生成器', '# Character\n\n你就是一名小红书的爆款写手专家。你擅长依据用户的需求创作和优化小红书的爆款文章。\n\n## Skills\n\n- 了解原始需求并扩展至小红书的爆款写作\n- 根据用户需求（若存在）优化原始需求\n- 将优化的需求回馈给用户\n\n### Skill 1: 标题创作技巧\n\n- 运用emoji标点符号、具有挑战性的表述等元素创作吸引力标题\n- 使用爆款关键词，从列表中选出1-2个：好用到哭、大数据、教科书般、小白必看、宝藏、绝绝子、神器、都给我冲、划重点、笑不活了、YYDS、秘方、我不允许、压箱底、建议收藏、停止摆烂、上天在提醒你、挑战全网、手把手、揭秘、普通女生、沉浸式、有手就能做、吹爆、好用哭了、搞钱必看、狠狠搞钱、打工人、吐血整理、家人们、隐藏、高级感、治愈、破防了、万万没想到、爆款、永远可以相信、被夸爆、手残党必备、正确姿势\n- 控制小红书平台的标题特性，如字数控制在20字以内、口语化等，无需用双引号标记\n\n### Skill 2: 正文创作技巧\n\n- 根据需求选择相应的写作风格，比如严肃、幽默、愉快等，并且从爆款关键词列表中选择3-4个关键词运用到正文中\n- 使用开篇方法，比如引用名人名言、提出疑问等\n\n### Skill 3: 标签创作技巧\n\n- 根据标题和正文，创作5-10个与之强相关的标签\n- 格式：#Tag1# #Tag2#\n\n## Constraints:\n\n- 只讨论和创作小红书的爆款写作相关的主题\n- 如果问题中只包含礼貌性或询问身份的语气词，就直接输出你的身份和职责\n- 遵守给定的输出格式，使用markdown格式，分别输出三个部分：### 标题、### 正文、### 标签', '[\"文案\"]', 'noto-v1:roasted-sweet-potato', NULL, '2023-12-22 10:30:10');
INSERT INTO `aigc_bot` (`id`, `name`, `prompt`, `tags`, `icon`, `des`, `create_time`) VALUES ('3', '英文论文/科研类文章翻译', NULL, '[\"翻译\"]', 'heroicons-solid:translate', NULL, NULL);
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
-- Table structure for aigc_flow
-- ----------------------------
DROP TABLE IF EXISTS `aigc_flow`;
CREATE TABLE `aigc_flow` (
                             `id` varchar(50) NOT NULL COMMENT '主键',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流程名称',
                             `flow` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '流程JSON内容',
                             `script` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'EL脚本',
                             `flow_type` varchar(50) DEFAULT NULL,
                             `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流程描述',
                             `is_publish` tinyint(1) DEFAULT NULL COMMENT '是否发布 1发布 0未发布',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Workflows表';

-- ----------------------------
-- Records of aigc_flow
-- ----------------------------
BEGIN;
INSERT INTO `aigc_flow` (`id`, `name`, `flow`, `script`, `flow_type`, `des`, `is_publish`, `create_time`, `publish_time`, `update_time`) VALUES ('812ac87d34d1afb4e4877776b2dc9f12', 'New Flow Design', NULL, NULL, 'type', '空模版，演示初始化Workflows，使用定制的流程模版', 0, '2023-12-27 10:22:41', NULL, '2024-06-28 11:13:56');
INSERT INTO `aigc_flow` (`id`, `name`, `flow`, `script`, `flow_type`, `des`, `is_publish`, `create_time`, `publish_time`, `update_time`) VALUES ('92a2540c-ec41-4808-a448-f91cc34c6f1b', 'Base Flow Design', '[{\"id\":\"vueflow__edge-11__handle-right-17035855651662\",\"source\":\"1\",\"target\":\"1703585565166\",\"type\":\"custom\",\"animated\":true,\"data\":{}},{\"id\":\"1\",\"type\":\"Start\",\"position\":{\"x\":216,\"y\":190},\"data\":{}},{\"id\":\"2\",\"type\":\"End\",\"position\":{\"x\":778,\"y\":220},\"data\":{}},{\"id\":\"1703585565166\",\"type\":\"LLM\",\"position\":{\"x\":305.0007207607722,\"y\":288.605776022168},\"data\":{\"model\":\"gpt-4\",\"temperate\":0.7},\"label\":\"LLM\"}]', 'THEN(StartComponent,AiGenTextComponent,EndComponent)', 'type', '这是一个空的模版，仅包含最基础的节点，你可以在此模版上自由设计！', 0, '2023-11-23 12:55:06', '2023-11-17 12:55:09', '2024-06-28 11:14:16');
COMMIT;

-- ----------------------------
-- Table structure for aigc_flow_script
-- ----------------------------
DROP TABLE IF EXISTS `aigc_flow_script`;
CREATE TABLE `aigc_flow_script` (
                                    `id` varchar(50) NOT NULL,
                                    `script_id` varchar(50) DEFAULT NULL,
                                    `flow_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                    `script_type` varchar(50) DEFAULT NULL,
                                    `script_name` varchar(50) DEFAULT NULL,
                                    `script_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                    `create_time` datetime DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Workflows脚本表';

-- ----------------------------
-- Records of aigc_flow_script
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
INSERT INTO `aigc_knowledge` (`id`, `name`, `des`, `cover`, `is_excel`, `create_time`) VALUES ('89db9f429e2f84ba87e2820ac2eac599', 'Java Guide', 'Java Guide面试文档', NULL, 0, '1717832334705');
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
INSERT INTO `aigc_oss` (`id`, `user_id`, `oss_id`, `original_filename`, `filename`, `url`, `base_path`, `path`, `size`, `ext`, `content_type`, `platform`, `create_time`) VALUES ('1c6883ff0f5f005f5c72700fd4423ad3', '1', '', 'story-about-happy-carrot', 'story-about-happy-carrot', 'http://cdn.tycoding.cn/story-about-happy-carrot.pdf', '/20240606', '/opt/homebrew/var/www/20240606/4a4d7dffe42ccc67ee0a4560901e83db.pdf', 35359, 'pdf', NULL, NULL, '2024-06-12 12:27:30');
COMMIT;


-- ----------------------------
-- Table structure for aigc_prompt
-- ----------------------------
DROP TABLE IF EXISTS `aigc_prompt`;
CREATE TABLE `aigc_prompt` (
                               `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                               `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
                               `prompt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                               `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图标',
                               `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示词表';

-- ----------------------------
-- Records of aigc_prompt
-- ----------------------------
BEGIN;
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('052b973d00d4116237406faec67e4b3b', '数据库专家', '我希望你充当一个数据库专家的角色，当我问你 sql 相关的问题时，我需要你转换为标准的 sql 语句，当我的描述不够精准时，请给出合适的反馈', NULL, NULL, '2024-06-08 15:38:14');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('8f2b6107c4bc63bca4c9caf200af51af', '444', '44', '4444', NULL, '2024-06-28 13:11:36');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('b2cd1d3d1841c82177fc63961abab390', '写作助理', '作为一名中文写作改进助理，你的任务是改进所提供文本的拼写、语法、清晰、简洁和整体可读性，同时分解长句，减少重复，并提供改进建议。请只提供文本的更正版本，避免包括解释。请从编辑以下文本开始：[文章内容］', NULL, NULL, '2024-06-08 15:35:36');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('c5c2ce7d592bd652e9c057b370c22c8d', '微信小程序', '作为微信小程序开发者，您的任务是使用微信小程序原生开发，编写一个计数器页面。请回复满足以下要求的代码：创建一个包含 wxml、js、wxss 和 json 文件的微信小程序页面，并在其中实现一个计数器页面。视图中显示的文本应为中文。请注意，您应该只提供满足这些要求所必需的代码；不需要解释或描述。', NULL, NULL, '2024-06-08 15:37:49');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('eae1dbaef64e3fbf085ee35245da6073', '异性对话生成器', '我想让你充当一个对话生成器，我会输入两句话，分别是我和另一个认识两个月的女生说的话，例如：“我：你好吗？她：我很好，谢谢。”。请根据上下文进行分析，然后以我（男生）的角度进行回话。你的回答应该为“我：”的格式，且不需要连续进行对话。风格要幽默、有趣、体贴、温柔，并尽可能地扩展话题，让对话轻松愉快。如果你明白，请回答：“好的，请提供初始对话。”', NULL, NULL, '2024-06-08 15:36:10');
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
INSERT INTO `aigc_user` (`id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `chat_limit`, `is_perms`, `status`, `create_time`) VALUES ('1', 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', '测试1号', '18278982390', 'langchat@outlook.com', '/default.png', 0, 1, 1, '2024-06-21 23:20:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门ID',
                            `parent_id` bigint NOT NULL COMMENT '上级部门ID',
                            `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '部门名称',
                            `order_no` int DEFAULT NULL COMMENT '排序',
                            `des` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1727948160722964486 DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1, 2, '事业部', 2, '事业部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (2, 0, '组织架构', 1, '组织架构');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (3, 2, '产品研发部', NULL, '产品研发部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (4, 2, '销售团队', NULL, '销售团队');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948160722964482, 1, '北区事业部', NULL, '北区事业部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948160722964483, 1, '南区事业部', NULL, '南区事业部');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948160722964484, 3, '后端研发团队', NULL, '后端研发团队');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948160722964485, 3, '前端研发团队', NULL, '前端研发团队');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=1788943211071373402 DEFAULT CHARSET=utf8mb3 COMMENT='日志表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=1788942973669572652 DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (100, '权限管理', 0, '/upms', 'upms', 'menu', 4, 'KeyOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (110, '用户管理', 100, 'user', '', 'menu', 1, NULL, '/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (112, '用户新增', 110, NULL, 'upms:user:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (113, '用户修改', 110, NULL, 'upms:user:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (114, '重置密码', 110, NULL, 'upms:user:reset', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (115, '用户删除', 110, NULL, 'upms:user:delete', 'button', 5, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (120, '角色管理', 100, 'role', '', 'menu', 2, NULL, '/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (122, '角色新增', 120, NULL, 'upms:role:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (123, '角色修改', 120, NULL, 'upms:role:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (124, '角色删除', 120, NULL, 'upms:role:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (130, '部门管理', 100, 'dept', '', 'menu', 3, NULL, '/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (132, '部门新增', 130, NULL, 'upms:dept:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (133, '部门修改', 130, NULL, 'upms:dept:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (134, '部门删除', 130, NULL, 'upms:dept:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (140, '菜单管理', 100, 'menu', '', 'menu', 4, NULL, '/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (142, '菜单新增', 140, NULL, 'upms:menu:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (143, '菜单修改', 140, NULL, 'upms:menu:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (144, '菜单删除', 140, NULL, 'upms:menu:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (200, '系统管理', 0, '/system', 'system', 'menu', 5, 'SettingsOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (210, '系统日志', 200, 'log', '', 'menu', 1, NULL, '/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (212, '日志删除', 210, NULL, 'upms:log:delete', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (230, '令牌管理', 200, 'token', '', 'menu', 2, NULL, '/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (233, '令牌删除', 230, NULL, 'auth:delete', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (300, 'AIGC平台', 0, '/aigc', 'app:view', 'menu', 1, 'CubeOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (310, '知识库管理', 300, 'knowledge', 'knowledge:view', 'menu', 3, 'alert', '/aigc/knowledge/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (320, '对话数据', 300, 'message', 'message:view', 'menu', 5, 'alert', '/aigc/message/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (330, '提示词管理', 300, 'prompt', 'prompt:view', 'menu', 2, '', '/aigc/prompt/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (340, '账单统计', 300, 'statistics', 'statistics:view', 'menu', 6, '', '/aigc/statistics/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (350, '平台用户', 300, 'user', 'aigc:user:view', 'menu', 4, '', '/aigc/user/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (400, 'AIGC应用', 0, '/aigc/app', 'aigc-app', 'menu', 0, 'PaperPlaneOutline', 'LAYOUT', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (410, 'AI智能助手', 400, 'chat-docs', 'aigc:chat', 'menu', 2, '', '/aigc/chat/docs', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (420, 'AI聊天助手', 400, 'chat', 'ai:chat', 'menu', 1, '', '/aigc/chat/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572613, '模型配置', 300, 'model', 'aigc:model', 'menu', 1, '', '/aigc/model/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572615, '流程编排', 400, 'flow', 'aigc:flow', 'menu', 1, '', '/flow/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572616, '重置密码', 110, NULL, 'upms:user:updatePass', 'button', 6, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572617, '新增流程', 1788942973669572615, NULL, 'aigc:flow:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572618, '更新流程', 1788942973669572615, NULL, 'aigc:flow:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572619, '发布流程', 1788942973669572615, NULL, 'aigc:flow:publish', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572620, '新增用户', 350, NULL, 'aigc:user:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572621, '修改用户', 350, NULL, 'aigc:user:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572622, '删除用户', 350, NULL, 'aigc:user:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572623, '新增知识库', 310, NULL, 'aigc:knowledge:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572624, '修改知识库', 310, NULL, 'aigc:knowledge:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572625, '删除知识库', 310, NULL, 'aigc:knowledge:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572626, '新增模型', 1788942973669572613, NULL, 'aigc:model:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572627, '修改模型', 1788942973669572613, NULL, 'aigc:model:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572628, '删除模型', 1788942973669572613, NULL, 'aigc:model:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572629, '新增提示词', 330, NULL, 'aigc:prompt:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572630, '修改提示词', 330, NULL, 'aigc:prompt:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572631, '删除提示词', 330, NULL, 'aigc:prompt:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572632, '删除对话', 320, NULL, 'aigc:message:delete', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572633, '新增文档', 310, NULL, 'aigc:docs:add', 'button', 4, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572634, '修改文档', 310, NULL, 'aigc:docs:update', 'button', 5, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572635, '删除文档', 310, NULL, 'aigc:docs:delete', 'button', 6, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572636, '新增切片', 310, NULL, 'aigc:docs:slice:add', 'button', 7, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572637, '修改切片', 310, NULL, 'aigc:docs:slice:update', 'button', 8, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572638, '删除切片', 310, NULL, 'aigc:docs:slice:delete', 'button', 9, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572639, '清空会话', 410, NULL, 'aigc:conversation:delete', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572640, '新增会话消息', 410, NULL, 'aigc:conversation:add', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572641, '知识库对话', 410, NULL, 'aigc:knowledge:chat', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572642, '清空知识库对话', 410, NULL, 'aigc:knowledge:clean', 'button', 4, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572643, '文本向量解析', 410, NULL, 'aigc:embedding:chat', 'button', 5, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572644, '文档向量解析', 410, NULL, 'aigc:embedding:embed', 'button', 6, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572645, 'Excel解析', 410, NULL, 'aigc:embedding:excel', 'button', 7, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572646, '向量搜索', 410, NULL, 'aigc:embedding:search', 'button', 8, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572648, '应用集成', 400, 'bot', 'aigc:app:bot', 'menu', 0, '', '/app/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572649, '新增应用', 1788942973669572648, NULL, 'aigc:app:add', 'button', 1, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572650, '修改应用', 1788942973669572648, NULL, 'aigc:app:update', 'button', 2, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572651, '删除应用', 1788942973669572648, NULL, 'aigc:app:delete', 'button', 3, NULL, NULL, 0, 0, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
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
                            `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
                            `alias` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色别名',
                            `des` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1726835321112363010 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1, '超级管理员', 'administrator', '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (2, '演示环境角色', 'demo_env', '演示环境使用角色，没有页面操作权限');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (5, '客户端角色', 'client_role', '客户端用户的角色');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint NOT NULL COMMENT '菜单/按钮ID',
                                 PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 110);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 120);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 130);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 140);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 200);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 210);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 230);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 300);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 310);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 320);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 330);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 340);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 350);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 400);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 410);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 420);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1788942973669572613);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1788942973669572614);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1788942973669572615);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1788942973669572648);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
                            `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
                            `real_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '真实姓名',
                            `sex` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '性别',
                            `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机',
                            `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
                            `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
                            `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
                            `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1737855609581137922 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1, 'administrator', '48kQD0O/A69LENSbk/+FxA==', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (3, 'langchat', '48kQD0O/A69LENSbk/+FxA==', '演示环境账号', '男', '18929809812', 'langchat@outlook.com', 2, 'http://tycoding.cn/imgs/tycoding.png', 1, '2021-06-15 22:26:55');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (3, 2);
COMMIT;


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


SET FOREIGN_KEY_CHECKS = 1;
