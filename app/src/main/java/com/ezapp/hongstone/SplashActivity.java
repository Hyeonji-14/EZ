package com.ezapp.hongstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.ezapp.hongstone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SplashActivity extends Activity {
    Handler handler;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.background_splash);

        helper = new DBHelper(this, "EZ", null, 1);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                JSONObject json = null;
                try {
                    json = new JSONObject(message.obj.toString());
                    JSONObject json2 = (JSONObject)json.get("response");
                    JSONObject json3 = (JSONObject)json2.get("body");

                    JSONArray jarray = json3.getJSONArray("items");

                    helper.delete();
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jObject = jarray.getJSONObject(i);
                        helper.insert(jObject.optString("tfcwkerMvmnCnterNm"),jObject.optString("rdnmadr"),jObject.optString("lnmadr"),jObject.optString("latitude"), jObject.optString("longitude"),jObject.optString("carHoldCo"),jObject.optString("carHoldKnd"),jObject.optString("slopeVhcleCo"),jObject.optString("liftVhcleCo"),jObject.optString("rceptPhoneNumber"),jObject.optString("rceptItnadr"),jObject.optString("appSvcNm"),jObject.optString("weekdayRceptOpenHhmm"),jObject.optString("weekdayRceptColseHhmm"),jObject.optString("wkendRceptOpenHhmm"),jObject.optString("wkendRceptCloseHhmm"),jObject.optString("weekdayOperOpenHhmm"),jObject.optString("weekdayOperColseHhmm"),jObject.optString("wkendOperOpenHhmm"),jObject.optString("wkendOperCloseHhmm"),jObject.optString("beffatResvePd"),jObject.optString("useLmtt"),jObject.optString("insideOpratArea"),jObject.optString("outsideOpratArea"),jObject.optString("useTrget"),jObject.optString("useCharge"),jObject.optString("institutionNm"),jObject.optString("phoneNumber"),jObject.optString("referenceDate"),jObject.optString("instt_code"),jObject.optString("instt_nm"));
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                catch (JSONException e) {
                    System.out.println("json::"+json);
                };

                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_tfcwker_mvmn_cnter_api"); /*URL*/

                    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "I%2BG%2FJl5mYK3s9h22yUHqvVOnKfKN738GMJUpqKAX2BIlCsxPzpkD9Nvy%2B2lC68Dykiwf3uFxJI6TZku5DkfKSg%3D%3D"); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
                    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("156", "UTF-8")); /*한 페이지 결과 수*/
                    urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/

                    System.out.println(urlBuilder.toString());
                    try {
                        URL url = new URL(urlBuilder.toString());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Content-type", "application/json");

                        BufferedReader reader = null;
                        if (connection.getResponseCode() == 200) {
                            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                            reader = new BufferedReader(inputStreamReader);
                        } else {
                            System.out.println("실패");
                        }
                        StringBuilder builder = new StringBuilder();
                        String temp;
                        while ((temp = reader.readLine()) != null){
                            builder.append(temp);
                        }

                        reader.close();
                        connection.disconnect();
                        String result = builder.toString();

                        Message msg = handler.obtainMessage();
                        msg.obj = result;
                        handler.sendMessage(msg);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}