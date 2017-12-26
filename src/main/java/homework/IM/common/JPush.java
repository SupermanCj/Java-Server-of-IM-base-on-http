package homework.IM.common;



import java.util.List;
import org.apache.log4j.Logger;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class JPush extends Constant{
	private final static String appKey="a9c8cd24648560c42332cb3a";
	private final static String masterSecret="05cdf548f25ba3d78569948d";
	Logger logger = Logger.getLogger(getClass());
	

	
	private static PushPayload payloadForSendToAliasMessageWithoutNotification(String title, List<String> aliases, String message) {
		return PushPayload
				.newBuilder()
				.setNotification(null)
				.setAudience(Audience.alias(aliases))
				.setMessage(Message.newBuilder().setTitle(title).setMsgContent(message).build())
				.setPlatform(Platform.android())
				.build();
	}
	
	private static PushPayload payloadForSendToAliasMessageWithoutNotification(String title, List<String> aliases, String message,int id) {
		return PushPayload
				.newBuilder()
				.setNotification(null)
				.setAudience(Audience.alias(aliases))
				.setMessage(Message.newBuilder()
							.setTitle(title)
							.setMsgContent(message)
							.addExtra("id", id)
							.build())
				.setPlatform(Platform.android())
				.build();
	}
	
	public static boolean sendMessageToAlias(String title, List<String> aliases, String message) {
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);
		try {
			PushResult pushResult = jPushClient.sendPush(
					payloadForSendToAliasMessageWithoutNotification(title, aliases, message));
			return pushResult.isResultOK();
		}catch (Exception e) {
			return false;
		}finally {
			jPushClient.close();
		}
	}
	public static boolean sendMessageToAlias(String title, List<String> aliases, String message,int id) {
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);
		try {
			PushResult pushResult = jPushClient.sendPush(
					payloadForSendToAliasMessageWithoutNotification(title, aliases, message,id));
			return pushResult.isResultOK();
		}catch (Exception e) {
			return false;
		}finally {
			jPushClient.close();
		}
	}


}
