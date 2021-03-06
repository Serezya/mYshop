package ru.test.test2_connect_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.MalformedURLException;



public class KolProductChanceSets extends AsyncTask<String, Void, String>
{
    public double chance1, chance2;
    public String type, str;
    public Sets S;
    private ProgressDialog dialog;

    public KolProductChanceSets(String type, Double chance1, Double chance2, Sets s)
    {
        this.chance1 = chance1;
        this.chance2 = chance2;
        this.type = type;
        this.S = s;
    }

    protected void onPreExecute() {
        this.dialog = new ProgressDialog(this.S);
        this.dialog.setMessage("Загрузка");

        if (!this.dialog.isShowing()){
            this.dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... arg0)
    {
        try {
            String link = "https://xoxol1898.000webhostapp.com/index2.php";
            String data = URLEncoder.encode("host", "UTF-8") + "=localhost&" + URLEncoder.encode("user","UTF-8") + "=id12516608_admin&" + URLEncoder.encode("pas","UTF-8") + "=moloko123&" + URLEncoder.encode("database","UTF-8") + "=id12516608_baseshop&" + URLEncoder.encode("type","UTF-8") + "=KolProductChance&" + URLEncoder.encode("chance1","UTF-8") + "=" + chance1 + "&" + URLEncoder.encode("chance2","UTF-8") + "=" + chance2 + "&" + URLEncoder.encode("type_product","UTF-8") + "=" + type;
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            //Read Server
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();
        } catch (Exception e) {
            return ("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.dismiss();
        this.S.out(Html.fromHtml(result).toString());
    }
}
