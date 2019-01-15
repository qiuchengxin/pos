package com.market.pos.service.JPAService;

import com.market.pos.pojo.TeamList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamListRepository extends JpaRepository<TeamList,Integer> {
    //通过id查询
    public TeamList findById(long id);

    public TeamList findByTId(String tId);

}
