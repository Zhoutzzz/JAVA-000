create schema study collate utf8_general_ci;

create table user_tx
(
	id bigint not null
		primary key,
	dollars decimal(11,6) default 0.000000 null,
	rmb decimal(11,6) default 0.000000 null,
	user_name varchar(10) null,
	created_time datetime default CURRENT_TIMESTAMP null,
	updated_time datetime default CURRENT_TIMESTAMP null,
	freeze_rmb decimal(11,6) default 0.000000 null,
	freeze_dollars decimal(11,6) default 0.000000 null
);

