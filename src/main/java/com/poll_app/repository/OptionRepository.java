package com.poll_app.repository;

import com.poll_app.model.Options;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Options, Long> {
}
