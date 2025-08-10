package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.shop.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM t_account a WHERE a.del_flag = false AND a.is_display = true AND a.game_id = ?1 ORDER BY a.priority DESC NULLS LAST", nativeQuery = true)
    List<Account> findAllByGameIdAndDelFlagFalseAAndDisplayTrueOrderByPriority(Long gameId);

    Account findByAccountCode(String accountCode);
}
