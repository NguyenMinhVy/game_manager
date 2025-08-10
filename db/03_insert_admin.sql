-- m_user
INSERT INTO m_user (
    phone_number,
    name,
    password,
    role_id,
    insert_id,
    insert_date,
    update_id,
    update_date,
    del_flag
) VALUES (
             '0842426282',
             'NguyenMinhVy',
             '$2b$12$uQSGDO52NosLtSXbFsKNe.z82fBELA5YiQ8p6sdGq8EECkxubKFJm', -- 123456
             1,
             1,
             NOW(),
             1,
             NOW(),
             FALSE
         );
