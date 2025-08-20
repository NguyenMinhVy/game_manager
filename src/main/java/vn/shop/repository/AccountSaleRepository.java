package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.shop.entity.AccountSale;

@Repository
public interface AccountSaleRepository extends JpaRepository<AccountSale, Long> {
}
