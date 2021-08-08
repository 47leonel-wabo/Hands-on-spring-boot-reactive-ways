create table users (
    id bigint auto_increment,
    name varchar(50),
    balance decimal,
    primary key (id)
);

insert into users (name, balance) values ('Anna Smith', 1520.98);
insert into users (name, balance) values ('John Doe', 350.0);

create table user_operation (
    id bigint auto_increment,
    user_id bigint,
    amount decimal,
    operation_date timestamp,
    primary key (id),
    foreign key (user_id) references users(id) on delete cascade
)