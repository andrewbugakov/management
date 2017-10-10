package com.pet.project.service;


import java.util.List;

public interface StatService {
    List getLates(int id);
    List getEarlies(int id);
    List getEfficient(int id);
}
