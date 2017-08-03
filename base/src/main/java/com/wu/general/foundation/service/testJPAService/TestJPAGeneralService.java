package com.wu.general.foundation.service.testJPAService;

import com.wu.general.foundation.domain.TestJPAGeneral;

/**
 * Created by Wu on 2017/8/3.
 */
public interface TestJPAGeneralService {

	public TestJPAGeneral findById(Long id);

	boolean save(TestJPAGeneral testJPA);

	boolean update(TestJPAGeneral testJPA);

	boolean delete(Long id);
}
