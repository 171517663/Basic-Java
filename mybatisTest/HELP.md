建表

        CREATE DATABASE `mybatis`;
        
        USE `mybatis`;
        
        CREATE TABLE `user`(
        `id` INT(20) NOT NULL,
        `name` VARCHAR(30) DEFAULT NULL,
        `pwd` VARCHAR(30) DEFAULT NULL,
        PRIMARY KEY (`id`)
        )ENGINE=INNODB DEFAULT CHARSET=utf8;
        
        INSERT USER(`id`,`name`,`pwd`) VALUES
        (1,'小黄','123456'),
        (2,'小黄','123456'),
        (3,'小黄','123456');

