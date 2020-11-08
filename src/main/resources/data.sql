-- Insertion des rôles nécessaires
INSERT IGNORE INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_COMPANY'),
(3, 'ROLE_STUDENT');


--
-- INSERTION DE COMPTES TEST
--

-- INSERTION COMPANY TEST --
INSERT IGNORE INTO `company` (`id`, `description`, `label_logo`, `mail_address`, `name`, `state`, `website_url`) VALUES
(1, '', '', 'mail', 'ENTREPRISE_TEST', 'ENREGISTRE', 'url');

-- INSERTION USER ADMIN --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(1, 'ADMIN', 'TEST', 'admin_test', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `admin` (`id`, `id_user`) VALUES (1, 1);

-- INSERTION USER COMPANY_EMPLOYEE --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(2, 'COMPANY', 'TEST', 'company_test', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `company_employee` (`id_company`, `id_employee`) VALUES (1, 2);

-- INSERTION USER STUDENT --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(3, 'STUDENT', 'TEST', 'student_test', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `student` (`id`, `description`, `label`, `label_picture`, `school_year`, `state`, `id_user`) VALUES
(1, NULL, NULL, NULL, '5A', 'ENREGISTRE', 3);


--
-- INSERTION DES LIAISONS ENTRE USER ET ROLE
--
INSERT IGNORE INTO `user_role` (`role_id`, `user_id`) VALUES (1, 1);
INSERT IGNORE INTO `user_role` (`role_id`, `user_id`) VALUES (2, 2);
INSERT IGNORE INTO `user_role` (`role_id`, `user_id`) VALUES (3, 3);