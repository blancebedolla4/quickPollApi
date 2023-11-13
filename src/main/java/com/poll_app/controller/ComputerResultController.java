package com.poll_app.controller;

import com.poll_app.dto.OptionCount;
import com.poll_app.dto.VoteResult;
import com.poll_app.model.Vote;
import com.poll_app.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ComputerResultController {

    @Autowired
    private VoteRepository voteRepository;

//    @RequestMapping(value="/computeresult", method= RequestMethod.GET)
@GetMapping("/computeresult")
public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();

        for(Vote v : allVotes) {

            totalVotes ++;


            OptionCount optionCount = tempMap.get(v.getOption().getId());

            if(optionCount == null) {

                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);

            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());


        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
