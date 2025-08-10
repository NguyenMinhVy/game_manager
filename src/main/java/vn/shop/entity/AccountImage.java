package vn.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_account_image")
public class AccountImage extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_image_id")
	private Long accountImageId;

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_code")
	private String accountCode;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "image_name")
	private String imageName;
}
