package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.model.Topic;
import de.oncampus.quizlingo.repository.TopicRepository;
import de.oncampus.quizlingo.domain.dto.TopicDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        List<TopicDTO> topics = new ArrayList<>();
        for (Topic topic : topicRepository.findAll()) {
            TopicDTO topicDTO = convertToTopicDTO(topic);
            topics.add(topicDTO);
        }
        return topics;
    }

    private TopicDTO convertToTopicDTO(Topic topic) {
        return new TopicDTO(topic.getName());
    }

    private Topic convertToTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        return topic;
    }

    @Override
    public TopicDTO createTopic(TopicDTO topicDTO) throws TopicAlreadyExistException {
        if (topicExists(topicDTO.getName())) {
            throw new TopicAlreadyExistException("There is already a topic with the name: "
                    + topicDTO.getName());
        }
        topicRepository.save(convertToTopic(topicDTO));
        return topicDTO;
    }

    private boolean topicExists(String name) {
        return topicRepository.findByName(name) != null;
    }

    public static class TopicAlreadyExistException extends Throwable {
        public TopicAlreadyExistException(String s) {
        }
    }
}
