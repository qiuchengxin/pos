package com.market.pos.service.JPAService;

import com.market.pos.pojo.TeamTree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamTreeRepository extends JpaRepository<TeamTree,String> {
    //通过id查询
    public Optional<TeamTree> findById(String id);

}
