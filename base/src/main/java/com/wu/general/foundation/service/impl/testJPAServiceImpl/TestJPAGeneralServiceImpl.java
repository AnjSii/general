package com.wu.general.foundation.service.impl.testJPAServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wu.general.foundation.domain.TestJPAGeneral;
import com.wu.general.foundation.service.testJPAService.TestJPAGeneralService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Wu on 2017/8/3.
 */
@Service
@Transactional
public class TestJPAGeneralServiceImpl implements TestJPAGeneralService {

	/*存放unitName指向的DataBase对应的EntityBean实例集合，以及对这些实例进行生命周期管理*/
	/*注入的是实体管理器，执行持久化操作的，需要配置文件persistence.xml。*/
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TestJPAGeneral findById(Long id) {
		TestJPAGeneral testJPA = entityManager.find(TestJPAGeneral.class, id);
		return testJPA;
	}

	@Override
	public boolean save(TestJPAGeneral testJPA) {
		entityManager.persist(testJPA);
		return true;
	}

	@Override
	public boolean update(TestJPAGeneral testJPA) {
		entityManager.merge(testJPA);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		TestJPAGeneral testJPA = entityManager.find(TestJPAGeneral.class, id);
		entityManager.remove(testJPA);
		return true;
	}
}
