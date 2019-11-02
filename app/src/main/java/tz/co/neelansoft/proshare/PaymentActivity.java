package tz.co.neelansoft.proshare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends Activity {

    private WebView wvSecure;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);

        wvSecure = findViewById(R.id.wvSecure);
        wvSecure.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        wvSecure.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        JsonObjectRequest joRequest = new JsonObjectRequest(Request.Method.GET, "http://18.218.143.30:8088/payment/token", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getString("redirectUrl").length()>0){
                                wvSecure .loadUrl(response.getString("redirectUrl"));
                                Log.e("PaymentActivity",response.getString("redirectUrl"));
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(PaymentActivity.this,"Error occured",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PaymentActivity",error.toString());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(PaymentActivity.this);
        rq.add(joRequest);
    }

}
