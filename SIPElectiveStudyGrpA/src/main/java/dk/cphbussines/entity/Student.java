/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.entity;

/**
 *
 * @author Søren
 */
public class Student {

    private String name;
    private Vote vote;
    private String topicA;
    private String topicB;
    private CategoryEnum category;

    public Student(String name, Vote vote) {
        this.name = name;
        this.vote = vote;
        this.topicA = null;
        this.topicB = null;
        this.category = CategoryEnum.D;
    }

    public String getName() {
        return name;
    }

    public Vote getVote() {
        return vote;
    }

    public String getTopicA() {
        return topicA;
    }

    public String getTopicB() {
        return topicB;
    }

    public void setTopicA(String topicA) {
        this.topicA = topicA;
    }

    public void setTopicB(String topicB) {
        this.topicB = topicB;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
    
    

}
