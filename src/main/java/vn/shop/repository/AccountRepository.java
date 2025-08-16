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
      AND a.is_display = true
      AND a.game_id = :gameId
      AND (:minPrice IS NULL OR a.sell_price >= :minPrice)
      AND (:maxPrice IS NULL OR a.sell_price <= :maxPrice)
      AND (:accountCode IS NULL OR a.account_code LIKE %:accountCode%)
    ORDER BY 
      CASE WHEN :sort = 'asc' THEN a.sell_price END ASC,
      CASE WHEN :sort = 'desc' THEN a.sell_price END DESC,
      a.priority DESC NULLS LAST
    """,
        countQuery = """
    SELECT count(*)
    FROM t_account a
    WHERE a.del_flag = false
      AND a.is_display = true
      AND a.game_id = :gameId
      AND (:minPrice IS NULL OR a.sell_price >= :minPrice)
      AND (:maxPrice IS NULL OR a.sell_price <= :maxPrice)
      AND (:accountCode IS NULL OR a.account_code LIKE %:accountCode%)
    """,
        nativeQuery = true)
Page<Account> findAllByGameIdAndDelFlagFalseAAndDisplayTrueOrderByPriority(
        Long gameId,
        Long minPrice,
        Long maxPrice,
        String accountCode,
        String sort,
        Pageable pageable
);
    Account findByAccountCode(String accountCode);
}
