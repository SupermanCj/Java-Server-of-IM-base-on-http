package homework.IM.model.db;

public class SecurityQuestion {
	private int questionId;
	private String questionContent;
	
	public SecurityQuestion() {
		super();
		
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
}
