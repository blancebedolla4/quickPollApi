package com.poll_app.controller;

import com.poll_app.exception.ResourceNotFoundException;
import com.poll_app.model.Poll;
import com.poll_app.repository.PollRepository;
import com.poll_app.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/polls") // Use class-level request mapping to avoid repeating "/polls" in each method
public class PollController {
    @Autowired
    private PollService pollService;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> p  = pollService.getPollById(pollId);
        p.orElseThrow(() ->  new ResourceNotFoundException("Poll with id " + pollId + " not found"));
        }


    @GetMapping
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollService.getAllPolls();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollService.createPoll(poll);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Optional<Poll> p = pollService.getPollById(pollId);
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
            return new ResponseEntity<>(p.get(), HttpStatus.OK);
        }


        @PutMapping("/{pollId}")
        public ResponseEntity<?> updatePoll (@RequestBody Poll p, @PathVariable Long pollId){
            pollService.editPoll(pollId, p);
            verifyPoll(pollId);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }

        @DeleteMapping("{pollId}")
        public ResponseEntity<?> deletePoll (@PathVariable Long pollId){
            verifyPoll(pollId);
            pollService.deletePollById(pollId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }


