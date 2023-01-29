package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.domain.dto.QuestionDTO;
import de.oncampus.quizlingo.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller for all question-related requests.
 */
@RestController
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Returns a list of all quiz questions.
     *
     * @return  the list of all questions
     */
    @GetMapping("/questions")
    @CrossOrigin
    public List<QuestionDTO> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    /**
     * Returns all the questions by a topic
     *
     * @param  topic the topic that that questions should belong to
     * @return the list of all questions with the given topic
     */
    @GetMapping("/questions/{topic}")
    public List<QuestionDTO> getQuestionsByTopic(@PathVariable String topic){
        return questionService.getQuestionsByTopic(topic);
    }

    /**
     * Updates a question and returns the updated question
     *
     * @param  id  the id of question to be updated
     * @param  questionCommand  the question information that the new question should have
     * @return QuestionDTO  the updated question
     */
    @PutMapping("/questions/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionCommand questionCommand){
        return questionService.updateQuestion(id, questionCommand);
    }

    /**
     * Deletes a question
     *
     * @param  id  the id of question to be deleted
     */
    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    /**
     * Creates a question and returns the created question
     *
     * @param  questionCommand  the question information that the new question should have
     * @return QuestionDTO  the created question
     */
    @PostMapping("/questions")
    public QuestionDTO addQuestion(@RequestBody QuestionCommand questionCommand){
        return questionService.addQuestion(questionCommand);
    }
}
