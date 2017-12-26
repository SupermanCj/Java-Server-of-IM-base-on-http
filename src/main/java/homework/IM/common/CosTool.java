package homework.IM.common;




import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

public class CosTool {
	private static long appId = 1253647932;
	private static String secretId = "AKIDDG6kCQoIuJVTElfB3jCRVJr9JFxPOWBa";
	private static String secretKey = "GH2XmlX6RXO1PYT9amkUzGI5ey3ua3II";
	private static String bucketName = "im";
	private static Credentials cred;
	private static ClientConfig clientConfig;
	static {
		cred = new Credentials(appId, secretId, secretKey);
		clientConfig = new ClientConfig();
		clientConfig.setRegion("gz");
	}
	
	public static boolean uploadAvatarPicture(byte[] picture, String fileName) {
		COSClient client = new COSClient(clientConfig, cred);
		UploadFileRequest request = new UploadFileRequest(bucketName,fileName, picture);
		request.setInsertOnly(InsertOnly.OVER_WRITE);
		String uploadFileRet = client.uploadFile(request);
		JSONObject res = JSONObject.parseObject(uploadFileRet);
		client.shutdown();
		if(res.getIntValue("code")==0) {
			
			System.out.println(uploadFileRet);

			return true;
		}else {
			System.out.println(uploadFileRet);
			return false;
		}
	}
	public static boolean deleteFoodPicture(String fileName) {
		COSClient client = new COSClient(clientConfig, cred);
		DelFileRequest request = new DelFileRequest(bucketName, "/avatar/"+fileName);
		String delFileRet = client.delFile(request);
		JSONObject res = JSONObject.parseObject(delFileRet);
		client.shutdown();
		if(res.getIntValue("code")==0) {
			System.out.println(delFileRet);
			return true;
		}else {
			System.out.println(delFileRet);
			return false;
		}
	}
}
