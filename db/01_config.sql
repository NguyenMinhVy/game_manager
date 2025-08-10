-- m_user
ALTER TABLE m_user
ADD CONSTRAINT fk_m_user_role
FOREIGN KEY (role_id)
    REFERENCES m_role(role_id)
    MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
NOT VALID;

CREATE INDEX idx_m_user_role_id ON m_user(role_id);



-- t_account
ALTER TABLE t_account
ADD CONSTRAINT fk_t_account_game
    FOREIGN KEY (game_id)
        REFERENCES m_game(game_id)
        MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
NOT VALID;

CREATE INDEX idx_t_account_game ON t_account(game_id);

ALTER TABLE t_account
    ADD CONSTRAINT fk_t_account_account_sale
        FOREIGN KEY (account_sale_id)
            REFERENCES t_account_sale(account_sale_id)
            MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    NOT VALID;

CREATE INDEX idx_t_account_account_sale ON t_account(account_sale_id);

-- t_account_sale
ALTER TABLE t_account_sale
    ADD CONSTRAINT fk_t_account_sale_customer
        FOREIGN KEY (customer_id)
            REFERENCES m_customer(customer_id)
            MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    NOT VALID;

CREATE INDEX idx_t_account_sale_customer ON t_account_sale(customer_id);


-- t_account_image
ALTER TABLE t_account_image
ADD CONSTRAINT fk_t_account_image_account
    FOREIGN KEY (account_id)
        REFERENCES t_account(account_id)
        MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
NOT VALID;

CREATE INDEX idx_t_account_image_account ON t_account_image(account_id);



