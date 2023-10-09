CREATE TABLE `usuario` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `email`  VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `telefone`  VARCHAR(11) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',

    `logradouro`  VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `numero`  VARCHAR(30) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `complemento`  VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `bairro`  VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `cidade`  VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `uf`  VARCHAR(2) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
    `cep`  VARCHAR(8) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',

    PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=0;