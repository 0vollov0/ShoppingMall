package com.ovollovo.shoppingmall.openapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Repository
public class NaverCaptchaAPI {
	
	public String getCaptchaKey() {
		Gson gson = new Gson();
		StringBuffer response = null;
		String clientId = "rfLUOh1mBtX6hfviB1ds";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
		String clientSecret = "gJwE0KQUyS";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";
		try {
			String code = "0"; // Ű �߱޽� 0, ĸ�� �̹��� �񱳽� 1�� ����
			String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // ���� �߻�
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			//System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		Map<?, ?> map = gson.fromJson(response.toString(), Map.class);
		return (String)map.get("key");
	}
	public String getCaptchaImage(String key) {
		OutputStream outputStream = null;
		String filename = null;
		String clientId = "rfLUOh1mBtX6hfviB1ds";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "gJwE0KQUyS";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        try {
            //String key = getCaptchaKey(); // https://openapi.naver.com/v1/captcha/nkey ȣ��� ���� Ű��
            String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // ������ �̸�����  ���� ����
                filename = Long.valueOf(new Date().getTime()).toString();
                File f = new File("C:\\Users\\OvollovO\\Documents\\GitHub\\ShoppingMall\\ShoppingMall\\src\\main\\webapp\\resources\\images\\"+filename + ".jpg");
                f.createNewFile();
                outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return filename+".jpg";
    }
	
	public JsonObject compareCaptchaKey(String key,String value) {
		StringBuffer response = null;
		String clientId = "rfLUOh1mBtX6hfviB1ds";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "gJwE0KQUyS";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        try {
            String code = "1"; // Ű �߱޽� 0,  ĸ�� �̹��� �񱳽� 1�� ����
            //String value = "YOUR_INPUT"; // ����ڰ� �Է��� ĸ�� �̹��� ���ڰ�
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JsonParser jsonParser = new JsonParser();
        return (JsonObject)jsonParser.parse(response.toString());
    }
}
