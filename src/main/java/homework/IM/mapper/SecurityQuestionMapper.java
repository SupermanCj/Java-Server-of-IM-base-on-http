package homework.IM.mapper;

import java.util.List;

import homework.IM.model.db.SecurityQuestion;

public interface SecurityQuestionMapper {
	public List<SecurityQuestion> getQuestionList();

	public SecurityQuestion getQuestionById(int securityQuestionId);
}
