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

 Date: 23/04/2024 14:09:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aigc_conversation
-- ----------------------------
DROP TABLE IF EXISTS `aigc_conversation`;
CREATE TABLE `aigc_conversation` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
  `prompt_id` int DEFAULT NULL COMMENT '提示词ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_conversation
-- ----------------------------
BEGIN;
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('3d70f1d465d867c516ae01fbf9634ccb', NULL, NULL, 'New Chat3', '2024-03-02 00:10:47');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('58c5daf0e818eb4e65cd2f62ecc165f7', NULL, NULL, 'New Chat7', '2024-03-02 09:15:07');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('65807b2a1917f3e151b19adb81527f09', NULL, NULL, 'New Chat', '2024-03-02 00:07:21');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('75bca00a40bf5b1e21b1db70dd621f47', NULL, NULL, 'New Chat2', '2024-03-02 00:09:01');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('a2ee0e298f3563dc7fccda7f4f8ba042', NULL, NULL, 'New Chat4', '2024-03-02 09:12:24');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('dc52e4731194ee8fe530374f5e1fbaa6', NULL, NULL, 'New Chat1', '2024-03-02 00:08:12');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('e2f8172420231d200ce36055d70940f9', NULL, NULL, 'New Chat5', '2024-03-02 09:12:38');
INSERT INTO `aigc_conversation` (`id`, `user_id`, `prompt_id`, `title`, `create_time`) VALUES ('f7a9be608f036e380b2f84537e0cb7af', NULL, NULL, 'New Chat6', '2024-03-02 09:13:08');
COMMIT;

-- ----------------------------
-- Table structure for aigc_kb
-- ----------------------------
DROP TABLE IF EXISTS `aigc_kb`;
CREATE TABLE `aigc_kb` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_kb
-- ----------------------------
BEGIN;
INSERT INTO `aigc_kb` (`id`, `name`, `des`, `create_time`) VALUES ('8', '测试', '啛啛喳喳', '1694423077482');
INSERT INTO `aigc_kb` (`id`, `name`, `des`, `create_time`) VALUES ('f228b6c9-bce2-4fd0-8f28-fbc3b893e36e', '555', '2222', NULL);
COMMIT;

