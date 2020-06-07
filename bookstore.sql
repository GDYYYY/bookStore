/*
Navicat MySQL Data Transfer

Source Server         : connection
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-06-07 14:26:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `b_id` int(11) NOT NULL,
  `onshelf` int(11) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '1', '123445', '当你学会独处', '周国平', '38.80', '当代文学大家周国平半生哲思与创作精华，一本让人学会与自我和解的心灵之书，写给每一个孤独的行路人。独处是灵魂生长的必要空间，在独处时，我们从他人和喧嚣中抽身出来，回到了自己。 ', '990', 'http://img3m8.ddimg.cn/70/15/28522168-1_l_3.jpg');
INSERT INTO `book` VALUES ('2', '1', '12355', '游牧民的世界史', '杉山正明', '61.70', '权威作者：日本研究草原民族与蒙古历史的首席专家；一种创见：游牧民是挑起“国家”的中坚力量，支撑了欧亚大陆历史发展；梁文道、腾讯文化、新周刊、新京报推荐', '479', 'http://img3m7.ddimg.cn/96/2/28481307-1_b_3.jpg');
INSERT INTO `book` VALUES ('3', '1', '9787201143873', '谜案鉴赏', '莉比·菲舍尔·赫尔曼', '35.80', '国际推理小说大奖安东尼提名奖、阿加莎提名奖得主继《谋杀鉴赏》后的又一力作。接棒阿加莎·克里斯蒂的新一代侦探小说女作家。 ', '3479', 'http://img3m1.ddimg.cn/66/36/28470981-1_b_3.jpg');
INSERT INTO `book` VALUES ('4', '1', '9787549570492', '新民说·宋：现代的拂晓时辰（新版）', '吴钩', '81.00', '文字版《清明上河图》，以近百幅宋画与丰富文献，全景式俯瞰一个被严重低估的朝代，一览宋代的繁华市井与旖旎风光，为“吴钩说宋”系列提纲掣领之作。', '5678', 'http://img3m3.ddimg.cn/37/35/27874873-1_l_3.jpg');
INSERT INTO `book` VALUES ('5', '1', '4566', '动机心理学:克服成瘾、拖延与懒惰的快乐原则', '罗曼·格尔佩林', '33.00', '《图书馆杂志》、《纽约时报图书周刊》重磅推荐，知名心理学家罗曼·格尔佩林深度剖析影响人类行动的思想动机，运用15种策略高效解决各类成瘾、拖延和懒惰问题。生活不是拥有，而是成为；共情不是目的，而是成就。', '456', 'http://img3m2.ddimg.cn/27/28/28520442-1_l.jpg');
INSERT INTO `book` VALUES ('6', '1', '453265', '人间失格', '太宰治', '9.75', '太宰治绝望的告白，独家收录太宰治绝笔之作《Goodbye》', '57675', 'http://img3m1.ddimg.cn/32/9/22557281-1_f_6.jpg');
INSERT INTO `book` VALUES ('7', '1', '467527', '图解本草纲目', '李时珍', '26.52', '《本草纲目》图解版本，畅销百万册图解经典系列全新修订，忠于原著！中国中医科学院研究员陈飞松、中国特效医术专业委员会', '4366', 'http://img3m2.ddimg.cn/74/6/23342492-1_f_3.jpg');
INSERT INTO `book` VALUES ('8', '1', '34554', '四书五经', '中华文化讲堂', '86.19', '儒家国学经典，大学、中庸、论语、孟子、尚书、诗经、左传、周易、礼记，经典超值读本', '4621', 'http://img3m9.ddimg.cn/78/1/25215279-1_f_3.jpg');
INSERT INTO `book` VALUES ('9', '1', '42536', '在路上', '杰克·凯鲁亚克', '49.70', '趁着年轻生猛，我要再和生活死磕几年！全新精装十大硬核价值版！从披头士乐队到乔布斯，它改变了无数人的命运，是被鲍勃?迪伦翻烂了的人生“圣经”！《教父》译者姚向辉翻译，书后附赠手绘地图、嬉皮风插画及88页旅行手记！翻开这一本，彻底读透《在路上》！读客熊猫君出品', '6745', 'http://img3m3.ddimg.cn/50/6/28510763-1_l.jpg');
INSERT INTO `book` VALUES ('10', '1', '9787514228540', '华胥引', '唐七', '59.34', '当当独家签名本！你有没有一生都不能忘记的人？ 你有没有到死都无法释怀的事？ 你有没有纵然以性命为代价，也要完成的执念，和挽回的过去？', '678', 'http://img3m6.ddimg.cn/60/30/28533246-1_l_9.jpg');
INSERT INTO `book` VALUES ('11', '1', '9787539998312', 'The Little Prince', 'Antoine de Saint-Exupéry', '36.00', '参考兰登书屋、企鹅出版社等经典英译本，全英文外教美式朗读音频，配原版高清插图，附赠《词汇注解》手册', '389098', 'http://img3m6.ddimg.cn/3/14/25102146-1_b_3.jpg');
INSERT INTO `book` VALUES ('12', '1', '9787550291904', '英语单词大书 ：Big Book of English Words', '梅丽·麦金农', '78.00', '限时送配套视频课！廖彩杏推荐，儿童英语单词明星产品，2~8岁；英国Usborne出版社权威出品，国内授权中英双语版！（新版可扫码听原版音频）25个主题、100个互动情景、1000个常用单词，认知加英语', '547575', 'http://img3m9.ddimg.cn/47/10/25069619-1_b_12.jpg');
INSERT INTO `book` VALUES ('13', '0', null, 'bookname', 'author', '999.90', null, '0', null);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `num` int(11) NOT NULL DEFAULT '1',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('1', '2', '2', '4', '2020-05-03 14:28:44');
INSERT INTO `cart` VALUES ('2', '2', '3', '5', '2020-05-03 18:23:17');
INSERT INTO `cart` VALUES ('4', '2', '1', '9', '2020-05-03 18:28:43');
INSERT INTO `cart` VALUES ('9', '2', '10', '4', '2020-05-05 12:59:24');

-- ----------------------------
-- Table structure for orderform
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform` (
  `f_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderform
-- ----------------------------
INSERT INTO `orderform` VALUES ('1', '1', '48.55', '2020-06-04 13:02:43');
INSERT INTO `orderform` VALUES ('2', '1', '179.00', '2020-06-04 13:02:45');
INSERT INTO `orderform` VALUES ('3', '1', '39.00', '2020-06-04 13:02:47');
INSERT INTO `orderform` VALUES ('4', '1', '116.40', '2020-06-04 13:03:01');
INSERT INTO `orderform` VALUES ('5', '2', '9.75', '2020-06-05 22:17:39');
INSERT INTO `orderform` VALUES ('6', '2', '447.30', '2020-06-05 22:19:53');
INSERT INTO `orderform` VALUES ('7', '1', '35.80', '2020-06-06 18:24:56');
INSERT INTO `orderform` VALUES ('8', '1', '344.76', '2020-06-07 12:56:24');
INSERT INTO `orderform` VALUES ('9', '1', '232.80', '2020-06-07 14:11:51');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `f_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `num` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '6', '1');
INSERT INTO `orders` VALUES ('2', '1', '1', '1');
INSERT INTO `orders` VALUES ('3', '2', '3', '5');
INSERT INTO `orders` VALUES ('4', '3', '6', '4');
INSERT INTO `orders` VALUES ('5', '4', '1', '3');
INSERT INTO `orders` VALUES ('6', '5', '6', '1');
INSERT INTO `orders` VALUES ('7', '6', '9', '9');
INSERT INTO `orders` VALUES ('8', '7', '3', '1');
INSERT INTO `orders` VALUES ('9', '8', '8', '4');
INSERT INTO `orders` VALUES ('10', '9', '1', '6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 's', 'ss', '1', '');
INSERT INTO `user` VALUES ('2', 'aaaa', '123', '0', 'aaaa@163.cn');
INSERT INTO `user` VALUES ('3', '22', '222', '0', '');
INSERT INTO `user` VALUES ('4', '4', '44', '0', null);
