

insert  into `role`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');


insert  into `user`(`id`,`name`,`username`,`password`) values (1,'张三','admin1','123456');


insert  into `user_roles`(`user_id`,`roles_id`) values (1,1);

call demo_insert();

