package com.poll_app.services;

import com.poll_app.model.Poll;
import com.poll_app.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PollService {
    //making it so i can inject.. autowired is the word
    @Autowired
    private PollRepository pollRepository; //injecting interface into this class

    //getAllPolls,
    public Iterable<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    //lets you create a poll
    public Poll createPoll(Poll poll){
        return pollRepository.save(poll);
    }
    //specify id
    public Optional<Poll> getPollById(Long id){
        return pollRepository.findById(id);
    }

    //edit question or option of poll

    public void editPoll(Long id, Poll poll){
        Poll existingPoll = pollRepository.findById(id).get();
        existingPoll.setQuestion(poll.getQuestion());
        existingPoll.setOptions(poll.getOptions());
        pollRepository.save(poll);
    }
    //allows you to delete poll by id

    public void deletePollById(Long id){
        pollRepository.deleteById(id);
    }


}
