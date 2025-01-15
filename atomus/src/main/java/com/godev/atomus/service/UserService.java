package com.godev.atomus.service;

import com.godev.atomus.entity.answer.Answer;
import com.godev.atomus.entity.ticker.TickerListData;
import com.godev.atomus.entity.user.User;
import com.godev.atomus.entity.user.UserPasswordEditData;
import com.godev.atomus.entity.user.UserProfileEditData;
import com.godev.atomus.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.editUser(new UserPasswordEditData(passwordEncoder.encode(user.getPassword())));
        userRepository.save(user);
    }

    public void saveProfile(Long id, List<Answer> answers) {
        User user = getReferenceById(id);
        user.editUser(new UserProfileEditData(determineProfileByAnswers(answers)));
        save(user);
    }

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> getAll() { return userRepository.findAll(); }

    public Long determineProfileByAnswers(List<Answer> answers) {
        double averageAnswer = averageAnswer(mapAnswers(answers));
        Long profile;
        if (averageAnswer <= 2) {
            profile = profileService.findByProfile("CAUTELOSO").getId();
        } else if (averageAnswer <= 4) {
            profile = profileService.findByProfile("MODERADO").getId();
        } else {
            profile = profileService.findByProfile("AGRESSIVO").getId();
        }
        return profile;
    }

    public Map<Long, Integer> mapAnswers(List<Answer> answers) {
        Map<Long, Integer> mappedList = new HashMap<>();
        for (Answer answer : answers) {
            Long questionId = answer.getQuestionId();
            int answerValue = answer.getAnswerValue();
            mappedList.put(questionId, answerValue);
        }
        return mappedList;
    }

    public Double averageAnswer(Map<Long, Integer> mappedList) {
        int sum = 0;
        int count = 0;
        for (int value : mappedList.values()) {
            sum += value;
            count++;
        }
        return (count != 0 ? (double) sum / count : (double) 0);
    }

    public TickerListData getRecommendations(Long id) {
        String profile = userRepository.findProfileByUserId(id);

        return switch (profile) {
            case "CAUTELOSO" -> searchService.searchListByType("fund");
            case "MODERADO" -> searchService.searchListByType("stock");
            case "AGRESSIVO" -> searchService.searchListByType("bdr");
            default -> searchService.searchList();
        };
    }
}

