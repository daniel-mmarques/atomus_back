package com.godev.atomus.entity.trade;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long>{

    @Query("""
        select t from Trade t
        where
        t.userId = :id
        order by t.tradeDate desc
    """)
    List<Trade> findAllByUsuarioId(Long id, Pageable pageable);

    @Query("""
        select t from Trade t
        join Product p on t.productId = p.id
        where
        t.userId = :id
        and
        p.name = :product
        order by t.tradeDate desc
    """)
    List<Trade> findAllByUsuarioIdAndProduct(Long id, String product, Pageable pagination);

    @Query("""
        select t from Trade t
        join Product p on t.productId = p.id
        where
        t.userId = :id
        and
        p.name = :product
        and
        t.title = :title
        order by t.tradeDate desc
    """)
    List<Trade> findAllByUsuarioIdAndTitle(Long id, String product, String title, Pageable pagination);

    @Query("""
        select p.name
        from Trade t
        join Product p on t.productId = p.id
        where t.userId = :userId
        group by p.name
    """)
    List<String> findProductsByUserId( Long userId);
}
