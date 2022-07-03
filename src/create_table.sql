


/*
+---------------------+-------------+------+-----+---------+----------------+
| Field               | Type        | Null | Key | Default | Extra          |
+---------------------+-------------+------+-----+---------+----------------+
| user_id             | int         | NO   | PRI | NULL    | auto_increment |
| user_phone          | text        | YES  |     | NULL    |                |
| user_avatar         | text        | YES  |     | NULL    |                |
| user_name           | text        | YES  |     | NULL    |                |
| user_email          | varchar(50) | YES  |     | NULL    |                |
| user_password       | varchar(50) | YES  |     | NULL    |                |
| user_favorite       | int         | YES  |     | NULL    |                |
| user_bias           | int         | YES  |     | NULL    |                |
| user_history_browse | int         | YES  |     | NULL    |                |
| user_history_review | int         | YES  |     | NULL    |                |
| user_last_login     | datetime    | YES  |     | NULL    |                |
+---------------------+-------------+------+-----+---------+----------------+
*/

CREATE TABLE user_tbl(
                         user_id int NOT NULL AUTO_INCREMENT,
                         user_phone text,
                         user_avatar text,
                         user_name text,
                         user_email varchar(50),
                         user_password varchar(50),
                         user_favorite INT,
                         user_bias INT,
                         user_history_browse INT,
                         user_history_review INT,
                         user_last_login DATETIME,
                         PRIMARY KEY(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
+-------------------+--------------+------+-----+---------+----------------+
| Field             | Type         | Null | Key | Default | Extra          |
+-------------------+--------------+------+-----+---------+----------------+
| news_id           | int          | NO   | PRI | NULL    | auto_increment |
| news_title        | varchar(200) | NO   |     | NULL    |                |
| news_cover_pic    | longblob     | YES  |     | NULL    |                |
| news_datetime     | datetime     | YES  |     | NULL    |                |
| news_author       | varchar(40)  | NO   |     | NULL    |                |
| news_content      | text         | YES  |     | NULL    |                |
| news_hit_vol      | int          | YES  |     | NULL    |                |
| news_favorite_vol | int          | YES  |     | NULL    |                |
| news_forward_vol  | int          | YES  |     | NULL    |                |
| news_paid_flag    | tinyint(1)   | YES  |     | NULL    |                |
+-------------------+--------------+------+-----+---------+----------------+

 */

CREATE TABLE news_tbl(
                         news_id INT NOT NULL AUTO_INCREMENT,
                         news_title VARCHAR(200) NOT NULL,
                         news_cover_pic LONGBLOB,
                         news_datetime DATETIME,
                         news_author VARCHAR(40) NOT NULL,
                         news_content text,
                         news_hit_vol INT,
                         news_favorite_vol INT,
                         news_forward_vol INT,
                         news_paid_flag tinyint(1),
                         PRIMARY KEY(news_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*

+---------------------+----------+------+-----+---------+----------------+
| Field               | Type     | Null | Key | Default | Extra          |
+---------------------+----------+------+-----+---------+----------------+
| user_id             | int      | YES  |     | NULL    |                |
| news_id             | int      | YES  |     | NULL    |                |
| review_id           | int      | NO   | PRI | NULL    | auto_increment |
| review_content      | text     | YES  |     | NULL    |                |
| review_datetime     | datetime | YES  |     | NULL    |                |
| review_upvote_vol   | int      | YES  |     | NULL    |                |
| review_downvote_vol | int      | YES  |     | NULL    |                |
+---------------------+----------+------+-----+---------+----------------+
*/

CREATE TABLE review_tbl(
                           user_id INT,
                           news_id INT,
                           review_id INT not null AUTO_INCREMENT,
                           review_content text,
                           review_datetime datetime,
                           review_upvote_vol INT,
                           review_downvote_vol INT,
                           PRIMARY KEY(review_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*

+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| ad_id            | int          | NO   | PRI | NULL    | auto_increment |
| ad_title         | varchar(200) | NO   |     | NULL    |                |
| ad_content       | text         | YES  |     | NULL    |                |
| ad_pic_hyperlink | varchar(400) | YES  |     | NULL    |                |
| ad_position_x    | double(20,5) | YES  |     | NULL    |                |
| ad_position_y    | double(20,5) | YES  |     | NULL    |                |
| ad_investor      | varchar(200) | NO   |     | NULL    |                |
| ad_datetime      | datetime     | YES  |     | NULL    |                |
| ad_region        | varchar(200) | YES  |     | NULL    |                |
| ad_device        | varchar(200) | YES  |     | NULL    |                |
| ad_hit_vol       | int          | YES  |     | NULL    |                |
| ad_weight        | double(20,5) | YES  |     | NULL    |                |
+------------------+--------------+------+-----+---------+----------------+
*/

CREATE TABLE ad_tbl(
    ad_id INT NOT NULL AUTO_INCREMENT,
    ad_title VARCHAR(200) NOT NULL,
    ad_content text,
    ad_pic_hyperlink VARCHAR(400),
    ad_position_x DOUBLE(20,5),
    ad_position_y DOUBLE(20,5),
    ad_investor VARCHAR(200) NOT NULL,
    ad_datetime datetime,
    ad_region  VARCHAR(200),
    ad_device  VARCHAR(200),
    ad_hit_vol INT,
    ad_weight  DOUBLE(20,5),
    PRIMARY KEY(ad_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;