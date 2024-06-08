/*
 Navicat Premium Data Transfer

 Source Server         : 39.106.32.39
 Source Server Type    : MySQL
 Source Server Version : 80100 (8.1.0)
 Source Host           : 39.106.32.39:3306
 Source Schema         : langchat

 Target Server Type    : MySQL
 Target Server Version : 80100 (8.1.0)
 File Encoding         : 65001

 Date: 08/06/2024 17:18:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1a8daf1596ea6717da49a90ea8a01c0c', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-05 22:46:23');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('1e147511ababfacb176c94af9137a1a0', '1', '1', NULL, 'ab8b8147-134e-4ea7-ad82-e5fd8759a58e', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天的实时事件可以从多个方面来查看和管理，尤其是利用各种现代工具和平台。以下是一些相关的实时事件管理工具和方法：\n\n### 1. Microsoft Teams 实时事件\nMicrosoft Teams 实时事件是一个强大的工具，允许用户安排和管理大型在线活动。通过Teams实时事件，用户可以：\n- 安排2024年9月之后的活动。\n- 使用视频进行演示，这对于演示者来说非常方便。\n- 设置测试事件以确保所有参与者熟悉流程。\n\n**建议**：提前计划事件，并设置测试事件以确保顺利进行。\n\n### 2. Google Analytics 实时报告\nGoogle Analytics 提供了一个实时报告功能，允许用户查看网站上实时发生的事件。这对于监测刚发布的内容是否受欢迎非常有用。通过“实时事件”报告，用户可以：\n- 查看实时触发的事件。\n- 监测网站即时的用户交互情况，帮助优化内容和用户体验。\n\n**建议**：访问[Google Analytics Learn](https://google.com/analytics/learn)获取更多资源和帮助。\n\n### 3. 使用Yammer实时事件\nYammer也是一个可以用来进行实时事件管理的工具，特别适用于企业内部的沟通和协作。通过Yammer，用户可以：\n- 进行团队内部的实时交流。\n- 共享和讨论实时事件及其影响。\n\n**建议**：在安排事件时，确保所有团队成员都了解并熟悉Yammer的使用方法。\n\n### 总结\n无论是通过Microsoft Teams还是Google Analytics，还是Yammer，实时事件管理工具都提供了丰富的功能来帮助用户更好地安排和监控他们的活动。提前计划、测试和监控都是确保事件成功的关键步骤。', 531, 300, '2024-06-08 10:48:42');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('294f5d2ae0d089a39906aceade6585aa', '1', '1', NULL, '0f4bba4d-70b3-4b96-9e02-8336d2cecf1c', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '北京时间今天几号', 0, 0, '2024-06-08 10:48:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2c1c0397a83dad11ad6094113fa35c4f', '1', '1', NULL, '69c9dce8-ff9d-40c1-b5b1-096976b2e581', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮忙的吗？', 17, 10, '2024-06-08 10:35:02');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('2d7d914fdbff4afea89bea6e40f4ab3b', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-05 22:46:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('320fa79f0938de60923ac20e3c8dd060', '1', '1', NULL, '3be30972-63a1-4131-bb6e-ee603e32b38f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮忙的吗？', 17, 10, '2024-06-08 10:20:34');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('334095f2d2d70a2b2fadffe2c49d378a', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个人工智能助手，可以回答你的问题、提供信息或帮助你解决问题。有什么我可以帮你的吗？', 50, 103, '2024-06-05 22:46:33');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('38cd04e28b4406d033a08092e4cff8f2', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是基于OpenAI的GPT-4模型。有什么我可以帮你的吗？', 30, 83, '2024-06-04 22:45:21');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('3cbc7d601d5d1c5c3e551605db37e5b6', '1', '052b973d00d4116237406faec67e4b3b', '052b973d00d4116237406faec67e4b3b', 'a71a79b4-6383-44f0-83d0-e19d08e40347', 'administrator', 'unknown', 'assistant', 'gpt-4o', '要查询数据库中表 `a` 的所有数据，您需要使用 SQL 语句。具体来说，如果您使用的是 MySQL、PostgreSQL、SQLite 或者其他类似的关系型数据库，您可以执行以下查询：\n\n```sql\nSELECT * FROM a;\n```\n\n这条 SQL 语句会从表 `a` 中检索所有行和所有列的数据。\n\n如果您使用的是某种编程语言来与数据库交互，例如 Python 或 Java，您还需要使用相应的数据库连接库来执行这条 SQL 语句。以下是一个使用 Python 和 `sqlite3` 库的例子：\n\n```python\nimport sqlite3\n\n# 连接到 SQLite 数据库（如果数据库文件不存在，会自动创建）\nconn = sqlite3.connect(\'your_database.db\')\n\n# 创建一个游标对象\ncur = conn.cursor()\n\n# 执行查询语句\ncur.execute(\"SELECT * FROM a\")\n\n# 获取所有结果\nrows = cur.fetchall()\n\n# 打印结果\nfor row in rows:\n    print(row)\n\n# 关闭游标和连接\ncur.close()\nconn.close()\n```\n\n请根据您的实际情况替换数据库类型和连接方法。如果您使用的是其他编程语言或者数据库系统，方法可能会有所不同。', 299, 12, '2024-06-08 15:43:42');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('436e2bf0385db00c0d8b0de6a228da1e', '1', '1', NULL, '3e9f895b-279e-4a5f-9fc6-4b910a36eb21', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:19:34');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('49e5c381f6812cdc504e8262d4ae409b', '1', '1', NULL, '64c567b6-ee3d-4d53-a222-1aaddf9863aa', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天是6月7号。', 8, 292, '2024-06-08 10:47:47');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('4a3564032dabd203adb006df50344980', '1', '052b973d00d4116237406faec67e4b3b', '052b973d00d4116237406faec67e4b3b', 'a71a79b4-6383-44f0-83d0-e19d08e40347', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '查询a表所有数据', 0, 0, '2024-06-08 15:43:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('5688b1a71140627c5c74f31aea16640f', '1', '1', NULL, '69c9dce8-ff9d-40c1-b5b1-096976b2e581', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:35:01');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('645823d7f6d73c69f152f9ed4d380129', '1', '1', NULL, '3036cb92-2415-4951-93ba-59d9c56bcc1a', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 77, '2024-06-05 22:46:25');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('65f9a478e1fd816954d763d38c5da587', '1', '1', NULL, '21baaf71-fdf9-4865-99c1-1879aa2db054', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:47:37');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('7290daf364e1cba333ec98db7f06c874', '1', '1', NULL, '21baaf71-fdf9-4865-99c1-1879aa2db054', 'administrator', 'unknown', 'assistant', 'gpt-4o', '在的，有什么我可以帮您的吗？', 16, 10, '2024-06-08 10:47:38');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('76c7cf1832fe41831cf03a1205ec2421', '1', '1', NULL, 'f8f50651-8402-486e-b5d5-5c68673aad04', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天发生了什么', 0, 0, '2024-06-08 10:48:50');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('78ec3240d1824799b0359d13a9ce5306', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你的模型版本', 0, 0, '2024-06-04 22:45:09');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('8abdaed25609a5943be1a8016620badc', '1', '1', NULL, '9c1939a6-40a1-4f81-b54e-6a6157da34ab', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '模型版本是多少', 0, 0, '2024-06-04 22:45:20');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9bd67f5a56c3998126b86f1e23073b47', '1', '1', NULL, '64c567b6-ee3d-4d53-a222-1aaddf9863aa', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天几号', 0, 0, '2024-06-08 10:47:46');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9d763c0ae26fb522169c5336720027fa', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '4f1c16f3-439c-41aa-8e0f-cb14d22ead7f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-08 15:41:53');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('9ef8b2e0e2689e98612047ce848b97ce', '1', '1', NULL, 'b0c30dee-8c6b-42ed-a0d0-0cab7851c907', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！我是一个由OpenAI开发的AI助手，使用的是ChatGPT模型。请问有什么我可以帮助你的吗？', 43, 55, '2024-06-04 22:45:11');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('aec6d1f0d6b10f0f5a42c2c3abddd56c', '1', '1', NULL, 'f8f50651-8402-486e-b5d5-5c68673aad04', 'administrator', 'unknown', 'assistant', 'gpt-4o', '今天是6月8日，历史上在这一天发生了以下重要事件：\n\n1. 1937年，已经退位的前英国国王爱德华八世与美国名流沃利斯·辛普森在法国图尔举行婚礼。这一事件在当时引起了广泛关注，因为爱德华八世为了与辛普森结婚而放弃了王位。\n\n2. 1998年，德国一部从慕尼黑驶往汉堡的城际快车在下萨克森发生出轨事故，造成101人死亡。这次事故是德国铁路史上最严重的一起事故之一。\n\n这些历史事件展示了从政治变动到交通事故等不同领域的重要事件，反映了历史的多样性和复杂性。', 267, 343, '2024-06-08 10:48:54');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('afa897911ec41cae5adab67595daf64d', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '4f1c16f3-439c-41aa-8e0f-cb14d22ead7f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮忙的吗？', 17, 9, '2024-06-08 15:41:59');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bab1c3a90064b5ed0ac6dc2c70f1f31a', '1', '1', NULL, '3be30972-63a1-4131-bb6e-ee603e32b38f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '在吗', 0, 0, '2024-06-08 10:20:33');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('bb0d6cae80a5127d26ec7ffc5a2b2a27', '1', '1', NULL, 'a764fd3b-2576-4bac-bd9c-a568abbb60a9', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-05 22:46:00');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c1d09111217524e9812c1f945c52dcfa', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"您好！我在的，有什么可以帮您的吗？\" }] }', 37, 28, '2024-06-04 22:41:32');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c398e5207eeecf0518f9210f6a1e2ed9', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '2f5d1173-5ce0-4da4-aadb-d511e546c57f', 'administrator', 'unknown', 'assistant', 'gpt-4o', '你好！有什么我可以帮你的吗？', 16, 9, '2024-06-08 15:42:12');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('c52c0e4ea44399cdd4f59ea6e5ab66bd', '1', '1', NULL, 'ab8b8147-134e-4ea7-ad82-e5fd8759a58e', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '今天有什么实时事件', 0, 0, '2024-06-08 10:48:34');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('d20f0831e09a417bdad9e6e9466d6725', '1', '1', NULL, '0f4bba4d-70b3-4b96-9e02-8336d2cecf1c', 'administrator', 'unknown', 'assistant', 'gpt-4o', '对不起，我无法实时访问互联网来获取当前的日期和时间。然而，您可以通过查看手机、电脑或其他设备上的时间来确定今天的日期。北京的时间是根据中国标准时间（CST），即UTC+8。', 73, 216, '2024-06-08 10:48:04');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('d8efbb0561cc7e7cfb65b6b2cd243d9a', '1', '89db9f429e2f84ba87e2820ac2eac599', NULL, '2f5d1173-5ce0-4da4-aadb-d511e546c57f', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你好', 0, 0, '2024-06-08 15:42:10');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('dbf806441a91686258c0f9bfcc9d7c50', '1', '1', NULL, '5537b43f-0f1a-4187-b261-7a47a2c1fe4b', 'administrator', '127.0.0.1', 'user', 'gpt-4o', '你是谁', 0, 0, '2024-06-05 22:46:31');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fa423a797e0c119daedf6dee419c0035', '1', '1', NULL, '1f370c34-7c30-44f4-9a8d-62759fdb8147', 'administrator', 'unknown', 'assistant', 'gpt-4', 'AssistantMessage { name = null contents = [TextContent { text = \"在的，有什么可以帮助您的吗?\" }] }', 34, 53, '2024-06-05 22:46:17');
INSERT INTO `aigc_message` (`id`, `user_id`, `conversation_id`, `prompt_id`, `chat_id`, `username`, `ip`, `role`, `model`, `message`, `tokens`, `prompt_tokens`, `create_time`) VALUES ('fe4ae1e276684bf7812c34ca2d6bdaa7', '1', '1', NULL, 'ffb726ef-f992-4671-8632-d0c398e7ca56', 'administrator', '127.0.0.1', 'user', 'gpt-4', '在吗', 0, 0, '2024-06-04 22:41:26');
COMMIT;

-- ----------------------------
-- Table structure for aigc_oss
-- ----------------------------
DROP TABLE IF EXISTS `aigc_oss`;
CREATE TABLE `aigc_oss` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=1788943211071373325 DEFAULT CHARSET=utf8mb3 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373321, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36', '2024-06-08 17:09:25');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373322, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36', '2024-06-08 17:09:31');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373323, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36', '2024-06-08 17:09:37');
INSERT INTO `sys_log` (`id`, `type`, `username`, `operation`, `url`, `time`, `method`, `params`, `ip`, `user_agent`, `create_time`) VALUES (1788943211071373324, 1, 'administrator', '修改菜单', '/upms/menu', 0, 'cn.tycoding.langchat.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36', '2024-06-08 17:09:56');
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
) ENGINE=InnoDB AUTO_INCREMENT=1788942973669572611 DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (100, '权限管理', 0, '/upms', 'upms', 'menu', 3, 'KeyOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (110, '用户管理', 100, 'user', '', 'menu', 1, NULL, '/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (111, '用户查看', 110, NULL, 'upms:user:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (112, '用户新增', 110, NULL, 'upms:user:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (113, '用户修改', 110, NULL, 'upms:user:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (114, '重置密码', 110, NULL, 'upms:user:reset', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (115, '用户删除', 110, NULL, 'upms:user:delete', 'button', 5, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (120, '角色管理', 100, 'role', '', 'menu', 2, NULL, '/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (121, '角色查看', 120, NULL, 'upms:role:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (122, '角色新增', 120, NULL, 'upms:role:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (123, '角色修改', 120, NULL, 'upms:role:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (124, '角色删除', 120, NULL, 'upms:role:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (130, '部门管理', 100, 'dept', '', 'menu', 3, NULL, '/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (131, '部门查看', 130, NULL, 'upms:dept:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (132, '部门新增', 130, NULL, 'upms:dept:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (133, '部门修改', 130, NULL, 'upms:dept:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (134, '部门删除', 130, NULL, 'upms:dept:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (140, '菜单管理', 100, 'menu', '', 'menu', 4, NULL, '/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (141, '菜单查看', 140, NULL, 'upms:menu:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (142, '菜单新增', 140, NULL, 'upms:menu:add', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (143, '菜单修改', 140, NULL, 'upms:menu:update', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (144, '菜单删除', 140, NULL, 'upms:menu:delete', 'button', 4, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (200, '系统管理', 0, '/system', '', 'menu', 4, 'SettingsOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (210, '系统日志', 200, 'log', '', 'menu', 1, NULL, '/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (211, '日志查看', 210, NULL, 'system:log:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (212, '日志删除', 210, NULL, 'system:log:delete', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (230, '令牌管理', 200, 'token', '', 'menu', 2, NULL, '/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (231, '令牌查看', 230, NULL, 'system:token:view', 'button', 1, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (232, '令牌详情', 230, NULL, 'system:token:info', 'button', 2, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (233, '令牌删除', 230, NULL, 'system:token:delete', 'button', 3, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (300, 'AIGC平台', 0, '/aigc', 'app:view', 'menu', 1, 'CubeOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (310, '知识库管理', 300, 'knowledge', 'knowledge:view', 'menu', 2, 'alert', '/aigc/knowledge/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (320, '对话数据', 300, 'message', 'message:view', 'menu', 4, 'alert', '/aigc/message/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (330, '提示词管理', 300, 'prompt', 'prompt:view', 'menu', 1, '', '/aigc/prompt/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (340, '账单统计', 300, 'statistics', 'statistics:view', 'menu', 5, '', '/aigc/statistics/index', 0, 0, 0, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (350, '平台用户', 300, 'user', 'aigc:user:view', 'menu', 3, '', '/aigc/user/index', 0, 0, 0, 1);
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
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
