package vn.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.shop.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

@Query(value = """
    SELECT *
    FROM t_account a
    WHERE a.del_flag = false
       AND (:isDisplay IS NULL OR a.is_display = :isDisplay)
      AND a.game_id = :gameId
      AND (:minPrice IS NULL OR a.sell_price >= :minPrice)
      AND (:maxPrice IS NULL OR a.sell_price <= :maxPrice)
      AND (:accountCode IS NULL OR a.account_code LIKE %:accountCode%)
      AND (:isSale IS NULL
        OR (:isSale = true AND a.account_sale_id IS NOT NULL)
        OR (:isSale = false AND a.account_sale_id IS NULL))
    ORDER BY 
      CASE WHEN :sort = 'asc' THEN a.sell_price END ASC,
      CASE WHEN :sort = 'desc' THEN a.sell_price END DESC,
      a.priority DESC NULLS LAST
    """,
        countQuery = """
    SELECT count(*)
    FROM t_account a
    WHERE a.del_flag = false
       AND (:isDisplay IS NULL OR a.is_display = :isDisplay)
      AND a.game_id = :gameId
      AND (:minPrice IS NULL OR a.sell_price >= :minPrice)
      AND (:maxPrice IS NULL OR a.sell_price <= :maxPrice)
      AND (:accountCode IS NULL OR a.account_code LIKE %:accountCode%)
      AND ( :isSale IS NULL
        OR (:isSale = true AND a.account_sale_id IS NOT NULL)
        OR (:isSale = false AND a.account_sale_id IS NULL))
    """,
        nativeQuery = true)
Page<Account> findAllByGameIdAndDelFlagFalseAAndDisplayTrueOrderByPriority(
        Long gameId,
        Long minPrice,
        Long maxPrice,
        String accountCode,
        String sort,
        Pageable pageable,
        Boolean isDisplay,
        Boolean isSale
);
    Account findByAccountCode(String accountCode);
}
