package com.logistics.moolryu.domains.hub.repositoy;

import com.logistics.moolryu.domains.hub.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HubRepository extends JpaRepository<Hub, Long> {
    Optional<Hub> findByHubName(String username);

}
