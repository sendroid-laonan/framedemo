
insert  into `role`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

insert  into `town`(name, manager, create_date) values ('平山镇','张福平','2017-11-01');

insert  into `users`(`id`,`name`,`username`,`password`,`phone`,`state`,`town_id`) values (1,'张三','admin1','123456','15578970190','1',1);

insert  into `user_roles`(`user_id`,`role_id`) values (1,1);

