package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);

}
