package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.TopicDTO;

import java.util.List;

public interface TopicService {

    List<TopicDTO> getAllTopics();

    TopicDTO createTopic(TopicDTO topicDTO) throws TopicServiceImpl.TopicAlreadyExistException;
}
