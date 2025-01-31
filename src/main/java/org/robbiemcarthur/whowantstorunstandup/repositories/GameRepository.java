package org.robbiemcarthur.whowantstorunstandup.repositories;

import org.robbiemcarthur.whowantstorunstandup.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}

