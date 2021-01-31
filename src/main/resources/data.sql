-- Insertion des rôles nécessaires
INSERT IGNORE INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_COMPANY'),
(3, 'ROLE_STUDENT');


--
-- INSERTION DE COMPTES TEST
--

-- INSERTION COMPANY TEST --
INSERT IGNORE INTO `company` (`id`, `description`, `mail_address`, `name`, `state`, `website_url`) VALUES
(1, 'Sopra Steria est une entreprise de services du numérique (ESN) française et une société de conseil en transformation digitale des entreprises et des organisations.',
'company@test.com', 'Sopra Steria', 'ENREGISTRE', 'https://www.soprasteria.com/fr');

INSERT IGNORE INTO `company` (`id`, `description`, `mail_address`, `name`, `state`, `website_url`) VALUES
(2, 'Atos est une entreprise de services du numérique (ESN) française, créée en 1997. Elle fait partie des 10 plus grandes ESN au niveau mondial',
'company2@test.com', 'Atos', 'ENREGISTRE', 'https://atos.net/fr/');




-- INSERTION USER ADMIN --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(1, 'Florence', 'PERRAUD', 'admin@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `admin` (`id`, `id_user`) VALUES (1, 1);

INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(2, 'Selam', 'LARIBI', 'admin2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `admin` (`id`, `id_user`) VALUES (2, 2);




-- INSERTION USER COMPANY_EMPLOYEE --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(3, 'Philippe', 'REBARD', 'employee@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `company_employee` (`id_company`, `id_employee`) VALUES (1, 3);

INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(4, 'Antoine', 'DECOURS', 'employee2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `company_employee` (`id_company`, `id_employee`) VALUES (2, 4);




-- INSERTION USER STUDENT --
INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(5, 'Edouard', 'BRONNERT', 'student@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `student` (`id`, `description`, `label`, `school_year`, `state`, `id_user`) VALUES
(1, 'Je suis à la recherche d\'une alternance à partir de septembre 2021. Je recherche un poste de développeur dans une ESN de la région lyonnaise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà assez bien.',
'En recherche d\'une alternance pour septembre 2021 dans la région lyonnaise', '5A', 'ENREGISTRE', 5);

INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(6, 'Léa', 'NICOLAS', 'student2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL);
INSERT IGNORE INTO `student` (`id`, `description`, `label`, `school_year`, `state`, `id_user`) VALUES
(2, 'Je suis à la recherche d\'une alternance à partir de septembre 2021. Je recherche un poste de développeur dans une ESN de la région lyonnaise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà assez bien.',
'En recherche d\'une alternance pour septembre 2021 dans la région lyonnaise', '5A', 'ENREGISTRE', 6);




-- INSERTION OFFERS --
INSERT IGNORE INTO `offer` (`id`, `label`, `description`, `city`, `address`, `mail_address`, `creation_date`, `created_by_user`, `state`) VALUES
(1, 'Développeur Java/Spring', 'Nous recherchons un développeur maîtrisant le langage java et le framework Spring. le développeur sera amené à réaliser des missions en autonomie pour un acteur du secteur des transport.',
'Villeurbanne', '11 avenue Leon Blum', 'contact-offer@test.com', '2020-11-09', 3, 'BROUILLON');

INSERT IGNORE INTO `offer` (`id`, `label`, `description`, `city`, `address`, `mail_address`, `creation_date`, `created_by_user`, `state`) VALUES
(2, 'Développeur Full Stack Java/Angular', 'Nous recherchons un développeur maîtrisant les bases d\'Angular et de Java. Il est fortement recommandé de maîtriser les frameworks Spring pour Java et Materials pour Angular',
'St-Priest', '25 avenue des Noisetiers', 'contact-offer2@test.com', '2020-12-08', 4, 'BROUILLON');




-- INSERTION COMPANY OFFER --
INSERT IGNORE INTO `company_offer` (`id_company`, `id_offer`) VALUES
(1, 1);

INSERT IGNORE INTO `company_offer` (`id_company`, `id_offer`) VALUES
(1, 2);




-- INSERTION ATTACHMENT --
INSERT IGNORE INTO `attachment` (`id`, `label`) VALUES
(1, 'OFFRE');

INSERT IGNORE INTO `attachment` (`id`, `label`) VALUES
(2, 'OFFRE');




-- INSERTION OFFERS ATTACHMENT--
INSERT IGNORE INTO `offer_attachment` (`id_offer`, `id_attachment`) VALUES
(1, 1);

INSERT IGNORE INTO `offer_attachment` (`id_offer`, `id_attachment`) VALUES
(2, 2);




-- INSERTION STUDENT WISH --
INSERT IGNORE INTO `student_wish`(`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_offer`, `id_student`) VALUES
(1, SYSDATE(), 1, 1, 'TRANSMIS', 1, 1);

INSERT IGNORE INTO `student_wish`(`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_offer`, `id_student`) VALUES
(2, SYSDATE(), 1, 1, 'TRANSMIS', 2, 2);




-- INSERTION OFFER_STUDENT_WISH --
INSERT IGNORE INTO `offer_student_wish` (`id_offer`, `id_student_wish`) VALUES
(1, 1);

INSERT IGNORE INTO `offer_student_wish` (`id_offer`, `id_student_wish`) VALUES
(2, 2);




-- INSERTION COMPANY WISH --
INSERT IGNORE INTO `company_wish` (`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_student`) VALUES
(1, SYSDATE(), 1, 1, 'TRANSMIS', 1, 1);

INSERT IGNORE INTO `company_wish` (`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_student`) VALUES
(2, SYSDATE(), 1, 1, 'TRANSMIS', 2, 2);






--
-- INSERTION DES LIAISONS ENTRE USER ET ROLE
--
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (1, 1);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (1, 2);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (2, 3);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (2, 4);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (3, 5);
INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES (3, 6);
