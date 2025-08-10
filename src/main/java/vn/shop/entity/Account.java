package vn.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_account")
public class Account extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_code")
	private String accountCode;

	@Column(name = "user_name_account")
	private String userNameAccount;

	@Column(name = "password_account")
	private String passwordAccount;

	@Column(name = "sell_price")
	private Long sellPrice;

	@Column(name = "description")
	private String description;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "is_display")
	private boolean isDisplay;

	@Column(name = "game_id")
	private Long gameId;

	@Column(name = "account_sale_id")
	private Long accountSaleId;
}
