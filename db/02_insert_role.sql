-- m_role
INSERT INTO m_role (role_id, role_name, insert_id,
                    insert_date,
                    update_id,
                    update_date,
                    del_flag)
VALUES (1, 'ROLE_ADMIN', 1,
        NOW(),
        1,
        NOW(),
        FALSE);