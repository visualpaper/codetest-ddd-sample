drop table if exists users;
create table if not exists users
(
  id      int unsigned not null primary key auto_increment,
  name    varchar(128) not null,
  api_key varchar(256) not null,
  unique uniq_api_key (api_key)
) character set utf8mb4;

// h2　は COLLATE 未サポート

drop table if exists transactions;
create table if not exists transactions
(
  id          int unsigned not null primary key auto_increment,
  user_id     int unsigned not null,
  amount      int          not null,
  description varchar(256) not null,
  CONSTRAINT fk_transactions_users FOREIGN KEY (user_id) REFERENCES users (id)
) character set utf8mb4;
