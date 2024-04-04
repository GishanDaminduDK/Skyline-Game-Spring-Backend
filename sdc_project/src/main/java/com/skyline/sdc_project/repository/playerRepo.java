package com.skyline.sdc_project.repository;
import com.skyline.sdc_project.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playerRepo extends CrudRepository<Player, Integer> {
    public Player findAdminByUsername(String username);
    public Player findAdminByEmailAndPassword(String email, String password);

}
