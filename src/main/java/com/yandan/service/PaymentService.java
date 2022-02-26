package com.yandan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yandan.dao.PrescriptionHisDao;
import com.yandan.dao.PrescriptionInfoDao;
import com.yandan.dao.ProjectHisDao;
import com.yandan.dao.ProjectInfoDao;
import com.yandan.dao.RegistrationHisDao;
import com.yandan.dao.RegistrationInfoDao;
import com.yandan.service.interfaces.PaymentServiceImpl;

@Service
public class PaymentService implements PaymentServiceImpl {
	@Autowired
	private RegistrationInfoDao registrationInfoDao;
	@Autowired
	private RegistrationHisDao registrationHisDao;
	@Autowired
	private ProjectInfoDao projectInfoDao;
	@Autowired
	private ProjectHisDao projectHisDao;
	@Autowired
	private PrescriptionInfoDao prescriptionInfoDao;
	@Autowired
	private PrescriptionHisDao prescriptionHisDao;

	@Override
	public int registrationRefund(HashMap<String, Object> paramMap) {
		String registrationId = (String) paramMap.get("registrationId");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", registrationId);
		List<String> ids = new ArrayList<String>();
		ids.add(registrationId);
		int flag = 0;
		flag = registrationInfoDao.deleteRegistrationInfo(ids);
		map.put("final_cost", 0);
		map.put("status", -1);
		flag = registrationHisDao.updateRegistrationHis(map);
		return flag;
	}

	public int projectRefund(HashMap<String, Object> paramMap) {
		String projectId = (String) paramMap.get("projectId");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", projectId);
		List<String> ids = new ArrayList<String>();
		ids.add(projectId);
		int flag = 0;
		flag = projectInfoDao.deleteProjectInfos(ids);
		map.put("final_cost", 0);
		map.put("status", -1);
		flag = projectHisDao.updateProjectHis(map);
		return flag;
	}

	public int prescriptionRefund(HashMap<String, Object> paramMap) {
		String prescriptionId = (String) paramMap.get("prescriptionId");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", prescriptionId);
		List<String> ids = new ArrayList<String>();
		ids.add(prescriptionId);
		int flag = 0;
		flag = prescriptionInfoDao.deletePrescriptionInfo(ids);
		map.put("final_cost", 0);
		map.put("status", -1);
		flag = prescriptionHisDao.updatePrescriptionHis(map);
		return flag;
	}
}