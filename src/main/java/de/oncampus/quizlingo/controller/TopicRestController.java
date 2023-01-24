package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.domain.model.Role;
import de.oncampus.quizlingo.service.TopicService;
import de.oncampus.quizlingo.service.TopicServiceImpl;
import de.oncampus.quizlingo.domain.dto.TopicDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * REST-Controller für all topic-related requests.
 */
@RestController
public class TopicRestController {

    private final TopicService topicService;

    public TopicRestController(TopicService topicService) {
        this.topicService = topicService;
    }


    /**
     * Returns a list of all quiz topics that the user can choose from.
     *
     * @return  the list of all topics
     */
    @GetMapping("/topics")
    public List<TopicDTO> getAllTopics(){

        return topicService.getAllTopics();
    }

    /**
     * Creates a new topic and returns the created topic
     *
     * @param  topicDTO  the topic to be created
     * @return topicDTO  the created topic
     * @throws TopicAlreadyExistException
     */
    @PostMapping("/topics")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(Role.ADMIN)
    public TopicDTO create(@RequestBody TopicDTO topicDTO) throws TopicServiceImpl.TopicAlreadyExistException {
        return topicService.createTopic(topicDTO);
    }
}
