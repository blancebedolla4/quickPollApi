package com.poll_app.repository;

import com.poll_app.model.Poll;
import org.springframework.data.repository.CrudRepository;


public interface PollRepository extends CrudRepository<Poll, Long> {
}
