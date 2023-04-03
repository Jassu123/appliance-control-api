package com.appliance.repository;
import com.appliance.entity.ApplianceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<ApplianceEntity,Long> {

}
