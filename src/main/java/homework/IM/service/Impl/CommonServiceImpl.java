package homework.IM.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homework.IM.mapper.SecurityQuestionMapper;
import homework.IM.model.db.SecurityQuestion;
import homework.IM.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private SecurityQuestionMapper securityQuestionMapper;

	@Override
	public List<SecurityQuestion> getSecurityQuestionList() {
		
		return securityQuestionMapper.getQuestionList();
	}
	
	
}
