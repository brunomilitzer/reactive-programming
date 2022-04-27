create table users(
    id bigint not null AUTO_INCREMENT,
    name varchar(50) not null,
    balance int not null,
    primary key (id)
);

create table user_transaction(
    id bigint not null AUTO_INCREMENT,
    user_id bigint not null,
    amount int not null,
    transaction_date timestamp not null,
    foreign key (user_id) references users(id) on delete cascade
);

insert into users (name, balance) values ('bob', 1000), ('mike', 1200), ('jack', 800), ('vanessa', 2000);