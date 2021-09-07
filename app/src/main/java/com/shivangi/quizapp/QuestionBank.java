package com.shivangi.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private static List<QuestionsList> javaQuestions()
    {
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1= new QuestionsList("Q 2 - An angle whose value __ is called complete angle.","A -180", "B - 270" , "C - 90" , "D - 360" , "D - 360","");
        final QuestionsList question2 = new QuestionsList("Q 2 - A line which cuts a pair of parallel lines is called.","A - tangent" , "B - chord" , "C - traversal" , "D - intersector","C - traversal","");
        final QuestionsList question3 = new QuestionsList("Q 3 - 4950/6+112*1.75=?*2","A - 510.5" , "B - 505.5" , "C - 515.5" , "D - none of the above.","A - 510.5","");
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);

        return questionsLists;

    }

    public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch(selectedTopicName){
            case "java":
                return javaQuestions();
        }
        return javaQuestions();
    }
}
