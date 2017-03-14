CREATE DATABASE if not exists bookstore;
use bookstore;

CREATE TABLE bs_user(
  uid INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL
);

INSERT  INTO bs_user VALUES (NULL,'zhangSan','123');
INSERT  INTO bs_user VALUES (NULL,'lisi','123');
INSERT  INTO bs_user VALUES (NULL,'wangwu','123');

-- ///////////////////////////////////////////

CREATE TABLE bs_category (
  cid INT PRIMARY KEY AUTO_INCREMENT,
  cname VARCHAR(100) NOT NULL
);

INSERT  INTO bs_category(cid,cname) VALUES (NULL,'JavaSE');
INSERT  INTO bs_category(cid,cname) VALUES (NULL,'JavaEE');
INSERT  INTO bs_category(cid,cname) VALUES (NULL,'Javascript');

-- ///////////////////////////////////////////

CREATE TABLE bs_book (
  bid INT PRIMARY KEY AUTO_INCREMENT,
  bname VARCHAR(100),
  price DECIMAL(5,1),
  author VARCHAR(20),
  image VARCHAR(200),
  cid INT,
  FOREIGN KEY (cid) REFERENCES bs_category(cid)
);

INSERT  INTO bs_book VALUES (NULL,'Java编程思想（第4版）','75.6','hliyang','book_img/9317290-1_l.jpg','1');
INSERT  INTO bs_book VALUES (NULL,'Java核心技术卷1','68.5','hliyang','book_img/20285763-1_l.jpg','1');
INSERT  INTO bs_book VALUES (NULL,'Java就业培训教程','39.9','hliyang','book_img/8758723-1_l.jpg','1');
INSERT  INTO bs_book VALUES (NULL,'Head First java','47.5','hliyang','book_img/9265169-1_l.jpg','1');
INSERT  INTO bs_book VALUES (NULL,'JavaWeb开发详解','83.3','孙鑫','book_img/22788412-1_l.jpg','2');
INSERT  INTO bs_book VALUES (NULL,'Struts2深入详解','63.2','孙鑫','book_img/20385925-1_l.jpg','2');
INSERT  INTO bs_book VALUES (NULL,'精通Hibernate','30.0','孙卫琴','book_img/8991366-1_l.jpg','2');
INSERT  INTO bs_book VALUES (NULL,'精通Spring2.x','63.2','陈华雄','book_img/20029394-1_l.jpg','2');
INSERT  INTO bs_book VALUES (NULL,'Javascript权威指南','93.6','（美）弗兰纳根','book_img/22722790-1_l.jpg','3');

-- /////////////////////////////////////////////

CREATE TABLE bs_order (
  oid varchar(40) PRIMARY KEY,
  ordertime DATETIME,
  price DECIMAL(10,0),
  uid INT,
  FOREIGN KEY (uid) REFERENCES bs_user (uid)
);

-- /////////////////////////////////////////////

CREATE TABLE bs_orderitem (
  iid INT PRIMARY KEY AUTO_INCREMENT,
  _count INT,
  subtotal DECIMAL(10,0),
  oid varchar(40),
  bid INT,
  FOREIGN KEY (oid) REFERENCES bs_order (oid),
  FOREIGN KEY (bid) REFERENCES bs_book (bid)
);