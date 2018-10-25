package be.vdab.personeel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.JobTitle;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long>{

}
