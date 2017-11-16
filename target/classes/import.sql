

insert  into `role`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

insert  into `town`(`id`,`name`,`manager`) values (1,'平山镇','张福平');

call demo_town_insert();

insert  into `users`(`id`,`name`,`username`,`password`,`phone`,`state`,`town_id`) values (1,'张三','admin1','123456','15578970190','1',1);

call demo_insert();

insert  into `user_roles`(`user_id`,`role_id`) values (1,1);

-- call demo_insert();

call demo_user_roles_insert();