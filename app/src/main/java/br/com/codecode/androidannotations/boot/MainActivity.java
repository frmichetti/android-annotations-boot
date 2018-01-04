package br.com.codecode.androidannotations.boot;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.codecode.androidannotations.boot.rest.service.GithubClient;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @RestService
    protected GithubClient githubClient;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById(R.id.button)
    protected Button button;

    @ViewById(R.id.floating_action_button)
    protected FloatingActionButton floatingActionButton;

    private Context context;

    @AfterInject
    void onInjectDependencies() {
    }

    @AfterViews
    protected void afterViews() {
        context = getBaseContext();
        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Click(R.id.button)
    protected void onFabClickAction() {
        searchAsync();
    }

    @Background
    void searchAsync() {
        ResponseEntity<JSONObject> responseEntity = githubClient.getResult("angular");
        switch (responseEntity.getStatusCode()) {
            case OK:
                Toast.makeText(context, responseEntity.getBody().toString(), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
