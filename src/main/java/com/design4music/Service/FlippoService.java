package com.design4music.Service;

import com.design4music.DAO.FlippoDao;
import com.design4music.Domain.Flippo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nekkyou on 20-9-2017.
 */
@Service
public class FlippoService {
	private FlippoDao flippoDao;

	@Autowired
	public FlippoService(FlippoDao flippoDao) {
		this.flippoDao = flippoDao;
	}

	public Flippo getFlippo(long id) {
		return flippoDao.findOne(id);
	}

	public List<Flippo> getAllFlippos() {
		return Lists.newArrayList(flippoDao.findAll());
	}

	public void initFlippos() {
		for(int i= 1; i <= 75; i++) {
			Flippo flippo = new Flippo(i);
			flippoDao.save(flippo);
		}
	}


}
