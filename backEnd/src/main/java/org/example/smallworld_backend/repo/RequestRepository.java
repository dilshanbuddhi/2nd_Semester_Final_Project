package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.Guid;
import org.example.smallworld_backend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request , Long> {

     List<Request> findAllByGuid_Id(long l);

     @Modifying
     @Query(value = "UPDATE request r SET r.status = ?2 WHERE r.id = ?1" , nativeQuery = true)
     void updatereq(long l, String status);
}
