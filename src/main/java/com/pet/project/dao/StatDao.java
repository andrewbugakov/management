package com.pet.project.dao;

import java.util.List;

public interface StatDao {
    List getLates(int id);
    List getEarlies(int id);
    List getEfficient(int id);
}
