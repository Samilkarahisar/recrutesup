--
-- Déchargement des données de la table `role`
--

INSERT IGNORE INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_COMPANY'),
(3, 'ROLE_STUDENT');


--
-- Déchargement des données de la table `company`
--

INSERT IGNORE INTO `company` (`id`, `description`, `mail_address`, `name`, `state`, `website_url`) VALUES
(1, 'Sopra Steria est une entreprise de services du numérique (ESN) française et une société de conseil en transformation digitale des entreprises et des organisations.', 'company@test.com', 'Sopra Steria', 'VALIDE', 'https://www.soprasteria.com/fr'),
(2, 'Atos est une entreprise de services du numérique (ESN) française, créée en 1997. Elle fait partie des 10 plus grandes ESN au niveau mondial', 'company2@test.com', 'Atos', 'VALIDE', 'https://atos.net/fr/'),
(3, 'Orange Wholesale France, aujourd’hui, c’est une gamme complète d’offres qui vous apporte le meilleur de nos réseaux.\r\n\r\nDans un marché marqué par de fortes mutations, les opérateurs ont besoin d’un partenaire capable de leur offrir un réseau de qualité et à la pointe de l’innovation. A ce titre, nous avons un rôle essentiel à jouer auprès de vous.', 'or-wh-fr@gmail.com', 'Orange Wholesale France', 'VALIDE', 'https://wholesalefrance.orange.fr/fr/'),
(4, 'Première ESN française à s’être engagée dans la labellisation « Numérique responsable », ISIA s’appuie sur un collectif de 120 collaborateurs engagés dans une démarche RSE. L\'ambition de l’entreprise est de mettre le numérique au service d’enjeux majeurs du monde de demain tels que la transition écologique et la santé. ', 'isia-contact@gmail.com', 'Groupe ISIA', 'VALIDE', 'https://groupe-isia.com/'),
(5, 'Aperam est une société produisant et commercialisant de l’acier inoxydable, de l’acier électrique et des aciers spéciaux. La société, séparée d\'ArcelorMittal début 2011, est cotée aux bourses d\'Amsterdam, Paris, Bruxelles et Luxembourg. Les principales usines européennes d\'Aperam sont situées en Belgique et en France.', 'aperam-contact@gmail.com', 'Aperam', 'VALIDE', 'https://www.aperam.com/'),
(6, 'Électricité de France (EDF) est une entreprise française, détenue à plus de 80 % par l\'État.\r\n\r\nIl s\'agit du premier producteur et du premier fournisseur d’électricité en France et en Europe. Au niveau mondial, EDF est le deuxième producteur d’électricité derrière China Energy Investment, en termes de puissance installée5,6. EDF est aussi la troisième compagnie d’électricité au monde en termes de chiffre d’affaires, après la State Grid China et l\'italien ENEL7.', 'edf-entreprise-contact@gmail.com', 'EDF', 'VALIDE', 'https://www.edf.fr/entreprises'),
(7, 'Carrefour Market, est une enseigne française de supermarché, apparue en octobre 2007, appartenant au groupe Carrefour, disposant d\'environ un millier de points de vente en France.\r\n\r\nDepuis 2011, le groupe Carrefour a décidé de déployer cette enseigne Carrefour Market à travers le monde pour l\'ensemble de ses supermarchés dont la surface oscille principalement entre 1 000 et 5 000 m2.', 'carrefour-market-contact@gmail.com', 'Carrefour Market', 'VALIDE', 'https://www.carrefour.fr/'),
(8, 'Amazon est une entreprise de commerce en ligne américaine basée à Seattle. Elle est l\'un des géants du Web, regroupés sous l\'acronyme GAFAM, aux côtés de Google, Apple, Facebook et Microsoft.', 'amazon-contact@gmail.com', 'Amazon', 'VALIDE', 'https://www.amazon.jobs/fr/locations/france'),
(9, 'Econocom est un groupe européen, qui met en œuvre la transformation digitale des entreprises et des organisations publiques.\r\n\r\nNous aidons nos clients à penser et à utiliser efficacement le numérique, en plaçant les utilisateurs finaux comme point de départ de toute transformation numérique.', 'econocom-contact@gmail.com', 'Econocom', 'ENREGISTRE', 'https://www.econocom.com/fr'),
(10, 'LE GROUPE SWORD EST UNE SOCIÉTÉ INTERNATIONALE DE SOFTWARE, DE CONSEILS\r\nET DE SERVICES. NOUS ASSISTONS LES LEADERS MONDIAUX DANS LEURS PROGRAMMES DE TRANSFORMATION TECHNOLOGIQUE ET DIGITALE.', 'sword-group-contact@gmail.com', 'Sword Group', 'ENREGISTRE', 'https://www.sword-group.com/'),
(11, NULL, 'apollo-contact@gmail.com', 'Apollo SSC', 'INVALIDE', '');


