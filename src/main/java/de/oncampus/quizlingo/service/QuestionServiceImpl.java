package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.QuestionCommand;
import de.oncampus.quizlingo.domain.dto.QuestionDTO;
import de.oncampus.quizlingo.domain.model.*;
import de.oncampus.quizlingo.repository.QuestionRepository;
import de.oncampus.quizlingo.repository.TermRepository;
import de.oncampus.quizlingo.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TermRepository termRepository;
    private final TopicRepository topicRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, TermRepository termRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.termRepository = termRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questionRepository.findAll().iterator().forEachRemaining(question -> questionDTOS.add(toQuestionDTO(question)));
        return questionDTOS;
    }

    private QuestionDTO toQuestionDTO(Question question) {
        List<String> terms = question.getTerms().stream().map(Term::getName).collect(Collectors.toList());
        return new QuestionDTO(
                question.getId(),
                question.getQuestionText(),
                question.getTaskText(),
                question.getTopic().getName(),
                question.getType(),
                question.getLevel(),
                terms,
                question.getOptions(),
                question.getCorrectAnswer());
    }

    @Override
    public QuestionDTO getQuestion(long id) {
        return questionRepository.findById(id).map(this::toQuestionDTO).orElse(null);
    }

    @Override
    public QuestionDTO addQuestion(QuestionCommand createQuestionCommand) {
        Question question = new Question();
        questionRepository.save(toQuestionEntity(createQuestionCommand, question));
        return toQuestionDTO(question);
    }

    @Override
    public List<QuestionDTO> getQuestionsByTopic(String topicName) {
        Topic topic = topicRepository.findByName(topicName);
        if(topic == null){
            topic = new Topic();
            topic.setName(topicName);
            topicRepository.save(topic);
        }
        return questionRepository.findQuestionsByTopic(topic).stream().map(this::toQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionCommand questionCommand) {
        Question question = questionRepository.findById(id).orElse(new Question());
        questionRepository.save(toQuestionEntity(questionCommand, question));
        return toQuestionDTO(question);
    }

    private Question toQuestionEntity(QuestionCommand createQuestionCommand, Question question) {
        transferDataToEntity(createQuestionCommand, question);
        return question;
    }

    private void transferDataToEntity(QuestionCommand createQuestionCommand, Question question) {
        question.setQuestionText(createQuestionCommand.getQuestionText());
        Topic topic = topicRepository.findByName(createQuestionCommand.getTopic());
        if(topic == null){
            topic = new Topic();
            topic.setName(createQuestionCommand.getTopic());
            topicRepository.save(topic);
        }
        question.setTopic(topic);
        question.setOptions(createQuestionCommand.getOptions());
        question.setCorrectAnswer(createQuestionCommand.getCorrectAnswer());
        question.setLevel(createQuestionCommand.getLevel());
        List<Term> terms = new ArrayList<>();
        createQuestionCommand.getTerms().forEach(name -> {
            Term term = termRepository.findByName(name);
            if (term == null){
                term = new Term();
                term.setName(name);
                termRepository.save(term);
            }
            terms.add(term);

        });
        question.setTerms(terms);
    }


}
