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
INSERT INTO `aigc_bot` (`id`, `name`, `prompt`, `tags`, `icon`, `des`, `create_time`) VALUES ('1', '测试', NULL, '[\"啊\",\"测试\",\"测试12\"]', NULL, '测试', '2023-12-19 20:15:10');
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
-- Records of aigc_conversation
-- ----------------------------
BEGIN;
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('0944016a208662cf444ac45b6d072ca9', NULL, NULL, 'New Chat', '2024-06-12 22:58:40');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('27d75c3b523c24bf0e713ee930a933f9', NULL, NULL, 'New Chat', '2024-06-12 23:00:37');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('369eb69cbda286e5a00a305f74eb6327', NULL, NULL, 'New Chat', '2024-06-12 22:58:36');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('4fd31f67a9ff01e6e9a7c74ccbbe543a', '1', NULL, 'New Chat', '2024-06-12 23:20:11');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('ae32d456c33ca8b67797cd833a843a2a', NULL, NULL, 'New Chat', '2024-06-12 22:57:43');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('c1ff128aaf3c1d3f2d9d9763e52d9625', NULL, NULL, 'New Chat', '2024-06-12 23:14:44');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('e5c9d5cc14bd8de68bfccc0bde1cb264', NULL, NULL, 'New Chat', '2024-06-12 22:59:57');
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
-- Records of aigc_message
-- ----------------------------
BEGIN;
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('080f932946a93ece2015f1eb7bcd4a93', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-12 16:03:27');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('09116fd4f14be517e8ad55e932c6b5a5', NULL, NULL, NULL, '29022303-d97b-4203-b112-7bbe286ea5f6', NULL, '127.0.0.1', 'user', 'gpt-4o', '你好', NULL, NULL, '2024-06-10 21:37:09');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('122b5667c608105478696e5abecfffbf', NULL, NULL, NULL, '29022303-d97b-4203-b112-7bbe286ea5f6', NULL, '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-10 21:37:09');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('127794932521ed2d7d569bfa85755dba', '1', '1', NULL, '12a3615e-3562-412f-9355-e45dd40c131e', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-14 23:30:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('17028170f33690186c908814749ca646', '1', '01b3690b3c52545075724cb6eed76a44', NULL, 'cb205884-8e32-4640-9873-73a360e49b27', 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '你好，我在的。有什么我可以帮你的吗？', 20, 10, '2024-06-12 15:16:13');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1a8daf1596ea6717da49a90ea8a01c0c', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-05 22:46:23');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1a9bc5d0a408e5bc2a9d4a0509c87ad9', NULL, NULL, NULL, 'd8fccf04-cd5f-45ea-ad58-cddcecc08172', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sdsd\nsdsd', NULL, NULL, '2024-06-10 21:25:40');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1e147511ababfacb176c94af9137a1a0', '1', '1', NULL, 'ab8b8147-134e-4ea7-ad82-e5fd8759a58e', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天的实时事件可以从多个方面来查看和管理，尤其是利用各种现代工具和平台。以下是一些相关的实时事件管理工具和方法：\n\n### 1. Microsoft Teams 实时事件\nMicrosoft Teams 实时事件是一个强大的工具，允许用户安排和管理大型在线活动。通过Teams实时事件，用户可以：\n- 安排2024年9月之后的活动。\n- 使用视频进行演示，这对于演示者来说非常方便。\n- 设置测试事件以确保所有参与者熟悉流程。\n\n**建议**：提前计划事件，并设置测试事件以确保顺利进行。\n\n### 2. Google Analytics 实时报告\nGoogle Analytics 提供了一个实时报告功能，允许用户查看网站上实时发生的事件。这对于监测刚发布的内容是否受欢迎非常有用。通过“实时事件”报告，用户可以：\n- 查看实时触发的事件。\n- 监测网站即时的用户交互情况，帮助优化内容和用户体验。\n\n**建议**：访问[Google Analytics Learn](https://google.com/analytics/learn)获取更多资源和帮助。\n\n### 3. 使用Yammer实时事件\nYammer也是一个可以用来进行实时事件管理的工具，特别适用于企业内部的沟通和协作。通过Yammer，用户可以：\n- 进行团队内部的实时交流。\n- 共享和讨论实时事件及其影响。\n\n**建议**：在安排事件时，确保所有团队成员都了解并熟悉Yammer的使用方法。\n\n### 总结\n无论是通过Microsoft Teams还是Google Analytics，还是Yammer，实时事件管理工具都提供了丰富的功能来帮助用户更好地安排和监控他们的活动。提前计划、测试和监控都是确保事件成功的关键步骤。', 531, 300, '2024-06-08 10:48:42');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('271320a7d370bba6cb75ae05a13968ba', NULL, '4f5b7c5d49512f0c4bb59b0bcf998681', NULL, '28cd8271-62aa-48b8-81ae-34ae24b4a5c7', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', NULL, NULL, '2024-06-10 22:01:21');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('28f3e47675c891e2261eb0547d3f9efc', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '2d582b7a-e55c-42d7-8b6f-0e2490954104', 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 9, '2024-06-13 15:55:39');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('294f5d2ae0d089a39906aceade6585aa', '1', '1', NULL, '0f4bba4d-70b3-4b96-9e02-8336d2cecf1c', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '北京时间今天几号', 0, 0, '2024-06-08 10:48:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2c1c0397a83dad11ad6094113fa35c4f', '1', '1', NULL, '69c9dce8-ff9d-40c1-b5b1-096976b2e581', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮忙的吗？', 17, 10, '2024-06-08 10:35:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2c64f8dd401a36a260ad25132f1646a7', '1', '01b3690b3c52545075724cb6eed76a44', NULL, 'ef16971d-c40b-4374-8602-b9ba7d5a0009', 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '在的，请问有什么可以帮您的吗？', 16, 10, '2024-06-12 09:56:27');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2d7d914fdbff4afea89bea6e40f4ab3b', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-05 22:46:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2e2b1833802994c8c6304aa78ef0cc93', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '在的。\n\n从前在蔬菜城镇VeggieVille，住着一根开朗的胡萝卜，名叫查理。查理是一根光彩照人的胡萝卜，总是洋溢着欢乐和积极的情绪。他那鲜艳的橙色皮肤和茂盛的绿色顶端令人赏心悦目，但真正让他与众不同的是他那富有感染力的笑声和温暖的性格。\n\n从那时起，关于快乐胡萝卜和他朋友们的故事传遍了四方，这是一个关于友谊、团结和积极向上的故事。查理、贝拉、蒂米和珀西继续过着快乐的生活，他们的笑声在VeggieVille中回荡。因此，快乐胡萝卜和他朋友们的故事提醒我们，无论遇到什么挑战，只要有乐观、团队合作和一点创造力，就没有什么是不可能的。\n\n他们用一个盛大的派对来庆祝他们的胜利，充满了笑声、舞蹈和欢乐的游戏。那天晚上，在闪烁的星空下，他们立下誓言，无论发生什么，他们都会一直互相支持。', 430, 225, '2024-06-12 13:56:54');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2fed8e0705f610dd8267dc8a1684a8db', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '你好！\n\nBased on the provided information, here\'s an answer:\n\nIn the charming town of VeggieVille, there lived a cheerful carrot named Charlie. Radiant with joy and positivity, Charlie\'s vibrant orange skin and lush green top made him stand out, but it was his infectious laughter and warm personality that truly made him unique.\n\nCharlie and his friends, Bella, Timmy, and Percy, shared a tale of friendship, unity, and positivity that spread far and wide. Their lives were filled with joy, and their laughter echoed throughout VeggieVille. \n\nAfter overcoming challenges together, they celebrated their victory with a grand party. The night was filled with laughter, dance, and merry games. Under the twinkling stars, they made a pact to always stand by each other, no matter what challenges came their way. \n\nThis story serves as a reminder that with optimism, teamwork, and a bit of creativity, anything is possible.', 190, 224, '2024-06-12 13:54:01');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('320fa79f0938de60923ac20e3c8dd060', '1', '1', NULL, '3be30972-63a1-4131-bb6e-ee603e32b38f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮忙的吗？', 17, 10, '2024-06-08 10:20:34');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('334095f2d2d70a2b2fadffe2c49d378a', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个人工智能助手，可以回答你的问题、提供信息或帮助你解决问题。有什么我可以帮你的吗？', 50, 103, '2024-06-05 22:46:33');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('38cd04e28b4406d033a08092e4cff8f2', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是基于OpenAI的GPT-4模型。有什么我可以帮你的吗？', 30, 83, '2024-06-04 22:45:21');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('394043f34e5e820453b388cd5228a0f3', NULL, NULL, NULL, '1d8e4d32-441f-4d9c-96d0-1fc2b51e12a5', NULL, '127.0.0.1', 'user', 'gpt-4o', '是', NULL, NULL, '2024-06-10 21:31:19');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('3cbc7d601d5d1c5c3e551605db37e5b6', '1', '052b973d00d4116237406faec67e4b3b', '052b973d00d4116237406faec67e4b3b', 'a71a79b4-6383-44f0-83d0-e19d08e40347', 'administrator', 'unknown', 'assistant', 'gpt-4o', '要查询数据库中表 `a` 的所有数据，您需要使用 SQL 语句。具体来说，如果您使用的是 MySQL、PostgreSQL、SQLite 或者其他类似的关系型数据库，您可以执行以下查询：\n\n```sql\nSELECT * FROM a;\n```\n\n这条 SQL 语句会从表 `a` 中检索所有行和所有列的数据。\n\n如果您使用的是某种编程语言来与数据库交互，例如 Python 或 Java，您还需要使用相应的数据库连接库来执行这条 SQL 语句。以下是一个使用 Python 和 `sqlite3` 库的例子：\n\n```python\nimport sqlite3\n\n# 连接到 SQLite 数据库（如果数据库文件不存在，会自动创建）\nconn = sqlite3.connect(\'your_database.db\')\n\n# 创建一个游标对象\ncur = conn.cursor()\n\n# 执行查询语句\ncur.execute(\"SELECT * FROM a\")\n\n# 获取所有结果\nrows = cur.fetchall()\n\n# 打印结果\nfor row in rows:\n    print(row)\n\n# 关闭游标和连接\ncur.close()\nconn.close()\n```\n\n请根据您的实际情况替换数据库类型和连接方法。如果您使用的是其他编程语言或者数据库系统，方法可能会有所不同。', 299, 12, '2024-06-08 15:43:42');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('436e2bf0385db00c0d8b0de6a228da1e', '1', '1', NULL, '3e9f895b-279e-4a5f-9fc6-4b910a36eb21', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:19:34');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('487be0adbdaddf547f24d2e1e47cce9c', '1', '01b3690b3c52545075724cb6eed76a44', NULL, 'cb205884-8e32-4640-9873-73a360e49b27', 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-12 15:16:12');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('49152cf5b691bb56445ed3d67b721314', '1', 'e46b16ba6b585d729f0ee942756963d4', NULL, NULL, 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '在的。\n\n从前在VeggieVille镇上，有一个快乐的胡萝卜，名字叫查理。查理是一根充满喜悦和积极性的胡萝卜。他那鲜艳的橙色皮肤和茂盛的绿色顶部让人赏心悦目，但真正让他与众不同的是他那富有感染力的笑声和热情洋溢的性格。\n\n从那时起，关于这根快乐的胡萝卜和他朋友们的故事传遍了四方，成为了友谊、团结和积极性的象征。查理、贝拉、蒂米和珀西继续快乐地生活着，他们的笑声在VeggieVille中回荡。\n\n这个快乐胡萝卜和他朋友们的故事提醒我们，无论遇到什么挑战，只要有乐观的心态、团队合作和一点创造力，一切皆有可能。\n\n他们用一个盛大的派对来庆祝他们的胜利，充满了笑声、舞蹈和欢快的游戏。那天晚上，在闪烁的星星下，他们立下了誓言，无论发生什么，都要相互支持。', 409, 225, '2024-06-12 23:29:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('49e5c381f6812cdc504e8262d4ae409b', '1', '1', NULL, '64c567b6-ee3d-4d53-a222-1aaddf9863aa', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天是6月7号。', 8, 292, '2024-06-08 10:47:47');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4a3564032dabd203adb006df50344980', '1', '052b973d00d4116237406faec67e4b3b', '052b973d00d4116237406faec67e4b3b', 'a71a79b4-6383-44f0-83d0-e19d08e40347', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '查询a表所有数据', 0, 0, '2024-06-08 15:43:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4c12ea77b97e272fa6050bde1802a034', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '0df2b25b-f359-4fd6-897a-40dd1da4c2b3', 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮你的吗？', 16, 9, '2024-06-13 11:08:27');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4c1cc48e8a67922dd095bc892f9e8a71', NULL, NULL, NULL, 'f8e0073f-8c44-4e33-b445-67baa1a45251', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', NULL, NULL, '2024-06-10 21:38:10');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4cf1330997e46a500bb6ae31db07f5aa', '1', 'd47090ea920e794d2915e0013149e002', 'd47090ea920e794d2915e0013149e002', '31fb5c39-4b74-42e8-9437-ef790a1044a1', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-12 17:11:10');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4fc944cd6fdaeafa967d9b6e7cb6e165', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '0df2b25b-f359-4fd6-897a-40dd1da4c2b3', 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-13 11:08:25');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('5688b1a71140627c5c74f31aea16640f', '1', '1', NULL, '69c9dce8-ff9d-40c1-b5b1-096976b2e581', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:35:01');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('5a13a072995713d280ab8862857007a8', '3', '3', NULL, '00da75d7-d48c-4a3a-9151-56a807ca4a6c', 'langchat', 'unknown', 'assistant', '056d9c38f3258606144b1a376a24ef16', '您好，有什么可以帮助您的吗？', 17, 9, '2024-06-28 15:16:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('5be69706510c077072c6011470b7bbbf', NULL, NULL, NULL, '1d8e4d32-441f-4d9c-96d0-1fc2b51e12a5', NULL, '127.0.0.1', 'user', 'gpt-4o', '是', 0, 0, '2024-06-10 21:31:19');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('5da4d01c0ab79a63bbe227ec9eb2e194', NULL, NULL, NULL, '231b63b0-288b-47e2-80b6-308fd5878dc1', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', NULL, NULL, '2024-06-10 21:36:54');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('645823d7f6d73c69f152f9ed4d380129', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 77, '2024-06-05 22:46:25');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('65f9a478e1fd816954d763d38c5da587', '1', '1', NULL, '21baaf71-fdf9-4865-99c1-1879aa2db054', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:47:37');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('672ea82d7d1d42966cdca48a98c546f6', '1', '1', NULL, '7ce6275c-0a16-432e-9135-15123ccda2c8', 'administrator', 'unknown', 'assistant', '056d9c38f3258606144b1a376a24ef16', '你好，有什么我可以帮助你的吗？', 18, 9, '2024-06-27 13:09:04');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('71c472aaab5e1cea38a067d22731b003', NULL, '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '0df2b25b-f359-4fd6-897a-40dd1da4c2b3', NULL, '127.0.0.1', 'user', 'gpt-4o', '你好', NULL, NULL, '2024-06-13 11:08:25');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('7290daf364e1cba333ec98db7f06c874', '1', '1', NULL, '21baaf71-fdf9-4865-99c1-1879aa2db054', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮您的吗？', 16, 10, '2024-06-08 10:47:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('7410283f19c4bbdcbc406310e1879c38', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, 'd52cf71a-e06e-47da-b473-25a0c145693a', 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮你的吗？', 16, 10, '2024-06-12 23:20:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('76c7cf1832fe41831cf03a1205ec2421', '1', '1', NULL, 'f8f50651-8402-486e-b5d5-5c68673aad04', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天发生了什么', 0, 0, '2024-06-08 10:48:50');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('76fca9fc13d4bdd57c5f0343481fb7e7', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-12 13:56:49');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('78ec3240d1824799b0359d13a9ce5306', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你的模型版本', 0, 0, '2024-06-04 22:45:09');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('79ca3d8ca64bcd917fbf4cd32bc9cd30', '1', 'd47090ea920e794d2915e0013149e002', 'd47090ea920e794d2915e0013149e002', 'e5619654-fa30-462b-8f2e-7fe5afa205b2', 'administrator', 'unknown', 'assistant', 'gpt-4o', 'stateDiagram-v2\n    [*] --> LoginScreen\n    LoginScreen --> EnterUsername: Username Entered\n    LoginScreen --> EnterPassword: Password Entered\n    EnterUsername --> ValidateUsername: Validating\n    EnterPassword --> ValidatePassword: Validating\n    ValidateUsername --> InvalidUsername: Invalid\n    ValidatePassword --> InvalidPassword: Invalid\n    ValidateUsername --> ValidatedUsername: Valid\n    ValidatePassword --> ValidatedPassword: Valid\n    ValidatedUsername --> SubmitForm: Ready to Submit\n    ValidatedPassword --> SubmitForm\n    SubmitForm --> LoginSuccess: Success\n    SubmitForm --> LoginFailure: Failure\n    InvalidUsername --> LoginScreen: Retry\n    InvalidPassword --> LoginScreen: Retry', 145, 94, '2024-06-12 17:11:18');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('7b551ac384ae75bd8b77769379c7a833', NULL, '01b3690b3c52545075724cb6eed76a44', NULL, 'ef16971d-c40b-4374-8602-b9ba7d5a0009', NULL, '127.0.0.1', 'user', 'gpt-4o', '在吗', NULL, NULL, '2024-06-12 09:56:26');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('8243f98081c331e6813f99279d2ad54f', NULL, '4f5b7c5d49512f0c4bb59b0bcf998681', NULL, 'bf70105d-7707-4871-830c-46037b2476a8', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', 0, 0, '2024-06-10 22:03:39');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('878a1c31f04302326b9f546ce96cc944', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, 'd52cf71a-e06e-47da-b473-25a0c145693a', 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-12 23:20:17');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('8abdaed25609a5943be1a8016620badc', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '模型版本是多少', 0, 0, '2024-06-04 22:45:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('8fb7563c0e7f3e0f7c0ee6a72814d9ac', NULL, NULL, NULL, 'd8fccf04-cd5f-45ea-ad58-cddcecc08172', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sdsd\nsdsd', 0, 0, '2024-06-10 21:25:40');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('92240694c229a703d2123d7404523b14', NULL, '4f5b7c5d49512f0c4bb59b0bcf998681', NULL, '28cd8271-62aa-48b8-81ae-34ae24b4a5c7', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', 0, 0, '2024-06-10 22:01:22');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('93236e6d4b5be71803b4a2a0f9dc7d94', NULL, NULL, NULL, 'd7855d03-f7ac-480a-87ad-a60c9f9cef33', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', 0, 0, '2024-06-10 21:33:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9a948d3186d637f7c434387b697cc804', '1', '01b3690b3c52545075724cb6eed76a44', NULL, 'ef16971d-c40b-4374-8602-b9ba7d5a0009', 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-12 09:56:26');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9bd67f5a56c3998126b86f1e23073b47', '1', '1', NULL, '64c567b6-ee3d-4d53-a222-1aaddf9863aa', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天几号', 0, 0, '2024-06-08 10:47:46');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9d763c0ae26fb522169c5336720027fa', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '4f1c16f3-439c-41aa-8e0f-cb14d22ead7f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-08 15:41:53');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9ef8b2e0e2689e98612047ce848b97ce', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个由OpenAI开发的AI助手，使用的是ChatGPT模型。请问有什么我可以帮助你的吗？', 43, 55, '2024-06-04 22:45:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9fdfc0554e18fdd7f02d6013217518ef', NULL, NULL, NULL, '1917383d-7b31-4391-ade4-9339d5fe02e1', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', NULL, NULL, '2024-06-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('a02cf64a3e9f0ec2cf31b7c8309ab1e7', NULL, NULL, NULL, 'd7855d03-f7ac-480a-87ad-a60c9f9cef33', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', NULL, NULL, '2024-06-10 21:33:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('a1b76b847635e7af75bc39d4ba40cdaa', NULL, '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '534d2bb3-1687-4134-bdec-031a29f0cf10', NULL, '127.0.0.1', 'user', 'gpt-4o', '你好', NULL, NULL, '2024-06-13 15:55:21');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('a25755296cc4ab7af15984b0ff5bd13c', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '你好！\n\nBased on the information provided, here\'s a summary of the story:\n\nIn the town of VeggieVille, there lived a cheerful carrot named Charlie. Charlie was known for his radiant orange skin, lush green top, infectious laughter, and warm personality. Along with his friends Bella, Timmy, and Percy, Charlie exemplified the values of friendship, unity, and positivity.\n\nTheir story became well-known, spreading far and wide as a tale of joy and camaraderie. The friends faced challenges together, always approaching them with optimism, teamwork, and creativity, proving that anything is possible when they work together.\n\nTo celebrate their victories, they held grand parties filled with laughter, dancing, and games. One special night, under the twinkling stars, they made a pact to always support each other, no matter what the future held.\n\nThis heartwarming tale of the happy carrot and his friends serves as a reminder of the power of positivity and the importance of standing by each other through life\'s challenges.', 205, 224, '2024-06-12 16:03:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('a48faf106c7ea2b8382f32e4abe8672e', '1', '1', NULL, '12a3615e-3562-412f-9355-e45dd40c131e', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，请问有什么我可以帮忙的吗？', 18, 10, '2024-06-14 23:30:08');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('a655737c2e70bf228d941ee99f273082', NULL, '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '2d582b7a-e55c-42d7-8b6f-0e2490954104', NULL, '127.0.0.1', 'user', 'gpt-4o', '你好', NULL, NULL, '2024-06-13 15:55:37');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('aa4f3ee24d721b3755511055fc5f649e', NULL, '01b3690b3c52545075724cb6eed76a44', NULL, 'cb205884-8e32-4640-9873-73a360e49b27', NULL, '127.0.0.1', 'user', 'gpt-4o', '在吗', NULL, NULL, '2024-06-12 15:16:12');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('ab8eec2a1eb41963c040755caa1836c7', '1', '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, '2d582b7a-e55c-42d7-8b6f-0e2490954104', 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-13 15:55:37');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('acd9cdb464287c5707e98f45b624b8c9', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-12 13:53:51');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('aec6d1f0d6b10f0f5a42c2c3abddd56c', '1', '1', NULL, 'f8f50651-8402-486e-b5d5-5c68673aad04', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天是6月8日，历史上在这一天发生了以下重要事件：\n\n1. 1937年，已经退位的前英国国王爱德华八世与美国名流沃利斯·辛普森在法国图尔举行婚礼。这一事件在当时引起了广泛关注，因为爱德华八世为了与辛普森结婚而放弃了王位。\n\n2. 1998年，德国一部从慕尼黑驶往汉堡的城际快车在下萨克森发生出轨事故，造成101人死亡。这次事故是德国铁路史上最严重的一起事故之一。\n\n这些历史事件展示了从政治变动到交通事故等不同领域的重要事件，反映了历史的多样性和复杂性。', 267, 343, '2024-06-08 10:48:54');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('af8854d2578d707d6db31aaa063477ff', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', NULL, '在吗', 0, 0, '2024-06-12 13:20:22');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('afa897911ec41cae5adab67595daf64d', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '4f1c16f3-439c-41aa-8e0f-cb14d22ead7f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 9, '2024-06-08 15:41:59');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('b18f7754642079568953763c42fe44b0', NULL, '4f5b7c5d49512f0c4bb59b0bcf998681', NULL, 'bf70105d-7707-4871-830c-46037b2476a8', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', NULL, NULL, '2024-06-10 22:03:39');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('b2c40e991d6c6ae841cc3f77e3c7b09c', '1', 'e46b16ba6b585d729f0ee942756963d4', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bab1c3a90064b5ed0ac6dc2c70f1f31a', '1', '1', NULL, '3be30972-63a1-4131-bb6e-ee603e32b38f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bb0d6cae80a5127d26ec7ffc5a2b2a27', '1', '1', NULL, 'a764fd3b-2576-4bac-bd9c-a568abbb60a9', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bd14c3883e9ead479665cd0cd24f389e', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', 'gpt-4o', '你是谁', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c1d09111217524e9812c1f945c52dcfa', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"您好！我在的，有什么可以帮您的吗？\" }] }', 37, 28, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c398e5207eeecf0518f9210f6a1e2ed9', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '2f5d1173-5ce0-4da4-aadb-d511e546c57f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮你的吗？', 16, 9, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c52c0e4ea44399cdd4f59ea6e5ab66bd', '1', '1', NULL, 'ab8b8147-134e-4ea7-ad82-e5fd8759a58e', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天有什么实时事件', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c8095c6a4e3cbea565fa0cf3f34ea942', NULL, NULL, NULL, '1917383d-7b31-4391-ade4-9339d5fe02e1', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', 0, 0, '2024-07-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c8a86e6264979ca0d338f20616048b65', NULL, '4fd31f67a9ff01e6e9a7c74ccbbe543a', NULL, 'd52cf71a-e06e-47da-b473-25a0c145693a', NULL, '127.0.0.1', 'user', 'gpt-4o', '在吗', NULL, NULL, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('ce87bb33cfd193272e7d5d40cdd91f8a', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', '127.0.0.1', 'user', NULL, '在吗', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('cf74dbf7b439fc0cea83430b61ec0d94', '1', 'd47090ea920e794d2915e0013149e002', 'd47090ea920e794d2915e0013149e002', '31fb5c39-4b74-42e8-9437-ef790a1044a1', 'administrator', 'unknown', 'assistant', 'gpt-4o', 'graph TD\n    A[你好] --> B[世界]', 16, 95, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('d20f0831e09a417bdad9e6e9466d6725', '1', '1', NULL, '0f4bba4d-70b3-4b96-9e02-8336d2cecf1c', 'administrator', 'unknown', 'assistant', 'gpt-4o', '对不起，我无法实时访问互联网来获取当前的日期和时间。然而，您可以通过查看手机、电脑或其他设备上的时间来确定今天的日期。北京的时间是根据中国标准时间（CST），即UTC+8。', 73, 216, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('d8efbb0561cc7e7cfb65b6b2cd243d9a', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '2f5d1173-5ce0-4da4-aadb-d511e546c57f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('dbf806441a91686258c0f9bfcc9d7c50', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你是谁', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('e68ef4939f413ac6c0f4d4763266e809', '1', '1', NULL, '7ce6275c-0a16-432e-9135-15123ccda2c8', 'administrator', '127.0.0.1', 'user', '056d9c38f3258606144b1a376a24ef16', '你好', 0, 0, '2024-06-27 13:09:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('e7a3901d7ffa66ee494310b3132817a2', '3', '3', NULL, '00da75d7-d48c-4a3a-9151-56a807ca4a6c', 'langchat', '127.0.0.1', 'user', '056d9c38f3258606144b1a376a24ef16', '你好', 0, 0, '2024-06-28 15:16:08');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('ed58c42cea81596856b12191360d4ed4', '1', 'd47090ea920e794d2915e0013149e002', 'd47090ea920e794d2915e0013149e002', 'e5619654-fa30-462b-8f2e-7fe5afa205b2', 'administrator', '127.0.0.1', 'user', 'gpt-4o', 'login', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('f4cf6640d01820b3bced1b0830f61019', NULL, NULL, NULL, '231b63b0-288b-47e2-80b6-308fd5878dc1', NULL, '127.0.0.1', 'user', 'gpt-4o', 'sd', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('f63562cd42757a6e8ad1dcfe6ab10c9d', '1', '1c6883ff0f5f005f5c72700fd4423ad3', NULL, NULL, 'langchat@outlook.com', 'unknown', 'assistant', 'gpt-4o', '我是来自VeggieVille的小镇居民，名叫Charlie，一根总是充满欢乐和积极能量的胡萝卜。我的橙色皮肤和绿色顶端总是引人注目，但真正让我与众不同的是我那传染性的笑声和温暖的个性。我和我的朋友们——Bella、Timmy和Percy——在VeggieVille过着快乐的生活，我们的故事也因其友谊、团结和积极的精神而被广泛传颂。在菜园的阳光角落，我们一起度过了无数美好的时光。通过乐观、团队合作和一点点创意，我们相信任何挑战都能被克服。', 239, 256, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('f77f60731c57b3ff57e2eaabb7590959', NULL, NULL, NULL, 'f8e0073f-8c44-4e33-b445-67baa1a45251', NULL, '127.0.0.1', 'user', 'gpt-4o', '是的', 0, 0, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fa423a797e0c119daedf6dee419c0035', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"在的，有什么可以帮助您的吗?\" }] }', 34, 53, '2024-08-10 21:34:29');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fe4ae1e276684bf7812c34ca2d6bdaa7', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-08-10 21:34:29');
COMMIT;

-- ----------------------------
-- Table structure for aigc_model
-- ----------------------------
DROP TABLE IF EXISTS `aigc_model`;
CREATE TABLE `aigc_model` (
                              `id` varchar(50) NOT NULL COMMENT '主键',
                              `model` varchar(100) DEFAULT NULL COMMENT '模型名称',
                              `provider` varchar(100) DEFAULT NULL COMMENT '供应商',
                              `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '别名',
                              `response_limit` int DEFAULT NULL COMMENT '响应长度',
                              `temperature` int DEFAULT NULL COMMENT '温度',
                              `top_p` int DEFAULT NULL,
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
                              `model_type` varchar(50) DEFAULT NULL,
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
                            `file_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
                            `target_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件存储名称',
                            `bucket` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '桶路径',
                            `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件地址',
                            `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
                            `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
                            `size` bigint DEFAULT NULL COMMENT '文件大小',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of aigc_oss
-- ----------------------------
BEGIN;
INSERT INTO `aigc_oss` (`id`, `user_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `create_time`) VALUES ('1c6883ff0f5f005f5c72700fd4423ad3', '1', 'story-about-happy-carrot', '4a4d7dffe42ccc67ee0a4560901e83db.pdf', '/20240606', 'http://127.0.0.1/20240606/4a4d7dffe42ccc67ee0a4560901e83db.pdf', '/opt/homebrew/var/www/20240606/4a4d7dffe42ccc67ee0a4560901e83db.pdf', 'pdf', 35359, '2024-06-12 12:27:30');
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
                             `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of aigc_user
-- ----------------------------
BEGIN;
INSERT INTO `aigc_user` (`id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `chat_limit`, `status`, `create_time`) VALUES ('1', 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', '测试1号', '18278982390', 'langchat@outlook.com', '/default.png', 0, 1, '2024-06-21 23:20:40');
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
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373337, 1, 'administrator', '删除用户', '/upms/user/4', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:31:12');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373338, 1, 'administrator', '删除用户', '/upms/user/5', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:31:16');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373339, 1, 'administrator', '删除用户', '/upms/user/6', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:31:18');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373340, 1, 'administrator', '删除用户', '/upms/user/2', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:31:23');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373341, 1, 'administrator', '修改用户', '/upms/user', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:32:51');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373342, 1, 'administrator', '删除角色', '/upms/role/6', 0, 'cn.tycoding.langchat.upms.controller.SysRoleController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:32:58');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373343, 1, 'administrator', '删除角色', '/upms/role/3', 0, 'cn.tycoding.langchat.upms.controller.SysRoleController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:33:03');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373344, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:34:03');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373345, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:34:14');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373346, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:34:26');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373347, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:35:27');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373348, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:35:37');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373349, 1, 'administrator', '修改部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.update()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:35:43');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373350, 1, 'administrator', '新增部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.add()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:35:50');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373351, 1, 'administrator', '新增部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.add()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:36:01');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373352, 1, 'administrator', '新增部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.add()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:36:35');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373353, 1, 'administrator', '新增部门', '/upms/dept', 0, 'cn.tycoding.langchat.upms.controller.SysDeptController.add()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:36:44');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373354, 1, 'administrator', '删除角色', '/upms/role/1', 0, 'cn.tycoding.langchat.upms.controller.SysRoleController.delete()', '', '124.127.73.88', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 10:37:30');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373355, 1, 'langchat', '修改角色', '/upms/role', 0, 'cn.tycoding.langchat.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 13:12:34');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373356, 1, 'langchat', '修改角色', '/upms/role', 0, 'cn.tycoding.langchat.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 13:15:43');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373357, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:43:03');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373358, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:43:12');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373359, 1, 'administrator', '删除菜单', '/upms/menu/211', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:43:25');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373360, 1, 'administrator', '删除菜单', '/upms/menu/231', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:43:29');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373361, 1, 'administrator', '删除菜单', '/upms/menu/232', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:44:04');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373362, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:44:24');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373363, 1, 'administrator', '删除菜单', '/upms/menu/111', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:45:38');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373364, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:46:44');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373365, 1, 'administrator', '删除菜单', '/upms/menu/121', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:47:07');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373366, 1, 'administrator', '删除菜单', '/upms/menu/131', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:47:17');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373367, 1, 'administrator', '删除菜单', '/upms/menu/141', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:47:32');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373368, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:48:34');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373369, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:48:57');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373370, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:49:05');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373371, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:49:21');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373372, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:52:53');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373373, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:53:11');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373374, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:53:27');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373375, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:54:04');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373376, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:54:25');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373377, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:54:39');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373378, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:54:48');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373379, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:55:25');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373380, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:55:42');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373381, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:56:00');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373382, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:56:45');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373383, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:57:03');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373384, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:57:38');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373385, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 15:58:57');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373386, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:01:37');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373387, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:01:53');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373388, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:02:09');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373389, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:02:58');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373390, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:03:15');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373391, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:03:31');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373392, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:04:48');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373393, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:05:25');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373394, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:11:01');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373395, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:11:38');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373396, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:12:45');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373397, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:13:07');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373398, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:13:26');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373399, 1, 'administrator', '新增菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:13:51');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373400, 1, 'administrator', '新增用户', '/upms/user', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:21:23');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373401, 1, 'administrator', '新增用户', '/upms/user', 0, 'cn.tycoding.langchat.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', '2024-06-28 16:21:36');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=1788942973669572647 DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (100, '权限管理', 0, '/upms', 'upms', 'menu', 3, 'KeyOutline', 'LAYOUT', 0, 0, 1, 1);
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
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (200, '系统管理', 0, '/system', '', 'menu', 4, 'SettingsOutline', 'LAYOUT', 0, 0, 1, 1);
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
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572614, 'AIGC流程', 0, '/aigc/workflow', 'aigc:flow', 'menu', 0, 'ConstructOutline', 'LAYOUT', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1788942973669572615, '流程编排', 1788942973669572614, 'flow', 'aigc:flow', 'menu', 1, '', '/flow/index', 0, 0, 0, 1);
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
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作用户ID',
                           `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
                           `target_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件存储名称',
                           `bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '桶路径',
                           `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件地址',
                           `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
                           `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
                           `size` bigint DEFAULT NULL COMMENT '文件大小',
                           `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件描述',
                           `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件来源渠道 input/output',
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
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (4, '测试角色', 'test', '这是测试的');
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
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1, 'administrator', 'G2XltQvvMCezT1DNkjPyTA==', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
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

SET FOREIGN_KEY_CHECKS = 1;