--
-- Déchargement des données de la table `user`
--

INSERT IGNORE INTO `user` (`id`, `firstname`, `lastname`, `mail_address`, `password`, `phone_number`) VALUES
(1, 'Florence', 'PERRAUD', 'admin@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(2, 'Selma', 'LARIBI', 'admin2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(3, 'Philippe', 'REBARD', 'employee@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(4, 'Antoine', 'DECOURS', 'employee2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(5, 'Edouard', 'BRONNERT', 'student@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(6, 'Léa', 'NICOLAS', 'student2@test.com', '$2y$10$yY66fnZ1IdrxaxDPezNlKunsrZQ/9byNLwreYs1cjp1zoqMfc0v36', NULL),
(7, 'Maxime', 'NINET', 'maxime-ninet@gmail.com', '$2a$10$Fx5TF2rornZPZ2NE5lFRmuoHnu.sSjzHPqH6dJm6q8MNyBSnU2xfi', '0633215468'),
(8, 'Camille', 'BLANCHE', 'camille-blanche@gmail.com', '$2a$10$/0vDJHLiTzKJKAfpPz.cXupYvqxzGCTccHQgYjQlf5FVT8p6JB2AK', '0644859654'),
(9, 'Marion', 'DE MOLLES', 'marion-dm@gmail.com', '$2a$10$RTOvo.SRe4W/Jspa3/0PSufeyaJjSKUKtlGjFBTifhhTAYVE/.aXK', '0621554788'),
(10, 'Pierre', 'DEMION', 'pierre-deums@gmail.com', '$2a$10$oYDNHk2IcfrNq9tryy17T.wSMI70yhoJ/jQ5X8OrT3MV1T/unfVyO', '0632326598'),
(11, 'Alexandre', 'TOLA', 'alexandre-tola@gmail.com', '$2a$10$419cIs40KsiRnTZJT89qWeh6JqZjVZFXXyPXr/1o7AoSoaEiaN3NC', '0614142577'),
(12, 'Antoine', 'CEBULSKI', 'antoi-cebubu@gmail.com', '$2a$10$7.3idtbBaBai6vPnYYGuaupIbhIOIEpEcYoZl4AN7PGcm8/D9iA9u', '0656854124'),
(13, 'Thomas', 'SOLER', 'thomas-soler@gmail.com', '$2a$10$ZxltmDs.z52L3ovgiTJFuueWQ18m9pax52M9YSYW/tdyN2555JuO.', '0623565985'),
(14, 'Colin', 'RAMEL', 'colin-ramel@gmail.com', '$2a$10$NyzLYMIPPDGsvFMnAnSi6Oq9BJl5Uu/yaN9iLl1cDpQElikQtmaRi', '0623657474'),
(15, 'Adam', 'BOUTTE', 'adam-boutte@gmail.com', '$2a$10$J0hVWbpjMFGdos0sdNz95eyUoOPN2gFkK0DmOxzd8dPo4ti.UkoW2', '0625478569'),
(16, 'Alexia', 'PILLOT', 'alexia-pillot@soprasteria.com', '$2a$10$0KhTnC5zzcqcGB8Q.A1rJehCUxr52E7bBVMiiCR.SQDOi5umhM9My', '0625285417'),
(17, 'Sébastien', 'JOUBERT', 'seb-joubert@soprasteria.com', '$2a$10$hzKpEduZmdbkdPtVfP/gF.9dkceL65d.4PdboLTwnG3CsyKNmhrfK', '0625488754'),
(18, 'Maxime', 'KERMANEC', 'maxime-kermanec@atos.com', '$2a$10$.2AtOke7rOEVlnhHnNr4DOtpv9G/7xVzT5oGhPs5o1aiULf1bSMsa', '0625412214'),
(19, 'Mathilde', 'SALAS', 'mathilde.salas@atos.com', '$2a$10$ht.3ddwcZez0KxiYSMihNuIF/.kK6.c3tR90Hnn54w0RiptkAkyhS', '0625412547'),
(20, 'Rebeka', 'BY', 'rebeka-by@orange.com', '$2a$10$VKqdYiCz4jnNn58nKoLpsOuhgIym.F4Yu4C4BN1q7lVLR.69q0oUa', '0625123666'),
(21, 'Marcel', 'DUPROST', 'marcel-duprost@orange.com', '$2a$10$Wbbsyz8eKTAhf/I0ozAbte69rWeXtXkg7vq7cIGwGBYrErGCLsNTC', '0623569856'),
(22, 'Sandrine', 'BERTILLIER', 'sandrine-bertillier@isia.com', '$2a$10$LOaE7oJKz0jAPpoUyoWJr.9SmkNTb1S95ZoFXYnbJRqqx/2qC3oka', '0625251478'),
(23, 'Hugo', 'BONGIORNO', 'hugo-bongiorno@isia.com', '$2a$10$ltWvgtzdgjSUlL.xwPS0XO0I94xMfmOH4n9HfVPoc9OJKLAIH2hG2', '0623525369'),
(24, 'Loic', 'LAPORTE', 'loic-laporte@aperam.com', '$2a$10$/gsl89zBs3X/CQoDwIpkkeBWZ4aiTdSsU0YjJBQjQVXELPrSwQzoa', '0622251448'),
(25, 'Laurent', 'GERARD', 'laurent-gerard@aperam.com', '$2a$10$CF8jq0jDjOAIwXWsx8N4jOqwB.OhKtJZLTk3zmrWF8zxiu33GQx46', '0663225411'),
(26, 'Ludovic', 'BERNOULLI', 'ludovic-bernoulli@edf.com', '$2a$10$c6pyQ9Pb0VUEioiYA3M/buOfbVfbiR15mkta5etNXUVsbkUShtYG2', '0663255874'),
(27, 'Cassandre', 'RAMEL', 'cassandre-ramel@edf.com', '$2a$10$PO940/iiVqz6pcXdVvii2eCp/88D9bWRbRXUB3xgYR0OIb3AdAw36', '0632569854'),
(28, 'Bob', 'DYLAN', 'bob-dylan@carrefour.com', '$2a$10$BnWVILOptjFEY5KoqkyhBevOckrj0JO3xwYybqnUHND8jgliHFa..', '0611454125'),
(29, 'Bruno', 'FRERES', 'bruno-freres@carrefour.com', '$2a$10$R4VIM81HV/Me.Pz58/oO1e.ONhhVqtY4WtZNTLBWALWItI7nKmt/e', '0252148962'),
(30, 'Alexandre', 'PASQUIER', 'alexandre-pasquier@amazon.com', '$2a$10$K9z67pdgCUd.cfEmJAxpG.62LJ06DbFTNy.PK3z/MzNbXTeApFs8y', '0627951354'),
(31, 'Bastien', 'BASTER', 'bastien-baster@amazon.com', '$2a$10$by9gWANyRN4U3khi33HIR.ylZwXL6nhEiiu34rjT7tjwsY7ov1XeC', '0632674184'),
(32, 'Thomas', 'BROUTIER', 'thomas-broutier@econocom.com', '$2a$10$FJUFR8kBFGFZQvfiKKQfeu3TGia2HDElxjkD078xiND/OgFf1SGtq', '0656966214'),
(33, 'Isabelle', 'DUPONT', 'isabelle-dupont@econocom.com', '$2a$10$zElNpipmt3P.gM/xVle7teOrhh.kdsc6iuRqLNFy3FXBhYyVjgUFq', '0658547854'),
(34, 'jean', 'VENDAL', 'jean-vendal@sword.com', '$2a$10$HFibGUxfnb1oEaI6VDlYme6DckDpCpui19CCtzDc4iApT2d4wbPUy', '0652665488'),
(35, 'Laura', 'BUSTIER', 'laura-bustier@sword.com', '$2a$10$Z5.A1D/QWe4noOpdmW2EKuHqvFD4l/TXCqsCtmG//XfaXhX26UIqG', '0658544111'),
(36, 'Serge', 'TREPI', 'serge-trepi@apollo.com', '$2a$10$7vgH0Y0LdbIOATksAG3HFeA5KMjINumRjAp908a1m4Ea6sWrTVbwm', '0623357418'),
(37, 'Emilie', 'BERJOT', 'emilie-berjot@apollo.com', '$2a$10$UXE4S391bXvtVzirbHOsWuTMiKZ2VlJw8S.Om1EuOr7X3YXXsTs3S', '0637489512');


--
-- Déchargement des données de la table `admin`
--

INSERT IGNORE INTO `admin` (`id`, `id_user`) VALUES
(1, 1),
(2, 2);


--
-- Déchargement des données de la table `company_employee`
--

INSERT IGNORE INTO `company_employee` (`id_company`, `id_employee`) VALUES
(1, 3),
(1, 16),
(1, 17),
(2, 4),
(2, 18),
(2, 19),
(3, 20),
(3, 21),
(4, 22),
(4, 23),
(5, 24),
(5, 25),
(6, 26),
(6, 27),
(7, 28),
(7, 29),
(8, 30),
(8, 31),
(9, 32),
(9, 33),
(10, 34),
(10, 35),
(11, 36),
(11, 37);


--
-- Déchargement des données de la table `student`
--

INSERT IGNORE INTO `student` (`id`, `description`, `label`, `school_year`, `state`, `id_user`) VALUES
(1, 'Je suis à  la recherche d\'une alternance à  partir de septembre 2021. Je recherche un poste de développeur dans une ESN de la région lyonnaise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà  assez bien.', 'En recherche d\'une alternance pour septembre 2021 dans la région lyonnaise', '5A', 'VALIDE', 5),
(2, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur dans une ESN de la région grenobloise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà  assez bien.', 'En recherche d\'une alternance pour octobre 2021 dans la région grenobloise', '5A', 'VALIDE', 6),
(3, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur dans une ESN de la région stéphanoise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà  assez bien.', 'En recherche d\'une alternance pour octobre 2021 dans la région stéphanoise', '4A', 'VALIDE', 7),
(4, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste d\'apprentissage pour devenir expert en sécurité.', 'Recherche une alternance en sécurité des SI', '3A', 'VALIDE', 8),
(5, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur dans une ESN de la région grenobloise, dans le but de développer mes capacités PHP.', 'Cherche un emploi en alternance pour rentrée 2021', '4A', 'VALIDE', 9),
(6, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur dans une ESN de la région grenobloise, dans le but de développer mes capacités Java et Angular que je maîtrise déjà  assez bien.', 'Recherche une alternance dans une ESN, pour développer mes compétences Java/Angular', '5A', 'VALIDE', 10),
(7, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur dans une ESN de la région lyonnaise, je suis véhiculé et peux donc me déplacer autour de Lyon.', 'En recherche d\'un emploi en alternance pour octobre 2021', '5A', 'ENREGISTRE', 11),
(8, 'Je suis à  la recherche d\'une alternance à  partir de septembre 2021. Je recherche un poste de développeur dans une ESN de la région lyonnaise, dans le but de développer mes capacités Php.', 'Recherche un poste de développeur PHP', '4A', 'ENREGISTRE', 12),
(9, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste d\'apprentissage pour apprendre à devenir un expert data, notamment en Big Data et Machine Learning.', 'Recherche un poste d\'alternance en Big Data ou Machine Learning', '4A', 'INDISPONIBLE', 13),
(10, 'Je suis à  la recherche d\'une alternance à  partir d\'octobre 2021. Je recherche un poste de développeur Front-end sur des projets divers et variés. Le but étant de développer mes capacités Angular.', 'En recherche d\'une alternance Front-End, spécialisée en Angular', '5A', 'INDISPONIBLE', 14),
(11, '', '', '3A', 'INVALIDE', 15);


--
-- Déchargement des données de la table `offer`
--

INSERT IGNORE INTO `offer` (`id`, `address`, `city`, `creation_date`, `description`, `label`, `mail_address`, `state`, `created_by_user`) VALUES
(1, '11 avenue Leon Blum', 'Villeurbanne', '2020-11-09', 'Nous recherchons un développeur maîtrisant le langage java et le framework Spring. le développeur sera amené Ã  réaliser des missions en autonomie pour un acteur du secteur des transport.', 'Développeur Java/Spring', 'contact-offer@test.com', 'DISPONIBLE', 3),
(2, '25 avenue des Noisetiers', 'St-Priest', '2020-12-08', 'Nous recherchons un développeur maîtrisant les bases d\'Angular et de Java. Il est fortement recommandé de maîtriser les frameworks Spring pour Java et Materials pour Angular', 'Développeur Full Stack Java/Angular', 'contact-offer2@test.com', 'DISPONIBLE', 4),
(3, '11 rue des Noisetiers', 'Limonest', '2021-01-26', 'Poste d\'alternance Full Stack sur des technologies variés. Vous serez épaulés par des experts puis serez ensuite amenés à évoluer par vous-même en autonomie sur des missions de complexités diverses. Le poste concerne une équipe de moins de 10 personnes  pour un de nos clients lyonnais du domaine bancaire', 'Développeur Full Stack', 'soprasteria@contact.com', 'DISPONIBLE', 3),
(4, '11 rue des Noisetiers', 'Limonest', '2021-01-26', 'Venez découvrir le domaine devOps. Entourez d\'experts, vous pourrez évoluer sur des projets attractifs et à forte valeur ajoutée ! De quoi booster vos compétences et votre CV ! Notre équipe n\'attend que vous !', 'Ingénieur DevOps', 'contact@soprasteria.com', 'EN_VALIDATION', 3),
(5, '108 avenue garibaldi', 'Lyon', '2021-01-26', 'Nous recherchons un passionné de cybersécurité, plutôt que quelqu\'un de vraiment expert dedans. Les compétences ne nous intéressent pas, nous cherchons à vous faire évoluer et vous former tout au long de votre apprentissage. \nLe poste se trouve en centre-ville directement chez un de nos clients du domaine bancaire. Vous serez en permanence accompagné de quelqu\'un de chez Sopra Steria, et serez pleinement impliqué dans les décisions prises quotidiennement.', 'Ingénieur CyberSécurité', 'soprasteria@contact.com', 'EN_VALIDATION', 3),
(6, '11 rue des Noisetiers', 'Limonest', '2021-01-26', 'Le poste concerne le domaine de la data, c\'est à dire la gestion de données en base de données. Leur manipulation, leur traitement. Vos missions concerneront des problématiques diverses  comme :\n- l\'optimisation des flux en transit sur les serveurs\n- la récurrence de certaines requêtes et l\'impact sur les performances chez le client\n- la pluridisciplinarité des objets en BDD manipulés\n', 'Ingénieur Data', 'soprasteria@contact.com', 'EN_VALIDATION', 3),
(7, '11 rue des Noisetiers', 'Limonest', '2021-01-26', 'Ce poste concerne un projet d\'application web pour un de nos clients du domaine de l\'énergie. Vous serez amené à développer vos compétences Back, en utilisant principalement Java et des frameworks comme Spring. Vous serez amené à manipuler des bases de données et des modèles objets parfois complexes. Bien sûr vous serez encadré par des experts qui tâcheront de vous faire évoluer dans ce milieu et dans notre entreprise.', 'Technicien Java/Spring', 'soprasteria@contact.com', 'BROUILLON', 3),
(8, '5 avenue Berthelot', 'St-Priest', '2021-01-26', 'Nous cherchons une personne motivée pour apprendre à développer une application web, en maîtrisant toutes les phases de développement. ', 'Développeur Full Stack', 'atos@contact.com', 'DISPONIBLE', 4),
(9, '5 avenue Berthelot', 'St-Priest', '2021-01-26', 'Maîtrisez les phases DevOps ainsi que la méthodologie DevOps. ', 'Ingénieur DevOps', 'atos@contact.com', 'EN_VALIDATION', 4),
(10, '5 rue leon blum', 'Lyon', '2021-01-26', 'Nous recherchons un futur alternant pour un post Full Stack avec comme technos : J2EE et l\'utilisation du framework Spring. Et aussi l\'utilisation de reactJS.\n\nTous les niveaux son appréciés, nous nous chargerons de votre montée en compétences et de votre évolution dans le domaine.', 'Développeur J2EE/ReactJs', 'atos@contact.com', 'EN_VALIDATION', 4);


--
-- Déchargement des données de la table `attachment`
--

INSERT IGNORE INTO `attachment` (`id`, `label`) VALUES
(1, 'OFFRE'),
(2, 'OFFRE');


--
-- Déchargement des données de la table `offer_attachment`
--

INSERT IGNORE INTO `offer_attachment` (`id_offer`, `id_attachment`) VALUES
(1, 1),
(2, 2);


--
-- Déchargement des données de la table `student_wish`
--

INSERT IGNORE INTO `student_wish` (`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_offer`, `id_student`) VALUES
(1, '2021-01-26', 1, 1, 'TRANSMIS', 1, 1),
(2, '2021-01-26', 1, 1, 'TRANSMIS', 2, 2),
(3, '2021-01-26', 2, 2, 'TRANSMIS', 2, 1),
(4, '2021-01-26', 2, 2, 'TRANSMIS', 1, 2),
(5, '2021-01-26', 1, 3, 'TRANSMIS', 3, 1),
(6, '2021-01-26', 1, 4, 'TRANSMIS', 8, 1);


--
-- Déchargement des données de la table `offer_student_wish`
--

INSERT IGNORE INTO `offer_student_wish` (`id_offer`, `id_student_wish`) VALUES
(1, 1),
(1, 4),
(2, 2),
(2, 3),
(3, 5),
(8, 6);


--
-- Déchargement des données de la table `company_wish`
--

INSERT IGNORE INTO `company_wish` (`id`, `creation_date`, `priority_receiver`, `priority_sender`, `state`, `id_company`, `id_student`) VALUES
(1, '2021-01-26', 1, 1, 'TRANSMIS', 1, 1),
(2, '2021-01-26', 1, 1, 'TRANSMIS', 2, 2),
(3, '2021-01-26', 1, 2, 'TRANSMIS', 1, 7),
(4, '2021-01-26', 1, 3, 'TRANSMIS', 1, 3),
(5, '2021-01-26', 1, 4, 'TRANSMIS', 1, 10),
(6, '2021-01-26', 2, 2, 'TRANSMIS', 2, 1),
(7, '2021-01-26', 1, 3, 'TRANSMIS', 2, 4),
(9, '2021-01-26', 1, 4, 'TRANSMIS', 2, 9),
(10, '2021-01-26', 1, 5, 'TRANSMIS', 2, 8);


--
-- Déchargement des données de la table `company_offer`
--

INSERT IGNORE INTO `company_offer` (`id_company`, `id_offer`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(2, 2),
(2, 8),
(2, 9),
(2, 10);


--
-- Déchargement des données de la table `user_role`
--

INSERT IGNORE INTO `user_role` (`id_role`, `id_user`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(2, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25),
(2, 26),
(2, 27),
(2, 28),
(2, 29),
(2, 30),
(2, 31),
(2, 32),
(2, 33),
(2, 34),
(2, 35),
(2, 36),
(2, 37),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15);