-- m_role
COMMENT ON COLUMN m_role.role_id IS '1 = ADMIN';

-- m_game
COMMENT ON TABLE m_game IS 'Lưu về 1 loại game nhất định';

-- t_account

COMMENT ON TABLE t_account IS 'Bảng lưu thông tin tài khoản game được bán';
COMMENT ON COLUMN t_account.account_id IS 'ID duy nhất của tài khoản game';
COMMENT ON COLUMN t_account.account_code IS 'Mã tài khoản game (mã quản lý nội bộ)';
COMMENT ON COLUMN t_account.user_name_account IS 'Tên đăng nhập của tài khoản game';
COMMENT ON COLUMN t_account.password_account IS 'Mật khẩu của tài khoản game';
COMMENT ON COLUMN t_account.description IS 'Mô tả chi tiết tài khoản';
COMMENT ON COLUMN t_account.priority IS 'Độ ưu tiên khi hiển thị sản phẩm';
COMMENT ON COLUMN t_account.game_id IS 'ID trò chơi mà tài khoản thuộc về';

-- t_account_sale
COMMENT ON TABLE t_account_sale IS 'Bảng lưu thông tin tài khoản game đã bán';
COMMENT ON COLUMN t_account_sale.account_sale_id IS 'id account đã bán';
COMMENT ON COLUMN t_account_sale.account_id IS 'ID duy nhất của tài khoản game';
COMMENT ON COLUMN t_account_sale.cost_price IS 'Giá vốn của tài khoản (số tiền nhập)';
COMMENT ON COLUMN t_account_sale.sell_price IS 'Giá bán của tài khoản';
COMMENT ON COLUMN t_account_sale.sell_date IS 'Ngày bắt đầu bán hoặc chuyển giao tài khoản';
COMMENT ON COLUMN t_account_sale.sell_complete_date IS 'Ngày hoàn tất bán và nhận đủ tiền';
COMMENT ON COLUMN t_account_sale.sell_status IS 'Trạng thái bán: 1=Chưa bán, 2=Đã bán đang thu tiền, 3=Đã bán đã thu đủ tiền';
COMMENT ON COLUMN t_account_sale.deposit IS 'Số tiền khách đã đặt cọc';
COMMENT ON COLUMN t_account_sale.paid_amount IS 'Tổng số tiền đã thanh toán';
COMMENT ON COLUMN t_account_sale.outstanding_amount IS 'Số tiền khách còn nợ';
COMMENT ON COLUMN t_account_sale.loan_term_months IS 'Kỳ hạn trả góp (tháng)';
COMMENT ON COLUMN t_account_sale.paid_months IS 'Số tháng đã thanh toán';
COMMENT ON COLUMN t_account_sale.monthly_payment_amount IS 'Số tiền phải trả mỗi tháng';
COMMENT ON COLUMN t_account_sale.customer_id IS 'ID khách hàng mua tài khoản';
COMMENT ON COLUMN t_account_sale.note_for_sell IS 'Ghi chú nội bộ về quá trình bán';