-- ----------------------------
-- Table structure for aigc_kb_doc
-- ----------------------------
DROP TABLE IF EXISTS `aigc_kb_doc`;
CREATE TABLE `aigc_kb_doc` (
  `id` varchar(50) NOT NULL,
  `kb_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文档内容',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文档描述',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `kb_id` (`kb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_kb_doc
-- ----------------------------
BEGIN;
INSERT INTO `aigc_kb_doc` (`id`, `kb_id`, `content`, `des`, `create_time`) VALUES ('10', '6365df7d93bbe2b377c8534d4276f7df', '2323rtrthrthrrthht', '111', '1694423077482');
INSERT INTO `aigc_kb_doc` (`id`, `kb_id`, `content`, `des`, `create_time`) VALUES ('11', NULL, 'Once upon a time in the town of VeggieVille, there lived a cheerful carrot named Charlie.\nCharlie was a radiant carrot, always beaming with joy and positivity.\nHis vibrant orange skin and lush green top were a sight to behold, but it was his infectious laughter and warm personality that really set him apart.\n\nCharlie had a diverse group of friends, each a vegetable with their own unique characteristics.\nThere was Bella the blushing beetroot, always ready with a riddle or two; Timmy the timid tomato, a gentle soul with a heart of gold; and Percy the prankster potato, whose jokes always brought a smile to everyone\'s faces.\nDespite their differences, they shared a close bond, their friendship as robust as their natural goodness.\n\nTheir lives were filled with delightful adventures, from playing hide-and-seek amidst the leafy lettuce to swimming in the dewy droplets that pooled on the cabbage leaves.\nTheir favorite place, though, was the sunlit corner of the vegetable patch, where they would bask in the warmth of the sun, share stories, and have hearty laughs.\n\nOne day, a bunch of pesky caterpillars invaded VeggieVille.\nThe vegetables were terrified, fearing they would be nibbled to nothingness.\nBut Charlie, with his usual sunny disposition, had an idea.\nHe proposed they host a grand feast for the caterpillars, with the juiciest leaves from the outskirts of the town.\nCharlie\'s optimism was contagious, and his friends eagerly joined in to prepare the feast.\n\nWhen the caterpillars arrived, they were pleasantly surprised.\nThey enjoyed the feast and were so impressed with the vegetables\' hospitality that they promised not to trouble VeggieVille again.\nIn return, they agreed to help pollinate the flowers, contributing to a more lush and vibrant VeggieVille.\n\nCharlie\'s idea had saved the day, but he humbly attributed the success to their teamwork and friendship.\nThey celebrated their victory with a grand party, filled with laughter, dance, and merry games.\nThat night, under the twinkling stars, they made a pact to always stand by each other, come what may.\n\nFrom then on, the story of the happy carrot and his friends spread far and wide, a tale of friendship, unity, and positivity.\nCharlie, Bella, Timmy, and Percy continued to live their joyful lives, their laughter echoing through VeggieVille.\nAnd so, the tale of the happy carrot and his friends serves as a reminder that no matter the challenge, with optimism, teamwork, and a bit of creativity, anything is possible.', NULL, '1694423077482');
INSERT INTO `aigc_kb_doc` (`id`, `kb_id`, `content`, `des`, `create_time`) VALUES ('9', NULL, '1111222', '111', '1694423077482');
COMMIT;

-- ----------------------------
-- Table structure for aigc_kb_file
-- ----------------------------
DROP TABLE IF EXISTS `aigc_kb_file`;
CREATE TABLE `aigc_kb_file` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `kb_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '知识库ID',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件名称',
  `target_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存储名称',
  `bucket` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件目录',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件地址',
  `size` int DEFAULT NULL COMMENT '文件大小',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件来源',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件描述',
  `is_embed` tinyint(1) DEFAULT NULL COMMENT '是否Embedding',
  `create_time` varchar(50) DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_kb_file
-- ----------------------------
BEGIN;
INSERT INTO `aigc_kb_file` (`id`, `kb_id`, `file_name`, `target_name`, `bucket`, `type`, `url`, `size`, `source`, `des`, `is_embed`, `create_time`) VALUES ('1', '6365df7d93bbe2b377c8534d4276f7df', '问答对.txt', '71a7e1864bfd53889bb5499d1b384a92.txt', '/20230831', 'txt', 'http://127.0.0.1:8090/upload/20230831/71a7e1864bfd53889bb5499d1b384a92.txt', 757737, NULL, '问答对.txt', 1, '1694423077482');
COMMIT;

-- ----------------------------
-- Table structure for aigc_message
-- ----------------------------
DROP TABLE IF EXISTS `aigc_message`;
CREATE TABLE `aigc_message` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `chat_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '消息的ID',
  `conversation_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会话ID',
  `prompt_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '应用ID',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色，user和assistant',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型名称',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '消息内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `conversation_id` (`conversation_id`),
  KEY `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_message
-- ----------------------------
BEGIN;
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('011993c763a881255ca3349867201bf2', '5c6252db-2028-4138-a293-35bca2c368e1', '65807b2a1917f3e151b19adb81527f09', NULL, 'user', NULL, '在吗', '2024-03-02 00:07:21');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('013b76aa987d2bc9c80b8cfab5695393', 'fa8be8dd-e299-4365-851d-ff9532584ff8', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:08:53');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('027f641cf5ca2269d5c1116cb1033021', 'f94d8039-2015-4a15-967a-00e62fb71baf', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 15:16:07');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('028a6e0f8d6ba50b322c57f3dabd738e', '76ccbc9e-54fc-4fd2-961f-33ee9bc8d789', 'a2ee0e298f3563dc7fccda7f4f8ba042', NULL, 'user', NULL, '今天是几号', '2024-03-02 09:12:24');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('034e691331e4cb0d142403ba73650e5f', '774ca393-59ad-4ac2-b58a-f73eb224cc87', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'nihao', '2024-04-01 14:10:34');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('0668fbaba90af0309a267f1f6b9d8067', '62529c00-173f-422f-a470-8ed7c69271d3', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'z', '2024-04-01 12:54:00');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('0823cc199313523a5ad6423b5b6e729d', 'b41370c4-bed3-4c6d-8d37-8f4145a2f3a6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在', '2024-04-01 11:12:20');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('0b39cbc6c68cb5377066de24f57f1e85', '4a270220-4c3e-4c13-8f58-dcfce6e7ce72', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 12:50:23');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('0c1475afaccaa7ddaef4fde9dac9e26e', '2e07957a-058f-4f91-a147-e879eaaa8a2e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 14:08:46');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('0f700476ade74f5a0fde5164a8872f33', '1d35999f-6fd0-4565-b695-ff87fa1c0a61', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你好', '2024-04-01 14:01:27');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('100f177a1ce67d75517449549581ff06', '2812c3b4-ef40-4fd3-b823-4fe4828e62c6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '小猫图片', '2024-04-03 14:44:00');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('10ebbbdf0bc559029544b91756e6d570', '902a6c5f-0625-4ff8-ae44-1658615fb195', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'hi', '2024-04-01 13:22:08');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1178b0d3927efeac3c9f5a6bc9ce4a1e', '68078459-eb09-4452-b13b-6855e88f9414', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '12', '2024-04-01 13:01:59');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('15ac63c2a10170c63d821d865cddd945', '52981628-3f34-496e-8b5d-9c0f0fc3508b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'zai', '2024-04-01 12:47:23');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('162abd6fe5928cedddd8c9d32b468a27', '4fcedae0-d4fb-42ce-9512-3aab50cd3056', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', 'Sure! Here is a simple \"Hello World\" program written in JavaScript:\n\n```javascript\nconsole.log(\"Hello, World!\");\n```\n\nYou can run this code in a JavaScript environment, such as a browser console or a Node.js runtime, to see \"Hello, World!\" printed to the console. If you have any specific requirements or need further assistance, feel free to let me know!', '2024-04-01 14:12:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('16314a27624f5410c67e4877d6b48744', '76ccbc9e-54fc-4fd2-961f-33ee9bc8d789', 'a2ee0e298f3563dc7fccda7f4f8ba042', NULL, 'assistant', NULL, '我无法访问外部时间，所以我无法回答今天是几号的问题。', '2024-03-02 09:12:29');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('164e91399ad0ad61ea055cb4f1013f43', '2c5ac655-8d6e-46c3-9bc9-3b070c6098f0', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 14:14:14');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('16828dbe1c85a8a860c7066dd7f7caa6', '4f32afab-c230-46c1-a91f-c2c880cc3402', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-03-08 23:43:37');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('16a5a052eeb32ec1b3182e0e6be665be', '729d96f8-02ae-4159-b758-191cfb0e386a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '[TextContent { text = \"在不在呀\" }]', '2024-04-01 13:52:44');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1c9de5fa4590e4157b2eff20a3e2c897', '30519588-a3db-4438-9064-97cd6b20c603', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好！你在和阿妈在一起吗？需要我为你做些什么呢？', '2024-04-04 17:16:29');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1d67d07314fa2a464d1b06970dfbdd0a', 'f1bea4e9-0c55-47b9-8a21-9cb976be45b3', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:37:16');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1db4c9760b4c85d34097d9951c39168d', '3bedb171-8d94-41ea-9d8e-837557dcd0bf', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 10:05:45');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1dc1cabd34518a3a42000393b30894f3', 'b6cc1e66-abdf-4fdd-bd8a-e446dfa0796c', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 14:04:28');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('1efe091c13dc380117ba2997812f6b17', '7b47d5f2-03b6-4178-8768-743037426f13', '3d70f1d465d867c516ae01fbf9634ccb', NULL, 'assistant', NULL, '我是一个语言模型，无法有现实生活或感受。\n\n我是一个人工智能系统，可以根据输入的语言和信息提供不同的答案或建议。', '2024-03-02 00:10:49');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('2292ac4d062e9d37f73493e7068c534e', '1f0a89a1-5613-4fb7-bbe1-43ed649138f4', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:06:06');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('22b810ad6500e6a7995f2bc5eeaf8850', '35bfc57c-a2fb-403e-b0bf-e53d121a93ce', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 13:51:54');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('22d6922e3a9f06a1dddc4c711443a1ea', '68446e34-73ad-48d8-82b4-dccfe898f3ed', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-02 17:46:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('23608dbdbb8fc7ae9b12eaf6816170ea', 'd9987af9-172e-44c5-b064-5b9f592e3b20', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在', '2024-04-01 13:04:42');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('24797c36bfff6a157e645e640b321f7c', '62f1321d-b9b3-4f3e-9d97-98236e2a72a1', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 10:04:45');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('259d559e60cfb8284e53454ab59fd0e5', 'b2d9f394-7d12-4608-b628-899d73176be5', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好，请问有什么可以帮助您的吗？', '2024-04-04 17:14:33');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('263ccfddfd9a1742410d2d9ce0e50cd4', '2e07957a-058f-4f91-a147-e879eaaa8a2e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好，请问有什么可以帮助您的吗？', '2024-04-01 14:08:47');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('2655a4151fc593acf25268d8c8e92718', '9160f88c-09b9-4660-ae4b-02eb614342ad', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '您好，请问有什么可以帮助您的吗？', '2024-04-01 13:43:25');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('29482682c40177bddc8b414b2e099ba0', '179cb070-a95b-47ed-8e46-e5cec2b2206b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用c写', '2024-04-01 14:12:35');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('2b1152ede4f399738cb60d209d36258b', '65501032-2335-42be-a17c-66430e3342ab', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'hi', '2024-04-01 13:20:58');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('2ef10bd39ca908250b29f49e3698633d', '9d83c1db-74c9-4914-b691-dc432ba15d7d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '您好，在的。请问有什么可以帮助您的吗？', '2024-04-01 13:52:04');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('339afbf44a1146a37347e8bbd084c640', '8cafaeea-d743-43c9-8540-369d69161a15', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 15:06:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('343833626868bbde3d87b33b17d66a65', '5781eada-55fd-4335-a746-bc0a23b8bab1', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 12:51:51');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('34652221cfcceb359a4400a5da820524', 'd77a6ce9-6f9b-45e2-bd0c-f185c7987aef', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:14:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('354736e29166da507b98084f41863e97', 'c11a3e45-fa3c-4f19-b4b6-e768020ef87f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:50:56');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('35d42a7b19f325f665dc6725f3093dc3', '34253d3f-b71c-4033-9f73-f6fc2d7ca96b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 14:01:23');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('368665ed101e8f7099b6a5e7806dbd06', '30519588-a3db-4438-9064-97cd6b20c603', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在阿妈', '2024-04-04 17:16:27');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('3841a86954fdc00ec262c6876547a62d', 'bec87d7a-e945-40fa-b860-3070c88e2da7', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在', '2024-04-01 14:07:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('39fc6470842f092c3ebfea5976ce72a2', 'b804bd07-b142-4f6b-9d84-ecdb2c1673d9', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '12', '2024-04-01 12:58:24');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('3d383c2514e6c429749e014c42328aac', 'b2d9f394-7d12-4608-b628-899d73176be5', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:14:31');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('4292dd2bfc716c534694b7ba415ca67f', '65daa89e-24ab-4fb4-8fa1-23b6b8561b09', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '```java\npublic class HelloWorld {\n\n    public static void main(String[] args) {\n        System.out.println(\"Hello World!\");\n    }\n}\n``` 到控制台\n        System.out.println(\"Hello, World!\");\n    }\n}\n```', '2024-04-01 14:06:13');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('4785b4bbf57c3f7046e268ca731b97fe', '695a2ec3-9019-4b17-b081-92f527fd4e7a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 15:04:52');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('48d88412a131b2f6b81eddcf0c04507c', '6d7b9754-21ca-4faa-b60f-752b5baf591a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '我是 Gemini，一个大型多模态模型，由 Google 训练。 AI 模型。', '2024-04-01 11:20:31');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('49450226432e81aa3c0facb388431044', '2a4cdb98-0c15-4a38-abec-2d65fda9f239', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用Java写helloworld', '2024-04-01 14:09:05');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('4af5fa6cfc71133718bc706dd99a7c0c', 'f1a92705-7772-4cbf-bd86-067d37cbaf22', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'zaima', '2024-04-01 13:18:09');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('4fb313f1aee14770ea513a77b7c102a6', '4742686c-8966-446e-8f8c-209aa541fd0c', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '使用Java给我写一个Helloworld', '2024-04-01 10:01:12');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('4ff5a3450f496faa75ef662ca6ffa12a', '1d35999f-6fd0-4565-b695-ff87fa1c0a61', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '您好！', '2024-04-01 14:01:28');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('506ee7a0eff776d8310eb79626cf4236', '850abf12-01d6-4a3f-bada-96af4091c3e7', 'e2f8172420231d200ce36055d70940f9', NULL, 'user', NULL, '为什么', '2024-03-02 09:12:38');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('52ab9f125e146cc54a191e508e49eae9', '85ea93a4-da65-4c5b-8526-e8c82341242d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '生成一张图片', '2024-04-02 17:47:18');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('53d55c17f8f40bd0247da475f9458083', 'f91bdc91-198e-4b18-b4db-91ea261e6e4e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'zaima', '2024-04-01 12:46:45');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5607ef870246fbbf20ae0fdc150ed299', 'f62830a5-b575-40e9-b626-52dcd51defd2', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:07:06');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('57763b405708b651c14c564bea7ee14c', '9272e581-8b09-4262-8138-4e0a4265903f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你好', '2024-04-01 13:46:41');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('57aca81aa3a476785e60225e11c5730d', '84d22420-4268-48df-bbb7-5967b49c2809', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '您好，我在。请问有什么可以帮助您的吗？message\": \"success\",\n \"data\": {\n  \"id\": \"222e6a0bb3a047e590e5f0b058b046f3\",\n  \"question\": \"在吗\",\n  \"answer\": \"我在\"\n }\n}', '2024-04-01 14:01:15');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5855e5d57fad01707126088006d05e97', '4fcedae0-d4fb-42ce-9512-3aab50cd3056', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用js写', '2024-04-01 14:12:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5a3036e20a9169a8aae102f8d4f39e2e', '34253d3f-b71c-4033-9f73-f6fc2d7ca96b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '我是Gemini，是Google开发的多模态AI语言模型。', '2024-04-01 14:01:24');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5b425d0e77e33fb035de9552fa6219e4', 'b602729b-2dba-41cd-be08-c855eb557bf1', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'nihao', '2024-04-01 13:55:35');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5b58a9662e9067bc1573dd0458293e92', '7aa1a5e6-c42a-49c1-aa08-3f687053dc8d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 12:48:33');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5d861c666c746f0d486e798747b5847a', 'd83779df-5358-4395-bed6-98c2416d8804', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'zaima ', '2024-04-01 13:18:42');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5d956044503c0c3ec63cae2c861812fb', '8a79358c-0171-4f33-bd66-693827169888', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '在吗', '2024-04-03 14:55:48');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5dc2df94f8e7e0576ef42dc40a8f7209', '179cb070-a95b-47ed-8e46-e5cec2b2206b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', 'Sure! Here is a simple \"Hello World\" program written in C language:\n\n```c\n#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\\n\");\n    return 0;\n}\n```\n\nYou can copy and paste this code into a C compiler, save it as a `.c` file (e.g., `hello.c`), compile it, and then run the executable to see \"Hello, World!\" printed on the screen. Let me know if you need any further assistance!', '2024-04-01 14:12:37');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5ddf6efc2265f919db122884aad0e9da', 'd6e4c749-b49c-4317-8bb8-690723a755e2', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai_image', '生成小猫图片\n\n\n', '2024-04-03 14:31:30');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5e2820a7b94437a2aff3addaaffeb526', '49117da5-8e9f-4809-8179-334797e099ec', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'z', '2024-04-01 13:34:44');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5e6cd3e274fa08aff5a31f6cb68aaf30', '5da96365-4169-473b-93ab-38a2362a2614', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '您刚才没有任何内容。确定您问的是什么。您能重新表述一下您的问题吗？', '2024-04-01 14:06:45');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('5e76793ddfab7a25a0b25a5de25d8558', 'ecb533e9-508a-4819-b341-6cc618d8be86', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 13:50:45');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('61274cf10423ccb2121bada5bdc20c3e', '774ca393-59ad-4ac2-b58a-f73eb224cc87', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好！有什么可以帮助你的吗？', '2024-04-01 14:10:46');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('612e14bf2546c099adf6ee585a3ad05e', '147de3a0-d83d-4ef8-83aa-8e3ddb496cc0', 'f7a9be608f036e380b2f84537e0cb7af', NULL, 'assistant', NULL, '我無法提供今日日期的資訊，因為我無法訪問外部資料或日期來源。', '2024-03-02 09:13:09');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('62c8febc3d074f22306d5e5b037f3da5', 'c28de4a3-92c0-4999-990c-ca0f1cf7900e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 13:51:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('64039b9e97d50bc17123dde4e7bba6fb', '5da96365-4169-473b-93ab-38a2362a2614', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '我刚才说了什么', '2024-04-01 14:06:44');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('6558bc44e8ee318542ea3b96a163853b', 'd04fcf00-9af7-4839-8271-9e9a0b838535', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:06:44');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('66e8d44930bb783597a6338fc95db614', 'ca1ab9d1-9e7b-478a-9d8c-00f4a69a7fac', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '用c', '2024-04-01 14:06:22');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('690887e8824990d6187e3b57f6dca62c', '5a13ffc8-e8fd-4c6c-8da3-a1e6dc6ee8c4', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '小猫图片', '2024-04-03 15:01:24');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('6959b88e6bbea9f886d93ec54d51e69e', '20bcef1f-144a-4c20-ac86-55ba44c3d130', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'z', '2024-04-01 12:54:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('6b1829cbab21f29ee3b2cfd1e3e8d53d', 'bd5fb029-3aee-4dec-80b6-bea9c13a0f07', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', 'Hello! To write a simple \"Hello World\" program in Java, you can use the following code:\n\n```java\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}\n```\n\nSimply copy and paste this code into a Java IDE or text editor, save it as `HelloWorld.java`, and then compile and run the program. It will print out \"Hello, World!\" in the console. Let me know if you need any further assistance!', '2024-04-01 14:12:23');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('6d577e45f09467ff7ffd4c7019d33a3a', '429a14b6-ff1b-4b5a-9377-d2db44e8916f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 14:24:18');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('6df081030436878db6e695254fae4fe9', '7a6d3414-2f9c-492f-825e-c2e16324e3e3', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-03-31 22:02:05');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('70d2a80295268537b54bc50491a5beda', '6a415457-6644-4c1d-bc96-28b5cfa046e6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在', '2024-04-03 15:20:31');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('75e3afdc500191016b88d69b35b35d76', 'ad109f44-b75e-43ba-9fb4-8c26a6e47384', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai_image', '生成小猫图片\n\n\n', '2024-04-03 14:41:44');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7627a0215834b93654f3eda870ffa7ef', '501412dd-d2f5-43fc-a40d-a635f1e8abc1', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:11:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7649cba9e057639e3fc112dd40fe072c', 'df67a168-f5df-49ea-aafc-fe3e3c84a7b6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好！有什么可以帮助你的吗？', '2024-04-04 17:35:58');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7876749346b5e88d336bc64395210533', '64469482-3324-475a-a795-2a7405e3e2ab', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用中文回答', '2024-04-01 14:13:50');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('79e068d5b20f3c4c056b45a426f401b6', '3341de66-475b-4bbb-bc3f-b2e9a23f2d0a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:52:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7e5898985c8c55530bede7ed8d68e488', 'f5396706-69c0-46f3-b6f0-9a9da1d9a7a0', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:05:41');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7eb086772afaf40afaee3cadf3a82f5a', '1cfba920-2667-447b-bbbd-b7086b9ff769', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-03-31 21:39:06');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('7eb709bb92f77f17796ac7c8b648d56d', 'df67a168-f5df-49ea-aafc-fe3e3c84a7b6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你好', '2024-04-04 17:35:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('8b403ea54aeceab0cc03a5aaef25e604', 'de8f16e9-d8a5-4f2d-98ae-dc3858c73288', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'i', '2024-04-01 11:36:27');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('8f406d58a686b91ec09502704b9db454', '86efcfb0-b592-48a3-8b28-1148f8c1741d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'we', '2024-04-01 13:25:28');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('90f6b833efa4789911a6cba85118bc0b', '6af2c6b7-75f3-4764-bd60-1ba8ded8f371', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你好', '2024-04-01 14:06:53');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('91f898c5b646acad368e46c53468f749', '6370fe1c-0f8e-42df-9bd6-70f3fa96382c', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'fg', '2024-04-01 13:29:13');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9202e8e918f8796377e251a2a967b557', 'ca1ab9d1-9e7b-478a-9d8c-00f4a69a7fac', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '我无法用 C 编写代码，但我可以尝试将您的问题翻译成 C 代码：\n\n```c\nprintf(\"用c\");\n```知识库\nchar *responses[] = {\n    \"你好！\",\n    \"很高兴见到你！\",\n    \"我可以回答你的问题。\",\n    \"我还可以帮你做其他事情，例如设置闹钟或播放音乐。\",\n    \"还有什么我可以帮助你的吗？\"\n};\n\n// 聊天机器人主函数\nint main() {\n    // 获取用户的输入\n    char input[1024];\n    printf(\"请输入您的问题或请求：\");\n    fgets(input, sizeof(input), stdin);\n\n    // 根据用户的输入生成响应\n    int response_index = rand() % (sizeof(responses) / sizeof(char *));\n    printf(\"%s\\n\", responses[response_index]);\n\n    return 0;\n}\n```\n\n此聊天机器人可以回答用户的问题或请求，并提供简单的帮助。您可以根据需要对其进行修改和扩展以满足您的特定需求。', '2024-04-01 14:06:26');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9444f9f7939b6831d9ca3a03368a5239', 'cfbca8ad-26f0-4f0a-99f4-7b77edb21a90', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用php写', '2024-04-01 14:13:58');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('970c55c358f22950272d16ebfdf029de', '7eb31c9e-7ca3-42c7-858e-dac0c2941a70', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-03-31 21:55:01');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('97513d50b24ac5ce34b97e1491544a5e', '429a14b6-ff1b-4b5a-9377-d2db44e8916f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好，请问有什么可以帮助你的吗？', '2024-04-03 14:24:20');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9852a2f81992955a5a6e05cb84d85471', 'd4be4625-53a7-4a56-a36a-f0bd9b8366b0', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 12:44:56');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9c65fa1c6448feb8e62c0710f84fee7e', '4d182743-5ebc-425a-bca8-2aa61e40ac01', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '12', '2024-04-01 13:00:29');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9c791a7db98019cf598085fee1370575', '9160f88c-09b9-4660-ae4b-02eb614342ad', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'hi', '2024-04-01 13:43:23');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9e979ced03551c027cea42ed7e7d64d0', 'f295020e-b86a-4cab-af84-a3b8848f0e48', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你好', '2024-04-04 18:13:42');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('9fb65d15f3fa668d9195d90c2719a7a0', '85ea93a4-da65-4c5b-8526-e8c82341242d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '很抱歉，我无法生成图片。我是一个文本聊天机器人，可以回答您的问题，提供信息和建议。如果您有任何问题或需要帮助，请随时告诉我。我会尽力回答您的问题。', '2024-04-02 17:47:19');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a17d31a3e6563999ccb65bc9172bc1c6', 'b41370c4-bed3-4c6d-8d37-8f4145a2f3a6', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '**空间中**\n\n* 空中\n* 上方\n* 下方\n* 右方\n* 左方\n* 前面\n* 后面\n* 内侧\n* 外侧\n* 边界\n* 表面\n* 中心\n* 深处\n\n**时间上**\n\n* 现在\n* 过去\n* 未来\n* 早晨\n* 中午\n* 下午\n* 晚上\n* 凌晨\n* 黄昏\n* 黎明\n* 瞬间\n* 永恒\n\n**程度**\n\n* 大小\n* 多少\n* 多少\n* 快慢\n* 多少\n\n**方向**\n\n* 上坡下坡\n* 顺风\n* 逆风\n\n**形状**\n\n* 正方形\n* 长方形\n* 圆形\n* 三角形\n\n**质地**\n\n* 光滑的\n* 粗糙的\n* 柔软的\n* 坚硬的\n* 粘稠的\n\n**声音**\n\n* 响亮的\n* 柔和的\n* 高音的\n* 低沉的\n* 刺耳的\n\n**味道**\n\n* 甜的\n* 咸的\n* 酸的\n* 苦的\n\n**温度**\n\n* 温暖的\n* 寒冷的\n* 凉爽的\n* 炎热的\n* 温暖', '2024-04-01 11:12:26');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a4a6bdb8ca0832be998107b0c5e63b79', '9272e581-8b09-4262-8138-4e0a4265903f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好！有什么可以帮助你的吗？如果有任何问题，随时告诉我哦。', '2024-04-01 13:46:43');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a4a81572d93b9261b0ce2a509d018a3e', 'abb5c249-b4bc-4914-a1e3-3d0dc6b2ca20', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你好', '2024-04-03 15:22:59');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a4ea01d9a41996ee09829ee1fab0624d', '8126fd43-5f46-4bae-95dd-e9f104226751', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-04 17:03:38');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a59692aaac0182926f2c8855335d3858', '8e52d403-77e7-4ad2-bb73-68834fda6a75', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:28:18');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a59d37f6a2e81f8fc2f190ae78a95397', '850abf12-01d6-4a3f-bada-96af4091c3e7', 'e2f8172420231d200ce36055d70940f9', NULL, 'assistant', NULL, '\"为什么\"是用于问原因或解释的疑问词语。它通常用于以下目的：\n\n* **请求解释**某件事的原因。\n* **提出疑问**或推敲某件事的可能性。\n* **表达对某件事的看法或观点**。\n\n例如：\n\n* 你为什么要去课堂学习？\n* 这件事为什么要那么重要？\n* 他为什么要偷这笔钱？', '2024-03-02 09:12:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a5f436666573f900af3d074f587ed25a', '78c4a1af-829c-4599-b709-b66aa6c14abc', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-03-31 22:23:37');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a6eac7209fdd82e7c1ec955c173d91e7', '65daa89e-24ab-4fb4-8fa1-23b6b8561b09', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '用Java写helloworld', '2024-04-01 14:06:11');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('a83ac90ba49bd442d27d544bc578e100', 'ecc9c95c-0e4f-46cf-8b57-cce96404c7f1', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '小猫图片', '2024-04-03 14:42:35');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('aa6be98a1865d4204a89d45990dd4c84', '8b43026a-7f27-493e-90eb-133edd535b06', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:49:03');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('acc25e8c3737ee6c61fd91dfa74bdaf1', '729d96f8-02ae-4159-b758-191cfb0e386a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 13:52:40');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('afb57128277f083ac514a3a4ddf34358', '7b47d5f2-03b6-4178-8768-743037426f13', '3d70f1d465d867c516ae01fbf9634ccb', NULL, 'user', NULL, '在吗', '2024-03-02 00:10:47');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b084df22e0493b90bb203f782b21adbb', 'b16c062d-0765-4b21-b8bb-cb95b673f025', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'zai', '2024-04-01 13:39:30');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b1a7cc002a02437aa665693eb053f911', '72bf3dc6-47fa-4aab-957f-52e3f9e59fe7', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 10:00:46');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b22cb457b9fac7974127e609e666a5ce', '6d7b9754-21ca-4faa-b60f-752b5baf591a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你', '2024-04-01 11:20:29');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b472b809691fededfcde157d61d44e10', '22f1d2b0-fe0d-49d9-824f-82a5807ec6c2', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用Java写hellowrold', '2024-04-01 14:10:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b5c400632d4e19210a593d3d351f0a76', 'f659d22b-b502-4d0a-8feb-c8e46aaf1a45', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '我是 Gemini，是 Google 开发的多模态 AI 模型。我的职责是理解和生成人类语言，并为各种自然语言处理任务（例如问答、语言翻译和对话生成）提供信息丰富的回复。', '2024-04-01 10:00:58');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b61877f97e216d4278ff4d33d4cad8e6', '72bf3dc6-47fa-4aab-957f-52e3f9e59fe7', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '在的，有什么需要帮助的吗？？', '2024-04-01 10:00:49');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b6459d03ad5faf5144f8554a25302f26', '6533d2a4-cda6-4128-a45f-2112bf240ecb', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'as', '2024-04-01 12:59:21');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b7e2f540a135b985bdce8b8fb0d92843', '6bebe62d-a036-40c5-aa76-93de53a2a0ae', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 13:52:10');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b8753b67090c1ba5ed27e5521dc27cee', '584e9305-5f19-40dd-99df-515df866c7dd', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'df', '2024-03-08 23:45:14');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('b9ad7eaad2ad034c82650175883555fc', '2a4cdb98-0c15-4a38-abec-2d65fda9f239', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '要用Java写一个helloworld程序，你可以按照以下步骤进行：\n\n1. 打开文本编辑器，如Notepad++或Eclipse等。\n2. 编写Java代码，代码如下：\n\n```java\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}\n```\n\n3. 将代码保存为HelloWorld.java文件。\n4. 打开命令提示符（或终端），进入保存HelloWorld.java文件的目录。\n5. 使用javac命令编译Java文件：javac HelloWorld.java\n6. 编译成功后，会生成HelloWorld.class文件。\n7. 使用java命令运行编译后的程序：java HelloWorld\n8. 如果一切顺利，你会在命令行中看到输出：Hello, World!\n\n希望这个步骤对你有帮助！如果有任何问题，请随时告诉我。', '2024-04-01 14:09:09');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('ba1a0bc95a6a012c0b3917c224bccc64', '4742686c-8966-446e-8f8c-209aa541fd0c', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '```java\npublic class HelloWorld {\n\n    public static void main(String[] args) {\n        // 输出 \"Hello World!\"\n        System.out.println(\"Hello World!\");\n    }\n}\n```', '2024-04-01 10:01:13');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('bdd554e587126261c569096ba68a4590', 'e6c7a9a6-3381-41d1-8361-4db8970a59df', '3d70f1d465d867c516ae01fbf9634ccb', NULL, 'assistant', NULL, 'What can I do for you?  \n\nIs there anything I can help you with today?', '2024-03-02 00:10:54');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('bf2a930dcfff487dbc4d23e65b105fbd', 'f295020e-b86a-4cab-af84-a3b8848f0e48', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好！有什么可以帮助你的吗？', '2024-04-04 18:13:43');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c035756319f2ac1a8255018ec43dec27', '84d22420-4268-48df-bbb7-5967b49c2809', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '在吗', '2024-04-01 14:01:10');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c2ee9805977ea45d45e2d3d9b4a56fb1', 'cfbca8ad-26f0-4f0a-99f4-7b77edb21a90', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '以下是一个用 PHP 编写的简单的 \"Hello World\" 程序：\n\n```php\n<?php\necho \"Hello, World!\";\n?>\n```\n\n您可以将这段代码保存为一个 `.php` 文件（比如 `hello.php`），然后在安装了 PHP 解释器的环境中运行它。它会在浏览器或终端中输出 \"Hello, World!\"。如果您需要更多帮助或有其他问题，请随时告诉我！', '2024-04-01 14:14:01');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c3bb60d22acf920896dac4086970bb0a', '46877502-2378-44fe-ae59-8c1d2c319957', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:55:49');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c3f48f9b0d2b95851f074203bc4f58b7', '9d83c1db-74c9-4914-b691-dc432ba15d7d', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:52:02');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c61e5fe8fca5a94233d6640602bfc474', 'f6fbdcfa-a513-4427-a571-15d838dfcebb', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'hi', '2024-04-01 13:20:10');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c7be99008e7911df52e073b62ae487cd', 'e6a88d5c-5e2a-4af1-9774-b121f6c18584', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'zai', '2024-04-03 15:22:35');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c85d8a0263da9b6149c38f37d240fe9f', 'e2777fa5-d0f3-4b4d-93e5-5b65770f2e58', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'o', '2024-04-01 11:39:41');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c90fb7a7cfc0fbd46e3f11986fe7ff57', '75236704-dc99-46d2-b08f-5f969b8dcc98', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai_image', '生成小猫图片', '2024-04-03 14:24:30');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c967d5ccdc2f9f87dfbace43af7daeb4', '310f3224-648a-4766-a070-cb6ceed2290a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'zaima ', '2024-04-01 13:44:48');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c9a4abae43c39b6d85ce96829fada910', '66f86ccd-0c3f-4197-861a-778612b5cede', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '您好！请问您想要用C语言实现什么样的功能呢？可以告诉我具体的需求，我会尽力帮助您。', '2024-04-01 14:09:20');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('c9f4db5ab2796464bbbc11d982658273', '29e8c763-64e6-4eb8-a47f-f5936d6f42ee', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'sd', '2024-04-01 11:47:05');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('cc20e67ca851ccf4f9ea1c66264880ea', 'f659d22b-b502-4d0a-8feb-c8e46aaf1a45', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 10:00:56');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('cebce5c7e01f6e5f4a5b7027e6c8ad60', '4a1fad00-58c8-4c7a-99f8-98c84be71e8a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '小猫图片\n\n\n', '2024-04-03 14:42:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('cf5410334dc69dce8b9644cca55166bb', '3553392b-02a9-451b-9959-c618125c0358', 'e2f8172420231d200ce36055d70940f9', NULL, 'assistant', NULL, '为了提供更清晰的答案，我需要您先提供您想要知道为什么的内容。请提供您想查询的内容，我会尽力为您解答。', '2024-03-02 09:12:56');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('d1552db1b6ce2be81a3767f5fa1c38a2', 'bd5fb029-3aee-4dec-80b6-bea9c13a0f07', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用Java写hellowrold\n\n\n', '2024-04-01 14:12:18');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('d27c031d80d0ae11800b8474fcbb57a4', 'e6c7a9a6-3381-41d1-8361-4db8970a59df', '3d70f1d465d867c516ae01fbf9634ccb', NULL, 'user', NULL, '啊啊啊', '2024-03-02 00:10:53');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('d7abbcb161435c2dd7167ae2d73b6f69', '64469482-3324-475a-a795-2a7405e3e2ab', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '好的，我会用中文回答您的问题。如果您有任何疑问或需要帮助，请随时告诉我！', '2024-04-01 14:13:51');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('d8af653ecdff61e61592c8f8af3a6576', '147de3a0-d83d-4ef8-83aa-8e3ddb496cc0', 'f7a9be608f036e380b2f84537e0cb7af', NULL, 'user', NULL, '今天是几号2', '2024-03-02 09:13:09');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('d8ec96b3d75ad95b92ebe987833c27d1', '2df62260-f024-41fc-ae47-1be96863a8b5', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', 'o', '2024-04-01 13:34:00');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('da0e41756c1cee794e4bfb16f358836e', '7f57d101-6d5b-455e-9f76-b988c07f3884', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'zai', '2024-04-01 11:34:28');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('daf089e4e82e0b341337253b9ae253f6', 'd2b24e13-cc61-4bce-bd89-fa92cf5dafa7', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini_image', '小猫图片', '2024-04-03 14:56:52');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('dc17217bd9f100b78747c18882dea624', '134e854e-b090-43b1-8fa0-07ea12953d3b', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:51:51');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('de4b10784809c1f2bf5a662cb401be9d', '2c5ac655-8d6e-46c3-9bc9-3b070c6098f0', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '您好！是的，我在这里。有什么可以帮助您的吗？如果您有任何问题或需要帮助，请随时告诉我。我会尽力回答您的问题。', '2024-04-03 14:14:16');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('dee95fcc81443976e5ededc72b00ba81', '53a333cb-c9c7-455a-9a14-a7da25422821', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-01 13:17:11');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e0b561401196e567f831e8d997a89061', '1ebe3549-2dc5-4909-902b-28af06089006', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:52:51');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e0e1b8840ac88c21053c1bf466de08e5', 'b6cc1e66-abdf-4fdd-bd8a-e446dfa0796c', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '我是 Gemini，是 Google 开发的多模态 AI 语言模型。我的职责是尽我所能提供信息并回答问题。', '2024-04-01 14:04:30');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e104373a839d3fa21a54fa0ebec3ce9a', '22f1d2b0-fe0d-49d9-824f-82a5807ec6c2', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '要用Java写一个helloworld程序，你可以参考以下代码：\n\n```java\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}\n```\n\n你可以将上面的代码保存在一个名为HelloWorld.java的文件中，然后使用Java编译器编译它，并在命令行中运行生成的.class文件。这样就可以看到输出结果为\"Hello, World!\"。希望这可以帮助到你。如果有任何疑问，请随时询问。', '2024-04-01 14:11:07');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e2035ea977c97f47b377829a867841ec', '1f46a9f3-502b-4c33-bff1-b573f758a675', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '我是一个AI助手，可以回答你的问题和提供帮助。有什么可以帮助你的吗？', '2024-04-01 14:08:53');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e355f50870fb8ead4f7e6c09b1fb287b', '3553392b-02a9-451b-9959-c618125c0358', 'e2f8172420231d200ce36055d70940f9', NULL, 'user', NULL, '为什么', '2024-03-02 09:12:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e48afe08ec4fe0ec4e1bc04af983fb77', '2850abca-50d6-4028-b926-22222f4f622e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai_image', '生成小猫图片', '2024-04-03 14:40:09');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e6ac5f2a4c2caada5918dcee53f1ec3b', '30145c16-e884-4a51-8896-29b075f8c537', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用go写', '2024-04-01 14:14:12');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e7f2dd883e2872511350fb0691de93d5', '6e6241ab-a35e-418d-8381-8aedea4a4edf', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '在吗', '2024-04-03 15:17:17');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e7f754e631934d655498d2ffe52332f9', '30145c16-e884-4a51-8896-29b075f8c537', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '以下是一个用 Go 语言编写的简单的 \"Hello World\" 程序：\n\n```go\npackage main\n\nimport \"fmt\"\n\nfunc main() {\n    fmt.Println(\"Hello, World!\")\n}\n```\n\n您可以将这段代码保存为一个 `.go` 文件（比如 `hello.go`），然后在安装了 Go 编译器的环境中编译并运行它。它会在终端中输出 \"Hello, World!\"。如果您需要更多帮助或有其他问题，请随时告诉我！', '2024-04-01 14:14:17');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('e9cbb4db7546b9ef3aec43c8c95431b8', 'ec4a9152-32df-422d-9e2f-6be25a5f9017', '75bca00a40bf5b1e21b1db70dd621f47', NULL, 'user', NULL, '啊', '2024-03-02 00:09:01');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('ea15b64b13050dab5231be999864226a', 'aa5e6622-8cf2-4750-8344-76addf799200', 'dc52e4731194ee8fe530374f5e1fbaa6', NULL, 'user', NULL, '啊', '2024-03-02 00:08:12');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('ecee7b28d8026f484695942480831652', '66f86ccd-0c3f-4197-861a-778612b5cede', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '用c些一个', '2024-04-01 14:09:18');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('f12cc1a5a65cf2fea3b0548df5cf97e7', '310f3224-648a-4766-a070-cb6ceed2290a', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', 'Hello! How can I assist you today?', '2024-04-01 13:44:50');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('f350536d35d5007d29d96d45e72c7fb2', '68446e34-73ad-48d8-82b4-dccfe898f3ed', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好，请问有什么可以帮助您的吗？如果有任何问题，请随时告诉我。', '2024-04-02 17:46:57');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('f3cfd1c106be16d657e6c86fa9c4854c', 'ab65fb23-99c9-4732-a539-181c0dcd48e3', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', 'zai', '2024-04-01 11:33:00');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('f4f4219f4e286b6051923c90c77679d4', '877a6f73-881d-44be-b3d5-166904ec254e', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '这些', '2024-04-01 13:09:55');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('f60fadde484ecb6f62e1ffea31c7aa04', '1f46a9f3-502b-4c33-bff1-b573f758a675', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'openai', '你是谁', '2024-04-01 14:08:51');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('fe8db3755689530e92a861f5ca099ee7', 'ecb533e9-508a-4819-b341-6cc618d8be86', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'gemini', '您好，在的。请问有什么可以帮助您的吗？帮您的吗？', '2024-04-01 13:50:49');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('ff26d442bba23354678ed816ac9b6b81', '18340fe5-8833-4523-8b12-dc0f1474d88f', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'user', 'gemini', '你是谁', '2024-04-01 13:51:00');
INSERT INTO `aigc_message` (`id`, `chat_id`, `conversation_id`, `prompt_id`, `role`, `model`, `message`, `create_time`) VALUES ('ffe6761b1946d604daf02415871715bd', 'fa8be8dd-e299-4365-851d-ff9532584ff8', '58c5daf0e818eb4e65cd2f62ecc165f7', NULL, 'assistant', 'openai', '你好，请问有什么可以帮助您的吗？如果有任何问题或者需要帮助，请随时告诉我。', '2024-04-04 17:08:57');
COMMIT;

-- ----------------------------
-- Table structure for aigc_model
-- ----------------------------
DROP TABLE IF EXISTS `aigc_model`;
CREATE TABLE `aigc_model` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型名称',
  `model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型类型',
  `chat_model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '对话模型',
  `temperature` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '温度',
  `token_limit` varchar(255) DEFAULT NULL COMMENT '回复上限',
  `system_prompt` varchar(255) DEFAULT NULL COMMENT '提示词',
  `limit_prompt` varchar(255) DEFAULT NULL COMMENT '限定词',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_model
-- ----------------------------
BEGIN;
INSERT INTO `aigc_model` (`id`, `name`, `model`, `chat_model`, `temperature`, `token_limit`, `system_prompt`, `limit_prompt`, `des`, `create_time`) VALUES ('204e8f35-f19c-491b-ae39-b9885d46edd3', '1111666', 'GPT', 'gpt-3.5-turbo', '2', '2', '2', '2', '2', '1694483626839');
INSERT INTO `aigc_model` (`id`, `name`, `model`, `chat_model`, `temperature`, `token_limit`, `system_prompt`, `limit_prompt`, `des`, `create_time`) VALUES ('4', 'GPT模型', 'GPT', 'gpt-3.5-turbo', NULL, NULL, NULL, NULL, NULL, '1694423077482');
COMMIT;

-- ----------------------------
-- Table structure for aigc_oss
-- ----------------------------
DROP TABLE IF EXISTS `aigc_oss`;
CREATE TABLE `aigc_oss` (
  `id` bigint NOT NULL COMMENT '主键',
  `client_id` bigint DEFAULT NULL COMMENT '客户ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原始文件名称',
  `target_name` varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  `bucket` varchar(255) DEFAULT NULL COMMENT '桶路径',
  `url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件的绝对路径',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `des` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `channel` varchar(10) DEFAULT NULL COMMENT '文件来源渠道 input/output',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of aigc_oss
-- ----------------------------
BEGIN;
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743150837070331905, 1738071220801204225, '公开信息', 'test-1743150837020000258.pdf', '/20240105', 'https://www.gov.cn/zhengce/pdfFile/2023_PDF.pdf', '/Users/tycoding/data/github/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743150837020000258.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 14:02:00', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743287101715140609, 1738071220801204225, 'test', 'test-1743287101631254530.pdf', '/20240105', 'http://127.0.0.1:8100/file/20240105/test-1743287101631254530.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743287101631254530.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 23:03:28', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743291000563998722, 1738071220801204225, 'test', 'test-1743291000501084161.pdf', '/20240105', 'http://127.0.0.1:8100/file/20240105/test-1743291000501084161.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743291000501084161.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 23:18:58', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743291854805270529, 1738071220801204225, 'test', 'test-1743291854742355970.pdf', '/20240105', 'http://127.0.0.1:8100/file/20240105/test-1743291854742355970.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743291854742355970.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 23:22:22', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743296303019876353, 1738071220801204225, 'test', 'test-1743296301308600321.pdf', '/20240105', 'http://127.0.0.1:8100/file/20240105/test-1743296301308600321.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743296301308600321.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 23:40:02', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743296723196878850, 1738071220801204225, 'test', 'test-1743296720659324930.pdf', '/20240105', 'http://127.0.0.1:8100/file/20240105/test-1743296720659324930.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240105/test-1743296720659324930.pdf', 'pdf', 96853, 'test.pdf', '2024-01-05 23:41:42', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743313368443289602, 1738071220801204225, 'test', 'test-1743313368313266178.pdf', '/20240106', 'http://127.0.0.1:8100/file/20240106/test-1743313368313266178.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240106/test-1743313368313266178.pdf', 'pdf', 96853, 'test.pdf', '2024-01-06 00:47:51', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743316028537995265, 1738071220801204225, 'test', 'test-1743316028458303490.pdf', '/20240106', 'http://127.0.0.1:8100/file/20240106/test-1743316028458303490.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240106/test-1743316028458303490.pdf', 'pdf', 96853, 'test.pdf', '2024-01-06 00:58:25', NULL);
INSERT INTO `aigc_oss` (`id`, `client_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `create_time`, `channel`) VALUES (1743316859815493633, 1738071220801204225, 'test', 'test-1743316859723218946.pdf', '/20240106', 'http://127.0.0.1:8100/file/20240106/test-1743316859723218946.pdf', '/Users/tycoding/data/project/langchain-chat/langchat-server/target/classes/static/file/20240106/test-1743316859723218946.pdf', 'pdf', 96853, 'test.pdf', '2024-01-06 01:01:43', NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aigc_prompt
-- ----------------------------
BEGIN;
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('065c71becd73a94b55b2bd817ad81519', '担任雅思写作考官', '我希望你假定自己是雅思写作考官，根据雅思评判标准，按我给你的雅思考题和对应答案给我评分，并且按照雅思写作评分细则给出打分依据。此外，请给我详细的修改意见并写出满分范文。第一个问题是：It is sometimes argued that too many students go to university, while others claim that a university education should be a universal right.Discuss both sides of the argument and give your own opinion.对于这个问题，我的答案是：In some advanced countries, it is not unusual for more than 50% of young adults to attend college or university. Critics, however, claim that many university courses are worthless and young people would be better off gaining skills in the workplace. In this essay, I will examine both sides of this argument and try to reach a conclusion.There are several reasons why young people today believe they have the right to a university education. First, growing prosperity in many parts of the world has increased the number of families with money to invest in their children’s future. At the same time, falling birthrates mean that one- or two-child families have become common, increasing the level of investment in each child. It is hardly surprising, therefore, that young people are willing to let their families support them until the age of 21 or 22. Furthermore, millions of new jobs have been created in knowledge industries, and these jobs are typically open only to university graduates.However, it often appears that graduates end up in occupations unrelated to their university studies. It is not uncommon for an English literature major to end up working in sales, or an engineering graduate to retrain as a teacher, for example. Some critics have suggested that young people are just delaying their entry into the workplace, rather than developing professional skills.请依次给到我以下内容：具体分数及其评分依据、文章修改意见、满分范文。', NULL, NULL, '2024-04-23 10:46:28');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('42bf5cf2a7e97efe75c4a57804a719c9', '充当英英词典(附中文解释)', '将英文单词转换为包括中文翻译、英文释义和一个例句的完整解释。请检查所有信息是否准确，并在回答时保持简洁，不需要任何其他反馈。第一个单词是“Hello”', NULL, NULL, '2024-04-23 10:50:59');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('869a89226945f6f9b1065eaa1e293726', '充当前端智能思路助手', '我想让你充当前端开发专家。我将提供一些关于Js、Node等前端代码问题的具体信息，而你的工作就是想出为我解决问题的策略。这可能包括建议代码、代码逻辑思路策略。我的第一个请求是“我需要能够动态监听某个元素节点距离当前电脑设备屏幕的左上角的X和Y轴，通过拖拽移动位置浏览器窗口和改变大小浏览器窗口。”', NULL, NULL, '2024-04-23 10:51:08');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('8f030e6371717de3ae25d2da6ec40761', '充当英语翻译和改进者', '我希望你能担任英语翻译、拼写校对和修辞改进的角色。我会用任何语言和你交流，你会识别语言，将其翻译并用更为优美和精炼的英语回答我。请将我简单的词汇和句子替换成更为优美和高雅的表达方式，确保意思不变，但使其更具文学性。请仅回答更正和改进的部分，不要写解释。我的第一句话是“how are you ?”，请翻译它。', NULL, NULL, '2024-04-23 10:50:32');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('9d17607db8078a8e415c0f964c171dbb', '充当英翻中', '下面我让你来充当翻译家，你的目标是把任何语言翻译成中文，请翻译时不要带翻译腔，而是要翻译得自然、流畅和地道，使用优美和高雅的表达方式。请翻译下面这句话：“how are you ?”', NULL, NULL, '2024-04-23 10:50:47');
INSERT INTO `aigc_prompt` (`id`, `name`, `prompt`, `icon`, `des`, `create_time`) VALUES ('acf268ce86eef23bce11593c756e4c2a', '充当 Linux 终端', '我想让你充当 Linux 终端。我将输入命令，您将回复终端应显示的内容。我希望您只在一个唯一的代码块内回复终端输出，而不是其他任何内容。不要写解释。除非我指示您这样做，否则不要键入命令。当我需要用英语告诉你一些事情时，我会把文字放在中括号内[就像这样]。我的第一个命令是 pwd', NULL, NULL, '2024-04-23 10:46:58');
COMMIT;

-- ----------------------------
-- Table structure for aigc_user
-- ----------------------------
DROP TABLE IF EXISTS `aigc_user`;
CREATE TABLE `aigc_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of aigc_user
-- ----------------------------
BEGIN;
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362304631325102103, 'administrator', '48kQD0O/A69LENSbk/+FxA==', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362598312234024962, 'tycoding', '48kQD0O/A69LENSbk/+FxA==', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, '/default.png', 0, '2021-02-19 11:02:08');
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1404807635385069569, 'demo', '48kQD0O/A69LENSbk/+FxA==', '演示环境账号', '男', '18929809812', 'ty@qq.com', 1362597682681577273, 'http://tycoding.cn/imgs/tycoding.png', 1, '2021-06-15 22:26:55');
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1726814791469555713, '12', '$2a$10$2HxR1V/lEvcjgp.Hp/JMiucO/u9LcrgFmcuzTCcotkkh0u4c4I36O', '122222', '男', NULL, 'tycoding@qq.com', 1362597682681577273, '/default.png', 1, '2023-11-21 12:08:23');
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1727894777458745346, '232323', '$2a$10$.uevNrDHgcjHhmyZtOhGj.4tMwVuUh3yDHnSsW7qEGi/cABfsT12a', '3434', '男', '34', '34', 1726842159006482433, '/default.png', 1, '2023-11-24 11:39:52');
INSERT INTO `aigc_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1737855609581137921, 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', NULL, NULL, NULL, 'langchat@outlook.com', NULL, '/default.png', 1, '2023-12-21 23:20:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL COMMENT '部门ID',
  `parent_id` bigint NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1, 1362597682681577273, '测试2', 2, '测试');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1362597682681577273, 0, '测试部门', 1, '测试');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1726842159006482433, 1362597682681577273, '2', NULL, '111');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948009891598338, 1362597682681577273, '222', NULL, '2222');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1727948160722964481, 0, 'dfdfd', NULL, 'dfdfdf');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL COMMENT '编号',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='日志表';

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
  `id` bigint NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402556566351122433, '权限管理', 0, '/upms', '', 'menu', 30, 'KeyOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402557067260071938, '用户管理', 1402556566351122433, 'user', '', 'menu', 31, NULL, '/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402557067260071948, '用户查看', 1402557067260071938, NULL, 'upms:user:view', 'button', 32, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402562175471669250, '用户新增', 1402557067260071938, NULL, 'upms:user:add', 'button', 33, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402563862928248833, '用户修改', 1402557067260071938, NULL, 'upms:user:update', 'button', 34, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402564440660070202, '重置密码', 1402557067260071938, NULL, 'upms:user:reset', 'button', 35, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402564440660070402, '用户删除', 1402557067260071938, NULL, 'upms:user:delete', 'button', 36, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402891854378217473, '角色管理', 1402556566351122433, 'role', '', 'menu', 40, NULL, '/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402891854378217483, '角色查看', 1402891854378217473, NULL, 'upms:role:view', 'button', 41, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402895826841288705, '角色新增', 1402891854378217473, NULL, 'upms:role:add', 'button', 42, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402895916096077825, '角色修改', 1402891854378217473, NULL, 'upms:role:update', 'button', 43, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896004537171970, '角色删除', 1402891854378217473, NULL, 'upms:role:delete', 'button', 44, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896397505708033, '部门管理', 1402556566351122433, 'dept', '', 'menu', 50, NULL, '/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896397505708133, '部门查看', 1402896397505708033, NULL, 'upms:dept:view', 'button', 51, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896819314278401, '部门新增', 1402896397505708033, NULL, 'upms:dept:add', 'button', 52, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896925455335425, '部门修改', 1402896397505708033, NULL, 'upms:dept:update', 'button', 53, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897035325128705, '部门删除', 1402896397505708033, NULL, 'upms:dept:delete', 'button', 54, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897356944359426, '菜单管理', 1402556566351122433, 'menu', '', 'menu', 60, NULL, '/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897356944359526, '菜单查看', 1402897356944359426, NULL, 'upms:menu:view', 'button', 61, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897461059567617, '菜单新增', 1402897356944359426, NULL, 'upms:menu:add', 'button', 62, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897607436582914, '菜单修改', 1402897356944359426, NULL, 'upms:menu:update', 'button', 63, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897754488881153, '菜单删除', 1402897356944359426, NULL, 'upms:menu:delete', 'button', 64, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402900711645126657, '系统管理', NULL, '/system', '', 'menu', 70, 'SettingsOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402901682110603265, '系统日志', 1402900711645126657, 'log', '', 'menu', 71, NULL, '/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402901682110603365, '日志查看', 1402901682110603265, NULL, 'system:log:view', 'button', 72, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902022428041217, '日志删除', 1402901682110603265, NULL, 'system:log:delete', 'button', 73, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902525295730689, '文件管理', 1402900711645126657, 'file', '', 'menu', 80, NULL, '/system/oss/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902525295730789, '文件查看', 1402902525295730689, NULL, 'system:oss:view', 'button', 81, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902709501173762, '文件新增', 1402902525295730689, NULL, 'system:oss:add', 'button', 82, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902826891354113, '文件修改', 1402902525295730689, NULL, 'system:oss:update', 'button', 83, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402903214973526017, '文件删除', 1402902525295730689, NULL, 'system:oss:delete', 'button', 84, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561086978, '令牌管理', 1402900711645126657, 'token', '', 'menu', 90, NULL, '/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561087978, '令牌查看', 1403542523561086978, NULL, 'system:token:view', 'button', 91, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561187978, '令牌详情', 1403542523561086978, NULL, 'system:token:info', 'button', 92, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542705811984386, '令牌删除', 1403542523561086978, NULL, 'system:token:delete', 'button', 93, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403549005811984386, '项目文档', NULL, '/doc', 'doc:view', 'menu', 100, 'DocumentTextOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1406058379956326402, '开发文档', 1403549005811984386, 'http://docs.boot.tycoding.cn/', 'doc:dev:view', 'menu', 101, NULL, NULL, 0, 1, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1406058753513623553, '开源地址', 1403549005811984386, 'https://github.com/Tumo-Team/tumo-boot', 'doc:git:view', 'menu', 102, NULL, NULL, 0, 1, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423636580482088961, '字典管理', 1402900711645126657, 'dict', NULL, 'menu', 110, NULL, '/system/dict/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423638875592654849, '字典新增', 1423636580482088961, NULL, 'system:dict:add', 'button', 111, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639000616468481, '字典修改', 1423636580482088961, NULL, 'system:dict:update', 'button', 112, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639102978457602, '字典删除', 1423636580482088961, NULL, 'system:dict:delete', 'button', 112, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639486551752706, '字典项新增', 1423636580482088961, NULL, 'system:dict:item:add', 'button', 113, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639588704026626, '字典项修改', 1423636580482088961, NULL, 'system:dict:item:update', 'button', 114, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423640622671265793, '字典项删除', 1423636580482088961, NULL, 'system:dict:item:delete', 'button', 115, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1727150504603152386, 'AIGC平台', 0, '/aigc', 'app:view', 'menu', 1, 'CubeOutline', 'LAYOUT', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1727214745333739521, '知识库管理', 1727150504603152386, 'knowledge', 'knowledge:view', 'menu', 3, 'alert', '/aigc/knowledge/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1727215281965576194, '会话窗口数据', 1727150504603152386, 'message', 'message:view', 'menu', 5, 'alert', '/aigc/message/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1738098708558295041, '提示词管理', 1727150504603152386, 'prompt', 'prompt:view', 'menu', 0, '', '/aigc/prompt/index', 0, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` varchar(50) NOT NULL COMMENT '主键',
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
INSERT INTO `sys_oss` (`id`, `user_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `channel`, `create_time`) VALUES ('365045fd408b1a45042fc63458be146a', NULL, '中文文档', '87cf3f5c038d168dad159ee15008e1f1.docx', '/20240408', 'http://127.0.0.1:8100/20240408/87cf3f5c038d168dad159ee15008e1f1.docx', '/Users/tycoding/data/my/langchat/langchat-server/target/classes/static/20240408/87cf3f5c038d168dad159ee15008e1f1.docx', 'docx', 10998, NULL, NULL, '2024-04-08 10:07:40');
INSERT INTO `sys_oss` (`id`, `user_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `channel`, `create_time`) VALUES ('3aaa062e83891c6c38345dbe04fbcb1e', NULL, 'story-about-happy-carrot', '701595a319d4448b0990c3f3ccd2c2db.txt', '/20240408', 'http://127.0.0.1:8100/20240408/701595a319d4448b0990c3f3ccd2c2db.txt', '/Users/tycoding/data/my/langchat/langchat-server/target/classes/static/20240408/701595a319d4448b0990c3f3ccd2c2db.txt', 'txt', 2499, NULL, NULL, '2024-04-08 09:56:06');
INSERT INTO `sys_oss` (`id`, `user_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `channel`, `create_time`) VALUES ('a04e8ac2456817bc15a8eaeb68498335', NULL, '区域信息', '2bb8b00737716e057e2c45ab42aa4ead.xlsx', '/20240408', 'http://127.0.0.1:8100/20240408/2bb8b00737716e057e2c45ab42aa4ead.xlsx', '/Users/tycoding/data/my/langchat/langchat-server/target/classes/static/20240408/2bb8b00737716e057e2c45ab42aa4ead.xlsx', 'xlsx', 10433, NULL, NULL, '2024-04-08 10:08:00');
INSERT INTO `sys_oss` (`id`, `user_id`, `file_name`, `target_name`, `bucket`, `url`, `path`, `type`, `size`, `des`, `channel`, `create_time`) VALUES ('b4706ba05035cba970d02c2f4ade7e62', NULL, 'ed33090147376821fd6cf8620a3b8bb3', '282c736059560b6657cd59b473b4f4ee.pdf', '/20240408', 'http://127.0.0.1:8100/20240408/282c736059560b6657cd59b473b4f4ee.pdf', '/Users/tycoding/data/my/langchat/langchat-server/target/classes/static/20240408/282c736059560b6657cd59b473b4f4ee.pdf', 'pdf', 35359, NULL, NULL, '2024-04-08 12:59:58');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1362304631325192103, '超级管理员', 'administrator', '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1404805390442635266, '演示环境角色', 'demo_env', '演示环境使用角色，没有页面操作权限');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1620258484253642753, '111', '1111', '1111');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1620258568231997441, '测试角色', 'test', '这是测试的');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1726835321112363008, '客户端角色', 'client_role', '客户端用户的角色');
INSERT INTO `sys_role` (`id`, `name`, `alias`, `des`) VALUES (1726835321112363009, '23', '23', '2222');
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
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1406058379956326402);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402900711645126657);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402901682110603265);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402901682110603365);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902022428041217);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902525295730689);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902525295730789);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902709501173762);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902826891354113);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402903214973526017);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561086978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561087978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561187978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542705811984386);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423636580482088961);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423638875592654849);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639000616468481);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639102978457602);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639486551752706);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639588704026626);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423640622671265793);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1726835321112363009, 1406058379956326402);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362304631325102103, 'administrator', '48kQD0O/A69LENSbk/+FxA==', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362598312234024962, 'tycoding', '48kQD0O/A69LENSbk/+FxA==', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, '/default.png', 0, '2021-02-19 11:02:08');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1404807635385069569, 'demo', '48kQD0O/A69LENSbk/+FxA==', '演示环境账号', '男', '18929809812', 'ty@qq.com', 1362597682681577273, 'http://tycoding.cn/imgs/tycoding.png', 1, '2021-06-15 22:26:55');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1726814791469555713, '12', '$2a$10$2HxR1V/lEvcjgp.Hp/JMiucO/u9LcrgFmcuzTCcotkkh0u4c4I36O', '122222', '男', NULL, 'tycoding@qq.com', 1362597682681577273, '/default.png', 1, '2023-11-21 12:08:23');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1727894777458745346, '232323', '$2a$10$.uevNrDHgcjHhmyZtOhGj.4tMwVuUh3yDHnSsW7qEGi/cABfsT12a', '3434', '男', '34', '34', 1726842159006482433, '/default.png', 1, '2023-11-24 11:39:52');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1737855609581137921, 'langchat@outlook.com', '48kQD0O/A69LENSbk/+FxA==', NULL, NULL, NULL, 'langchat@outlook.com', NULL, '/default.png', 1, '2023-12-21 23:20:40');
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
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1362304631325102103, 1362304631325192103);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1362598312234024962, 1404805390442635266);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1404807635385069569, 1404805390442635266);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1726814791469555713, 1404805390442635266);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1726814791469555713, 1620258568231997441);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1727894777458745346, 1404805390442635266);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1737855609581137921, 1726835321112363008);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
