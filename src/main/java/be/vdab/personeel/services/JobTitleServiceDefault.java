package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.JobTitle;
import be.vdab.personeel.repositories.JobTitleRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class JobTitleServiceDefault implements JobTitleService {

	private final JobTitleRepository jobTitleRepository;
	
	public JobTitleServiceDefault(final JobTitleRepository jobTitleRepository) {
		this.jobTitleRepository = jobTitleRepository;
	}
	
	@Override
	public Optional<JobTitle> read(final long id) {
		return jobTitleRepository.findById(id);
	}

	@Override
	public List<JobTitle> findAll() {
		return jobTitleRepository.findAll();
	}
}
