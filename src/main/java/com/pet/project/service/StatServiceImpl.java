package com.pet.project.service;

import com.pet.project.dao.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("statService")
@Transactional
public class StatServiceImpl implements StatService {
    @Autowired
    StatDao statDao;
    @Override
    public List getLates(int id) {
        return statDao.getLates(id);
    }

    @Override
    public List getEarlies(int id) {
        return statDao.getEarlies(id);
    }

    @Override
    public List getEfficient(int id) {
        return statDao.getEfficient(id);
    }
}
