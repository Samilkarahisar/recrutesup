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

-- INSERTION OFFERS --
INSERT IGNORE INTO `offer` (`id`, `label`, `description`, `city`, `mail_address`, `creation_date`, `created_by_user`, `state`) VALUES
(1, 'TEST', 'DESCRIPTION_TEST', 'CITY', 'MAIL', '2010-04-02', 2, 'ENREGISTRE');
INSERT IGNORE INTO `offer` (`id`, `label`, `description`, `city`, `mail_address`, `creation_date`, `created_by_user`, `state`) VALUES
(2, 'TEST2', 'DESCRIPTION_TEST2', 'CITY2', 'MAIL2', '2010-04-05', 2, 'ENREGISTRE');


-- INSERTION ATTACHMENT --
INSERT IGNORE INTO `attachment` (`id`, `label`) VALUES
(1, 'TESTLABEL');
INSERT IGNORE INTO `attachment` (`id`, `label`) VALUES
(2, 'TESTLABEL2');

-- INSERTION OFFERS ATTACHMENT--
INSERT IGNORE INTO `offer_attachment` (`id_offer`, `id_attachment`) VALUES
(1, 1);
INSERT IGNORE INTO `offer_attachment` (`id_offer`, `id_attachment`) VALUES
(2, 2);

-- INSERTION STUDENT WISH --
INSERT IGNORE INTO `student_wish`(`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_offer`, `id_student`) VALUES
(1, SYSDATE(), 2, 1, 'ENREGISTRE', 1, 1, 1);
INSERT IGNORE INTO `student_wish`(`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_offer`, `id_student`) VALUES
(2, SYSDATE(), 2, 1, 'ENREGISTRE', 1, 2, 1);


-- INSERTION COMPANY WISH --
INSERT IGNORE INTO `company_wish` (`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_student`) VALUES
(1, SYSDATE(), 3, 2, 'ENREGISTRE', 1, 1);


-- INSERTION COMPANY OFFER --
INSERT IGNORE INTO `company_offer` (`id_company`, `id_offer`) VALUES
(1, 1);
INSERT IGNORE INTO `company_offer` (`id_company`, `id_offer`) VALUES
(1, 2);

--
-- INSERTION DES LIAISONS ENTRE USER ET ROLE
--
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (1, 1);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (2, 2);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (3, 3);
