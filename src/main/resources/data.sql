/* Clear WALLETS */
DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

/* Insert wallets */
INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
    )
VALUES (
        1, 'Joao - User', 12345678900, 'joao@test.com', '123456', 1, 1000.00, 1
    );

INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
    )
VALUES (
        2, 'Maria - Lojista', 12345678901, 'maria@test.com', '123456', 2, 1000.00, 1
    );