package com.yandan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yandan.service.PaymentService;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@RequestMapping("/payment/registrationRefund")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> registrationRefund(HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("registrationId", request.getParameter("id"));
		int flag = 0;
		flag = paymentService.registrationRefund(paramMap);
		if (flag > 0) {
			resultMap.put("msg", "退费成功!");
		} else {
			resultMap.put("msg", "退费失败! ");
		}
		return resultMap;
	}
	@RequestMapping("/payment/projectRefund")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> projectRefund(HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectId", request.getParameter("id"));
		int flag = 0;
		flag = paymentService.projectRefund(paramMap);
		if (flag > 0) {
			resultMap.put("msg", "退费成功!");
		} else {
			resultMap.put("msg", "退费失败! ");
		}
		return resultMap;
	}
	@RequestMapping("/payment/prescriptionRefund")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> prescriptionRefund(HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("prescriptionId", request.getParameter("id"));
		int flag = 0;
		flag = paymentService.prescriptionRefund(paramMap);
		if (flag > 0) {
			resultMap.put("msg", "退费成功!");
		} else {
			resultMap.put("msg", "退费失败! ");
		}
		return resultMap;
	}
}
